package com.example.cmong_pilates_attendance_project.ui.admin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseFragment
import com.example.cmong_pilates_attendance_project.ui.admin.screen.ReregisterUserScreen
import com.example.cmong_pilates_attendance_project.utils.Utils
import com.example.cmong_pilates_attendance_project.viewmodel.UserViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.ReregisterUserViewModel

//class ReregisterUserFragment : BaseFragment() {
//    private val viewModel: ReregisterUserViewModel by viewModels<ReregisterUserViewModel>()
//    private val userViewModel: UserViewModel by activityViewModels<UserViewModel>()
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        init()
//        // Inflate the layout for this fragment
//        return ComposeView(mContext).apply {
//            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
//            setContent {
//                ReregisterUserScreen()
//            }
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setDataObserver()
//    }
//
//    private fun init(){
//        //이용 기간 UI 데이터 업데이트
//        val duration = userViewModel.searchedUser.value?.duration
//        viewModel.setDurationState(duration!!)
//        viewModel.setDurationText(duration)
//
//        //이용 시작일 UI 데이터 업데이트
//        val userStartDateTime = userViewModel.searchedUser.value?.startDateTime
//        val userStartDateTimeStr = Utils.convertTimeStampToDateString(userStartDateTime!!)
//        updateUserStartDate(userStartDateTimeStr)
//        viewModel.setStartDateText(userStartDateTimeStr)
//    }
//
//    private fun setDataObserver(){
//        viewModel.isSuccessUpdateUser.observe(viewLifecycleOwner){
//            if(it==true){
//                showToast(getString(R.string.text_success_re_register_user))
//                findNavController().popBackStack()
//            }else{
//                showToast(getString(R.string.text_fail_re_register_user))
//            }
//        }
//    }
//
//
//    //검색된 유저의 startDate를 불러와 현재 UI 데이터에 업데이트한다.
//    private fun updateUserStartDate(dateStr: String){
//        val year = dateStr.split("-")[0].toInt()
//        val month = dateStr.split("-")[1].toInt()-1
//        val date= dateStr.split("-")[2].toInt()
//        viewModel.setStartDate(year, month, date)
//    }
//
//    //뒤로가기
//    val callback = object : OnBackPressedCallback(true) {
//        override fun handleOnBackPressed() {
//            if (viewModel.durationVisibility) {
//                viewModel.setVisibilityDuration(false)
//            } else {
//                findNavController().popBackStack()
//            }
//        }
//    }
//}