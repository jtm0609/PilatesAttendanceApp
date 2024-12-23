package com.example.cmong_pilates_attendance_project.ui.admin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseFragment
import com.example.cmong_pilates_attendance_project.ui.admin.screen.ChangeAttendanceCountScreen
import com.example.cmong_pilates_attendance_project.viewmodel.ChangeAttendanceCountViewModel
import com.example.data.model.AdminEntity


//class ChangeAttendanceCountFragment : BaseFragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return ComposeView(mContext).apply {
//            setContent {
//                ChangeAttendanceCountScreen()
//            }
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setDataObserver()
//        viewModel.getAdminData()
//    }
//    private fun setDataObserver() {
//        viewModel.isEmptyAdminData.observe(viewLifecycleOwner){
//            if(it==true){ //관리자 데이터가 없다면 추가를 해준다.
//                viewModel.addAdminData(AdminEntity(
//                    maxAttendance = 1
//                ))
//            }
//        }
//        viewModel.adminData.observe(viewLifecycleOwner){
//            if(it==null) return@observe
//            viewModel.setAttendanceCount(it.maxAttendance)
//        }
//
//        viewModel.isChangeCount.observe(viewLifecycleOwner){
//            if(it==true){
//                showToast(getString(R.string.text_complete_change_attendance_count))
//                findNavController().popBackStack()
//            }
//        }
//    }
//}