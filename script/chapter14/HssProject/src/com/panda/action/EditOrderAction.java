package com.panda.action;

import com.opensymphony.xwork2.ActionSupport;
import com.panda.po.EmployeesEntity;
import com.panda.po.OrdersEntity;
import com.panda.service.EmployeeService;
import com.panda.service.OrderService;
import org.hibernate.criterion.Order;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/6.
 * TIME:14:56.
 */
//处理编辑订单的Action
public class EditOrderAction extends ActionSupport {
    private OrdersEntity orderOnes;
    @Resource(name = "orderService")
    private OrderService orderService;
    private String orderDate;

    public EditOrderAction() {
    }

    public String edit() throws Exception {
        System.out.println("订单进入编辑");
        Calendar calendar = Calendar.getInstance();
        String[] strs = orderDate.split("-");
        calendar.set(Calendar.YEAR,Integer.parseInt(strs[0]));
        calendar.set(Calendar.MONTH,Integer.parseInt(strs[1])-1);
        calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(strs[2]));
        System.out.println(calendar);
        orderOnes.setOrderDate(calendar.getTime());
        if(orderService.updateOrder(orderOnes)) {
            return SUCCESS;
        }
        else {
            this.addActionError("请传入空缺参数");
            return INPUT;
        }
    }

    public OrdersEntity getOrderOnes() {
        return orderOnes;
    }

    public void setOrderOnes(OrdersEntity orderOnes) {
        this.orderOnes = orderOnes;
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

