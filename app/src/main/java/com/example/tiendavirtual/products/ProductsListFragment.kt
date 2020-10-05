package com.example.tiendavirtual.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tiendavirtual.R
import kotlinx.android.synthetic.main.fragment_productslist.*

abstract class ProductsListFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_productslist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter= ProductsAdapter()

//        val verticalDecorator = DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
//        val horizontalDecorator = DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL)
//        val drawableHorizontal = ContextCompat.getDrawable(requireContext(),R.drawable.divider_horizontal)
//        val drawableVertical = ContextCompat.getDrawable(requireContext(),R.drawable.divider_vertical)
//
//        verticalDecorator.setDrawable(drawableVertical!!)
//        horizontalDecorator.setDrawable(drawableHorizontal!!)
//
//        recyclerView.addItemDecoration(verticalDecorator)
//        recyclerView.addItemDecoration(horizontalDecorator)
//
//        recyclerView.apply {
//            addItemDecoration(verticalDecorator)
//            addItemDecoration(horizontalDecorator)
//            setHasFixedSize(true)
//        }
        adapter.setProductClickListener {

        }

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

    }

    override fun onResume() {
        super.onResume()
        getProducts()
    }
    abstract fun getProducts(forceUpdate: Boolean = false)

}