package com.example.controldeabordaje.Screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.controldeabordaje.GradientBox.GradientBox
import com.example.controldeabordaje.GradientBox.isSmallScreenHeight
import com.example.controldeabordaje.GradientBox.rememberImeState
import com.example.controldeabordaje.Navigation.AppScrens
import com.example.controldeabordaje.R
import com.example.controldeabordaje.Response.ApiService
import com.example.controldeabordaje.Response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun LoginScreen(navController: NavController) {
    val isImeVisible by rememberImeState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        GradientBox(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                val animatedUpperSectionRatio by animateFloatAsState(
                    targetValue = if (isImeVisible) 0f else 0.35f,
                    label = ""
                )
                AnimatedVisibility(visible = !isImeVisible, enter = fadeIn(), exit = fadeOut()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(animatedUpperSectionRatio),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "BIENVENIDO A CONTROL DE ABORDAJE",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White,
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (isSmallScreenHeight()) {
                        Spacer(modifier = Modifier.fillMaxSize(0.05f))
                    } else {
                        Spacer(modifier = Modifier.fillMaxSize(0.05f))
                    }
                    Text(
                        text = "Inicia sesión",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.Gray
                    )
                    if (isSmallScreenHeight()) {
                        Spacer(modifier = Modifier.fillMaxSize(0.05f))
                    } else {
                        Spacer(modifier = Modifier.fillMaxSize(0.05f))
                    }
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    )

                    val apiService: ApiService by lazy {
                        ApiService.create()
                    }

                    var userName = rememberSaveable { mutableStateOf("") }
                    var userPassword = rememberSaveable { mutableStateOf("") }
                    val passwordVisible = rememberSaveable { mutableStateOf(false) }
                    val context = LocalContext.current

                    fun performLogin() {
                        val call = apiService.postLogin(email = userName, password = userPassword)

                        call.enqueue(object : Callback<LoginResponse> {
                            override fun onResponse(
                                call: Call<LoginResponse>,
                                response: Response<LoginResponse>
                            ) {
                                if (response.isSuccessful) {
                                    val loginResponse = response.body()

                                    if (loginResponse == null) {
                                        Toast.makeText(context, "Campos vacíos", Toast.LENGTH_LONG)
                                            .show()
                                        return
                                    }
                                    if (loginResponse.success) {
                                        navController.navigate(route = AppScrens.ScannerQr.route)
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Credenciales incorrectas",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "No se logró conectar con el servidor",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                Toast.makeText(context, "Error del servidor", Toast.LENGTH_LONG)
                                    .show()
                            }
                        })
                    }
                    OutlinedTextField(
                        value = userName.value, onValueChange = { userName.value = it },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = "person")
                        },
                        label = {
                            Text(
                                text = "username",
                                style = TextStyle(fontSize = 22.sp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 20.dp, 20.dp, 0.dp),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 22.sp

                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = Color(0xFF006BC9),
                            containerColor = Color.White,
                            focusedBorderColor = Color(0xFF006BC9)
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        maxLines = 1,
                        shape = RoundedCornerShape(10.dp)

                    )
                    val visualTransformation = if (passwordVisible.value)
                        VisualTransformation.None
                    else PasswordVisualTransformation()

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = userPassword.value, onValueChange = { userPassword.value = it },

                        leadingIcon = {
                            Icon(Icons.Default.Info, contentDescription = "password")
                        },
                        label = {
                            Text(
                                style = TextStyle(fontSize = 22.sp),
                                text = "password"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 20.dp, 20.dp, 0.dp),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 22.sp
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = Color(0xFF006BC9),
                            containerColor = Color.White,
                            focusedBorderColor = Color(0xFF006BC9)
                        ),
                        singleLine = true,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = visualTransformation,
                        trailingIcon = {
                            if (userPassword.value.isNotBlank()) {
                                PasswordVisibleIcon(passwordVisible)
                            } else null
                        },
                        shape = RoundedCornerShape(10.dp)
                    )
                    if (isImeVisible) {
                        Button(
                            onClick = {
                                //performLogin()
                                navController.navigate(route = AppScrens.ScannerQr.route)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                                .padding(horizontal = 16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF006BC9),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp),
                        )
                        {
                            Text(
                                text = "Entrar",
                                style = TextStyle(fontSize = 24.sp),
                                fontWeight = FontWeight(500)
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Button(
                                onClick = {
                                    //performLogin()
                                    navController.navigate(route = AppScrens.ScannerQr.route)
                                }, modifier = Modifier
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF006BC9),
                                    contentColor = Color.White,
                                ),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 50.dp
                                ),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = "Entrar",
                                    style = TextStyle(fontSize = 24.sp),
                                    fontWeight = FontWeight(500)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("ServiceCast")
fun hideKeyboard(context: Context) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow((context as Activity).currentFocus?.windowToken, 0)
}

@Composable
fun PasswordVisibleIcon(
    passwordVisible: MutableState<Boolean>,
) {
    val image =
        if (passwordVisible.value)
            Icons.Default.VisibilityOff
        else
            Icons.Default.Visibility
    IconButton(onClick = {
        passwordVisible.value = !passwordVisible.value
    }) {
        Icon(imageVector = image, contentDescription = null)
    }
}

@Preview
@Composable
fun tyd() {
    LoginScreen(navController = rememberNavController())
}