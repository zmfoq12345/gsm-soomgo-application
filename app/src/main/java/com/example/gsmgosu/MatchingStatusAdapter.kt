package com.example.gsmgosu


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_mainicon.view.*
import kotlinx.android.synthetic.main.layout_matchingstatus.view.*

class MatchingStatusAdapter(val context:Context, val dataList:ArrayList<MatchingStatusData>):RecyclerView.Adapter<MatchingStatusAdapter.ViewHolder>() {


    //아이템의 갯수
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchingStatusAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_matchingstatus, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: MatchingStatusAdapter.ViewHolder, position: Int) {
        holder.bindItems(dataList[position])
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data : MatchingStatusData){
            //이미지표시

//            Glide.with(itemView.context).load(data.image)
//                .into(itemView.matchingStatus_UserImage)

            itemView.matchingStatus_UserInfo.text = "${data.grade}학년 ${data.name}"
            itemView.matchingStatus_UserIntroduce.text = data.introduce

            //각각의 아이템 클릭시

            itemView.setOnClickListener {
                //여기서 토스터를 어떻게?

                //Toast.makeText(itemView.context, "아이템 '${data.name}'를 클릭했습니다.", Toast.LENGTH_LONG).show()
                //val intent = Intent(itemView.context,SelectGrade::class.java)
                //intent.putExtra("field", data.name)
                //itemView.context.startActivity(intent)

            }
        }
    }


}

