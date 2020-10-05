package com.example.tiendavirtual.products.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.tiendavirtual.R
import com.example.tiendavirtual.products.ProductsAdapter
import com.example.tiendavirtual.products.ProductsListFragment
import com.example.tiendavirtual.products.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_productslist.recyclerView
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : ProductsListFragment() {

    private val productsViewModel by viewModel<ProductsViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductsAdapter()

        adapter.setProductClickListener {
            val action = HomeFragmentDirections.toProductDetailFragment(it)
            findNavController().navigate(action)
        }

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        productsViewModel.products.observe(viewLifecycleOwner, Observer { products->
                adapter.setProducts(products)
            }
        )

        productsViewModel.mostrarProgreso.observe(viewLifecycleOwner, Observer {
            swipeRefresh.isRefreshing = it
        })

        swipeRefresh.setOnRefreshListener {
            productsViewModel.getHomeProducts(true)
        }
    }

    override fun getProducts(forceUpdate: Boolean){
        productsViewModel.getHomeProducts(forceUpdate)
    }
}