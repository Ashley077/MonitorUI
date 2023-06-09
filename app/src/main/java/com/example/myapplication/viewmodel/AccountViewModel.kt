package com.example.myapplication.viewmodel

import androidx.lifecycle.*
import com.example.myapplication.model.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val accountService: AccountService) : ViewModel() {
    private val inputUserName = MutableLiveData("")
    val inputUserNameData: LiveData<String>
        get() = inputUserName

    private val inputPassword = MutableLiveData("")
    val inputPasswordData: LiveData<String>
        get() = inputPassword

    private val canLoginButtonEnable = MediatorLiveData<Boolean>()
    val canLoginButtonEnableData: LiveData<Boolean>
        get() = canLoginButtonEnable

    private val lastTimeLoginIsFail = MediatorLiveData<Boolean>()
    val lastTimeLoginIsFailData: LiveData<Boolean>
        get() = lastTimeLoginIsFail

    init {
        canLoginButtonEnable.value = false
        lastTimeLoginIsFail.value = false
        canLoginButtonEnable.addSource(inputUserName) {
            canLoginButtonEnable.value = checkUserName() && checkPassword()
        }

        canLoginButtonEnable.addSource(inputPassword) {
            canLoginButtonEnable.value = checkUserName() && checkPassword()
        }

        lastTimeLoginIsFail.addSource(inputUserName) {
            lastTimeLoginIsFail.value = false
        }

        lastTimeLoginIsFail.addSource(inputPassword) {
            lastTimeLoginIsFail.value = false
        }


    }

    fun setInputUserName(newUserName: String) {
        inputUserName.value = newUserName
    }

    fun setInputPassword(newPassword: String) {
        inputPassword.value = newPassword
    }

    private fun checkUserName(): Boolean {
        return !inputUserName.value.isNullOrEmpty()
    }

    private fun checkPassword(): Boolean {
        return !inputPassword.value.isNullOrEmpty()
    }

    suspend fun login(): Boolean {
        val result = viewModelScope.async(Dispatchers.IO) {
            if (inputUserName.value.isNullOrEmpty() || inputPassword.value.isNullOrEmpty()) {
                return@async false
            }
            return@async accountService.login(inputUserName.value!!, inputPassword.value!!)
        }.await()

        inputPassword.value = ""
        if (!result) {
            lastTimeLoginIsFail.value = true
        }

        return result
    }
}
