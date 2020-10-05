package com.example.tiendavirtual.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiendavirtual.R
import kotlinx.android.synthetic.main.fragment_cart.*
import org.koin.android.viewmodel.ext.android.viewModel

class CartFragment: Fragment() {

    private val cartViewModel by viewModel<CartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CartAdapter()
        adapter.setProductClickListener {
            val action = CartFragmentDirections.toProductDetailFragment(it)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)



        cartViewModel.products.observe(viewLifecycleOwner, Observer { products->
            adapter.setProducts(products)
        }
        )

        cartViewModel.mostrarProgreso.observe(viewLifecycleOwner, Observer {
            swipeRefresh.isRefreshing = it
        })

        swipeRefresh.setOnRefreshListener {
            cartViewModel.getCartProducts()
        }
    }

    override fun onResume() {
        super.onResume()
        cartViewModel.getCartProducts()
    }
}