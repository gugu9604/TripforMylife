package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HotelList extends Activity {

    Button btn_main;
    LinearLayout re1,re2,re3,re4,re5,re6,re7,re8,re9,re10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_view);

        btn_main = findViewById(R.id.btn_main);
        re1= findViewById(R.id.re1);
        re2= findViewById(R.id.re2);
        re3= findViewById(R.id.re3);
        re4= findViewById(R.id.re4);
        re5= findViewById(R.id.re5);
        re6= findViewById(R.id.re6);
        re7= findViewById(R.id.re7);
        re8= findViewById(R.id.re8);
        re9= findViewById(R.id.re9);
        re10= findViewById(R.id.re10);

        re1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hotelscombined.co.kr/Hotel/Hotel_Jungfrau_Murren.htm"));
                startActivity(it);

            }
        });

        re2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hotelscombined.co.kr/Hotel/Sportchalet_Murren.htm"));
                startActivity(it);
            }
        });

        re3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hotelscombined.co.kr/Hotel/Terrace_Pantheon_Relais.htm"));
                startActivity(it);
            }
        });

        re4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hotelscombined.co.kr/Hotel/Hotel_Trieste_Verona.htm"));
                startActivity(it);
            }
        });

        re5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hotelscombined.co.kr/Hotel/Park_Plaza_Westminster_Bridge_London.htm"));
                startActivity(it);
            }
        });

        re6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hotelscombined.co.kr/Hotel/London_Marriott_Hotel_County_Hall.htm"));
                startActivity(it);
            }
        });

        re7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hotelscombined.co.kr/Hotel/Marlin_Waterloo.htm"));
                startActivity(it);
            }
        });

        re8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hotelscombined.co.kr/Hotel/Hyatt_Regency_Paris_Etoile.htm"));
                startActivity(it);
            }
        });

        re9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hotelscombined.co.kr/Hotel/Le_Meridien_Etoile.htm"));
                startActivity(it);
            }
        });

        re10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hotelscombined.co.kr/Hotel/Ibis_Strasbourg_Centre_Historique.htm"));
                startActivity(it);
            }
        });


    }

    public void go_main(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(R.anim.translate_left, R.anim.translate_right);
        finish();
    }

}
