package com.arkainfoteck.helpmate.Model;

public class JobHistoryModel {

//    String text1,text2,text3,text4,text5;
//    String image1,image2,image3;
//    String type;
//
//    public JobHistoryModel(String text1, String text2, String text3, String text4, String text5, String image1, String image2, String image3, String type) {
//        this.text1 = text1;
//        this.text2 = text2;
//        this.text3 = text3;
//        this.text4 = text4;
//        this.text5 = text5;
//        this.image1 = image1;
//        this.image2 = image2;
//        this.image3 = image3;
//        this.type = type;
//    }
//
//    public String getText1() {
//        return text1;
//    }
//
//    public void setText1(String text1) {
//        this.text1 = text1;
//    }
//
//    public String getText2() {
//        return text2;
//    }
//
//    public void setText2(String text2) {
//        this.text2 = text2;
//    }
//
//    public String getText3() {
//        return text3;
//    }
//
//    public void setText3(String text3) {
//        this.text3 = text3;
//    }
//
//    public String getText4() {
//        return text4;
//    }
//
//    public void setText4(String text4) {
//        this.text4 = text4;
//    }
//
//    public String getText5() {
//        return text5;
//    }
//
//    public void setText5(String text5) {
//        this.text5 = text5;
//    }
//
//    public String getImage1() {
//        return image1;
//    }
//
//    public void setImage1(String image1) {
//        this.image1 = image1;
//    }
//
//    public String getImage2() {
//        return image2;
//    }
//
//    public void setImage2(String image2) {
//        this.image2 = image2;
//    }
//
//    public String getImage3() {
//        return image3;
//    }
//
//    public void setImage3(String image3) {
//        this.image3 = image3;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    String maid_book_id;
    String type;
    String timeing;
    String orderedtime;
    String no_hour;
    String location;
    String price;
    String image;
    String no_maids;
    String dates;
    String net_price;

    public JobHistoryModel(String maid_book_id, String type, String timeing, String orderedtime, String no_hour, String location, String price, String image, String no_maids, String dates, String net_price) {
        this.maid_book_id = maid_book_id;
        this.type = type;
        this.timeing = timeing;
        this.orderedtime = orderedtime;
        this.no_hour = no_hour;
        this.location = location;
        this.price = price;
        this.image = image;
        this.no_maids = no_maids;
        this.dates = dates;
        this.net_price = net_price;
    }

    public String getMaid_book_id() {
        return maid_book_id;
    }

    public void setMaid_book_id(String maid_book_id) {
        this.maid_book_id = maid_book_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimeing() {
        return timeing;
    }

    public void setTimeing(String timeing) {
        this.timeing = timeing;
    }

    public String getOrderedtime() {
        return orderedtime;
    }

    public void setOrderedtime(String orderedtime) {
        this.orderedtime = orderedtime;
    }

    public String getNo_hour() {
        return no_hour;
    }

    public void setNo_hour(String no_hour) {
        this.no_hour = no_hour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNo_maids() {
        return no_maids;
    }

    public void setNo_maids(String no_maids) {
        this.no_maids = no_maids;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getNet_price() {
        return net_price;
    }

    public void setNet_price(String net_price) {
        this.net_price = net_price;
    }
}
