package com.example.gsmgosu


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_mainicon.view.*

class RecyclerViewAdapter(val dataList:ArrayList<InitData>):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    //아이템의 갯수
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_mainicon, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(dataList[position])
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data : InitData){
            //이미지표시
            Glide.with(itemView.context).load(data.imageResource)
                .into(itemView.recyclerView_ImageView)
            itemView.recyclerView_TextView.text = data.name

            //각각의 아이템 클릭시
            itemView.setOnClickListener {
                //여기서 토스터를 어떻게?
                Toast.makeText(itemView.context, "아이템 '${data.name}'를 클릭했습니다.", Toast.LENGTH_LONG).show()
            }
        }

    }

}

