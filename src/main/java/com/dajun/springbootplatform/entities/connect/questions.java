package com.dajun.springbootplatform.entities.connect;

public class questions {
    private String question;
    private Integer q_state;
    private String user_tel;
    private Integer id;

    public questions() {
    }

    public questions(String question, Integer q_state, String user_tel, Integer id) {
        this.question = question;
        this.q_state = q_state;
        this.user_tel = user_tel;
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getQ_state() {
        return q_state;
    }

    public void setQ_state(Integer q_state) {
        this.q_state = q_state;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
