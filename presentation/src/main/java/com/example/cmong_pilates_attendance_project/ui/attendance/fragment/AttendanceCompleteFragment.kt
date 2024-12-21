package com.example.cmong_pilates_attendance_project.ui.attendance.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.base.BaseFragment
import com.example.cmong_pilates_attendance_project.ui.attendance.screen.AttendanceCompleteScreen
import com.example.cmong_pilates_attendance_project.viewmodel.AttendanceViewModel


class AttendanceCompleteFragment :
    BaseFragment() {
    private val viewModel: AttendanceViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                AttendanceCompleteScreen(viewModel = viewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed( Runnable {
            findNavController().popBackStack()
        },3000)
    }
}