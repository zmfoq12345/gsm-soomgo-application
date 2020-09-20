package com.example.gsmgosu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView

public class FieldAdapter : RecyclerView.Adapter<FieldViewHolder>() {

    private var modelList = ArrayList<FieldModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
        return FieldViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.choose_item,parent,false))
    }


    override fun getItemCount(): Int {
        return this.modelList.size
    }

    override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
        holder.bind(this.modelList[position])
    }

    // 외부에서 데이터 넘기기
    fun submitList(modelList: ArrayList<FieldModel>){
        this.modelList = modelList
    }


}