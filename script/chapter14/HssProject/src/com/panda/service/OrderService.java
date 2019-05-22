package com.panda.service;

import com.panda.po.OrdersEntity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/10.
 * TIME:15:18.
 */
public interface OrderService {
    void addOrder(OrdersEntity ordersEntity) throws Exception;//添加订单
    List<OrdersEntity> queryOrder(int pageSize, int pageNow) throws Exception;//分页查询所有订单
    int queryOrder() throws Exception;//查询所有订单
    OrdersEntity queryOrderByOrderId(int orderId) throws Exception;//根据id查询订单
    void  deleteOrder(int orderId) throws Exception;//删除订单
    boolean  updateOrder(OrdersEntity ordersEntity) throws Exception;//修改订单信息
}
