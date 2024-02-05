package com.example.cmong_pilates_attendance_project.view.attendance.ui

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.utils.Constant
import com.example.cmong_pilates_attendance_project.viewmodel.RegisterUserViewModel

class RegisterUserScreen() {

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
    fun toolbar(viewModel: RegisterUserViewModel) {
        TopAppBar(
            title = {
                textView(
                    text = stringResource(id = R.string.text_menu_register_user),
                    Color.White,
                    30.sp,
                    TextAlign.Center
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF2b2b2b)),
            navigationIcon = {
                IconButton(onClick = {
                    if(!viewModel.durationVisibility) {
                        //findNavController().popBackStack()
                    }}) {
                    Icon(
                        Icons.Filled.ArrowBack, "backIcon", tint = Color.White,
                        modifier = Modifier.size(50.dp).padding(end = 10.dp)
                    )
                }
            },
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun inputEditItem(viewModel: RegisterUserViewModel, inputType: Int, modifier: Modifier =Modifier) {
        var titleText: String? = null
        var hintText: String? = null
        var keyboardType: KeyboardType? = null
        var text by remember {
            mutableStateOf("")
        }
        when (inputType) {
            Constant.INPUT_NAME -> {
                titleText = stringResource(
                    id = R.string.text_input_name
                )
                hintText = stringResource(
                    id = R.string.text_input_hint_name
                )
                keyboardType = KeyboardType.Text
                text= viewModel.name
            }

            Constant.INPUT_PHONE_NUMBER -> {
                titleText = stringResource(
                    id = R.string.text_input_phone_number
                )
                hintText = stringResource(
                    id = R.string.text_input_hint_phone_number
                )
                keyboardType = KeyboardType.Number
                text = viewModel.phone
            }
        }

        textView(
            text = titleText!!,
            color = Color.White, fontSize = 30.sp, textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 20.dp, start = 70.dp, bottom = 10.dp)
        )

        TextField(
            value = text,
            onValueChange = { textValue ->
                if(textValue.length<=11)
                    if(inputType== Constant.INPUT_NAME){
                        viewModel.setNameText(textValue)
                    }else{
                        if(textValue.contains(",") || textValue.contains("-")){
                            return@TextField
                        }
                        viewModel.setPhoneText(textValue)

                    }
            },
            placeholder = {
                Text(
                    hintText!!,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.CenterStart)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType!!,
                imeAction = ImeAction.Done
            ),             modifier = modifier
            ,
            enabled= !viewModel.durationVisibility
        )
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun registerUserScreen(viewModel: RegisterUserViewModel) {
        Scaffold(
            modifier= Modifier.testTag("REGISTER_USER_SCREEN"),
            containerColor = Color(0xFF2b2b2b),
            topBar = { toolbar(viewModel) },
            content = {
                //회원 정보 입력 뷰
                Box(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                        ) { //hideKeyboard()
                 }
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.TopCenter)
                    ) {
                        Divider(
                            color = Color(0xFF333333),
                            thickness = 1.dp,
                            modifier = Modifier.padding(bottom = 15.dp)
                        )
                        inputEditItem(viewModel,inputType = Constant.INPUT_NAME, modifier= Modifier
                            .padding(start = 70.dp, end=70.dp)
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color(0XFFE7E7E7))
                            .testTag("ID_TEXT_FIELD") )
                        inputEditItem(viewModel, inputType = Constant.INPUT_PHONE_NUMBER, modifier = Modifier
                            .padding(start = 70.dp, end=70.dp)
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color(0XFFE7E7E7))
                            .testTag("PHONE_NUMBER_TEXT_FIELD"))
                        //inputTextItem(inputType = Constant.INPUT_DURATION)
                        //inputTextItem(inputType = Constant.INPUT_START_DATE)
                    }

                    //저장 버튼
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 30.dp, start = 60.dp, end = 60.dp)
                            .clickable {
                                //clickSaveButton()
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
        if(viewModel.durationVisibility) {
            //durationSettingView()
        }
    }
}