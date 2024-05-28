package com.udppcmyplantsitter.viewModel.appNavegation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udppcmyplantsitter.model.UserRepository
import androidx.lifecycle.viewmodel.compose.viewModel
import com.udppcmyplantsitter.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class userViewModel(private val repository: UserRepository): ViewModel(){


    private val _nameUser = MutableStateFlow("")
    val nameUser: StateFlow<String> get() = _nameUser

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email

    fun onNameUserChange(newName: String) {
        _nameUser.value = newName
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun registerUser(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            // Aquí deberías agregar la lógica de registro con Firebase
            // Ejemplo:
            if (_nameUser.value.isNotEmpty() && _password.value.isNotEmpty() && _email.value.isNotEmpty()) {
                // Simula registro exitoso
                onSuccess()
            } else {
                onError("All fields are required")
            }
        }
    }
}