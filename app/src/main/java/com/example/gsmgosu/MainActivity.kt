package com.example.gsmgosu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var modelList = ArrayList<FieldModel>()

    private lateinit var recyclerAdapter: FieldAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.modelList.add(FieldModel(name = "IOS", profileImage = R.drawable.chooseios))

        recyclerAdapter = FieldAdapter()

        recyclerAdapter.submitList(this.modelList)

        // 리사이클러뷰 설정
        dataList.apply {

            // 리사이클러뷰 방향 등 설정
            layoutManager = GridLayoutManager(this@MainActivity,2, GridLayoutManager.VERTICAL, false)

            // 어답터 장착
            adapter = recyclerAdapter
        }
    }
}