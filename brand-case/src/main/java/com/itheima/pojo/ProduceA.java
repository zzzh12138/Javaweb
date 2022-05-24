package com.itheima.pojo;

import java.net.Inet4Address;


public class ProduceA {
    //主键
    private Integer Id;
    //品牌名称
    private String produceBrandAName;
    //企业名称
    private String companyAName;
    //字段
    private Integer ordered;
    //月售数量
    private Integer numbered;
    //售价
    private Integer price;
    //评分
    private Integer star;
    //顾客反馈
    private String evaluation;
    //之前购买时间
    private java.sql.Timestamp time;
    //描述信息
    private String  description;
    //状态 0禁用 1启用
    private Integer status;
    //硅铝氧化物组分
    private Integer percentage;
    //匹配c的编号
    private Integer _match_;

    public Integer getNumbered() {
        return numbered;
    }

    public void setNumbered(Integer numbered) {
        this.numbered = numbered;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public java.sql.Timestamp getTime() {
        return time;
    }

    public void setTime(java.sql.Timestamp time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer get_match_() {
        return _match_;
    }

    public void set_match_(Integer _match_) {
        this._match_ = _match_;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getProduceBrandAName() {
        return produceBrandAName;
    }

    public void setProduceBrandAName(String produceBrandAName) {
        this.produceBrandAName = produceBrandAName;
    }

    public String getCompanyAName() {
        return companyAName;
    }

    public void setCompanyAName(String companyAName) {
        this.companyAName = companyAName;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    @Override
    public String toString() {
        return "ProduceA{" +
                "Id=" + Id +
                ", produceBrandAName='" + produceBrandAName + '\'' +
                ", companyAName='" + companyAName + '\'' +
                ", ordered=" + ordered +
                ", numbered=" + numbered +
                ", price=" + price +
                ", star=" + star +
                ", evaluation='" + evaluation + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", percentage=" + percentage +
                ", _match_=" + _match_ +
                '}';
    }
}
