package com.example.cronometrotemporizador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callCronometro(View view){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_ct, new CronometroFragment())
                .commit();
    }
}
