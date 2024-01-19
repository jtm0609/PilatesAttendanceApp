package com.example.cmong_pilates_attendance_project.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.cmong_pilates_attendance_project.R

abstract class BaseFragment
     : Fragment() {
    protected lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    protected fun showToast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }
}