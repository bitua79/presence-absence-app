package com.application.presence_absence.ui.core

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.application.presence_absence.core.entities.ErrorResult
import com.application.presence_absence.core.entities.Result
import com.application.presence_absence.core.entities.Success
import com.application.presence_absence.core.extensions.runMain
import com.application.presence_absence.ui.utils.getError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class UiStateViewModel : ViewModel() {

    private val _uiViewState = MutableStateFlow<UiStatus>(UiIdle)
    val uiViewState = _uiViewState.asStateFlow()

    suspend fun <T> useCaseInvoker(
        useCase: suspend () -> Result<T>,
    ) {
        // Change UiState to Loading when useCase is called
        setUiViewState(UiLoading)

        // Wait until result reaches
        useCase().apply apply@{
            runMain {
                // When the result reaches, until the error/success of it is determined, the status is changed to Idle to remove the loading
                setUiViewState(UiIdle)

                // Set UiState according to result of useCase
                getResult()
            }
        }
    }

    suspend fun <T> useCaseInvoker(
        useCase: suspend () -> Result<T>,
        dataStateReady: (dataState: T) -> Unit
    ) {
        // Change UiState to Loading when useCase is called
        setUiViewState(UiLoading)

        useCase().apply apply@{
            runMain {
                // When the result reaches, until the error/success of it is determined, the status is changed to Idle to remove the loading
                setUiViewState(UiIdle)

                // Set UiState and dataState according to result of useCase
                getResultAndState { dataStateReady(it) }
            }
        }
    }

    private suspend fun <T> Result<T>.getResult() {
        runMain {
            when (this@getResult) {
                is Success -> setUiViewState(
                    UiSuccess
                )

                is ErrorResult -> setUiViewState(
                    exception.getError()
                )
            }
        }
    }

    private suspend fun <T> Result<T>.getResultAndState(
        dataStateReady: (dataState: T) -> Unit
    ) {
        runMain {
            when (this@getResultAndState) {
                is Success -> {
                    setUiViewState(
                        UiSuccess
                    )
                    dataStateReady(
                        this@getResultAndState.data
                    )
                }

                is ErrorResult -> setUiViewState(
                    exception.getError()
                )
            }
        }
    }


    // When result state is resolved and handled and we are in a neutral state (IdleUiState)
    fun clearState() {
        setUiViewState(UiIdle)
    }

    @CallSuper
    open fun setUiViewState(value: UiStatus) {
        _uiViewState.update {
            value
        }
    }
}