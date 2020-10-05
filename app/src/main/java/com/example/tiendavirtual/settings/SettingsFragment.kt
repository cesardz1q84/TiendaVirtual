package com.example.tiendavirtual.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.tiendavirtual.LoginViewModel
import com.example.tiendavirtual.R
import com.example.tiendavirtual.login.domain.model.User
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SettingsFragment: Fragment() {

    private val loginViewModel by sharedViewModel<LoginViewModel> ()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()



//        if(loginViewModel.user == null){
//            navController.navigate(R.id.toLoginFragment)
//        }

        loginViewModel.user.observe(viewLifecycleOwner,{user->
            if (user != null){
                edtUserName.setText(user.userName)
                edtMobilePhone.setText(user.mobilePhone.toString())
            }else{
                navController.navigate(R.id.toLoginFragment)
            }
        }
        )
    }



}