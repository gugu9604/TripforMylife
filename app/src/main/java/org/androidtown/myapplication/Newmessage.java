package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Newmessage extends Activity{
    Context context;
    static ArrayList<Newmessage> newmessages  = new ArrayList<>();
    Button send_btn,btn_main;
    EditText edit_txt_content;
    EditText edit_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmessage);
        send_btn = findViewById(R.id.send_btn);
        edit_txt_content = findViewById(R.id.edit_txt_content);
        edit_title = findViewById(R.id.edit_title);
        btn_main = findViewById(R.id.btn_main);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"메세지 내용이 등록되었습니다",Toast.LENGTH_SHORT).show();
                MainActivity.mymasseges1.add(new Mymassege(edit_title.getText().toString(),edit_txt_content.getText().toString()));
                MainActivity.myListViewAdapter.notifyDataSetChanged();
            }
        });

    }

    public void go_main(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(R.anim.translate_left, R.anim.translate_right);
        finish();
    }
}
