package com.arkainfoteck.helpmate.Model;

public class OrderHistoryModel {
   String image;
   String name;
   String phone;
   String active;
   String id;
   String cookid;

    public OrderHistoryModel(String image, String name, String phone, String active, String id, String cookid) {
        this.image = image;
        this.name = name;
        this.phone = phone;
        this.active = active;
        this.id = id;
        this.cookid = cookid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCookid() {
        return cookid;
    }

    public void setCookid(String cookid) {
        this.cookid = cookid;
    }
}
