package com.example.gsmgosu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_background);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 2000);
    }

    private class splashhandler implements Runnable {
        public void run() {
            // 자동 로그인 기능 (SharedPreferences)
//            SharedPreferences userLogin = getSharedPreferences("login_data",MODE_PRIVATE);
//            String email = userLogin.getString("email","");
//            String pw = userLogin.getString("pw","");
//            if (!email.equals("") && !pw.equals("")){
//                // 로그인 파일 따로 구현 후 여기에서 사용
//            }else{
                  // 회원가입으로 이동
//            }
            startActivity(new Intent(getApplication(), ChatActivity.class));
//            startActivity(new Intent(getApplication(), MainActivity.class));
            SplashActivity.this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }

}