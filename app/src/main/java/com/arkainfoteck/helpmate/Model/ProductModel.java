package com.arkainfoteck.helpmate.Model;

public class ProductModel {
   // @SerializedName("name")
    String name;
  //  @SerializedName("image")
    String image;
    String hours;
    String type;
    String details;
    String price;
    String content;
    String content2;
    String content3;

    public ProductModel(String name, String image, String hours, String type, String details, String price, String content, String content2, String content3) {
        this.name = name;
        this.image = image;
        this.hours = hours;
        this.type = type;
        this.details = details;
        this.price = price;
        this.content = content;
        this.content2 = content2;
        this.content3 = content3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }
}
