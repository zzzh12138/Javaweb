package com.itheima.pojo;

public class ProduceC {
    //主键
    private Integer Id;
    //品牌名称
    private String  produceBrandCName;
    //评分
    private Integer star;
    //企业名称
    private String companyCName;
    //企业名称
    private String evaluation;
    //月售数量
    private Integer numbered;
    //字段
    private String ordered;
    //状态 0禁用 1启用
    private Integer status;
    //售价
    private Integer price;
    //硅铝氧化物组分
    private Integer percentage;
    //描述信息
    private String  description;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getProduceBrandCName() {
        return produceBrandCName;
    }

    public void setProduceBrandCName(String produceBrandCName) {
        this.produceBrandCName = produceBrandCName;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getCompanyCName() {
        return companyCName;
    }

    public void setCompanyCName(String companyCName) {
        this.companyCName = companyCName;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Integer getNumbered() {
        return numbered;
    }

    public void setNumbered(Integer numbered) {
        this.numbered = numbered;
    }

    public String getOrdered() {
        return ordered;
    }

    public void setOrdered(String ordered) {
        this.ordered = ordered;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProduceC{" +
                "Id=" + Id +
                ", produceBrandCName='" + produceBrandCName + '\'' +
                ", star=" + star +
                ", companyCName='" + companyCName + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", numbered=" + numbered +
                ", ordered='" + ordered + '\'' +
                ", status=" + status +
                ", price=" + price +
                ", percentage=" + percentage +
                ", description='" + description + '\'' +
                '}';
    }
}
