package com.king.domain;

import com.king.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Orders implements Serializable {

    private String id;
    private String orderNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date orderTime;
    private String orderTimeStr;
    private Integer orderStatus;
    private String orderStatusStr;
    private Integer peopleCount;
    // 产品（一对一、多对一）
    private Product product;
    // 旅客（多对多）
    private List<Traveller> travellers;
    // 会员（一对一）
    private Member member;
    private Integer payType;
    private String payTypeStr;
    private String orderDesc;

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusStr() {
        if (orderStatus != null){
            if (orderStatus == 1){
                orderStatusStr = "已支付";
            }
            if (orderStatus == 0){
                orderStatusStr = "未支付";
            }
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", orderTimeStr='" + orderTimeStr + '\'' +
                ", orderStatusStr='" + orderStatusStr + '\'' +
                ", peopleCount=" + peopleCount +
                ", product=" + product +
                ", travellers=" + travellers +
                ", member=" + member +
                ", payTypeStr='" + payTypeStr + '\'' +
                ", orderDesc='" + orderDesc + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        if (orderTime != null){
            orderTimeStr = DateUtils.dateToString(orderTime, "yyyy-MM-dd HH:mm:ss");
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        if (payType != null){
            if (payType == 2){
                payTypeStr = "其它";
            }
            if (payType == 1){
                payTypeStr = "微信";
            }
            if (payType == 0){
                payTypeStr = "支付宝";
            }
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}


