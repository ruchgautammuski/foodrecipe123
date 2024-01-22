package com.example.foodrecipesapp.presentation.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipesapp.R
import com.example.foodrecipesapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val homeScreenViewModel by viewModels<HomeViewModel> { HomeViewModel.Factory }

    private lateinit var homeAdapter: HomeAdapter
    private lateinit var dataList: ArrayList<RecipeModel>
    private lateinit var filteredList: ArrayList<RecipeModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataList = ArrayList()
        filteredList = dataList
        homeAdapter = HomeAdapter(filteredList)
        binding.recipesRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = homeAdapter
            homeAdapter.notifyDataSetChanged()
        }

        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Not required
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Not required
            }

            override fun afterTextChanged(et: Editable?) {
                val input = et.toString()
                if (input.isNotEmpty()) {
                    filteredList.clear()
                    for (data: RecipeModel in dataList) {
                        if (data.name.contains(input, ignoreCase = true)) {
                            filteredList.add(data)
                        }
                    }
                    homeAdapter.notifyDataSetChanged()
                } else {
                    filteredList.clear()
                    filteredList = dataList
                    homeAdapter.notifyDataSetChanged()
                }
            }

        })

        binding.shopBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_shopFragment)
        }

        binding.veg.setOnClickListener {
            filteredList.clear()
            for (data: RecipeModel in dataList) {
                if (data.isVeg) {
                    filteredList.add(data)
                }
            }
            homeAdapter.notifyDataSetChanged()
            binding.clearFilter.visibility = View.VISIBLE
        }

        binding.nonVegBtn.setOnClickListener {
            filteredList.clear()
            for (data: RecipeModel in dataList) {
                if (!data.isVeg) {
                    filteredList.add(data)
                }
            }
            homeAdapter.notifyDataSetChanged()
            binding.clearFilter.visibility = View.VISIBLE
        }

        binding.clearFilter.setOnClickListener {
            filteredList.clear()
            filteredList = dataList
            homeAdapter.notifyDataSetChanged()
            binding.clearFilter.visibility = View.GONE
        }

        observe()

    }

    private fun observe() {
        lifecycleScope.launch {
            homeScreenViewModel.dataList.collect { list ->
                dataList.clear()
                filteredList.clear()
                list?.forEach {
                    val model = RecipeModel(
                        it.id,
                        it.title,
                        it.image,
                        it.summary,
                        false,
                        it.vegetarian
                    )
                    dataList.add(model)
                    filteredList.add(model)
                }
                homeAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}