package com.example.gsmgosu

import android.graphics.Typeface
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.choose_item.view.*

class FieldViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    val TAG: String = "로그"

    private val usernameTextView = itemView.filed_txt
    private val profileImageView = itemView.profile_img

    // 데이터와 뷰를 묶는다.
    fun bind(model: FieldModel) {
        Log.d(TAG, "MyViewHolder - bind() called")

        // 텍스트뷰 와 실제 텍스트 데이터를 묶는다.
        usernameTextView.text = model.name
        usernameTextView.typeface =  Typeface.DEFAULT_BOLD
        Glide
            .with(App.instance)
            .load(model.profileImage)
//            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(profileImageView)
    }
}