package com.dajun.springbootplatform.entities.dealLobby;

public class buyersChat {
    private int id;
    private String b_chat;
    private String b_phone;
    private String s_phone;
    private int state;

    public buyersChat() {
    }

    public buyersChat(int id, String b_chat, String b_phone, String s_phone,int state) {
        this.id = id;
        this.b_chat = b_chat;
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

    public String getB_chat() {
        return b_chat;
    }

    public void setB_chat(String b_chat) {
        this.b_chat = b_chat;
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
