package com.example.movieapp.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.R
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.crayola
import com.example.movieapp.utils.screens.MyScreens
import com.example.movieapp.utils.SessionManger
import com.example.movieapp.utils.androidColors
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userInfo: SessionManger
) {
    var isLoading by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showSnackbar by remember { mutableStateOf(false) }
    var showSnackbarForValidation by remember { mutableStateOf(false) }
    var showSnackbarForPassword by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Avatar()
            Spacer(modifier = Modifier.padding(top = 130.dp))

            RegisterTextFields(
                name = name,
                onNameChange = { name = it },
                email = email,
                onEmailChange = { email = it },
                password = password,
                onPasswordChange = { password = it }
            )

            Spacer(modifier = Modifier.padding(top = 30.dp))

            Box {

                LaunchedEffect(showSnackbar) {
                    if (showSnackbar) {
                        snackbarHostState.showSnackbar("Please fill all fields!")
                        showSnackbar = false
                    }
                }
                LaunchedEffect(showSnackbarForValidation) {
                    if (showSnackbarForValidation) {
                        snackbarHostState.showSnackbar("email not valid")
                        showSnackbarForValidation = false
                    }
                }
                LaunchedEffect(showSnackbarForPassword) {
                    if (showSnackbarForPassword) {
                        snackbarHostState.showSnackbar("password must be 8 characters")
                        showSnackbarForPassword = false
                    }
                }

                if (isLoading) {
                    ProgressBar()
                    LaunchedEffect(isLoading) {
                        delay(1000)
                        userInfo.saveUserInfo(name = name, email = email)
                        navController.navigate(MyScreens.MainScreen.route) {
                            navController.popBackStack()
                        }
                    }
                } else {
                    BtnSubmit {
                        when {
                            !validateUserInput(name, email, password) -> showSnackbar = true
                            !isValidEmail(email) -> showSnackbarForValidation = true
                            password.length < 8 -> showSnackbarForPassword = true
                            else -> isLoading = true
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun RegisterTextFields(
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit
) {

    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text(text = "Name") },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_round_person_24),
                contentDescription = null
            )
        })
    TextField(
        modifier = Modifier.padding(top = 16.dp),
        value = email,
        onValueChange = onEmailChange,
        label = { Text(text = "Email") },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_round_email_24),
                contentDescription = null
            )
        }
    )
    TextField(
        modifier = Modifier.padding(top = 16.dp),
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible)
                painterResource(R.drawable.baseline_visibility_24)
            else
                painterResource(R.drawable.baseline_visibility_off_24)

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = image, contentDescription = "Toggle Password Visibility")
            }
        }
    )
}

private fun validateUserInput(vararg input: String) = input.all { it.isNotEmpty() }


@Composable
fun ProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier.scale(.6f))
}

@Composable
fun BtnSubmit(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .fillMaxWidth(.8f)
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = crayola)
    ) {
        Text("Submit", color = androidColors.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun Avatar() {
    Image(
        painter = painterResource(R.drawable.user_avatar),
        contentDescription = null,
        modifier = Modifier
            .size(150.dp)
            .padding(top = 32.dp)
    )
}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Preview
@Composable
private fun RegisterScreenPrev() {
    MovieAppTheme {
        val navController = rememberNavController()
        val context = LocalContext.current
        val userInfo = SessionManger(context)
        RegisterScreen(navController = navController, userInfo = userInfo)
    }
}