package com.example.cmong_pilates_attendance_project.ui.attendance.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseFragment
import com.example.cmong_pilates_attendance_project.ui.admin.activity.AdminActivity
import com.example.cmong_pilates_attendance_project.viewmodel.AdminViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.AttendanceViewModel


class AttendanceMainFragment :
    BaseFragment() {
    val viewModel: AttendanceViewModel by activityViewModels()
    val adminViewModel: AdminViewModel by viewModels()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataObserver()
    }

    override fun onResume() {
        super.onResume()
        adminViewModel.getAdminData()
    }


    private fun setDataObserver() {
        adminViewModel.adminData.observe(this) {
            if (it == null) return@observe
            viewModel.setMaxAttendanceCount(it.maxAttendance)
        }

        viewModel.isAlreadyAttendance.observe(this) {
            if (it == true) {
                showToast(getString(R.string.text_noti_already_attendance_user))
            }
        }

        viewModel.isNoExistUser.observe(this) {
            if (it == true) {
                showToast(getString(R.string.text_noti_retry_phone_number))
            }
        }


        //출석 완료
        viewModel.isSuccessAttendance.observe(this) {
            if (it == true)
                findNavController().navigate(R.id.action_attendanceMainFragment_to_attendanceCompleteFragment)
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.onClearData()
    }

    @Composable
    fun textView(
        text: String,
        color: Color,
        fontSize: TextUnit,
        textAlign: TextAlign,
        modifier: Modifier = Modifier
    ) {
        Text(
            text = text,
            color = color,
            modifier = modifier,
            textAlign = textAlign,
            fontSize = fontSize,

        )
    }

    @Composable
    fun imageView(@DrawableRes drawableId: Int) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "image",
            contentScale = ContentScale.Crop
        )
    }

    @Composable
    fun marginHeight(height: Dp) {
        Spacer(modifier = Modifier.height(10.dp))
    }

    @Composable
    fun mainView() {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds()
                .testTag("MAIN_VIEW")
                .background(Color(0xFF2b2b2b)),
        ) {
            IconButton(
                onClick = { movePage() }, modifier = Modifier.padding(start=20.dp,top=30.dp)

            ) {
                Icon(
                    Icons.Filled.Person, "backIcon", tint = Color.White,
                    modifier = Modifier.size(45.dp)
                )
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clipToBounds()
                    .padding(top=5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .height(400.dp)
                        .width(450.dp)
                ) {
                    imageView(R.drawable.app_logo)
                }
                textView(
                    text = stringResource(R.string.msg_info_pilates),
                    Color.White,
                    35.sp,
                    TextAlign.Center,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clipToBounds()
                    .padding(top=40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                marginHeight(20.dp)
                textView(
                    text = stringResource(R.string.msg_input_phone_number),
                    Color.White,
                    30.sp,
                    TextAlign.Left
                )

                marginHeight(10.dp)

                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .width(500.dp)
                        .height(50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = viewModel.phoneNumber,
                        color = Color.Black,
                        letterSpacing = 1.5.sp,
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp,
                        modifier = Modifier.testTag("PHONE_NUMBER_TEXT")
                    )


                }

                marginHeight(10.dp)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .border(2.dp, Color.White)
                        .padding(10.dp)
                        .width(400.dp)
                ) {
                    Row() {
                        textView(text = "7", Color.White, 90.sp, TextAlign.Center,
                            Modifier
                                .width(120.dp)
                                .height(110.dp)
                                .wrapContentSize(align = Alignment.Center)
                                .testTag("7")
                                .clickable { onClickNumber("7") })
                        textView(text = "8", Color.White, 90.sp, TextAlign.Center,
                            Modifier
                                .width(120.dp)
                                .height(110.dp)
                                .wrapContentSize(align = Alignment.Center)
                                .testTag("8")
                                .clickable { onClickNumber("8") })
                        textView(text = "9", Color.White, 90.sp, TextAlign.Center,
                            Modifier
                                .width(120.dp)
                                .height(110.dp)
                                .wrapContentSize(align = Alignment.Center)
                                .testTag("9")
                                .clickable { onClickNumber("9") })
                    }
                    Row() {
                        textView(text = "4", Color.White, 90.sp, TextAlign.Center,
                            Modifier
                                .width(120.dp)
                                .height(110.dp)
                                .wrapContentSize(align = Alignment.Center)
                                .testTag("4")
                                .clickable { onClickNumber("4") })
                        textView(text = "5", Color.White, 90.sp, TextAlign.Center,
                            Modifier
                                .width(120.dp)
                                .height(110.dp)
                                .wrapContentSize(align = Alignment.Center)
                                .testTag("5")
                                .clickable { onClickNumber("5") })
                        textView(text = "6", Color.White, 90.sp, TextAlign.Center,
                            Modifier
                                .width(120.dp)
                                .height(110.dp)
                                .wrapContentSize(align = Alignment.Center)
                                .testTag("6")
                                .clickable { onClickNumber("6") })
                    }
                    Row() {
                        textView(text = "1", Color.White, 90.sp, TextAlign.Center,
                            Modifier
                                .width(120.dp)
                                .height(110.dp)
                                .wrapContentSize(align = Alignment.Center)
                                .testTag("1")
                                .clickable { onClickNumber("1") })
                        textView(text = "2", Color.White, 90.sp, TextAlign.Center,
                            Modifier
                                .width(120.dp)
                                .height(110.dp)
                                .wrapContentSize(align = Alignment.Center)
                                .testTag("2")
                                .clickable { onClickNumber("2") })
                        textView(text = "3", Color.White, 90.sp, TextAlign.Center,
                            Modifier
                                .width(120.dp)
                                .height(110.dp)
                                .wrapContentSize(align = Alignment.Center)
                                .testTag("3")
                                .clickable { onClickNumber("3") })
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = { onClickDelete() }, modifier = Modifier
                                .width(120.dp)
                                .height(110.dp)
                        ) {
                            Icon(
                                Icons.Filled.ArrowBack, "backIcon", tint = Color.White,
                                modifier = Modifier.width(90.dp).height(100.dp).align(Alignment.CenterVertically)
                            )
                        }
                        textView(text = "0", Color.White, 90.sp, TextAlign.Center,
                            Modifier
                                .wrapContentSize(Alignment.Center)
                                .width(120.dp)
                                .align(Alignment.CenterVertically)
                                .clickable { onClickNumber("0") })

                        textView(text = "입장", Color.White, 60.sp, TextAlign.Center,
                            Modifier
                                .wrapContentSize(align = Alignment.Center)
                                .width(120.dp)
                                .align(Alignment.CenterVertically)
                                .testTag("ENTRANCE")
                                .clickable { onClickOk() })
                    }
                }
            }
            marginHeight(10.dp)
        }
    }

    private fun onClickNumber(number: String) {
        viewModel.apply {
            if (phoneNumber.length < 8) {
                setPhoneNumber(phoneNumber + number)
            }
        }
    }

    private fun onClickDelete() {
        viewModel.apply {
            setPhoneNumber(phoneNumber.dropLast(1))
        }
    }

    private fun onClickOk() {
        viewModel.apply {
            checkUser("010$phoneNumber")
        }
    }

    private fun movePage() {
        val intent = Intent(mContext, AdminActivity::class.java)
        activity?.startActivity(intent)
    }
}