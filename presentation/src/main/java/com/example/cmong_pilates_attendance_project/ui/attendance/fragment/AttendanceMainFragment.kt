package com.example.cmong_pilates_attendance_project.ui.attendance.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseFragment
import com.example.cmong_pilates_attendance_project.ui.attendance.screen.AttendanceMainScreen
import com.example.cmong_pilates_attendance_project.viewmodel.AdminViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.AttendanceViewModel


//class AttendanceMainFragment :
//    BaseFragment() {
//    val viewModel: AttendanceViewModel by activityViewModels()
//    val adminViewModel: AdminViewModel by viewModels()
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        return ComposeView(requireContext()).apply {
//            setContent {
//                AttendanceMainScreen()
//            }
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setDataObserver()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        adminViewModel.getAdminData()
//    }
//
//
//    private fun setDataObserver() {
//        adminViewModel.adminData.observe(this) {
//            if (it == null) return@observe
//            viewModel.setMaxAttendanceCount(it.maxAttendance)
//        }
//
//        viewModel.isAlreadyAttendance.observe(this) {
//            if (it == true) {
//                showToast(getString(R.string.text_noti_already_attendance_user))
//            }
//        }
//
//        viewModel.isNoExistUser.observe(this) {
//            if (it == true) {
//                showToast(getString(R.string.text_noti_retry_phone_number))
//            }
//        }
//
//
//        //출석 완료
//        viewModel.isSuccessAttendance.observe(this) {
//            if (it == true)
//                findNavController().navigate(R.id.action_attendanceMainFragment_to_attendanceCompleteFragment)
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        viewModel.onClearData()
//    }
//
//}