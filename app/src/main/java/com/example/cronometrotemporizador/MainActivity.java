package com.example.cronometrotemporizador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btCron;
    private Button btTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callTelaInic();
        btCron = findViewById(R.id.btCron);
        btTemp = findViewById(R.id.btTemp);
    }

    public void callTelaInic(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_ct, new TelaInicialFragment())
                .commit();
    }

    public void callCronometro(View view){
        btCron.setEnabled(false);
        btTemp.setEnabled(true);
        getSupportActionBar().setTitle(R.string.bt_cron);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_ct, new CronometroFragment())
                .commit();
    }

    public void callTemporizador(View view){
        btTemp.setEnabled(false);
        btCron.setEnabled(true);
        getSupportActionBar().setTitle(R.string.bt_temp);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_ct, new TemporizadorFragment())
                .commit();
    }
}
