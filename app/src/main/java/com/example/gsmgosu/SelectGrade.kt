package com.example.gsmgosu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_select_grade.*

class SelectGrade : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_grade)


        val adapter = VPAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        tab.setupWithViewPager(viewPager)



    }
}