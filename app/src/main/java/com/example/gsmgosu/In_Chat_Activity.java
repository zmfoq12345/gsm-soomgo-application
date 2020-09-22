package com.example.gsmgosu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class In_Chat_Activity extends AppCompatActivity {

    private String CHAT_NAME;
    private String USER_NAME;

    private ListView chat_view;
    private EditText chat_edit;
    private ImageButton chat_send, back;
    public static TextView chat_title;
    private MessageAdapter messageAdapter;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);

        // 위젯 ID 참조
        chat_view = (ListView) findViewById(R.id.messages_view);
        chat_edit = (EditText) findViewById(R.id.editText);
        chat_send = (ImageButton) findViewById(R.id.chat_sent);
        chat_title = (TextView)findViewById(R.id.chat_title);

        back = (ImageButton)findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        messageAdapter = new MessageAdapter(this);
        chat_view.setAdapter(messageAdapter);
        // 로그인 화면에서 받아온 채팅방 이름, 유저 이름 저장
        Intent intent = getIntent();
        CHAT_NAME = intent.getStringExtra("chatName");
        USER_NAME = intent.getStringExtra("userName");

        chat_title.setText(CHAT_NAME);
        // 채팅 방 입장
        openChat(CHAT_NAME);

        // 메시지 전송 버튼에 대한 클릭 리스너 지정
        chat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chat_edit.getText().toString().equals(""))
                    return;

                ChatDTO chat = new ChatDTO(USER_NAME, chat_edit.getText().toString()); //ChatDTO를 이용하여 데이터를 묶는다.
                databaseReference.child("chat").child(CHAT_NAME).push().setValue(chat); // 데이터 푸쉬
                chat_edit.setText(""); //입력창 초기화

            }
        });
    }

    private void addMessage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter) {
        ChatDTO chatDTO = dataSnapshot.getValue(ChatDTO.class);
        if (chatDTO.getUserName().equals(chat_title.getText().toString())){
            adapter = new ArrayAdapter(this, R.layout.chat_theirmessage, R.id.message_body);
        }
        adapter.add(chatDTO.getUserName() + " : " + chatDTO.getMessage());
    }

    private void removeMessage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter) {
        ChatDTO chatDTO = dataSnapshot.getValue(ChatDTO.class);
        adapter.remove(chatDTO.getUserName() + " : " + chatDTO.getMessage());
    }

    private void openChat(String chatName) {
        // 리스트 어댑터 생성 및 세팅
        Context ct = this;

//        final ArrayAdapter<String> adapter = new ArrayAdapter(ct, R.layout.chat_mymessage, R.id.message_body);

        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 등..리스너 관리
        databaseReference.child("chat").child(chatName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatDTO chatDTO = dataSnapshot.getValue(ChatDTO.class);
                messageAdapter.add(chatDTO);
                chat_view.setSelection(chat_view.getCount() - 1);
                Log.e("LOG", "s:" + s);
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});
    }
}