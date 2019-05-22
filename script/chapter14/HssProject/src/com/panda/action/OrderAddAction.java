package com.panda.action;

import com.opensymphony.xwork2.ActionSupport;
import com.panda.po.EmployeesEntity;
import com.panda.po.OrdersEntity;
import com.panda.service.EmployeeService;
import com.panda.service.OrderService;
import com.panda.util.BaseFunction;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/28.
 * TIME:18:41.
 */
/*处理员工信息的Action*/
public class OrderAddAction extends ActionSupport {
    private OrdersEntity ordersEntity ;
    @Resource(name = "orderService")
    private OrderService orderService ;
    private String orderDate ;
    private String aa="2012-12-21";
    public OrderAddAction(){}

    public String addOrder()  throws Exception {
        System.out.println("进入添加订单页面"+orderDate);
        Date  date = BaseFunction.StringToDate(orderDate);
        ordersEntity.setOrderDate(date);
        System.out.println("设置后的日期"+ordersEntity.getOrderDate());

        if (ordersEntity.getOrderId() == 0 || ordersEntity.getEmployeeId() ==0||ordersEntity.getCustomerName() == null ||
                "".equals(ordersEntity.getCustomerName()) || ordersEntity.getCustomerTel() ==null ||
                "".equals(ordersEntity.getCustomerTel())) {
            this.addActionError("请传入空缺参数");
            return INPUT;
        }
        else {
            OrdersEntity ordersEntity2 = orderService.queryOrderByOrderId(ordersEntity.getOrderId());
            if (ordersEntity2 != null) {
                this.addActionError("订单编号已经存在");
                return INPUT;
            }
            else {
                orderService.addOrder(ordersEntity);
                return SUCCESS;
            }
        }
    }

    public OrdersEntity getOrdersEntity() {
        return ordersEntity;
    }

    public void setOrdersEntity(OrdersEntity ordersEntity) {
        this.ordersEntity = ordersEntity;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
