package com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.mileage

import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.cmong_pilates_attendance_project.utils.ResourceProvider
import com.example.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ChangeUserMileageViewModel @Inject constructor(
    private val repository: UserRepository,
    private val provider: ResourceProvider
) : BaseViewModel<ChangeUserMileageContract.Event, ChangeUserMileageContract.State, ChangeUserMileageContract.Effect>() {

    private fun changeUserMileage(phone: String, mileage: Int) {
        compositeDisposable.add(
            repository.updateUserMileage(phone, mileage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        val msg = provider.getString(R.string.text_complete_change_mileage)
                        setEffect { ChangeUserMileageContract.Effect.ShowToast(msg) }
                        setEffect { ChangeUserMileageContract.Effect.CompleteChangeMileage(mileage) }
                    }, {
                        LogUtil.d("error ${it.message}}")
                    }
                )
        )
    }

    override fun createInitialState(): ChangeUserMileageContract.State {
        return ChangeUserMileageContract.State()
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
                if (uiState.value.mileage > 0) {
                    setState {
                        this.copy(mileage = mileage - 1)
                    }
                }
            }

            is ChangeUserMileageContract.Event.OnClickMileageUp -> {
                setState {
                    this.copy(mileage = mileage + 1)
                }
            }
        }
    }
}