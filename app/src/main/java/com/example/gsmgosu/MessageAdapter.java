package com.example.gsmgosu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MessageAdapter extends BaseAdapter {

    List<ChatDTO> messages = new ArrayList<ChatDTO>();
    Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void add(ChatDTO message) {
        this.messages.add(message);
        notifyDataSetChanged();
    }

//    public void add(Message message) {
//        this.messages.add(message);
//        notifyDataSetChanged();
//    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ChatDTO message = messages.get(i);

        if (message.getUserName().equals(In_Chat_Activity.chat_title.getText().toString())) {
            convertView = messageInflater.inflate(R.layout.chat_mymessage, null);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getMessage());
        } else {
            convertView = messageInflater.inflate(R.layout.chat_theirmessage, null);
            holder.avatar = (View) convertView.findViewById(R.id.avatar);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);

            holder.name.setText(message.getUserName());
            holder.messageBody.setText(message.getMessage());
            GradientDrawable drawable = (GradientDrawable) holder.avatar.getBackground();
        }

        return convertView;
    }

}

class MessageViewHolder {
    public View avatar;
    public TextView name;
    public TextView messageBody;
}