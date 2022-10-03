package com.dajun.springbootplatform.entities.connect;

public class answers {
    private String answer;
    private Integer a_state;
    private String user_tel;
    private Integer id;

    public answers() {
    }

    public answers(String answer, Integer a_state, String user_tel, Integer id) {
        this.answer = answer;
        this.a_state = a_state;
        this.user_tel = user_tel;
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getA_state() {
        return a_state;
    }

    public void setA_state(Integer a_state) {
        this.a_state = a_state;
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
