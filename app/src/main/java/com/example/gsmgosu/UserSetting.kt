package com.example.gsmgosu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_setting.*

class UserSetting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_setting)

        // 뒤로가기
        myPage_Back.setOnClickListener { this.finish() }

        // 로그아웃
        myPage_LogOut.setOnClickListener {
            //파이어베이스 로그아웃 하는 코드
        }

        // 내정보
        myPage_MyProfile.setOnClickListener { startActivity(Intent(this, MyProfile::class.java)) }

        myPage_DeleteUser.setOnClickListener {// 회원 탈퇴하는 코드

        }
    }
}