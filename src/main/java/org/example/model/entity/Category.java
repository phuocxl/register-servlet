package org.example.model.entity;

public class Category {
    private int cateid;
    private String catename;
    private String icon;
    private int status;

    public Category() {
    }

    public Category(int cateid, String catename, String icon, int status) {
        this.cateid = cateid;
        this.catename = catename;
        this.icon = icon;
        this.status = status;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
