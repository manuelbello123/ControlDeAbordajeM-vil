package com.example.controldeabordaje.Screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.controldeabordaje.R
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun ScannerQr(navController: NavController){
    var resultadoEscaneo by rememberSaveable { mutableStateOf("") }
    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            resultadoEscaneo = result.contents ?: "Resultado no encontrado"
        }
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor("#ffffff"))),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Image(
                painter = painterResource(id = R.drawable.latinalogo),
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp)
                    .fillMaxSize()
            )
            Button(
                onClick = { scanLauncher.launch(ScanOptions()) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 150.dp, end = 150.dp),
                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#8595AD"))),
                shape = RoundedCornerShape(15)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_qr_code),
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp))
            }
            Text(
                text = "\n $resultadoEscaneo",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                color = androidx.compose.ui.graphics.Color.Black
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 150.dp, end = 150.dp),
                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#5DADE2"))),
                shape = RoundedCornerShape(15)
            ) {
                Text(text = "Aprobar")
            }
        }
    }
}

@Preview
@Composable
fun a(){
    ScannerQr(navController = rememberNavController())
}