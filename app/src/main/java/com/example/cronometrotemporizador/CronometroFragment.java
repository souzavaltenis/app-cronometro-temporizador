package com.example.cronometrotemporizador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CronometroFragment extends Fragment {

    private TextView tv;
    private Handler handler;
    private Runnable runnable;
    private Integer segundos;
    private Boolean flagBtStart;
    private View view;

    public CronometroFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cronometro, container, false);

        tv = view.findViewById(R.id.tvTimeTemp);
        handler = new Handler();
        segundos=0;
        flagBtStart = false;
        runTimer();

        addFunctionBtStart();
        addFunctionBtStop();
        addFunctionBtReset();

        return view;
    }

    private void addFunctionBtStart(){
        Button btStart = view.findViewById(R.id.btStartTemp);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flagBtStart) {
                    runnable.run();
                    flagBtStart = true;
                }
            }
        });
    }

    private void addFunctionBtStop(){
        Button btStop = view.findViewById(R.id.btStopCron);
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagBtStart = false;
                handler.removeCallbacks(runnable);
            }
        });
    }

    private void addFunctionBtReset(){
        Button btReset = view.findViewById(R.id.btResetCron);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeTv(0);
            }
        });
    }

    private void runTimer(){
        runnable = new Runnable() {
            @Override
            public void run() {
                setTimeTv(++segundos);
                handler.postDelayed(this, 1000);
            }
        };
    }

    private void setTimeTv(Integer secs){
        segundos = secs;
        tv.setText(Utilidades.calcTime(secs));
    }
}
