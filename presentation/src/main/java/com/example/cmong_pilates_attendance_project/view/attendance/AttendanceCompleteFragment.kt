package com.example.cmong_pilates_attendance_project.view.attendance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseFragment


class AttendanceCompleteFragment :
    BaseFragment() {
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
    fun textView(text: String, color: Color, fontSize: TextUnit, textAlign: TextAlign, modifier: Modifier = Modifier){
        Text(
            text = text,
            color =color,
            modifier =modifier,
            textAlign =  textAlign,
            fontSize = fontSize
        )
    }

    @Composable
    fun imageView(@DrawableRes drawableId: Int){
        Image(
            painter= painterResource(id = drawableId),
            contentDescription = "image",
            contentScale = ContentScale.Crop
        )
    }

    @Composable
    fun marginHeight(height: Dp){
        Spacer(modifier = Modifier.height(10.dp))
    }

    @Preview
    @Composable
    fun mainView(){
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFF2b2b2b))) {
            Column(
                modifier= Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                textView(
                    text = "홍길동 님",
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center

                )
                textView(
                    text = stringResource(R.string.msg_complete_attendance),
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                marginHeight(20.dp)
                textView(
                    text = stringResource(R.string.msg_add_mileage),
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                marginHeight(20.dp)
                textView(
                    text = stringResource(R.string.msg_current_mileage),
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                marginHeight(10.dp)
                textView(
                    text = "12",
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    Modifier.border(1.dp, Color.White)
                )
            }

            Column(modifier= Modifier.height(50.dp).width(50.dp).align(Alignment.BottomEnd)) {
                imageView(R.drawable.app_logo)
            }
        }
    }
}