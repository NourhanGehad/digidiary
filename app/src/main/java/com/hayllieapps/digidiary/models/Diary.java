package com.hayllieapps.digidiary.models;

import java.io.Serializable;

public class Diary  implements Serializable {

    private int localdiary_id;
    private String text1;
    private String text2;
    private String diarydate;
    private String lastupdated;
    private byte[] image;
    private int deleted = 0; //1 soft deleted
    private int favourite = 0; // 0 - no
    private Boolean selected = false;

    public Diary(int localdiary_id, String text1, String text2, String diarydate, String lastupdated, byte[] image, int deleted, int favourite) {
        this.localdiary_id = localdiary_id;
        this.text1 = text1;
        this.text2 = text2;
        this.diarydate = diarydate;
        this.lastupdated = lastupdated;
        this.image = image;
        this.deleted = deleted;
        this.favourite = favourite;
    }


    public int getLocaldiary_id() {
        return localdiary_id;
    }

    public void setLocaldiary_id(int localdiary_id) {
        this.localdiary_id = localdiary_id;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getDiarydate() {
        return diarydate;
    }

    public void setDiarydate(String diarydate) {
        this.diarydate = diarydate;
    }

    public String getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(String lastupdated) {
        this.lastupdated = lastupdated;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
