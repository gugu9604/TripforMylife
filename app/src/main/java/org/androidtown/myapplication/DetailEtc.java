package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailEtc extends Activity {
    ImageView etc_de_img;
    TextView etc_de_name,etc_de_msg;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_etc);
        etc_de_img = findViewById(R.id.etc_de_img);
        etc_de_name = findViewById(R.id.etc_de_name);
        etc_de_msg = findViewById(R.id.etc_de_msg);

        Intent it = getIntent();
        Resources res = getResources();

        Bundle extras = getIntent().getExtras();
        byte[] b = extras.getByteArray("img");
        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
        etc_de_img.setImageBitmap(bmp);

        String name = it.getExtras().getString("name");
        etc_de_name.setText(name);

        String msg = it.getExtras().getString("msg");
        etc_de_msg.setText(msg);
    }
}
