package com.example.cronometrotemporizador;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TemporizadorFragment extends Fragment {

    private TextView tv;
    private Handler handler;
    private Runnable runnable;
    private Integer segundos;
    private View view;

    private Spinner spHoras;
    private Spinner spMins;
    private Spinner spSecs;

    private Button btStart;
    private Button btStop;
    private Button btResume;
    private Button btReset;

    public TemporizadorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_temporizador, container, false);

        spHoras = view.findViewById(R.id.spHoras);
        spMins = view.findViewById(R.id.spMin);
        spSecs = view.findViewById(R.id.spSecs);

        btStart = view.findViewById(R.id.btStartTemp);
        btReset = view.findViewById(R.id.btResetTemp);
        btStop = view.findViewById(R.id.btStopTemp);
        btResume = view.findViewById(R.id.btResumeTemp);

        btStop.setEnabled(false);
        btReset.setEnabled(false);
        btResume.setEnabled(false);
        setNumbersSpinner();

        tv = view.findViewById(R.id.tvTimeTemp);
        handler = new Handler();
        segundos = 0;

        runTimer();

        addFunctionBtStart();
        addFunctionBtReset();
        addFunctionBtStop();
        addFunctionBtResume();

        return view;
    }

    private void setNumbersSpinner(){
        spHoras.setAdapter(new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, Utilidades.getNumbers(0, 24)));
        spMins.setAdapter(new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, Utilidades.getNumbers(0, 59)));
        spSecs.setAdapter(new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, Utilidades.getNumbers(0, 59)));
    }

    private void setarSegundos(){
        Integer horas = Integer.parseInt(spHoras.getSelectedItem().toString());
        Integer mins = Integer.parseInt(spMins.getSelectedItem().toString());
        Integer secs = Integer.parseInt(spSecs.getSelectedItem().toString());
        segundos = horas * 3600 + mins * 60 + secs + 1;
    }

    private void addFunctionBtStart(){
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setarSegundos();
                handler.removeCallbacks(runnable);
                runnable.run();
                btStop.setEnabled(true);
                btStart.setEnabled(false);
                btReset.setEnabled(true);
            }
        });
    }

    private void addFunctionBtReset(){
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setarSegundos();
                tv.setText(Utilidades.calcTime(--segundos));
            }
        });
    }

    private void addFunctionBtStop(){
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                btStop.setEnabled(false);
                btResume.setEnabled(true);
            }
        });
    }

    private void addFunctionBtResume(){
        btResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runnable.run();
                btStop.setEnabled(true);
                btResume.setEnabled(false);
            }
        });
    }

    private void runTimer(){
        runnable = new Runnable() {
            @Override
            public void run() {
                if(segundos > 0){
                    segundos--;
                    tv.setText(Utilidades.calcTime(segundos));
                    handler.postDelayed(this, segundos == 0 ? 0 : 1000);
                }else{
                    Context context = getContext();
                    if(context != null) {
                        Toast.makeText(context, "Tempo Finalizado!", Toast.LENGTH_LONG).show();
                    }
                    btStop.setEnabled(false);
                    btStart.setEnabled(true);
                    btReset.setEnabled(false);
                }

            }
        };
    }

}
