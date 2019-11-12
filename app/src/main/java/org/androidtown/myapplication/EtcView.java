package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class EtcView extends AppCompatActivity {
    Button btn_go_main5;
    LinearLayout etc1,etc2,etc3;
    TextView etc_name1,etc_name2,etc_name3;
    TextView etc_msg1,etc_msg2,etc_msg3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etc_view);
        btn_go_main5= findViewById(R.id.btn_main6);
        etc1 = findViewById(R.id.etc1);
        etc2 = findViewById(R.id.etc2);
        etc3 = findViewById(R.id.etc3);
        etc_msg1 = findViewById(R.id.etc_msg1);
        etc_msg2 = findViewById(R.id.etc_msg2);
        etc_msg3 = findViewById(R.id.etc_msg3);
        etc_name3 = findViewById(R.id.etc_name3);
        etc_name2 = findViewById(R.id.etc_name2);
        etc_name1 = findViewById(R.id.etc_name1);


        etc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(getApplicationContext(),DetailEtc.class);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.java_img);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] b = baos.toByteArray();
                it.putExtra("img",b);
                it.putExtra("name",etc_name1.getText().toString());
                it.putExtra("msg",etc_msg1.getText().toString());
                startActivity(it);
            }
        });
        etc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),DetailEtc.class);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android_img);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] b = baos.toByteArray();
                it.putExtra("img",b);
                it.putExtra("name",etc_name2.getText().toString());
                it.putExtra("msg",etc_msg2.getText().toString());
                startActivity(it);
            }
        });
        etc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),DetailEtc.class);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.google_img);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] b = baos.toByteArray();
                it.putExtra("img",b);
                it.putExtra("name",etc_name3.getText().toString());
                it.putExtra("msg",etc_msg3.getText().toString());
                startActivity(it);
            }
        });

        btn_go_main5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.translate_left, R.anim.translate_right);
                finish();
            }
        });
    }


}
