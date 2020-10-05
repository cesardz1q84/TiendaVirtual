package com.example.tiendavirtual.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.tiendavirtual.R
import com.example.tiendavirtual.databinding.FragmentProductDetailBinding
import com.example.tiendavirtual.products.domain.model.Product
import kotlinx.android.synthetic.main.fragment_product_detail.*
import org.koin.android.viewmodel.ext.android.viewModel


class ProductDetailFragment: Fragment() {

    private val args: ProductDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var product: Product
    private lateinit var productId: String
    private val productDetailViewModel by viewModel<ProductDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        productId = args.ProductData.id

        product = args.ProductData

        product?.let {
            binding.product = it
            binding.executePendingBindings()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productId?.let {
            productDetailViewModel.getProductById(productId)
        }

        productDetailViewModel.product.observe(viewLifecycleOwner, Observer {
            product = it
            setupButtonFavorite(product.favorite)
            binding.product = it
        }
        )

        btnFavorite.setOnClickListener {
            product?.let {
                it.favorite = !it.favorite
                setupButtonFavorite(it.favorite)
                productDetailViewModel.updateFavorite(it.id, it.favorite)
//                btnFavorite.animate().apply {
//                    duration = 1000
//                }.withEndAction {
//
//                }.start()

            }
        }
    }

    fun setupButtonFavorite(esFavorito: Boolean){
        val buttonRes = if (esFavorito) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        btnFavorite.setImageResource(buttonRes)
    }
}