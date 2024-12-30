package com.example.presentation.ui.feature.admin.manageuser.attendancecount

import com.example.presentation.utils.LogUtil
import com.example.presentation.utils.ResourceProvider
import com.example.domain.usecase.GetAdminUseCase
import com.example.domain.usecase.UpdateAttendanceCountUseCase
import com.example.presentation.R
import com.example.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ChangeAttendanceCountViewModel
@Inject constructor(
    private val getAdminUseCase: GetAdminUseCase,
    private val updateAttendanceCountUseCase: UpdateAttendanceCountUseCase,
    private val provider: ResourceProvider
) :
    BaseViewModel<ChangeAttendanceCountContract.Event, ChangeAttendanceCountContract.State, ChangeAttendanceCountContract.Effect>() {

    init {
        getAdminData()
    }

    override fun createInitialState(): ChangeAttendanceCountContract.State {
        return ChangeAttendanceCountContract.State()
    }

    override fun handleEvent(event: ChangeAttendanceCountContract.Event) {
        when (event) {
            is ChangeAttendanceCountContract.Event.OnClickSaveButton -> {
                changeAttendanceCount(event.count)
            }

            ChangeAttendanceCountContract.Event.OnClickCountDown -> {
                if (uiState.value.attendanceCount > 1) {
                    setState {
                        this.copy(attendanceCount = attendanceCount - 1)
                    }
                }
            }

            ChangeAttendanceCountContract.Event.OnClickCountUp -> {
                setState {
                    this.copy(attendanceCount = attendanceCount + 1)
                }
            }
        }
    }

    private fun getAdminData() {
        compositeDisposable.add(
            getAdminUseCase().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ admin ->
                    setState {
                        this.copy(attendanceCount = admin.maxAttendance)
                    }
                    LogUtil.d("Success Get Admin, $admin")
                }, { throwable ->
                    LogUtil.d("Error Get Admin, ${throwable.message}")
                })
        )
    }

    private fun changeAttendanceCount(count: Int) {
        compositeDisposable.add(
            updateAttendanceCountUseCase(count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    LogUtil.d("Success Change Attendance Count!")
                    val msg = provider.getString(R.string.text_complete_change_attendance_count)
                    setEffect { ChangeAttendanceCountContract.Effect.ShowToast(msg) }
                    setEffect { ChangeAttendanceCountContract.Effect.CompleteChangeMileage }
                }, { throwable ->
                    LogUtil.d("Error Change Attendance Count, ${throwable.message}")
                })
        )
    }
}