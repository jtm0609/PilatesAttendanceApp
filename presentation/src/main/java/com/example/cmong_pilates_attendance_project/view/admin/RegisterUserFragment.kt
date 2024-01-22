package com.example.cmong_pilates_attendance_project.view.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chargemap.compose.numberpicker.ListItemPicker
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseFragment
import com.example.cmong_pilates_attendance_project.utils.Constant.INPUT_DURATION
import com.example.cmong_pilates_attendance_project.utils.Constant.INPUT_NAME
import com.example.cmong_pilates_attendance_project.utils.Constant.INPUT_PHONE_NUMBER
import com.example.cmong_pilates_attendance_project.utils.Constant.INPUT_START_DATE
import com.example.cmong_pilates_attendance_project.viewmodel.RegisterUserViewModel


class RegisterUserFragment : BaseFragment() {
    private val viewModel: RegisterUserViewModel  by viewModels<RegisterUserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //뒤로가기 처리 (프래그먼트)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(viewModel.durationVisibility){
                    viewModel.setVisibilityDuration(false)
                }else{
                    findNavController().popBackStack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        // Inflate the layout for this fragment
        return ComposeView(mContext).apply {
            setContent {
                mainView()
            }
        }
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
                    text = stringResource(id = R.string.text_menu_register_user),
                    Color.White,
                    20.sp,
                    TextAlign.Center
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF2b2b2b)),
            navigationIcon = {
                IconButton(onClick = { activity?.finish() }) {
                    Icon(
                        Icons.Filled.ArrowBack, "backIcon", tint = Color.White
                    )
                }
            },
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun editText(hint: String, keyboardType: KeyboardType, modifier: Modifier = Modifier) {
        val textState = remember {
            mutableStateOf("")
        }
        TextField(
            value = textState.value,
            onValueChange = { textValue -> textState.value = textValue },
            placeholder = {
                Text(
                    hint,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.CenterStart)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ), modifier = modifier
        )
    }

    @Composable
    fun inputEditItem(inputType: Int) {
        var titleText: String? = null
        var hintText: String? = null
        var keyboardType: KeyboardType? = null
        when (inputType) {
            INPUT_NAME -> {
                titleText = stringResource(
                    id = R.string.text_input_name
                )
                hintText = stringResource(
                    id = R.string.text_input_hint_name
                )
                keyboardType = KeyboardType.Text
            }

            INPUT_PHONE_NUMBER -> {
                titleText = stringResource(
                    id = R.string.text_input_phone_number
                )
                hintText = stringResource(
                    id = R.string.text_input_hint_phone_number
                )
                keyboardType = KeyboardType.Number
            }
        }

        textView(
            text = titleText!!,
            color = Color.White, fontSize = 30.sp, textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 10.dp)
        )
        editText(
            hint = hintText!!,
            keyboardType!!,
            modifier = Modifier
                .padding(start = 20.dp)
                .width(300.dp)
                .height(50.dp)
                .background(Color(0XFFE7E7E7))
        )
    }


    @Composable
    fun inputTextItem(inputType: Int) {
        var titleText: String? = null
        var imageVector: ImageVector? = null
        var contentText: String? =null
        when (inputType) {
            INPUT_DURATION -> {
                titleText = stringResource(
                    id = R.string.text_input_duration
                )
                imageVector = Icons.Filled.List
                contentText = viewModel.durationText
            }

            INPUT_START_DATE -> {
                titleText = stringResource(
                    id = R.string.text_input_start_date
                )
                imageVector = Icons.Filled.DateRange
                contentText=""
            }
        }

        textView(
            text = titleText!!,
            color = Color.White, fontSize = 30.sp, textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 10.dp)
        )
        Box(
            modifier = Modifier
                .padding(start = 20.dp)
                .width(300.dp)
                .height(50.dp)
                .background(Color(0xFFE8E0ED))
        ) {
            textView(
                text = contentText!!,
                color = Color.Black, fontSize = 15.sp, textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 12.dp)
            )

            IconButton(
                onClick = { iconClick(inputType) },
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
    fun durationSettingView (){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2b2b2b)),
        ) {
            val values = listOf("2주", "4주", "8주", "12주")
            var state by remember { mutableStateOf(values[0]) }

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
                value = state,
                onValueChange = { state = it },
                list = values,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(300.dp),
                textStyle = TextStyle(fontSize = 25.sp, color = Color.White),
                dividersColor = Color(0xFFE8E0ED)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(15.dp)
                    .clickable {
                        clickDurationSettingButton(state)
                    },
            ) {
                textView(
                    text = stringResource(R.string.text_setting_button),
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
                        inputEditItem(inputType = INPUT_NAME)
                        inputEditItem(inputType = INPUT_PHONE_NUMBER)
                        inputTextItem(inputType = INPUT_DURATION)
                        inputTextItem(inputType = INPUT_START_DATE)
                    }

                    //저장 버튼
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

        //이용 기간 설정 뷰
        if(viewModel.durationVisibility) {
            durationSettingView()
        }
    }

    //저장 버튼 클릭
    private fun clickSaveButton() {
        findNavController().popBackStack()
    }

    //이용 기간 설정 클릭
    private fun clickDurationSettingButton(duration: String) {
        viewModel.setVisibilityDuration(false)
        viewModel.setDurationText(duration)
    }

    //아이콘 클릭 이벤트
    private fun iconClick(iconType: Int){
        if(iconType == INPUT_DURATION){
            viewModel.setVisibilityDuration(true)
        }else{

        }
    }



}