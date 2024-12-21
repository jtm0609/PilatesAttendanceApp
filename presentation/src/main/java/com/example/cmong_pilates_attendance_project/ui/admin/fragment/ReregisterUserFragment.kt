package com.example.cmong_pilates_attendance_project.ui.admin.fragment

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chargemap.compose.numberpicker.ListItemPicker
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseFragment
import com.example.cmong_pilates_attendance_project.utils.Constant
import com.example.cmong_pilates_attendance_project.utils.Utils
import com.example.cmong_pilates_attendance_project.viewmodel.UserViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.ReregisterUserViewModel

class ReregisterUserFragment : BaseFragment() {
    private val viewModel: ReregisterUserViewModel by viewModels<ReregisterUserViewModel>()
    private val userViewModel: UserViewModel by activityViewModels<UserViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init()
        // Inflate the layout for this fragment
        return ComposeView(mContext).apply {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
            setContent {
                mainView()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataObserver()
    }

    private fun init(){
        //이용 기간 UI 데이터 업데이트
        val duration = userViewModel.searchedUser.value?.duration
        viewModel.setDurationState(duration!!)
        viewModel.setDurationText(duration)

        //이용 시작일 UI 데이터 업데이트
        val userStartDateTime = userViewModel.searchedUser.value?.startDateTime
        val userStartDateTimeStr = Utils.convertTimeStampToDateString(userStartDateTime!!)
        updateUserStartDate(userStartDateTimeStr)
        viewModel.setStartDateText(userStartDateTimeStr)
    }

    private fun setDataObserver(){
        viewModel.isSuccessUpdateUser.observe(viewLifecycleOwner){
            if(it==true){
                showToast(getString(R.string.text_success_re_register_user))
                findNavController().popBackStack()
            }else{
                showToast(getString(R.string.text_fail_re_register_user))
            }
        }
    }


    //검색된 유저의 startDate를 불러와 현재 UI 데이터에 업데이트한다.
    private fun updateUserStartDate(dateStr: String){
        val year = dateStr.split("-")[0].toInt()
        val month = dateStr.split("-")[1].toInt()-1
        val date= dateStr.split("-")[2].toInt()
        viewModel.setStartDate(year, month, date)
    }

    @Composable
    fun textView(
        text: String,
        color: Color,
        fontSize: TextUnit,
        textAlign: TextAlign = TextAlign.Center,
        modifier: Modifier = Modifier
    ) {
        Text(
            text = text,
            color = color,
            modifier = modifier,
            textAlign = textAlign,
            fontSize = fontSize
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun toolbar() {
        TopAppBar(
            title = {
                textView(
                    text = stringResource(id = R.string.text_menu_re_register_user),
                    Color.White,
                    30.sp,
                    TextAlign.Center
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF2b2b2b)),
            navigationIcon = {
                IconButton(onClick = {
                    if (!viewModel.durationVisibility)
                        findNavController().popBackStack()
                }) {
                    Icon(
                        Icons.Filled.ArrowBack, "backIcon", tint = Color.White,
                        modifier = Modifier.size(50.dp).padding(end = 10.dp)
                    )
                }
            },
        )
    }


    @Composable
    fun inputTextItem(inputType: Int) {
        var titleText: String? = null
        var imageVector: ImageVector? = null
        var contentText: String? = null
        when (inputType) {
            Constant.INPUT_DURATION -> {
                titleText = stringResource(
                    id = R.string.text_input_duration
                )
                imageVector = Icons.Filled.List
                contentText = viewModel.durationText
            }

            Constant.INPUT_START_DATE -> {
                titleText = stringResource(
                    id = R.string.text_input_start_date
                )
                imageVector = Icons.Filled.DateRange
                contentText = viewModel.startDateText
            }
        }

        textView(
            text = titleText!!,
            color = Color.White, fontSize = 30.sp, textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 20.dp, start = 70.dp, bottom = 10.dp)
        )
        Box(
            modifier = Modifier
                .padding(start = 70.dp, end=70.dp)
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(0xFFE8E0ED))
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    if (!viewModel.durationVisibility) {
                        iconClick(inputType)
                    }
                }
        ) {
            textView(
                text = contentText!!,
                color = Color.Black, fontSize = 15.sp, textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 12.dp)
            )

            IconButton(
                onClick = {
                    if (!viewModel.durationVisibility) {
                        iconClick(inputType)
                    }
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector!!, "backIcon", tint = Color.Black
                )
            }
        }
    }

    //이용 기간 설정 뷰
    @Composable
    fun durationSettingView() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2b2b2b)),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                textView(
                    text = "이용 기간을 선택해주세요.", color = Color.White, fontSize = 20.sp,
                    modifier = Modifier.padding(top = 25.dp)
                )
                Divider(
                    color = Color(0xFF333333),
                    thickness = 1.dp,
                    modifier = Modifier.padding(top = 25.dp)
                )
            }

            ListItemPicker(
                label = { it },
                value = viewModel.durationState,
                onValueChange = { viewModel.setDurationState(it) },
                list = viewModel.durationValues,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(300.dp),
                textStyle = TextStyle(fontSize = 25.sp, color = Color.White),
                dividersColor = Color(0xFFE8E0ED)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 30.dp, start = 60.dp, end = 60.dp)
                    .clickable {
                        clickDurationSettingButton(viewModel.durationState)
                    },
            ) {
                textView(
                    text = stringResource(R.string.text_setting_button),
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun mainView() {
        Scaffold(
            containerColor = Color(0xFF2b2b2b),
            topBar = { toolbar() },
            content = {
                //회원 정보 입력 뷰
                Box(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.TopCenter)
                    ) {
                        Divider(
                            color = Color(0xFF333333),
                            thickness = 1.dp,
                            modifier = Modifier.padding(bottom = 15.dp)
                        )
                        inputTextItem(inputType = Constant.INPUT_DURATION)
                        inputTextItem(inputType = Constant.INPUT_START_DATE)
                    }

                    //저장 버튼
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

        //이용 기간 설정 뷰
        if (viewModel.durationVisibility) {
            durationSettingView()
        }
    }

    //저장 버튼 클릭
    @RequiresApi(Build.VERSION_CODES.O)
    private fun clickSaveButton() {
        val userStartDate = viewModel.startDateText
        val userDuration = viewModel.durationState
        val userStartDateTime = Utils.dateStringToTimestamp(userStartDate)
        val userEndDateTime = Utils.getEndDateTimeMilli(userStartDateTime, userDuration)
        userViewModel.updateSearchedUserUsingDate(userStartDateTime, userEndDateTime, userDuration)
        viewModel.reRegisterUser(userViewModel.searchedUser.value!!)
    }

    //이용 기간 설정 클릭
    private fun clickDurationSettingButton(duration: String) {
        viewModel.setVisibilityDuration(false)
        viewModel.setDurationText(duration)
    }

    //아이콘 클릭 이벤트
    private fun iconClick(iconType: Int) {
        if (iconType == Constant.INPUT_DURATION) {
            viewModel.setVisibilityDuration(true)
        } else {
            showDateDialog()
        }
    }

    private fun showDateDialog() {

        val dlg = DatePickerDialog(
            mContext,
            { pView, pYear, pMonth, pDayOfMonth ->
                viewModel.setStartDate(pYear, pMonth, pDayOfMonth)
                viewModel.setStartDateText(
                    getString(
                        R.string.text_start_date,
                        pYear,
                        pMonth + 1,
                        pDayOfMonth
                    )
                )
            },
            viewModel.startYear, viewModel.startMonth, viewModel.startDay
        )
        dlg.show()
    }

    //뒤로가기
    val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (viewModel.durationVisibility) {
                viewModel.setVisibilityDuration(false)
            } else {
                findNavController().popBackStack()
            }
        }
    }


}