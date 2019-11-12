package org.androidtown.myapplication;
import java.util.Random;
import java.util.*;
import android.app.*;
import android.content.Intent;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;



public class GameActivity extends Activity {
    private int arrImage[] =   {0, 0, 0};
    private int arrScore[] =   {0, 0, 0};
    private int arrSResult[] = {0, 0, 0};
    private int arrButton[] = {0, 0, 0, 0};

    private int TitleColor = 0xFF2A2786;
    private int _counter = 0;
    private boolean _isRun = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        findViewById(R.id.Button01).setOnClickListener(MyButtonListener);
        findViewById(R.id.Button02).setOnClickListener(MyButtonListener);
        findViewById(R.id.Button03).setOnClickListener(MyButtonListener);

        InitGame();
        mHandler.sendEmptyMessageDelayed(0, 300);
    }

    public void InitGame() {
        arrImage[0] = R.drawable.img_1;       // 가위
        arrImage[1] = R.drawable.img_2;       // 바위
        arrImage[2] = R.drawable.img_3;       // 보

        arrSResult[0] = R.string.strResult0;   // 비김
        arrSResult[1] = R.string.strResult1;   // 이김
        arrSResult[2] = R.string.strResult2;   // 짐

        arrButton[0] = R.string.strButton2;
        arrButton[1] = R.string.strButton3;
        arrButton[2] = R.string.strButton4;
        arrButton[3] = R.string.strButton5;

        ((TextView) findViewById(R.id.txtResult)).setText("");
    }

    Button.OnClickListener MyButtonListener = new Button.OnClickListener() {
        public void onClick(View v) {
            int you = Integer.parseInt(v.getTag().toString());
            if (_isRun == true) {
                int phone = new Random().nextInt(3);
                _isRun = false;
                SetButtons(false);
                PanJung(you, phone);
            } else {
                if (you == 1) {
                    SetButtons(true);
                    _isRun = true;
                    mHandler.sendEmptyMessageDelayed(0, 0);
                }  else {
                    finish();
                    return;
                }
            }
        }
    };

    void SetButtons(boolean flag) {
        if (flag == false) {  // [가위] 버튼 감추기
            ((Button) findViewById(R.id.Button01)).setVisibility(View.INVISIBLE);
            ((Button) findViewById(R.id.Button02)).setText(arrButton[2]);
            ((Button) findViewById(R.id.Button03)).setText(arrButton[3]);
            TitleColor = 0xFF2A2786;
            ((TextView) findViewById(R.id.txtTitle)).setTextColor(TitleColor);

        } else {     // [가위] 버튼 보이기
            ((Button) findViewById(R.id.Button01)).setVisibility(View.VISIBLE);
            ((Button) findViewById(R.id.Button02)).setText(arrButton[0]);
            ((Button) findViewById(R.id.Button03)).setText(arrButton[1]);
            ((TextView) findViewById(R.id.txtResult)).setText("");
        }
    }

    void PanJung(int you, int phone) {
        int result;

        if (you == phone) result = 0;
        else if (you - phone == 1 || you - phone == -2) result = 1;
        else result = 2;

        if (result != 0) arrScore[result]++;
        arrScore[0]++;

        Display(you, phone, result);
    }

    void Display(int you, int phone, int result) {
        MakeReverse(arrImage[you]);

        ((ImageView) findViewById(R.id.ImageView02)).setImageResource(arrImage[phone]);


        ((TextView) findViewById(R.id.txtResult)).setText(arrSResult[result]);


        ((TextView) findViewById(R.id.scrYou)).setText(" " + arrScore[1]);
        ((TextView) findViewById(R.id.scrPhone)).setText(" " + arrScore[2]);
        ((TextView) findViewById(R.id.scrTot)).setText(" " + arrScore[0]);
    }

    void MakeReverse(int id) {
        Bitmap bitmap, reverse;
        Matrix matrix = new Matrix();
        matrix.postScale(-1, 1);

        bitmap = BitmapFactory.decodeResource(getResources(), id);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        reverse = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, false);
        ((ImageView) findViewById(R.id.ImageView01)).setImageBitmap(reverse);
    }

    public void DrawBlink() {
        Random rnd = new Random();
        int n1 = _counter % 3;
        int n2 = rnd.nextInt(3);

        _counter++;
        if (_counter % 2 == 1) {
            TitleColor = 0xFF2A2786 - TitleColor;
            ((TextView) findViewById(R.id.txtTitle)).setTextColor(TitleColor);
        }
        MakeReverse(arrImage[n1]);
        ((ImageView) findViewById(R.id.ImageView02)).setImageResource(arrImage[n2]);
    }


    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (_isRun == false) return;
            DrawBlink();
            mHandler.sendEmptyMessageDelayed(0, 300);
        }
    };

    public void go_main(View v) {
        Intent it = new Intent(this,MainActivity.class);
        startActivity(it);
        finish();
    }
}