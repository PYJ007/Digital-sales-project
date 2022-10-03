package com.dajun.springbootplatform.entities.dealLobby;

public class sellerChat {
    private int id;
    private String s_chat;
    private String b_phone;
    private String s_phone;
    private int state;

    public sellerChat() {
    }

    public sellerChat(int id, String s_chat, String b_phone, String s_phone,int state) {
        this.id = id;
        this.s_chat = s_chat;
        this.b_phone = b_phone;
        this.s_phone = s_phone;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getS_chat() {
        return s_chat;
    }

    public void setS_chat(String s_chat) {
        this.s_chat = s_chat;
    }

    public String getB_phone() {
        return b_phone;
    }

    public void setB_phone(String b_phone) {
        this.b_phone = b_phone;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }
}
