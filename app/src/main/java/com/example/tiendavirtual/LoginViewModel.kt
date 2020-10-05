package com.example.tiendavirtual

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tiendavirtual.cart.domain.usecase.GetNumberCartProductsUseCase
import com.example.tiendavirtual.login.domain.model.User

class LoginViewModel(
    private val getNumberCartProductsUseCase: GetNumberCartProductsUseCase
) : ViewModel() {

    //TODO add getLoginState or UserLogin
    //TODO get number of products within cart
    val _products: LiveData<Int> = getNumberCartProductsUseCase()

    private val _user: MutableLiveData<User> =  MutableLiveData(null)
    val user: LiveData<User>
    get() = _user

    fun logUser(userName: String, mobileNumber: Int){
        _user?.value = User("adbdb", userName, mobileNumber)
    }


}

