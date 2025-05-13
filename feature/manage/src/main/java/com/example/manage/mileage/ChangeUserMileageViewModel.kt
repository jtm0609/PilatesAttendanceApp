package com.example.manage.mileage

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.example.domain.model.User
import com.example.domain.usecase.UpdateMileageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.navigation.Route
import com.example.common.ResourceProvider
import com.example.domain.dataresource.DataResource
import com.example.feature.manage.R
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeUserMileageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val updateMileageUseCase: UpdateMileageUseCase,
    private val provider: ResourceProvider
) : BaseViewModel<ChangeUserMileageContract.Event, ChangeUserMileageContract.State, ChangeUserMileageContract.Effect>() {

    private val _user: User =
        savedStateHandle.toRoute<Route.ChangeUserMileage>(typeMap = Route.ChangeUserMileage.typeMap).user

    override fun createInitialState(): ChangeUserMileageContract.State {
        return ChangeUserMileageContract.State()
    }

    init {
        setState {
            this.copy(user = _user)
        }
    }

    private fun changeUserMileage(phone: String, mileage: Int) {
        viewModelScope.launch {
            updateMileageUseCase(phone, mileage).collect {
                when (it) {
                    is DataResource.Loading -> {
                        setState { this.copy(isLoading = true) }
                    }

                    is DataResource.Success -> {
                        val msg = provider.getString(R.string.text_complete_change_mileage)
                        setEffect { ChangeUserMileageContract.Effect.ShowToast(msg) }
                        setEffect { ChangeUserMileageContract.Effect.CompleteChangeMileage(mileage) }
                        setState { this.copy(isLoading = false) }
                    }

                    is DataResource.Error -> {
                        val msg = provider.getString(R.string.text_fail_change_mileage)
                        setEffect { ChangeUserMileageContract.Effect.ShowToast(msg) }
                        setState { this.copy(isLoading = false) }
                    }
                }
            }
        }
    }

    override fun handleEvent(event: ChangeUserMileageContract.Event) {
        when (event) {
            is ChangeUserMileageContract.Event.OnClickSaveButton -> {
                changeUserMileage(
                    event.phoneNumber,
                    event.mileage
                )
            }

            is ChangeUserMileageContract.Event.OnClickMileageDown -> {
                val user = uiState.value.user
                val currentMileage = user?.mileage ?: 0
                if (currentMileage > 0) {
                    setState {
                        this.copy(
                            user = user?.copy(
                                mileage = currentMileage - 1
                            )
                        )
                    }
                }
            }

            is ChangeUserMileageContract.Event.OnClickMileageUp -> {
                setState {
                    this.copy(
                        user = user?.copy(
                            mileage = user.mileage + 1
                        )
                    )
                }
            }
        }
    }
}