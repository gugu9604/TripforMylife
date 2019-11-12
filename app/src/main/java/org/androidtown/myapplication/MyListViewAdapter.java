package org.androidtown.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyListViewAdapter extends BaseAdapter {
    static  ArrayList<Mymassege> mymasseges = new ArrayList<>();

    Context context;    // 현재엑티비티를 가리키는 역활!
    int layout;         // 만들어 놓은 xml을 연결한다.
    ArrayList<Mymassege> mymessage;  //메인에서 만들어놨었던 리소를 배열에 넣어준다.
    LayoutInflater inflater;    // 클릭하면 해당되는 부분만 보여준다.
    Button send_btn;
    public MyListViewAdapter(Context context,ArrayList<Mymassege> mymessage,int layout) {
        this.context = context;
        this.layout = layout;
        this.mymessage = mymessage;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mymessage.size();
    }

    @Override
    public Object getItem(int position) {
        return mymessage.get(position).txt_number;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // 보여주는 부분!! 으로 제일중요하다.
        if(convertView == null){    // 객체가 없을때는 만들어준다!
            convertView = inflater.inflate(layout,parent,false);    //true 값이 들어가면 튕김
        }
     //   ImageView iv = convertView.findViewById(R.id.main_img);
       // iv.setImageResource(mymessage.get(position).main_img);

        TextView tv2 = convertView.findViewById(R.id.txt_time);
        tv2.setText(mymessage.get(position).txt_time);
        TextView tv3 = convertView.findViewById(R.id.txt_number);
        tv3.setText(mymessage.get(position).txt_number);
        TextView tv4 = convertView.findViewById(R.id.txt_title);
        tv4.setText(mymessage.get(position).txt_title);

        Button review_remove = convertView.findViewById(R.id.btn_remove);
        review_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.mymasseges1.remove(mymessage.get(position));
                notifyDataSetChanged();
            }
        });
        return convertView;
    }



}
