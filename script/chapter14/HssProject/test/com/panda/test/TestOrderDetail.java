package com.panda.test;

import com.panda.dao.OrderDetailDao;
import com.panda.daoImpl.OrderDetailDaoImpl;
import com.panda.po.OrderDetailsEntity;
import com.panda.po.ViewOrderDetailsEntity;
import com.panda.service.OrderDetailService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/5/10.
 * TIME:11:47.
 */
public class TestOrderDetail {
    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("spring/applicationContext.xml");
        OrderDetailService productService = (OrderDetailService) applicationContext.getBean("orderDetailService");
       /* OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
        orderDetailsEntity.setId(459640);
        orderDetailsEntity.setOrderId(7538);
        orderDetailsEntity.setProductId("phone2");
        orderDetailsEntity.setProductNum(3);
        orderDetailsEntity.setProductPrice(7998);*/
       /* orderDetailsEntity.setProductType("手机");
        orderDetailsEntity.setProductName("华为P9");*/

        OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
        List<ViewOrderDetailsEntity> list = productService.queryOrderDetail(3776,4,1);
        System.out.print(list.get(0).getProductType()+list.get(0).getId());
    }
}
