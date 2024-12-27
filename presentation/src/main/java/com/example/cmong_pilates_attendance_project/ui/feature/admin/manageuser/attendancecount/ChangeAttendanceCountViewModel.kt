package com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.attendancecount

import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.cmong_pilates_attendance_project.utils.ResourceProvider
import com.example.data.model.AdminEntity
import com.example.data.repository.AdminRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ChangeAttendanceCountViewModel
@Inject constructor(
    private val repository: AdminRepository,
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
            repository.getAdminData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    setState {
                        this.copy(attendanceCount = it.maxAttendance)
                    }
                    LogUtil.d("Success Search Admin, $it")
                }, {
                    addAdminData(
                        AdminEntity(
                            maxAttendance = 1
                        )
                    )
                    LogUtil.d("Error Search Admin, ${it.message}")
                })
        )
    }

    private fun addAdminData(adminEntity: AdminEntity) {
        compositeDisposable.add(
            repository.addAdminData(adminEntity).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    LogUtil.d("Success Add Admin")
                }, {
                    LogUtil.d("Error Add Admin, ${it.message}")
                })
        )
    }

    private fun changeAttendanceCount(count: Int) {
        compositeDisposable.add(
            repository.updateAttendanceCount(count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    LogUtil.d("Success Change Attendance Count!")
                    val msg = provider.getString(R.string.text_complete_change_attendance_count)
                    setEffect { ChangeAttendanceCountContract.Effect.ShowToast(msg) }
                    setEffect { ChangeAttendanceCountContract.Effect.CompleteChangeMileage }
                }, {
                    LogUtil.d("Error Change Attendance Count, ${it.message}")
                })
        )
    }
}