package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MyAlbum extends Activity {


    GridView gridView;
    Button btn_main7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_album);

        gridView = findViewById(R.id.gridview);
        ImageAdpater adapter = new ImageAdpater(this);  // 만들어준 객체 생성
        gridView.setAdapter(adapter);   // gridView에 만들어준것을 넣어준다!

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),position+"번째 내 추억.",Toast.LENGTH_SHORT).show();
            }
        });  // 여러개중에 하나 클릭할때 사용!

        btn_main7 = findViewById(R.id.btn_main7);
        btn_main7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.translate_left, R.anim.translate_right);
                finish();
            }
        });
    }
}