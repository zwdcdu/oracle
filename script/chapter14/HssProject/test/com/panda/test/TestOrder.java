package com.panda.test;

import com.panda.dao.EmployeeDao;
import com.panda.dao.OrderDao;
import com.panda.daoImpl.OrderDaoImpl;
import com.panda.daoImpl.OrderDetailDaoImpl;
import com.panda.po.OrderDetailsEntity;
import com.panda.po.OrdersEntity;
import com.panda.service.OrderDetailService;
import com.panda.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/10.
 * TIME:15:36.
 */
public class TestOrder {
    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("spring/applicationContext.xml");
        OrderService orderDao = (OrderService) applicationContext.getBean("orderService");
        /*
        测试查询订单的总数
        */
        int cnt = orderDao.queryOrder();
        System.out.println("订单总数：" + cnt);

        /*
        测试分页查询订单
        */
        List<OrdersEntity> list2 = orderDao.queryOrder(6,1);
        for (OrdersEntity oe:list2) {
            System.out.println(oe.getOrderId()+" "+oe.getCustomerName()+" "+oe.getOrderDate());
        }

//        /*
//        测试添加订单
//        */
////        OrdersEntity ordersEntity = new OrdersEntity() ;
////        ordersEntity.setOrderId(10002);
////        ordersEntity.setOrderDate();
//        String str1 = "hello";
//
//        String str2 = "he" + new String("llo");
//
//        System.out.println(str1 == str2);
//
/*
        //添加订单
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setOrderId(19563);
        ordersEntity.setEmployeeId(11);
        ordersEntity.setOrderDate(new Date());
        ordersEntity.setCustomerName("章义杰");
        ordersEntity.setCustomerTel("18486352964");
        ordersEntity.setDiscount(346L);
        orderDao.addOrder(ordersEntity);
        */
    }

}
