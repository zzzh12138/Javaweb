package com.itheima.pojo;

public class OrderManage {
    // id 主键
    private int id;
    // 品牌名称
    private String produceBrandOrderName;
    // 企业名称
    private String companyOrderName;
    // 订单号
    private String ordered;
    // 售价
    private String price;
    // 评分
    private String starA;
    // 评价
    private String descriptionA;
    // 评分
    private String starC;
    // 评价
    private String descriptionC;
    // 状态：0：订单表  1：购物车表
    private String status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduceBrandOrderName() {
        return produceBrandOrderName;
    }

    public void setProduceBrandOrderName(String produceBrandOrderName) {
        this.produceBrandOrderName = produceBrandOrderName;
    }

    public String getCompanyOrderName() {
        return companyOrderName;
    }

    public void setCompanyOrderName(String companyOrderName) {
        this.companyOrderName = companyOrderName;
    }

    public String getOrdered() {
        return ordered;
    }

    public void setOrdered(String ordered) {
        this.ordered = ordered;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStarA() {
        return starA;
    }

    public void setStarA(String starA) {
        this.starA = starA;
    }

    public String getDescriptionA() {
        return descriptionA;
    }

    public void setDescriptionA(String descriptionA) {
        this.descriptionA = descriptionA;
    }

    public String getStarC() {
        return starC;
    }

    public void setStarC(String starC) {
        this.starC = starC;
    }

    public String getDescriptionC() {
        return descriptionC;
    }

    public void setDescriptionC(String descriptionC) {
        this.descriptionC = descriptionC;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "OrderManage{" +
                "id=" + id +
                ", produceBrandOrderName='" + produceBrandOrderName + '\'' +
                ", companyOrderName='" + companyOrderName + '\'' +
                ", ordered='" + ordered + '\'' +
                ", price='" + price + '\'' +
                ", starA='" + starA + '\'' +
                ", descriptionA='" + descriptionA + '\'' +
                ", starC='" + starC + '\'' +
                ", descriptionC='" + descriptionC + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
