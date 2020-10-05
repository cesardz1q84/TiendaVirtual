package com.example.tiendavirtual.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.tiendavirtual.LoginViewModel
import com.example.tiendavirtual.R
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LoginFragment: Fragment() {

    private val loginViewModel by sharedViewModel<LoginViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        tvRegistrar.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.toRegisterFragment, null))

//        btnIngresar.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.toHomeFragment, null))

        btnIngresar.setOnClickListener {
            if(edtEmail.text.toString() == "cesardz1q84@gmail.com"){
                loginViewModel.logUser("cesar", 923988088)
                navController.navigate(R.id.toSettingsFragment)
            }
        }
    }

}