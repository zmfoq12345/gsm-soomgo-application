package com.example.gsmgosu

import android.content.Context
import android.preference.PreferenceManager

class SharedPreferenceManager(context: Context){

    private val pref = PreferenceManager.getDefaultSharedPreferences(context)

    fun getData(key: String): String? = pref.getString(key, "")

    fun setData(key : String, value : String) = pref.edit().putString(key, value).commit()
}