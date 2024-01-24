package com.example.cmong_pilates_attendance_project.view.attendance

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
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
import com.example.cmong_pilates_attendance_project.view.admin.AdminActivity


class AttendanceMainFragment :
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun editText(){
        val textState = remember{
            mutableStateOf("")
        }
        TextField(value = textState.value, onValueChange ={textValue -> textState.value = textValue} )
    }

    @Composable
    fun marginHeight(height: Dp){
        Spacer(modifier = Modifier.height(10.dp))
    }

    @Preview(showBackground = true)
    @Composable
    fun mainView(){
        Row(modifier = Modifier
            .fillMaxSize()
            .clipToBounds()
            .background(Color(0xFF2b2b2b)),
        ) {
            IconButton(onClick = {movePage()}, modifier = Modifier.padding(20.dp).size(100.dp)) {
                Icon(
                    Icons.Filled.Person, "backIcon", tint = Color.White
                )
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clipToBounds(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Box(modifier= Modifier.height(350.dp).width(350.dp)) {
                    imageView(R.drawable.app_logo)
                }
                textView(
                    text = stringResource(R.string.msg_info_pilates),
                    Color.White,
                    25.sp,
                    TextAlign.Center,
                    modifier = Modifier.padding(top=10.dp)
                )
            }

            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clipToBounds(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,) {
                marginHeight(20.dp)
                textView(
                    text = stringResource(R.string.msg_input_phone_number),
                    Color.White,
                    15.sp,
                    TextAlign.Left
                )

                marginHeight(10.dp)

                Box(modifier= Modifier
                    .background(Color.White)
                    .width(250.dp)
                    .height(30.dp)){
                    textView(
                        text = "",
                        Color.Black,
                        12.sp,
                        TextAlign.Center)

                }

                marginHeight(10.dp)

                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.border(2.dp, Color.White).padding(10.dp).width(220.dp)) {
                    Row() {
                        textView(text = "7", Color.White, 50.sp, TextAlign.Center, Modifier.width(60.dp).height(60.dp).wrapContentSize(align = Alignment.Center))
                        textView(text = "8", Color.White, 50.sp, TextAlign.Center, Modifier.width(60.dp).height(60.dp).wrapContentSize(align = Alignment.Center))
                        textView(text = "9", Color.White, 50.sp, TextAlign.Center, Modifier.width(60.dp).height(60.dp).wrapContentSize(align = Alignment.Center))
                    }
                    Row() {
                        textView(text = "4", Color.White, 50.sp, TextAlign.Center, Modifier.width(60.dp).height(60.dp).wrapContentSize(align = Alignment.Center))
                        textView(text = "5", Color.White, 50.sp, TextAlign.Center, Modifier.width(60.dp).height(60.dp).wrapContentSize(align = Alignment.Center))
                        textView(text = "6", Color.White, 50.sp, TextAlign.Center, Modifier.width(60.dp).height(60.dp).wrapContentSize(align = Alignment.Center))
                    }
                    Row() {
                        textView(text = "1", Color.White, 50.sp, TextAlign.Center, Modifier.width(60.dp).height(60.dp).wrapContentSize(align = Alignment.Center))
                        textView(text = "2", Color.White, 50.sp, TextAlign.Center, Modifier.width(60.dp).height(60.dp).wrapContentSize(align = Alignment.Center))
                        textView(text = "3", Color.White, 50.sp, TextAlign.Center, Modifier.width(60.dp).height(60.dp).wrapContentSize(align = Alignment.Center))
                    }
                    Row() {
                        textView(text = "←", Color.White, 35.sp, TextAlign.Center, Modifier.width(60.dp).height(60.dp).wrapContentSize(align = Alignment.Center))
                        textView(text = "0", Color.White, 50.sp, TextAlign.Center, Modifier.width(60.dp).height(60.dp).wrapContentSize(align = Alignment.Center))
                        textView(text = "OK", Color.White, 35.sp, TextAlign.Center, Modifier.width(60.dp).height(60.dp).wrapContentSize(align = Alignment.Center))
                    }
                }
            }
            marginHeight(10.dp)
        }
    }

    fun movePage(){
        val intent = Intent(mContext, AdminActivity::class.java)
        activity?.startActivity(intent)
    }
}