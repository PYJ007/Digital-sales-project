package com.dajun.springbootplatform.entities;

public class browsingHistory {
    //种子类型
    String seed_type;
    //该类型的浏览次数
    int numb;
    //该类型的种子在数据库中有多少个
    int total;

    public String getSeed_type() {
        return seed_type;
    }

    public void setSeed_type(String seed_type) {
        this.seed_type = seed_type;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
