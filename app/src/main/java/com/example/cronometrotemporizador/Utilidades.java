package com.example.cronometrotemporizador;

import android.annotation.SuppressLint;

public class Utilidades {

    @SuppressLint("DefaultLocale")
    public static String calcTime(Integer secs){
        Integer h =  secs/3600;
        Integer m = (secs - (3600*h)) / 60;
        Integer s = secs - (3600*h) - (60*m);
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
