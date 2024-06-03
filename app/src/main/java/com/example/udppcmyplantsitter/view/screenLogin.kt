package com.example.udppcmyplantsitter.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.udppcmyplantsitter.ui.theme.MainColor
import com.example.udppcmyplantsitter.ui.theme.SecondColor
import com.example.udppcmyplantsitter.viewModel.AuthFireBase
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenLogin(navController: NavController,
                viewModel: AuthFireBase = androidx.lifecycle.viewmodel.compose.viewModel()
){
// True = Login; False= Create
    val context = LocalContext.current
    val showLoginForm = rememberSaveable{
        mutableStateOf(true)
    }
    Surface(modifier = Modifier.fillMaxSize()
    ) {
        Row() {
            TopAppBar(
                title = { Text(text=" My Plant Sitter ღ") },
                colors = TopAppBarDefaults.topAppBarColors(
                    MainColor,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick ={ navController.navigate(route = appScreens.screenWelcome.router)}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )

                    }
                }
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            if( showLoginForm.value){
                Text(text = "Login with your account")
                UserForm(
                    isCreateAccount = false
                ){
                        email, password ->
                    viewModel.signInWithEmailAndPassword(email,password){
                        navController.navigate(route = appScreens.tabsMovements.router)
                    }

                }
            }else{
                Text(text = "Register an account")
                UserForm(
                    isCreateAccount = true
                ){
                        email, password ->
                    Log.d("PlantsTrue", "Registered with $email & $password")

                    Toast.makeText(
                        context, "Register True", Toast.LENGTH_SHORT
                    ).show()
                    viewModel.createUser(email,password){
                        navController.navigate(route = appScreens.screenLogin.router)
                    }

                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val question=
                    if (showLoginForm.value)"Don't have an account?"
                    else "Already have an account?"
                val option=
                    if(showLoginForm.value)"Register"
                    else "Log in"
                Text(text = question)
                Text(text = option,
                    modifier = Modifier
                        .clickable { showLoginForm.value = !showLoginForm.value }
                        .padding(start = 5.dp),
                    color= SecondColor
                )
            }
        }
    }


}

@Composable
fun UserForm(
    isCreateAccount: Boolean = false,
    onDone: (String,String) -> Unit={email,pwd ->}
){
    val email= rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }

    val passwordVisible= rememberSaveable {
        mutableStateOf(false)
    }

    val valido = remember(email.value,password.value){
        email.value.trim().isNotEmpty() &&
                password.value.trim().isNotEmpty()
    }

    val keyboardController= LocalSoftwareKeyboardController.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EmailImput(
            emailState= email
        )
        PasswordInput(
            passwordState= password,
            labelId= "Password",
            passwordVisible= passwordVisible
        )
        SubmitButton(
            textId= if( isCreateAccount)"Register" else "Log in",
            inputValido= valido
        ){
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButton(textId: String,inputValido:Boolean, onClic: ()-> Unit) {
    Button(onClick = onClic,
        enabled = inputValido,
        colors = ButtonDefaults.buttonColors(MainColor)){
        Text(text = textId,
            modifier= Modifier
                .padding(5.dp))
    }
}

@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
){
    val visualTransformation=
        if (passwordVisible.value)
            VisualTransformation.None
        else PasswordVisualTransformation()


    OutlinedTextField(
        value = passwordState.value,
        onValueChange ={passwordState.value= it},
        label= { Text(text = labelId)},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passwordState.value.isNotBlank()){
                PasswordVisibleIcon(passwordVisible)
            }else null
        }
    )
}

@Composable
fun PasswordVisibleIcon(passwordVisible: MutableState<Boolean>) {
    val image= if(passwordVisible.value)
        Icons.Default.VisibilityOff
    else
        Icons.Default.Visibility
    IconButton(onClick = {
        passwordVisible.value = !passwordVisible.value
    }) {
        Icon(imageVector = image, contentDescription ="")
    }
}

@Composable
fun EmailImput(
    emailState: MutableState<String>,
    labelId: String= "Email"
) {
    InputField(
        valueState= emailState,
        labelId= labelId,
        keyboardType= KeyboardType.Email
    )
}

@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType
){
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {valueState.value = it},
        label = { Text(text = labelId)},
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType= keyboardType
        )
    )
}