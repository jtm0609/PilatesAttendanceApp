package com.example.presentation.ui.feature.admin.manageuser.mileage

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.example.domain.model.User
import com.example.presentation.utils.LogUtil
import com.example.presentation.utils.ResourceProvider
import com.example.domain.usecase.UpdateMileageUseCase
import com.example.presentation.R
import com.example.presentation.base.BaseViewModel
import com.example.presentation.navigation.AppDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ChangeUserMileageViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val updateMileageUseCase: UpdateMileageUseCase,
    private val provider: ResourceProvider
) : BaseViewModel<ChangeUserMileageContract.Event, ChangeUserMileageContract.State, ChangeUserMileageContract.Effect>() {

    private val _user: User =
        savedStateHandle.toRoute<AppDestination.ChangeUserMileage>(typeMap = AppDestination.ChangeUserMileage.typeMap).user

    override fun createInitialState(): ChangeUserMileageContract.State {
        return ChangeUserMileageContract.State()
    }

    init {
        setState {
            this.copy(user = _user)
        }
    }

    private fun changeUserMileage(phone: String, mileage: Int) {
        compositeDisposable.add(
            updateMileageUseCase(phone, mileage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        val msg = provider.getString(R.string.text_complete_change_mileage)
                        setEffect { ChangeUserMileageContract.Effect.ShowToast(msg) }
                        setEffect { ChangeUserMileageContract.Effect.CompleteChangeMileage(mileage) }
                    }, { throwable ->
                        LogUtil.d("error ${throwable.message}}")
                    }
                )
        )
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