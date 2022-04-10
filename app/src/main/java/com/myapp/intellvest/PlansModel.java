package com.myapp.intellvest;

import com.google.firebase.database.Exclude;

public class PlansModel {
    private int local;
    private int needLocal;
    private int soper;
    private int needSoper;
    private int superior;
    private int needSuperior;
    private int manger;
    private int needManger;
    private int senior;
    private int needSenior;
    private int golden;
    private int needGolden;
    private int diamond;
    private int needDiamond;
    private String moreTxt;
    private String happyTxt;
    private String sadTxt;
    private String mKey;

    public PlansModel() {
    }

    public PlansModel(int local, int needLocal, int soper, int needSoper, int superior, int needSuperior, int manger, int needManger, int senior, int needSenior, int golden, int needGolden, int diamond, int needDiamond, String moreTxt, String happyTxt, String sadTxt) {
        this.local = local;
        this.needLocal = needLocal;
        this.soper = soper;
        this.needSoper = needSoper;
        this.superior = superior;
        this.needSuperior = needSuperior;
        this.manger = manger;
        this.needManger = needManger;
        this.senior = senior;
        this.needSenior = needSenior;
        this.golden = golden;
        this.needGolden = needGolden;
        this.diamond = diamond;
        this.needDiamond = needDiamond;
        this.moreTxt = moreTxt;
        this.happyTxt = happyTxt;
        this.sadTxt = sadTxt;
    }

    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public int getNeedLocal() {
        return needLocal;
    }

    public void setNeedLocal(int needLocal) {
        this.needLocal = needLocal;
    }

    public int getSoper() {
        return soper;
    }

    public void setSoper(int soper) {
        this.soper = soper;
    }

    public int getNeedSoper() {
        return needSoper;
    }

    public void setNeedSoper(int needSoper) {
        this.needSoper = needSoper;
    }

    public int getSuperior() {
        return superior;
    }

    public void setSuperior(int superior) {
        this.superior = superior;
    }

    public int getNeedSuperior() {
        return needSuperior;
    }

    public void setNeedSuperior(int needSuperior) {
        this.needSuperior = needSuperior;
    }

    public int getManger() {
        return manger;
    }

    public void setManger(int manger) {
        this.manger = manger;
    }

    public int getNeedManger() {
        return needManger;
    }

    public void setNeedManger(int needManger) {
        this.needManger = needManger;
    }

    public int getSenior() {
        return senior;
    }

    public void setSenior(int senior) {
        this.senior = senior;
    }

    public int getNeedSenior() {
        return needSenior;
    }

    public void setNeedSenior(int needSenior) {
        this.needSenior = needSenior;
    }

    public int getGolden() {
        return golden;
    }

    public void setGolden(int golden) {
        this.golden = golden;
    }

    public int getNeedGolden() {
        return needGolden;
    }

    public void setNeedGolden(int needGolden) {
        this.needGolden = needGolden;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getNeedDiamond() {
        return needDiamond;
    }

    public void setNeedDiamond(int needDiamond) {
        this.needDiamond = needDiamond;
    }

    public String getMoreTxt() {
        return moreTxt;
    }

    public void setMoreTxt(String moreTxt) {
        this.moreTxt = moreTxt;
    }

    public String getHappyTxt() {
        return happyTxt;
    }

    public void setHappyTxt(String happyTxt) {
        this.happyTxt = happyTxt;
    }

    public String getSadTxt() {
        return sadTxt;
    }

    public void setSadTxt(String sadTxt) {
        this.sadTxt = sadTxt;
    }
    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}
