package com.example.cmong_pilates_attendance_project.ui.attendance.fragment

import android.os.Bundle
import android.os.Handler
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
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseFragment
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
                mainView()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed( Runnable {
            findNavController().popBackStack()
        },3000)
    }

    @Composable
    fun textView(text: String, color: Color, fontSize: TextUnit, textAlign: TextAlign,
                 fontWeight: FontWeight = FontWeight.Normal,
                 modifier: Modifier = Modifier){
        Text(
            text = text,
            color =color,
            modifier =modifier,
            textAlign =  textAlign,
            fontSize = fontSize,
            fontWeight =fontWeight
        )
    }

    @Composable
    fun imageView(@DrawableRes drawableId: Int, modifier:Modifier=Modifier){
        Image(
            painter= painterResource(id = drawableId),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }

    @Composable
    fun marginHeight(height: Dp){
        Spacer(modifier = Modifier.height(height))
    }

    @Composable
    fun mainView(){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2b2b2b))) {
            Column(
                modifier= Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                textView(
                    text = "\"${viewModel.searchedUser.value?.name}\"님",
                    color = Color.White,
                    fontSize = 55.sp,
                    textAlign = TextAlign.Center

                )
                textView(
                    text = stringResource(R.string.msg_complete_attendance),
                    color = Color.White,
                    fontSize = 55.sp,
                    textAlign = TextAlign.Center
                )
                marginHeight(40.dp)
                textView(
                    text = stringResource(R.string.msg_add_mileage),
                    color = Color.White,
                    fontSize = 55.sp,
                    textAlign = TextAlign.Center
                )
                marginHeight(40.dp)
                textView(
                    text = stringResource(R.string.msg_current_mileage),
                    color = Color.White,
                    fontSize = 55.sp,
                    textAlign = TextAlign.Center
                )
                marginHeight(10.dp)
                Box(Modifier
                    .border(1.dp, Color.White)
                    .padding(15.dp)) {
                    textView(
                        text = viewModel.searchedUser.value?.mileage.toString(),
                        color = Color.White,
                        fontSize = 55.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            }

            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .align(Alignment.BottomEnd)
            ) {
                imageView(R.drawable.app_logo)
            }

        }
    }
}