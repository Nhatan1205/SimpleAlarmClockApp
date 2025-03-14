﻿# SimpleAlarmClockApp

#### Name: Nguyen Nhat An
#### Id: 22110007
#### Homework - Simple Alarm Clock App
---------------------------------------------------------
# Simple Alarm Clock App

## Introduction
The **Simple Alarm Clock App** is an Android application that allows users to set alarms with custom messages using a user-friendly interface. This app integrates Android's built-in alarm functionality, delegating alarm-setting tasks to the device's default clock or alarm app.

## Features
- Select a time for the alarm using a modern time picker.
- Enter a custom message to be displayed when the alarm goes off.
- Set alarms using an implicit intent (`ACTION_SET_ALARM`).
- Handles cases where no alarm app is available.
- Clean and visually appealing UI with Material 3 design.

## Installation
### Prerequisites
- Android device or emulator running Android 6.0 (API Level 23) or higher.
- Android Studio installed on your computer.
- Internet connection (for initial setup and dependencies).

### Steps
1. **Clone the repository**
   ```sh
   git clone https://github.com/Nhatan1205/SimpleAlarmClockApp.git
   ```
2. **Open the project in Android Studio**
   - Go to **File > Open** and select the cloned project folder.
3. **Build and run the app**
   - Click on the "Run" button or press `Shift + F10` to build and launch the app on an emulator or a real device.

## How to Use
1. **Launch the App**: Open the Simple Alarm Clock App.
2. **Set Alarm Message**: Enter a message (optional) in the text field.
3. **Pick a Time**: Use the time picker to select the desired alarm time.
4. **Set the Alarm**: Tap the "Set Alarm" button. If an alarm app is available, it will be set successfully.
5. **Handle Errors**: If no alarm app is installed, a toast message will notify you.

## Code Overview
### Main Components
- `MainActivity.kt`: Contains the UI and logic for user interaction.
- `setAlarm()` function:
  ```kotlin
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
  ```

## Future Improvements
- Add a list of scheduled alarms within the app.
- Implement custom alarm tones.
- Support for recurring alarms.

## Contact
For any issues or contributions, feel free to open an issue or reach out at [ngnhatan1205@gmail.com].


