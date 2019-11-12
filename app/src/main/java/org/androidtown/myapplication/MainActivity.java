package org.androidtown.myapplication;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback{


    private GoogleMap mGoogleMap = null;
    private Marker currentMarker = null;

    private static final String TAG = "googlemap_example";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int UPDATE_INTERVAL_MS = 1000;  // 1초
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500; // 0.5초

    private static final int PERMISSIONS_REQUEST_CODE = 100;
    boolean needRequest = false;

    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};


    Location mCurrentLocatiion;
    LatLng currentPosition;

    static ArrayList<Mymassege> mymasseges1 = new ArrayList<>();
    static MyListViewAdapter myListViewAdapter;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private Location location;

    private View mLayout;  // Snackbar 사용하기 위해서는 View가 필요합니다.

    Button btn_map_call;
    Button msg_btn2;
    Button btn_map_ve;
    Button btn_map_stlas;
    Button btn_my_home;
    Button btn_map_london;
    Button btn_map_paris;
    Button btn_map_swis;
    Button btn_map_ltaila;
    Button btn_hotel;
    Button btn_album;
    Button btn_dong;

    public void go_Air(View v) {
        startActivity(new Intent(MainActivity.this, AlrPlaneView.class));
        overridePendingTransition(R.anim.translate_left, R.anim.translate_right);
        finish();
    }

    public void go_instagram(View v) {
        startActivity(new Intent(MainActivity.this, InstaView.class));
        overridePendingTransition(R.anim.translate_left, R.anim.translate_right);
        finish();
    }
    public void go_etc(View V) {
        startActivity(new Intent(MainActivity.this, EtcView.class));
        overridePendingTransition(R.anim.translate_left, R.anim.translate_right);
        finish();
    }

    public void go_tip(View v) {
        startActivity(new Intent(MainActivity.this, Tip.class));
        overridePendingTransition(R.anim.translate_left, R.anim.translate_right);
        finish();
    }
    public void go_hotelList(View v) {
        startActivity(new Intent(MainActivity.this, HotelList.class));
        overridePendingTransition(R.anim.translate_left, R.anim.translate_right);
        finish();

    }
    public void go_game(View v) {
        startActivity(new Intent(MainActivity.this, GameActivity.class));
        overridePendingTransition(R.anim.translate_left, R.anim.translate_right);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        msg_btn2 = findViewById(R.id.msg_btn2);

        btn_my_home = findViewById(R.id.btn_my_home);
        btn_map_london = findViewById(R.id.btn_map_London);
        btn_map_paris = findViewById(R.id.btn_map_paris);
        btn_map_swis = findViewById(R.id.btn_map_swis);
        btn_map_ltaila = findViewById(R.id.btn_map_italia);
        btn_map_ve = findViewById(R.id.btn_map_ve);
        btn_map_stlas = findViewById(R.id.btn_map_stlas);
        btn_hotel = findViewById(R.id.btn_hotel);
        btn_album = findViewById(R.id.btn_album);
        btn_dong = findViewById(R.id.btn_dong);

        btn_dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMapReady9(mGoogleMap);
            }
        });
        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyAlbum.class));
                overridePendingTransition(R.anim.translate_left, R.anim.translate_right);


            }
        });

        btn_map_stlas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMapReady8(mGoogleMap);
            }
        });

        btn_map_ve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMapReady7(mGoogleMap);
            }
        });


        btn_map_ltaila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMapReady6(mGoogleMap);
            }
        });

        btn_map_swis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMapReady5(mGoogleMap);
            }
        });

        btn_map_paris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMapReady4(mGoogleMap);
            }
        });

        btn_map_london.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMapReady3(mGoogleMap);
            }
        });

        btn_my_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMapReady2(mGoogleMap);

            }
        });


        myListViewAdapter = new MyListViewAdapter(this,mymasseges1,R.layout.message);
        ListView listView = findViewById(R.id.listView); // 메인엑티비티에 있는 리스트뷰를 가져온다.
        listView.setAdapter(myListViewAdapter);



        msg_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),Newmessage.class);
                startActivity(it);
            }
        });


        mLayout = findViewById(R.id.layout_main);


        Log.d(TAG, "onCreate");

        btn_map_call = findViewById(R.id.btn_map_call);


        btn_map_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 01082585218"));
                startActivity(it);
            }
        });


        locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL_MS)
                .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);


        LocationSettingsRequest.Builder builder =
                new LocationSettingsRequest.Builder();

        builder.addLocationRequest(locationRequest);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }




    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            List<Location> locationList = locationResult.getLocations();

            if (locationList.size() > 0) {
                location = locationList.get(locationList.size() - 1);
                //location = locationList.get(0);

                currentPosition
                        = new LatLng(location.getLatitude(), location.getLongitude());


                String markerTitle = getCurrentAddress(currentPosition);
                String markerSnippet = "나의 위치:"+"위도:" + String.valueOf(location.getLatitude())
                        + " 경도:" + String.valueOf(location.getLongitude());

                Log.d(TAG, "onLocationResult : " + markerSnippet);

                //현재 위치에 마커 생성하고 이동
                setCurrentLocation(location, markerTitle, markerSnippet);

                mCurrentLocatiion = location;
            }


        }

    };



    private void startLocationUpdates() {

        if (!checkLocationServicesStatus()) {

            Log.d(TAG, "startLocationUpdates : call showDialogForLocationServiceSetting");
            showDialogForLocationServiceSetting();
        }else {

            int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);



            if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
                    hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED   ) {

                Log.d(TAG, "startLocationUpdates : 퍼미션 안가지고 있음");
                return;
            }


            Log.d(TAG, "startLocationUpdates : call mFusedLocationClient.requestLocationUpdates");

            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

            if (checkPermission())
                mGoogleMap.setMyLocationEnabled(true);

        }

    }

    public void onMapReady9(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        setDefaultLocation9();
        showAllMart2();
    }

    public void onMapReady8(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        setDefaultLocation8();
        showAllMart();
    }
    public void onMapReady7(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        setDefaultLocation7();
        showAllMart();
    }
    public void onMapReady6(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        setDefaultLocation6();
        showAllMart();
    }
    public void onMapReady5(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        setDefaultLocation5();
        showAllMart();
    }
    public void onMapReady4(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        setDefaultLocation4();
        showAllMart();
    }
    public void onMapReady3(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        setDefaultLocation3();
        showAllMart();
    }
    public void onMapReady2(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        setDefaultLocation2();
        showAllMart2();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.d(TAG, "onMapReady :");
        mGoogleMap = googleMap;
        setDefaultLocation();
        showAllMart();

        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);



        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED   ) {


            startLocationUpdates();


        }else {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

                Snackbar.make(mLayout, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.",
                        Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        ActivityCompat.requestPermissions( MainActivity.this, REQUIRED_PERMISSIONS,
                                PERMISSIONS_REQUEST_CODE);
                    }
                }).show();
            } else {

                ActivityCompat.requestPermissions( this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                Log.d( TAG, "onMapClick :");
            }
        });
    }

    private  void showAllMart2() {
        Marker h1,h2,h3,h4,h5,h6,h7,h8,h9,h10;
        LatLng h1Point = new LatLng(37.6186627,126.9176242);
        LatLng h2Point = new LatLng(37.606128,126.9332513);
        LatLng h3Point = new LatLng(37.554418,126.9204566);
        LatLng h4Point = new LatLng(37.6096413,126.9266158);
        LatLng h5Point = new LatLng(37.5504939,126.920444);
        LatLng h6Point = new LatLng(37.0294415,126.1581607);
        LatLng h7Point = new LatLng(37.6209021,126.9170073);
        LatLng h8Point = new LatLng(37.585421,126.9849934);
        LatLng h9Point = new LatLng(37.6231398,126.9293244);
        LatLng h10Point = new LatLng(37.5004101,126.8655919);
        Bitmap s1 =  BitmapFactory.decodeResource(getResources(), R.drawable.h_img1);
        s1 = Bitmap.createScaledBitmap(s1,200,200, true);
        h1 = mGoogleMap.addMarker(new MarkerOptions()
                .position(h1Point)
                .title("수정불막창")
                .snippet("우리동네 최고의 맛집 막창하면 여기!")
                .icon(BitmapDescriptorFactory.fromBitmap(s1))
        );
        h1.showInfoWindow();
        Bitmap s2 =  BitmapFactory.decodeResource(getResources(), R.drawable.h_img2);
        s2 = Bitmap.createScaledBitmap(s2,200,200, true);
        h2 = mGoogleMap.addMarker(new MarkerOptions()
                .position(h2Point)
                .title("우리집")
                .snippet("아늑한 우리집이 최고!")
                .icon(BitmapDescriptorFactory.fromBitmap(s2))
        );
        h2.showInfoWindow();
        Bitmap s3 =  BitmapFactory.decodeResource(getResources(), R.drawable.h_img3);
        s3 = Bitmap.createScaledBitmap(s3,200,200, true);
        h3 = mGoogleMap.addMarker(new MarkerOptions()
                .position(h3Point)
                .title("홍대 중앙포차")
                .snippet("친구형이 하는 최고의 술집!")
                .icon(BitmapDescriptorFactory.fromBitmap(s3))
        );
        h3.showInfoWindow();
        Bitmap s4 =  BitmapFactory.decodeResource(getResources(), R.drawable.h_img4);
        s4 = Bitmap.createScaledBitmap(s4,200,200, true);
        h4 = mGoogleMap.addMarker(new MarkerOptions()
                .position(h4Point)
                .title("불광CGV")
                .snippet("내가 맨날 영화보는곳!")
                .icon(BitmapDescriptorFactory.fromBitmap(s4))
        );
        h4.showInfoWindow();
        Bitmap s5 =  BitmapFactory.decodeResource(getResources(), R.drawable.h_img5);
        s5 = Bitmap.createScaledBitmap(s5,200,200, true);
        h5 = mGoogleMap.addMarker(new MarkerOptions()
                .position(h5Point)
                .title("피닉스 피자")
                .snippet("스윙스가 하는 피자맛집")
                .icon(BitmapDescriptorFactory.fromBitmap(s5))
        );
        h5.showInfoWindow();
        Bitmap s6 =  BitmapFactory.decodeResource(getResources(), R.drawable.h_img6);
        s6 = Bitmap.createScaledBitmap(s6,200,200, true);
        h6 = mGoogleMap.addMarker(new MarkerOptions()
                .position(h6Point)
                .title("돈까 ")
                .snippet("백종원이 인정한 돈가스 맛집!")
                .icon(BitmapDescriptorFactory.fromBitmap(s6))
        );
        h6.showInfoWindow();
        Bitmap s7 =  BitmapFactory.decodeResource(getResources(), R.drawable.h_img7);
        s7 = Bitmap.createScaledBitmap(s7,200,200, true);
        h7 = mGoogleMap.addMarker(new MarkerOptions()
                .position(h7Point)
                .title("청구성심병원")
                .snippet("내가 태어난 병원! " +
                          "1996.04.02")
                .icon(BitmapDescriptorFactory.fromBitmap(s7))
        );
        h7.showInfoWindow();
        Bitmap s8 =  BitmapFactory.decodeResource(getResources(), R.drawable.h_img8);
        s8 = Bitmap.createScaledBitmap(s8,200,200, true);
        h8 = mGoogleMap.addMarker(new MarkerOptions()
                .position(h8Point)
                .title("인창고등학교")
                .snippet("나의 고등학교!"
                       + "2014 졸업")
                .icon(BitmapDescriptorFactory.fromBitmap(s8))
        );
        h8.showInfoWindow();
        Bitmap s9 =  BitmapFactory.decodeResource(getResources(), R.drawable.h_img9);
        s9 = Bitmap.createScaledBitmap(s9,200,200, true);
        h9 = mGoogleMap.addMarker(new MarkerOptions()
                .position(h9Point)
                .title("불광중학교")
                .snippet("나의 중학교!" +
                        "2011 졸업")
                .icon(BitmapDescriptorFactory.fromBitmap(s9))
        );
        h9.showInfoWindow();
        Bitmap s10 =  BitmapFactory.decodeResource(getResources(), R.drawable.h_img10);
        s10 = Bitmap.createScaledBitmap(s10,200,200, true);
        h10 = mGoogleMap.addMarker(new MarkerOptions()
                .position(h10Point)
                .title("동양미래대학교")
                .snippet("나의 대학교!" +
                        "2020 졸업예정")
                .icon(BitmapDescriptorFactory.fromBitmap(s10))
        );
        h10.showInfoWindow();

    }

    private void showAllMart() {


        Marker m1, m2, m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15,m16,m17,m18,m19,m20,m21;
        LatLng m1Point = new LatLng(48.8879137,2.312064);
        LatLng m2Point = new LatLng(48.8737917,2.2950275);
        LatLng m3Point = new LatLng(48.8606111,2.3354553);
        LatLng m4Point = new LatLng(48.8583701,2.2922926);
        LatLng m5Point = new LatLng(48.8671769,2.7816378);
        LatLng m6Point = new LatLng(51.5194133,-0.1291453);
        LatLng m7Point = new LatLng(51.5407144,-0.1588799);
        LatLng m8Point = new LatLng(51.503324,-0.1217317);
        LatLng m9Point = new LatLng(51.5054564,-0.0775452);
        LatLng m10Point = new LatLng(51.5115961,-0.1221759);
        LatLng m11Point = new LatLng(48.8048649,2.1181667);
        LatLng m12Point = new LatLng(48.5813344,7.7514105);
        LatLng m13Point = new LatLng(48.583151,7.7430416);
        LatLng m14Point = new LatLng(46.6836976,7.8515493);
        LatLng m15Point = new LatLng(46.5531194,7.7666637);
        LatLng m16Point = new LatLng(46.5367821,7.9450859);
        LatLng m17Point = new LatLng(46.5604098,7.893273);
        LatLng m18Point = new LatLng(45.4405093,10.9872726);
        LatLng m19Point = new LatLng(43.773145,11.2537715);
        LatLng m20Point = new LatLng(41.8902102,12.4900422);
        LatLng m21Point = new LatLng(41.9021667,12.451748);

        Bitmap s1 =  BitmapFactory.decodeResource(getResources(), R.drawable.paris2);
        s1 = Bitmap.createScaledBitmap(s1,200,200, true);
        m1 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m1Point)
                .title("몽마르뜨 언덕")
                .snippet("밤에 가면 위험해요")
                .icon(BitmapDescriptorFactory.fromBitmap(s1))
        );
        m1.showInfoWindow();

        Bitmap s2 =  BitmapFactory.decodeResource(getResources(), R.drawable.image30);
        s2 = Bitmap.createScaledBitmap(s2,200,200, true);
        m2 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m2Point)
                .title("개선문")
                .snippet("올라가서 보면 굿")
                .icon(BitmapDescriptorFactory.fromBitmap(s2))
        );
        m2.showInfoWindow();
        Bitmap s3 =  BitmapFactory.decodeResource(getResources(), R.drawable.image18);
        s3 = Bitmap.createScaledBitmap(s3,200,200, true);
        m3 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m3Point)
                .title("루브르박물관")
                .snippet("모나리자를 봅시다")
                .icon(BitmapDescriptorFactory.fromBitmap(s3))
        );
        m3.showInfoWindow();
        Bitmap s4 =  BitmapFactory.decodeResource(getResources(), R.drawable.paris);
        s4 = Bitmap.createScaledBitmap(s4,200,200, true);
        m4 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m4Point)
                .title("에펠탑")
                .snippet("정말 이쁩니다")
                .icon(BitmapDescriptorFactory.fromBitmap(s4))
        );
        m4.showInfoWindow();
        Bitmap s5 =  BitmapFactory.decodeResource(getResources(), R.drawable.image10);
        s5 = Bitmap.createScaledBitmap(s5,200,200, true);
        m5 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m5Point)
                .title("디즈니랜드")
                .snippet("사람이 엄청 많아요")
                .icon(BitmapDescriptorFactory.fromBitmap(s5))
        );
        m5.showInfoWindow();

        Bitmap s6 =  BitmapFactory.decodeResource(getResources(), R.drawable.image19);
        s6 = Bitmap.createScaledBitmap(s6,200,200, true);
        m6 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m6Point)
                .title("대영박물관")
                .snippet("생각보다 별로에요")
                .icon(BitmapDescriptorFactory.fromBitmap(s6))
        );
        m6.showInfoWindow();
        Bitmap s7 =  BitmapFactory.decodeResource(getResources(), R.drawable.image16);
        s7 = Bitmap.createScaledBitmap(s7,200,200, true);
        m7 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m7Point)
                .title("캠던타운")
                .snippet("영국의 홍대")
                .icon(BitmapDescriptorFactory.fromBitmap(s7))
        );
        m7.showInfoWindow();
        Bitmap s8 =  BitmapFactory.decodeResource(getResources(), R.drawable.image11);
        s8 = Bitmap.createScaledBitmap(s8,200,200, true);
        m8 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m8Point)
                .title("런던아이")
                .snippet("야경이 예술")
                .icon(BitmapDescriptorFactory.fromBitmap(s8))
        );
        m8.showInfoWindow();
        Bitmap s9 =  BitmapFactory.decodeResource(getResources(), R.drawable.image32);
        s9 = Bitmap.createScaledBitmap(s9,200,200, true);
        m9 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m9Point)
                .title("타워브릿지")
                .snippet("사진찍기 명소")
                .icon(BitmapDescriptorFactory.fromBitmap(s9))
        );
        m9.showInfoWindow();
        Bitmap s10 =  BitmapFactory.decodeResource(getResources(), R.drawable.image22);
        s10 = Bitmap.createScaledBitmap(s10,200,200, true);
        m10 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m10Point)
                .title("라이온킹극장")
                .snippet("오리지날 뮤지컬")
                .icon(BitmapDescriptorFactory.fromBitmap(s10))
        );
        m10.showInfoWindow();
        Bitmap s11 =  BitmapFactory.decodeResource(getResources(), R.drawable.image31);
        s11 = Bitmap.createScaledBitmap(s11,200,200, true);
        m11 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m11Point)
                .title("베르사유궁전")
                .snippet("엄청나게 넓어요")
                .icon(BitmapDescriptorFactory.fromBitmap(s11))
        );
        m11.showInfoWindow();
        Bitmap s12 =  BitmapFactory.decodeResource(getResources(), R.drawable.image4);
        s12 = Bitmap.createScaledBitmap(s12,200,200, true);
        m12 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m12Point)
                .title("스트라스부르성당")
                .snippet("엄청커요")
                .icon(BitmapDescriptorFactory.fromBitmap(s12))
        );
        m12.showInfoWindow();
        Bitmap s13 =  BitmapFactory.decodeResource(getResources(), R.drawable.image6);
        s13 = Bitmap.createScaledBitmap(s13,200,200, true);
        m13 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m13Point)
                .title("스트라스광장")
                .snippet("야경이 좋습니다")
                .icon(BitmapDescriptorFactory.fromBitmap(s13))
        );
        m13.showInfoWindow();
        Bitmap s14 =  BitmapFactory.decodeResource(getResources(), R.drawable.image9);
        s14 = Bitmap.createScaledBitmap(s14,200,200, true);
        m14 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m14Point)
                .title("인터라켄")
                .snippet("엑티비티가 많아요")
                .icon(BitmapDescriptorFactory.fromBitmap(s14))
        );
        m14.showInfoWindow();
        Bitmap s15 =  BitmapFactory.decodeResource(getResources(), R.drawable.image33);
        s15 = Bitmap.createScaledBitmap(s15,200,200, true);
        m15 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m15Point)
                .title("라우터브루렌")
                .snippet("산장들이 좋아요")
                .icon(BitmapDescriptorFactory.fromBitmap(s15))
        );
        m15.showInfoWindow();
        Bitmap s16 =  BitmapFactory.decodeResource(getResources(), R.drawable.image8);
        s16 = Bitmap.createScaledBitmap(s16,200,200, true);
        m16 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m16Point)
                .title("융프라우")
                .snippet("스위스에서 제일큰산")
                .icon(BitmapDescriptorFactory.fromBitmap(s16))
        );
        m16.showInfoWindow();
        Bitmap s17 =  BitmapFactory.decodeResource(getResources(), R.drawable.image7);
        s17 = Bitmap.createScaledBitmap(s17,200,200, true);
        m17 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m17Point)
                .title("뮈렌")
                .snippet("스위스에서 제일좋은곳")
                .icon(BitmapDescriptorFactory.fromBitmap(s17))
        );
        m17.showInfoWindow();

        Bitmap s18 =  BitmapFactory.decodeResource(getResources(), R.drawable.image2);
        s18 = Bitmap.createScaledBitmap(s18,200,200, true);
        m18 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m18Point)
                .title("카스텔베키오다리")
                .snippet("오래된 다리")
                .icon(BitmapDescriptorFactory.fromBitmap(s18))
        );
        m18.showInfoWindow();
        Bitmap s19 =  BitmapFactory.decodeResource(getResources(), R.drawable.image5);
        s19 = Bitmap.createScaledBitmap(s19,200,200, true);
        m19 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m19Point)
                .title("산마르코 대성당")
                .snippet("500년동안 건설중")
                .icon(BitmapDescriptorFactory.fromBitmap(s19))
        );
        m19.showInfoWindow();
        Bitmap s20 =  BitmapFactory.decodeResource(getResources(), R.drawable.image1);
        s20 = Bitmap.createScaledBitmap(s20,200,200, true);
        m20 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m20Point)
                .title("콜로세움")
                .snippet("밖에서만 보는걸 추천")
                .icon(BitmapDescriptorFactory.fromBitmap(s20))
        );
        m20.showInfoWindow();
        Bitmap s21 =  BitmapFactory.decodeResource(getResources(), R.drawable.image3);
        s21 = Bitmap.createScaledBitmap(s21,200,200, true);
        m21 = mGoogleMap.addMarker(new MarkerOptions()
                .position(m21Point)
                .title("성베드로대성당")
                .snippet("천지창조 있는곳")
                .icon(BitmapDescriptorFactory.fromBitmap(s21))
        );
        m21.showInfoWindow();
    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");

        if (checkPermission()) {

            Log.d(TAG, "onStart : call mFusedLocationClient.requestLocationUpdates");
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);

            if (mGoogleMap!=null)
                mGoogleMap.setMyLocationEnabled(true);

        }


    }


    @Override
    protected void onStop() {

        super.onStop();

        if (mFusedLocationClient != null) {

            Log.d(TAG, "onStop : call stopLocationUpdates");
            mFusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }



    // 제일 중요한 부분!
    public String getCurrentAddress(LatLng latlng) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latlng.latitude,
                    latlng.longitude,
                    1);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }

    }


    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {


        if (currentMarker != null) currentMarker.remove();


        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);


        currentMarker = mGoogleMap.addMarker(markerOptions);

        //CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
        // mGoogleMap.moveCamera(cameraUpdate);



    }
    public void setDefaultLocation9() {
        //디폴트 위치, 동양미래대학교
        LatLng DEFAULT_LOCATION = new LatLng(37.5004101,126.8655919);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION,14));

    }

    public void setDefaultLocation8() {
        //디폴트 위치, 스트라스부르
        LatLng DEFAULT_LOCATION = new LatLng(48.5813344,7.7514105);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION,11));

    }
    public void setDefaultLocation7() {
        //디폴트 위치, 베로나
        LatLng DEFAULT_LOCATION = new LatLng(45.4405093,10.9872726);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION,11));

    }
    public void setDefaultLocation6() {
        //디폴트 위치, 이탈리아
        LatLng DEFAULT_LOCATION = new LatLng(41.8902102,12.4900422);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION,11));

    }

    public void setDefaultLocation5() {
        //디폴트 위치, 스위스
        LatLng DEFAULT_LOCATION = new LatLng(46.5604098,7.893273);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION,11));

    }

    public void setDefaultLocation4() {
        //디폴트 위치, 파리
        LatLng DEFAULT_LOCATION = new LatLng(48.8583701,2.2922926);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION,11));

    }

    public void setDefaultLocation3() {
        //디폴트 위치, 런던
        LatLng DEFAULT_LOCATION = new LatLng(51.503324,-0.1217317);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION,11));

    }
    public void setDefaultLocation2() {


        //디폴트 위치, 연시내역
        LatLng DEFAULT_LOCATION = new LatLng(37.606128,126.9332513);
       // String markerTitle = "위치정보 가져올 수 없음";
       // String markerSnippet = "위치 퍼미션과 GPS 활성 요부 확인하세요";
        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
      //  markerOptions.title(markerTitle);
      //  markerOptions.snippet(markerSnippet);
       // markerOptions.draggable(true);
       // markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = mGoogleMap.addMarker(markerOptions);

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION,14));

    }
    public void setDefaultLocation() {


        //디폴트 위치, 서울
        LatLng DEFAULT_LOCATION = new LatLng(37.5650172,126.849462);
        String markerTitle = "위치정보 가져올 수 없음";
        String markerSnippet = "위치 퍼미션과 GPS 활성 요부 확인하세요";


        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 10);
        mGoogleMap.moveCamera(cameraUpdate);

    }


    private boolean checkPermission() {

        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);



        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED   ) {
            return true;
        }

        return false;

    }



    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {


            boolean check_result = true;


            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if ( check_result ) {

                startLocationUpdates();
            }
            else {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {


                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            finish();
                        }
                    }).show();

                }else {


                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            finish();
                        }
                    }).show();
                }
            }

        }
    }


    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:


                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d(TAG, "onActivityResult : GPS 활성화 되있음");


                        needRequest = true;

                        return;
                    }
                }

                break;
        }
    }


}


