package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class Tip extends Activity {
    public final static String VIDEO_URL = "https://scontent.cdninstagram.com/vp/cc35620851c86bd58a5b2e7a69ab1ac8/5D08847C/t50.2886-16/44787451_699169367105137_4231009774142892464_n.mp4?_nc_ht=scontent.cdninstagram.com";
    public final static int URL = 1;
    VideoView videoView;
    Button btnStart, btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tipview);
        // 영상을 출력하기 위한 비디오뷰
        // SurfaceView 를 상속받아 만든클래스이다
        // 웬만하면 VideoView는 그때 그때 생성해서 추가 후 사용
        // 화면 전환시 여러 UI가 있을때 화면에 제일 먼저 그려져서 보기에 좋지 않을 때가 있다.
        // 예제에서 xml 에 추가해서 해보았다.
        videoView = (VideoView) findViewById(R.id.videoView2);

        Button button1 = (Button) findViewById(R.id.btnstart);
        Button button2 = (Button) findViewById(R.id.btnstop);
        videoView = (VideoView) findViewById(R.id.videoView2);

        int type = URL;
        switch (type) {
            case URL:
                // 동영상의 경로가 웹에 있다면 아래와 같이 설정
                videoView.setVideoURI(Uri.parse(VIDEO_URL));
                break;


        }

        // 미디어컨트롤러 추가하는부분
        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller);

        // 준비하는 과정을 미리함
        videoView.requestFocus();

        // 동영상이 재생준비가 완료되엇을떄를 알수있는 리스너 (실제 웹에서 영상을 다운받아 출력할때 많이 사용됨)
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            // 동영상 재생준비가 완료된후 호출되는 메서드
            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),
                        "동영상이 준비되었습니다.\n'재생' 버튼을 누르세요.", Toast.LENGTH_LONG)
                        .show();
            }
        });

        // 동영상 재생이 완료된걸 알수있는 리스너
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            // 동영상 재생이 완료된후 호출되는 메서드
            public void onCompletion(MediaPlayer player) {
                Toast.makeText(getApplicationContext(), "동영상 재생이 완료되었습니다.",
                        Toast.LENGTH_LONG).show();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                playVideo();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                stopVideo();
            }
        });
    }

    private void playVideo() {
        // 비디오를 처음부터 재생할땐 0
        videoView.seekTo(0);
        // 비디오 재생 시작
        videoView.start();
    }

    private void stopVideo() {
        // 비디오 재생 잠시멈춤
        videoView.pause();
        // 비디오 재생 완전 멈춤
        videoView.stopPlayback();

    }
    public void go_main(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(R.anim.translate_left, R.anim.translate_right);
        finish();
    }
}


