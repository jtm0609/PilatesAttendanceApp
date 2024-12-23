package com.example.cmong_pilates_attendance_project.ui.admin.fragment

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
import com.example.cmong_pilates_attendance_project.ui.admin.screen.ChangeUserMileageScreen
import com.example.cmong_pilates_attendance_project.viewmodel.UserViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.ChangeUserMileageViewModel

//class ChangeUserMileageFragment : BaseFragment() {
//    private val viewModel: ChangeUserMileageViewModel by viewModels<ChangeUserMileageViewModel>()
//    private val userViewModel: UserViewModel by activityViewModels<UserViewModel>()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return ComposeView(mContext).apply {
//            setContent { ChangeUserMileageScreen() }
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        init()
//        setDataObserver()
//    }
//
//    private fun setDataObserver(){
//        viewModel.isChangeMileage.observe(viewLifecycleOwner){
//            if(it==null) return@observe
//            userViewModel.updateSearchedUserMileage(viewModel.mileage)
//            showToast(getString(R.string.text_complete_change_mileage))
//            findNavController().popBackStack()
//        }
//    }
//
//    private fun init(){
//        viewModel.setMileage(userViewModel.searchedUser.value?.mileage!!)
//        viewModel.setName(userViewModel.searchedUser.value?.name!!)
//    }
//
//}