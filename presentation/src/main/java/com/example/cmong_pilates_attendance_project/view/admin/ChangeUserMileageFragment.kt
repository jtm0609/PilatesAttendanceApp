package com.example.cmong_pilates_attendance_project.view.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseFragment
import com.example.cmong_pilates_attendance_project.utils.Constant

class ChangeUserMileageFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(mContext).apply {
            setContent { mainView() }
        }
    }

    @Composable
    fun textView(
        text: String,
        color: Color,
        fontSize: TextUnit,
        textAlign: TextAlign,
        fontWeight: FontWeight = FontWeight.Normal,
        modifier: Modifier = Modifier
    ) {
        Text(
            text = text,
            color = color,
            modifier = modifier,
            textAlign = textAlign,
            fontSize = fontSize,
            fontWeight = fontWeight
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun toolbar() {
        TopAppBar(
            title = {
                textView(
                    text = stringResource(id = R.string.text_menu_change_attendance_count),
                    Color.White,
                    20.sp,
                    TextAlign.Center
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF2b2b2b)),
            navigationIcon = {
                IconButton(onClick = {findNavController().popBackStack()}) {
                    Icon(
                        Icons.Filled.ArrowBack, "backIcon", tint = Color.White
                    )
                }
            },
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun mainView() {
        Scaffold(
            containerColor = Color(0xFF2b2b2b),
            topBar = { toolbar() },
            content = {
                Divider(
                    color = Color(0xFF333333),
                    thickness = 1.dp,
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .padding(it)
                )
                Box(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(bottom = 60.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .background(
                                    Color(0xFF333333),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(30.dp)
                        ) {
                            textView(
                                text = stringResource(R.string.text_user_mileage, "홍길동"),
                                color = Color.White,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(10.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                textView(
                                    text = stringResource(R.string.text_user_score, 20),
                                    color = Color.White,
                                    fontSize = 60.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                textView(
                                    "↑",
                                    Color.White,
                                    fontSize = 50.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.clickable {
                                        changeMileage(Constant.UP_ATTENDANCE_COUNT)
                                    }
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                textView(
                                    "↓",
                                    Color.White,
                                    fontSize = 50.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.clickable {
                                        changeMileage(Constant.DOWN_ATTENDANCE_COUNT)
                                    }
                                )
                            }

                            textView(
                                "-",
                                Color.White,
                                fontSize = 50.sp,
                                textAlign = TextAlign.Center
                            )

                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(15.dp)
                            .clickable {
                                clickSaveButton()
                            },
                    ) {
                        textView(
                            text = stringResource(R.string.text_save_button),
                            color = Color.White,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .background(
                                    Color(0xFF333333),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .wrapContentSize(align = Alignment.Center)

                        )
                    }
                }
            }
        )
    }

    private fun changeMileage(flag: Int) {
        when (flag) {
            Constant.UP_ATTENDANCE_COUNT -> {

            }

            Constant.DOWN_ATTENDANCE_COUNT -> {

            }
        }
    }

    private fun clickSaveButton() {
        findNavController().popBackStack()
    }
}