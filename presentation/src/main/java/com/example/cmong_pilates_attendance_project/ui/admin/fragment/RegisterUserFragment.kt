package com.example.cmong_pilates_attendance_project.ui.admin.fragment

import android.app.DatePickerDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi

import androidx.compose.ui.platform.ComposeView

import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseFragment

import com.example.cmong_pilates_attendance_project.ui.admin.view.RegisterUserScreen
import com.example.cmong_pilates_attendance_project.viewmodel.RegisterUserViewModel

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterUserFragment : BaseFragment() {
    private val viewModel: RegisterUserViewModel  by viewModels<RegisterUserViewModel>()
    private val navController: NavController by lazy { findNavController() }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //뒤로가기 처리 (프래그먼트)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        // Inflate the layout for this fragment
        return ComposeView(mContext).apply {
            setContent {
                RegisterUserScreen(viewModel, navController, mContext)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataObserver()
    }

    private fun setDataObserver(){
        viewModel.isSuccessAddUser.observe(this){
            if(it==true){
                showToast(getString(R.string.text_complete_add_user))
                findNavController().popBackStack()
            }else{
                showToast(getString(R.string.text_duplicate_add_user))
            }
        }

        viewModel.isClickStartDate.observe(this){
            if(it==false) return@observe
            showDateDialog()
        }
    }

    private fun showDateDialog(){
        val dlg = DatePickerDialog(mContext,
            { pView, pYear, pMonth, pDayOfMonth ->
                viewModel.setStartDate(pYear, pMonth, pDayOfMonth)
                viewModel.setStartDateTextText(getString(R.string.text_start_date,pYear,pMonth+1,pDayOfMonth))
            },
            viewModel.startYear, viewModel.startMonth, viewModel.startDay)
        dlg.show()
    }


    //뒤로가기
    val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if(viewModel.durationVisibility){
                viewModel.setVisibilityDuration(false)
            }else{
                findNavController().popBackStack()
            }
        }
    }

    private fun hideKeyboard() {
        val inputManager = mContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            activity?.currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

}