package com.panda.action;

import com.opensymphony.xwork2.ActionSupport;
import com.panda.po.OrderDetailsEntity;
import com.panda.po.OrdersEntity;
import com.panda.service.OrderDetailService;
import com.panda.service.OrderService;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/5/10.
 * TIME:18:18.
 */
public class OrderDetailEditAction extends ActionSupport {

    private OrderDetailsEntity orderDetailsEntity ;

    @Resource(name = "orderDetailService")
    private OrderDetailService orderDetailService ;

    @Resource(name = "orderService")
    private OrderService orderService ;

    public OrderDetailEditAction(){}

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderDetailService getOrderDetailService() {
        return orderDetailService;
    }

    public void setOrderDetailService(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    public OrderDetailsEntity getOrderDetailsEntity() {
        return orderDetailsEntity;
    }

    public void setOrderDetailsEntity(OrderDetailsEntity orderDetailsEntity) {
        this.orderDetailsEntity = orderDetailsEntity;
    }

    public String add() throws Exception{
        System.out.println("进入更新");
        if (orderDetailsEntity.getId() ==0 || orderDetailsEntity.getOrderId() ==0 ||orderDetailsEntity.getProductId() ==null ||
                orderDetailsEntity.getProductNum() ==0 ||orderDetailsEntity.getProductPrice() ==0) {
            this.addActionError("请传入空缺参数");
            return INPUT;
        }
        else {
            orderDetailService.updateOrderDetail(orderDetailsEntity);
            return SUCCESS;
        }
    }
}
