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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseFragment
import com.example.cmong_pilates_attendance_project.utils.Constant
import com.example.cmong_pilates_attendance_project.utils.Constant.DOWN_ATTENDANCE_COUNT
import com.example.cmong_pilates_attendance_project.utils.Constant.UP_ATTENDANCE_COUNT
import com.example.cmong_pilates_attendance_project.viewmodel.ChangeAttendanceCountViewModel
import com.example.data.data.AdminEntity


class ChangeAttendanceCountFragment : BaseFragment() {
    private val viewModel: ChangeAttendanceCountViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(mContext).apply {
            setContent {
                mainView()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataObserver()
        viewModel.getAdminData()
    }
    private fun setDataObserver() {
        viewModel.isEmptyAdminData.observe(viewLifecycleOwner){
            if(it==true){ //관리자 데이터가 없다면 추가를 해준다.
                viewModel.addAdminData(AdminEntity(
                    maxAttendance = 1
                ))
            }
        }
        viewModel.adminData.observe(viewLifecycleOwner){
            if(it==null) return@observe
            viewModel.setAttendanceCount(it.maxAttendance)
        }

        viewModel.isChangeCount.observe(viewLifecycleOwner){
            if(it==true){
                showToast(getString(R.string.text_complete_change_attendance_count))
                findNavController().popBackStack()
            }
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
                    30.sp,
                    TextAlign.Center
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF2b2b2b)),
            navigationIcon = {
                IconButton(onClick = { findNavController().popBackStack() }) {
                    Icon(
                        Icons.Filled.ArrowBack, "backIcon", tint = Color.White,
                        modifier = Modifier.size(50.dp).padding(end = 10.dp)
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
                                .padding(60.dp)
                        ) {
                            textView(
                                text = stringResource(R.string.text_max_attendance_count),
                                color = Color.White,
                                fontSize = 40.sp,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(10.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                textView(
                                    text = stringResource(
                                        R.string.text_attendance_count,
                                        viewModel.attendanceCount
                                    ),
                                    color = Color.White,
                                    fontSize = 80.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                textView(
                                    "↑",
                                    Color.White,
                                    fontSize = 110.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.clickable {
                                        changeMileage(UP_ATTENDANCE_COUNT)
                                    }
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                textView(
                                    "↓",
                                    Color.White,
                                    fontSize = 110.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.clickable {
                                        changeMileage(DOWN_ATTENDANCE_COUNT)
                                    }
                                )
                            }

                            textView(
                                "-",
                                Color.White,
                                fontSize = 60.sp,
                                textAlign = TextAlign.Center
                            )

                        }
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 30.dp, start = 60.dp, end = 60.dp)
                            .clickable {
                                clickSaveButton()
                            },
                    ) {
                        textView(
                            text = stringResource(R.string.text_save_button),
                            color = Color.White,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
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
            UP_ATTENDANCE_COUNT -> {
                viewModel.apply {
                    setAttendanceCount(attendanceCount + 1)
                }
            }

            DOWN_ATTENDANCE_COUNT -> {
                viewModel.apply {
                    if (attendanceCount > 1) {
                        setAttendanceCount(attendanceCount - 1)
                    }
                }
            }
        }
    }

    private fun clickSaveButton() {
        viewModel.changeAttendanceCount(viewModel.attendanceCount)
    }
}