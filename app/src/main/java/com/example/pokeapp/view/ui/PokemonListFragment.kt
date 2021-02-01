package com.example.pokeapp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapp.databinding.PokemonListFragmentBinding
import com.example.pokeapp.utils.Constant.LIMIT
import com.example.pokeapp.view.adapter.PaginationScrollListener
import com.example.pokeapp.view.adapter.PokemonListAdapter
import com.example.pokeapp.viewmodel.PokemonListViewModel
import kotlinx.android.synthetic.main.pokemon_list_fragment.*

class PokemonListFragment : Fragment() {


    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    private lateinit var adapter: PokemonListAdapter
    private var binding: PokemonListFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = PokemonListFragmentBinding.inflate(inflater, container, false).apply {
                pokemonListFrag = ViewModelProvider(this@PokemonListFragment)
                    .get(PokemonListViewModel::class.java)
                lifecycleOwner = viewLifecycleOwner
            }
        }
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (binding?.pokemonListFrag?.getPokemonLisAllSize()!! == 0) {
            binding?.pokemonListFrag?.getPokemonList()
            setupAdapter()
        }
        setupObservers()
    }

    private fun setupObservers() {
        binding?.pokemonListFrag?.pokemonDetailLive?.observe(viewLifecycleOwner, Observer {
            isLoading = false
            progressbar.visibility = View.GONE
            adapter.updateList(it)

        })
    }

    private fun setupAdapter() {
        val viewModel = binding?.pokemonListFrag
        if (viewModel != null) {
            adapter = PokemonListAdapter(binding?.pokemonListFrag!!)
            val layoutManager = LinearLayoutManager(activity)
            pokemon_list_rc?.addOnScrollListener(
                object : PaginationScrollListener(layoutManager) {
                    override fun isLastPage(): Boolean {
                        return isLastPage
                    }

                    override fun isLoading(): Boolean {
                        return isLoading
                    }

                    override fun loadMoreItems() {
                        isLoading = true
                        //you have to call load more items to get more data
                        getMoreItems()
                    }
                })
            pokemon_list_rc.layoutManager = layoutManager
            pokemon_list_rc.adapter = adapter
        }
    }


    fun getMoreItems() {
        binding?.pokemonListFrag?.loadMore = true
        progressbar.visibility = View.VISIBLE
        binding?.pokemonListFrag?.addOffset(LIMIT)
        binding?.pokemonListFrag?.getPokemonList()
    }
}