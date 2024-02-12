package com.example.cmong_pilates_attendance_project.view.admin

import android.app.DatePickerDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
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
import com.example.cmong_pilates_attendance_project.utils.Utils
import com.example.cmong_pilates_attendance_project.view.admin.ui.RegisterUserScreen
import com.example.cmong_pilates_attendance_project.viewmodel.RegisterUserViewModel
import com.example.data.data.UserEntity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterUserFragment : BaseFragment() {
    private val viewModel: RegisterUserViewModel  by viewModels<RegisterUserViewModel>()
    //private val adminViewModel: AdminViewModel by viewModels<AdminViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //뒤로가기 처리 (프래그먼트)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)


        // Inflate the layout for this fragment
        return ComposeView(mContext).apply {
            setContent {
                RegisterUserScreen(viewModel, findNavController(), mContext)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataObserver()
    }

    private fun setDataObserver(){
        viewModel.isSuccessAddUser.observe(this){
            if(it==true){
                showToast(getString(R.string.text_complete_add_user))
                findNavController().popBackStack()
            }else{
                showToast(getString(R.string.text_duplicate_add_user))
            }
        }

        viewModel.isClickStartDate.observe(this){
            if(it==false) return@observe
            showDateDialog()
        }
    }

/*    @Composable
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
                    30.sp,
                    TextAlign.Center
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF2b2b2b)),
            navigationIcon = {
                IconButton(onClick = {
                    if(!viewModel.durationVisibility) {
                        findNavController().popBackStack()
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
    fun editText(hint: String, keyboardType: KeyboardType, textState: MutableState<String> ,modifier: Modifier = Modifier) {
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun inputEditItem(inputType: Int, modifier: Modifier =Modifier) {
        var titleText: String? = null
        var hintText: String? = null
        var keyboardType: KeyboardType? = null
        var text by remember {
            mutableStateOf("")
        }
        when (inputType) {
            INPUT_NAME -> {
                titleText = stringResource(
                    id = R.string.text_input_name
                )
                hintText = stringResource(
                    id = R.string.text_input_hint_name
                )
                keyboardType = KeyboardType.Text
                text= viewModel.name
            }

            INPUT_PHONE_NUMBER -> {
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
                    if(inputType== INPUT_NAME){
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

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun inputTextItem(inputType: Int) {
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current
        var titleText: String? = null
        var imageVector: ImageVector? = null
        var contentText: String? =null
        var testTag:String?=null
        when (inputType) {
            INPUT_DURATION -> {
                titleText = stringResource(
                    id = R.string.text_input_duration
                )
                imageVector = Icons.Filled.List
                contentText = viewModel.durationText
                testTag = "DURATION_TEXT"
            }

            INPUT_START_DATE -> {
                titleText = stringResource(
                    id = R.string.text_input_start_date
                )
                imageVector = Icons.Filled.DateRange
                contentText= viewModel.startDateText
                testTag = "START_DATE_TEXT"
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
                .testTag(testTag!!)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    if (!viewModel.durationVisibility) {
                        iconClick(inputType)
                        keyboardController?.hide()
                        focusManager.clearFocus()
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
                        keyboardController?.hide()
                        focusManager.clearFocus()
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
    fun durationSettingView (){
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
                        .testTag("DURATION_SELECT_COMPLETE")
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RegisterUserScreen() {
        Scaffold(
            modifier=Modifier.testTag("REGISTER_USER_SCREEN"),
            containerColor = Color(0xFF2b2b2b),
            topBar = { toolbar() },
            content = {
                //회원 정보 입력 뷰
                Box(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                        ) { hideKeyboard() }
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.TopCenter)
                    ) {
                        Divider(
                            color = Color(0xFF333333),
                            thickness = 1.dp,
                            modifier = Modifier.padding(bottom = 15.dp)
                        )
                        inputEditItem(inputType = INPUT_NAME, modifier= Modifier
                            .padding(start = 70.dp, end=70.dp)
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color(0XFFE7E7E7))
                            .testTag("ID_TEXT_FIELD") )
                        inputEditItem(inputType = INPUT_PHONE_NUMBER, modifier = Modifier
                            .padding(start = 70.dp, end=70.dp)
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color(0XFFE7E7E7))
                            .testTag("PHONE_NUMBER_TEXT_FIELD"))
                        inputTextItem(inputType = INPUT_DURATION)
                        inputTextItem(inputType = INPUT_START_DATE)
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
        if(viewModel.durationVisibility) {
            durationSettingView()
        }
    }

    //저장 버튼 클릭
    @RequiresApi(Build.VERSION_CODES.O)
    private fun clickSaveButton() {
        val userName = viewModel.name
        val userPhone = viewModel.phone
        val userDuration = viewModel.durationText
        val userStartDate = viewModel.startDateText


        *//*LogUtil.d("userName: $userName")
        LogUtil.d("userPhone: $userPhone")
        ///LogUtil.d("duration timeStamp: ${weekToTimestamp(userDuration)}")
        LogUtil.d("startDate timeStamp: ${dateStringToTimestamp(userStartDate)}")
        LogUtil.d("after timeStamp: ${getEndDate(dateStringToTimestamp(userStartDate), userDuration)}")*//*

        if(userName.isBlank() || userPhone.isBlank() || userDuration.isBlank() || userStartDate.isBlank()){
            showToast(getString(R.string.msg_text_empty))
            return
        }

        if(!Utils.isValidPhoneNumber(userPhone)){
            showToast(getString(R.string.msg_text_not_valid_phone_number))
            return
        }

        val userStartDateTime = Utils.dateStringToTimestamp(userStartDate)
        val userEndDateTime = Utils.getEndDate(userStartDateTime, userDuration)


        val user = UserEntity(
            name = userName,
            phoneNumber = userPhone,
            duration = userDuration,
            startDateTime =userStartDateTime,
            endDateTime = userEndDateTime
        )
        viewModel.addUser(user)
        //findNavController().popBackStack()
    }


    //이용 기간 설정 클릭
    private fun clickDurationSettingButton(duration: String) {
        viewModel.setVisibilityDuration(false)
        viewModel.setDurationText(duration)
    }

    //아이콘 클릭 이벤트
    @OptIn(ExperimentalComposeUiApi::class)
    private fun iconClick(iconType: Int){

        if(iconType == INPUT_DURATION){
            viewModel.setVisibilityDuration(true)
        }else{
            showDateDialog()
        }
    }
*/
    private fun showDateDialog(){

        val dlg = DatePickerDialog(mContext,
            { pView, pYear, pMonth, pDayOfMonth ->
                viewModel.setStartDate(pYear, pMonth, pDayOfMonth)
                viewModel.setStartDateTextText(getString(R.string.text_start_date,pYear,pMonth+1,pDayOfMonth))
            },
            viewModel.startYear, viewModel.startMonth, viewModel.startDay)
        dlg.show()
    }


    //뒤로가기
    val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if(viewModel.durationVisibility){
                viewModel.setVisibilityDuration(false)
            }else{
                findNavController().popBackStack()
            }
        }
    }

    private fun hideKeyboard() {
        val inputManager = mContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            activity?.currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

}