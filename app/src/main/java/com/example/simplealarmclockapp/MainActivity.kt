package com.example.simplealarmclockapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplealarmclockapp.ui.theme.SimpleAlarmClockAppTheme
import java.util.*
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.TimePicker
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.example.simplealarmclockapp.R
import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleAlarmClockAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AlarmClockApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmClockApp() {
    var selectedTime by remember { mutableStateOf("No time selected") }
    var alarmMessage by remember { mutableStateOf("") }
    val currentTime = Calendar.getInstance()
    val context = LocalContext.current
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(R.drawable.bg_alarm_3), // Ensure the image is in res/drawable
            contentDescription = "Background",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Alarm Clock",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFDDDDFF), // Light bluish-white to contrast with dark bg
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Alarm message input field with transparent background
            OutlinedTextField(
                value = alarmMessage,
                onValueChange = { alarmMessage = it },
                label = { Text("Alarm Message", color = Color(0xFFBBBBFF)) },
                placeholder = { Text("Enter alarm message", color = Color(0xFF8888CC)) },
                textStyle = androidx.compose.ui.text.TextStyle(color = Color(0xFFFFDD88)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp),
            )

            // Time Picker with light overlay
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x22000022) // Slightly transparent dark color
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // Center the TimePicker
                ) {
                    TimePicker(
                        state = timePickerState,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // Selected Time Display
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x33000044) // Transparent dark navy
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Selected Time: $selectedTime",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFEEEEFF) // Light text for readability
                    )

                    if (alarmMessage.isNotEmpty()) {
                        Text(
                            text = "Message: $alarmMessage",
                            fontSize = 16.sp,
                            color = Color(0xFFCCCCFF),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            // Set Alarm Button with glowing effect
            Button(
                onClick = {
                    val hour = timePickerState.hour
                    val minute = timePickerState.minute
                    selectedTime = String.format("%02d:%02d", hour, minute)
                    setAlarm(context, hour, minute, alarmMessage)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5555FF), // Soft neon blue
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Set Alarm",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}
fun setAlarm(context: Context, hour: Int, minute: Int, message: String) {
    val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
        putExtra(AlarmClock.EXTRA_HOUR, hour)
        putExtra(AlarmClock.EXTRA_MINUTES, minute)
        putExtra(AlarmClock.EXTRA_MESSAGE, message)
    }

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "No alarm app available", Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlarmClockApp() {
    SimpleAlarmClockAppTheme {
        AlarmClockApp()
    }
}
