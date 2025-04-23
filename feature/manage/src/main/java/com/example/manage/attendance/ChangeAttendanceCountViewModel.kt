package com.example.manage.attendance

import com.example.domain.usecase.GetAdminUseCase
import com.example.domain.usecase.UpdateAttendanceCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_android.base.BaseViewModel
import com.example.core_android.utils.LogUtil
import com.example.core_android.utils.ResourceProvider
import com.example.domain.dataresource.DataResource
import com.example.feature.manage.R
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            getAdminUseCase().collect {
                when (it) {
                    is DataResource.Loading -> {
                        setState { this.copy(isLoading = true) }
                    }

                    is DataResource.Success -> {
                        setState {
                            this.copy(
                                attendanceCount = it.data.maxAttendance,
                                isLoading = false
                            )
                        }
                    }

                    is DataResource.Error -> {
                        setState { this.copy(isLoading = false) }
                        LogUtil.d("Error Get Admin, ${it.throwable}")
                    }
                }
            }
        }
    }

    private fun changeAttendanceCount(count: Int) {
        viewModelScope.launch {
            updateAttendanceCountUseCase(count).collect {
                when (it) {
                    is DataResource.Loading -> {
                        setState { this.copy(isLoading = true) }
                    }

                    is DataResource.Success -> {
                        updateAttendanceCountUseCase(count)
                        LogUtil.d("Success Change Attendance Count!")
                        val msg = provider.getString(R.string.text_complete_change_attendance_count)
                        setEffect { ChangeAttendanceCountContract.Effect.ShowToast(msg) }
                        setEffect { ChangeAttendanceCountContract.Effect.CompleteChangeMileage }
                    }

                    is DataResource.Error -> {
                        LogUtil.d("Error Change Attendance Count, ${it.throwable}")
                    }
                }
            }
        }
    }
}