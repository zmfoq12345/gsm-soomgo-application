package com.example.gsmgosu

import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

data class InitData(val imageResource : Int, val name: String)
//data class MatchingStatusData(val name: String, val image: Image, val grade: String, val introduce: String)
data class MatchingStatusData(val name: String, val grade: Int, val introduce: String)

class MainActivity : AppCompatActivity() {
    var initDataList = arrayListOf<InitData>(
        InitData(R.drawable.icon_mobile_app, "모바일 앱"),
        InitData(R.drawable.icon_design, "디자이너"),
        InitData(R.drawable.icon_web, "웹"),
        InitData(R.drawable.icon_server, "서버"),
        InitData(R.drawable.icon_cloud, "클라우드"),
        InitData(R.drawable.icon_game_develope, "게임 개발"),
        InitData(R.drawable.icon_hardware, "하드웨어"),
        InitData(R.drawable.icon_etc, "기타")
    )
    var matchingStatusData = arrayListOf<MatchingStatusData>(
        MatchingStatusData("김효선", 1, "안녕하세요"),
        MatchingStatusData("김효선", 1, "안녕하세요"),
        MatchingStatusData("김효선", 1, "안녕하세요")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        val linearLayoutManager = LinearLayoutManager(this)
        val gridLayoutManager = GridLayoutManager(this, 4)

        main_recyclerView.layoutManager = gridLayoutManager
        val adapter = RecyclerViewAdapter(applicationContext, initDataList)

        main_recyclerView.adapter = adapter

        matchingStatus_RecyclerView.layoutManager = linearLayoutManager
        val matchingAdapter = MatchingStatusAdapter(applicationContext, matchingStatusData)
        matchingStatus_RecyclerView.adapter = matchingAdapter


    }
}