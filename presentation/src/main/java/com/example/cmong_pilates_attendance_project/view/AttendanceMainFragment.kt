package com.example.cmong_pilates_attendance_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseFragment
import com.example.cmong_pilates_attendance_project.databinding.FragmentAttendanceMainBinding


class AttendanceMainFragment :
    BaseFragment<FragmentAttendanceMainBinding>(R.layout.fragment_attendance_main) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                mainView()
            }
        }

    }
    
    @Composable
    fun textView(text: String){
        Text(text = text)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun editText(){
        val textState = remember{
            mutableStateOf("")
        }
        TextField(value = textState.value, onValueChange ={textValue -> textState.value = textValue} )
    }

    @Preview(showBackground = true)
    @Composable
    fun mainView(){
        MaterialTheme {
            Box(modifier= Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Column(Modifier.weight(1f)) {
                        textView(text = stringResource(R.string.msg_info_pilates))
                    }

                    Column(Modifier.weight(1f)) {
                        textView(text = stringResource(R.string.msg_input_phone_number))
                        editText()
                    }
                }
            }
        }
    }
}