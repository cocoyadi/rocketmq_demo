/**
 * Copyright(C),2005-2019,深圳市珍爱网信息技术有限公司
 **/
package com.coco.entity;

/**
 * @Description: TODO
 * @author zhangxiaoxun
 * @date 2019/7/28 14:18
 * @Version: V1.0
 *
 **/
public class OrderDO {
    private Integer orderId;
    private String desc;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    @Override
    public String toString() {
        return "OrderDO{" +
                "orderId=" + orderId +
                ", desc='" + desc + '\'' +
                '}';
    }
}
