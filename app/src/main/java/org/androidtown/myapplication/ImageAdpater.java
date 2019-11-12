package org.androidtown.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdpater extends BaseAdapter {
    //   반드시 정의해야하는 메소드 4개!
    Context context;        // 이거 있어야 메인뷰에 해당되는게 보여진다.
    int img[] ={ R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,
            R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8,
            R.drawable.image9,R.drawable.image10,R.drawable.image11,R.drawable.image16
            ,R.drawable.image17,R.drawable.image18,R.drawable.image19,R.drawable.image20
            ,R.drawable.image21,R.drawable.image22,R.drawable.image23,R.drawable.image24,
            R.drawable.image25
    };
    public ImageAdpater(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return img.length;  // 몇개보여줄지
    }

    @Override
    // 무엇이든 리턴해주는거 내가 선택한 순서에 해당되는거를 리턴해라
    public Object getItem(int position) {
        return null;
    }
    //
    @Override
    public long getItemId(int position) {
        return 0;
    }
    //  보여지는 것을 구현해 주는 부분 매게변수
    //  필요한것을 리턴해줘야한다..
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv;
        if(convertView==null) {
            iv = new ImageView(context);    // 뷰들을 보여줄때 어디에있는건지 알기 위해 context를 사용한다.
            iv.setScaleType(ImageView.ScaleType.FIT_XY);    // 이미지뷰의 이미지를 딱 맞춰서 설정해준다.
            iv.setLayoutParams(new GridView.LayoutParams(400,400));   //이미지의 가로,세로 크기를 지정해준다.
            iv.setPadding(20,20,20,20); // 패딩값도 설정해줄수 있다 이미지의 패딩값!
        }
        else {
            iv =(ImageView)convertView;

        }
        iv.setImageResource(img[position]); // 몇번째일때 몇번째의 이미지를 보여줄지 알려주기 위해서 인덱스 값으로 position이 들어가야한다.

        return iv; // 만들어진 iv를 리턴하면서 만들어진 iv를 보여준다!
    }
}
