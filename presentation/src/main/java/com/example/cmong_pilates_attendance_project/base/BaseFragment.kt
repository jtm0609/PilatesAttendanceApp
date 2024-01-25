package com.example.cmong_pilates_attendance_project.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.cmong_pilates_attendance_project.R

abstract class BaseFragment
     : Fragment() {
    protected lateinit var mContext: Context
    protected var progressBar: ProgressBar?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    protected fun showToast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showProgress(){
        val layout = RelativeLayout(mContext)
        progressBar = ProgressBar(mContext,null,
            android.R.attr.progressBarStyleLarge)
        progressBar?.isIndeterminate=true
        val params = RelativeLayout.LayoutParams(100,100)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        layout.addView(progressBar,params)

        activity?.setContentView(layout)
        progressBar?.visibility=View.VISIBLE
    }

    protected fun hideProgress(){
        progressBar?.visibility=View.GONE
    }
}