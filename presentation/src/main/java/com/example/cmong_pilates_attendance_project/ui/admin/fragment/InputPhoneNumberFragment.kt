package com.example.cmong_pilates_attendance_project.ui.admin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseFragment
import com.example.cmong_pilates_attendance_project.ui.admin.screen.InputPhoneNumberScreen
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.cmong_pilates_attendance_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


//@AndroidEntryPoint
//class InputPhoneNumberFragment : BaseFragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return ComposeView(mContext).apply {
//            setContent { InputPhoneNumberScreen() }
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setDataObserver()
//    }
//
//    private fun setDataObserver(){
//        userViewModel.searchedUser.observe(viewLifecycleOwner){
//            if(it==null) return@observe
//            if(userViewModel.isExistUser.value==true){
//                findNavController().navigate(R.id.action_inputPhoneNumberFragment_to_manageUserFragment)
//            }
//        }
//
//        userViewModel.isExistUser.observe(viewLifecycleOwner){
//            if(it==null) return@observe
//            if(it==false){
//                if(userViewModel.userPhoneNumber.isBlank()){
//                    showToast(getString(R.string.text_noti_input_phone_number))
//                }else {
//                    showToast(getString(R.string.text_not_exist_user))
//                }
//            }
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        userViewModel.clearData()
//    }
//
//}