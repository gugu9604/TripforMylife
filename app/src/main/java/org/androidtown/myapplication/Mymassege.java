package org.androidtown.myapplication;

import java.text.SimpleDateFormat;

public class Mymassege {


    String txt_time;
    String txt_number;
    String txt_title;

    public Mymassege(String txt_title,String txt_number) {

        this.txt_title = txt_title;
        this.txt_number = txt_number;

        long now =System.currentTimeMillis();
        java.util.Date date = new java.util.Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String txt = sdfNow.format(date);
        this.txt_time = txt;

    }
}

