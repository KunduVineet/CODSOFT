package com.teeniv.alarm_manager;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.teeniv.alarm_manager.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MaterialTimePicker timePicker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createNotificationChannel();

        binding.selectTime.setOnClickListener(view -> {
            timePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(12)
                    .setMinute(0)
                    .setTitleText("Select Alarm Time")
                    .build();

            timePicker.show(getSupportFragmentManager(),"androidknowledge");
            timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                @SuppressLint({"SetTextI18n", "DefaultLocale"})
                @Override
                public void onClick(View view) {
                    if(timePicker.getHour()>12)
                    {
                        binding.selectTime.setText(
                                String.format("%02d",(timePicker.getHour()-12))+":"+String.format("%02d",timePicker.getMinute())+ "pm"
                        );
                    }   else {
                        binding.selectTime.setText(
                                timePicker.getHour()+":"+timePicker.getMinute()+"am");
                    }

                    calendar =Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                    calendar.set(Calendar.MINUTE, timePicker.getMinute());
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                }
            });
        });

        binding.setAlarm.setOnClickListener(view -> {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent, PendingIntent.FLAG_IMMUTABLE);

            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
            Toast.makeText(MainActivity.this, "Alarm Set", Toast.LENGTH_SHORT).show();
        });
        binding.cancelAlarm.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent, PendingIntent.FLAG_IMMUTABLE);
            if(alarmManager == null)
            {
                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            } alarmManager.cancel(pendingIntent);

            Toast.makeText(MainActivity.this, "alarm Cancelled", Toast.LENGTH_SHORT).show();
        });
    }

    @SuppressLint("ObsoleteSdkInt")
    private void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1)
        {
            CharSequence name = "vkchannel";
            String desc = "channel for Alarm Manager";
            int imp = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("androidknowledge",name,imp);
            channel.setDescription(desc);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }
}