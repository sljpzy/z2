package com.baway.shenglijuan.bean;
/**
 类作用:封装数据bean类
 创建人：绳利娟
 创建时间： 2017/7/17   10:26
 */


public class Goods {
    private String title;
    private String money;
    private boolean flag;
    public Goods(String title, String money, boolean flag) {
        super();
        this.title = title;
        this.money = money;
        this.flag = flag;
    }
    public Goods() {
        super();
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMoney() {
        return money;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    @Override
    public String toString() {
        return "Goods [title=" + title + ", money=" + money + ", flag=" + flag
                + "]";
    }


}
