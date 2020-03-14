package com.example.cronometrotemporizador;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

public class Utilidades {

    @SuppressLint("DefaultLocale")
    public static String calcTime(Integer secs){
        Integer h =  secs/3600;
        Integer m = (secs - (3600*h)) / 60;
        Integer s = secs - (3600*h) - (60*m);
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public static List<String> getNumbers(Integer inicio, Integer fim){
        List<String> nums = new ArrayList<>();

        for(int i=inicio; i<=fim; i++){
            nums.add((i<10 ? "0"+i : i+""));
        }

        return nums;
    }
}
