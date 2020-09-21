package com.example.gsmgosu;

public class Message {
    private String text;
    private MemberData memberData;
    private boolean belongsToCurrentUser;

    public Message(String text, com.example.gsmgosu.MemberData data, boolean belongsToCurrentUser) {
        this.text = text;
        this.memberData = data;
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    public String getText() {
        return text;
    }

    public com.example.gsmgosu.MemberData getMemberData() {
        return memberData;
    }

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }
}
