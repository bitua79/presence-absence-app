package com.application.presence_absence.ui.features.login

import androidx.lifecycle.viewModelScope
import com.application.presence_absence.domain.params.PostLogin
import com.application.presence_absence.domain.usecases.DoLogin
import com.application.presence_absence.ui.widgets.UiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val doLogin: DoLogin
) : UiStateViewModel() {

    fun invokeSignInRequest(param: PostLogin) {
        viewModelScope.launch {
            useCaseInvoker { doLogin(param) }
        }
    }
}