package com.guruprasad.innovativeadmin.Model;

public class UploadProjectModel {

    private String projecId , logourl , title
            , description , feature , advantage , disadvantage , price , category , img1 , img2 , img3 , img4 , img5 ;


    public UploadProjectModel(String projecId, String logourl, String title, String description, String feature, String advantage, String disadvantage, String price, String category, String img1, String img2, String img3, String img4, String img5) {
        this.projecId = projecId;
        this.logourl = logourl;
        this.title = title;
        this.description = description;
        this.feature = feature;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.price = price;
        this.category = category;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.img5 = img5;
    }

    public UploadProjectModel(String projecId, String logourl, String title, String description, String feature, String advantage, String disadvantage, String price, String category) {
        this.projecId = projecId;
        this.logourl = logourl;
        this.title = title;
        this.description = description;
        this.feature = feature;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.price = price;
        this.category = category;
    }

    public UploadProjectModel() {
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getDisadvantage() {
        return disadvantage;
    }

    public void setDisadvantage(String disadvantage) {
        this.disadvantage = disadvantage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImag1() {
        return img1;
    }

    public void setImag1(String imag1) {
        this.img1 = imag1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

    public String getProjecId() {
        return projecId;
    }

    public void setProjecId(String projecId) {
        this.projecId = projecId;
    }
}
