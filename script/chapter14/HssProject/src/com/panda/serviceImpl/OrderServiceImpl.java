package com.panda.serviceImpl;

import com.panda.dao.OrderDao;
import com.panda.po.OrdersEntity;
import com.panda.service.OrderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/10.
 * TIME:15:19.
 */
@Component(value = "orderService")
public class OrderServiceImpl implements OrderService {
    @Resource(name = "orderDao")
    private OrderDao orderDao ;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    //添加订单
    public void addOrder(OrdersEntity ordersEntity) throws Exception {
            this.orderDao.addOrder(ordersEntity);
    }

    //分页查询订单
    public List<OrdersEntity> queryOrder(int pageSize, int pageNow) throws Exception {
        return this.orderDao.queryOrder(pageSize,pageNow);
    }

    //查询订单
    public int queryOrder() throws Exception {
        return this.orderDao.queryOrder();
    }

    //根据Id查询订单
    public OrdersEntity queryOrderByOrderId(int orderId) throws Exception {
        return this.orderDao.queryOrderByOrderId(orderId);
    }

    //删除订单
    public void deleteOrder(int orderId) throws Exception {
        this.orderDao.deleteOrder(orderId);
    }

    //更新订单
    public boolean updateOrder(OrdersEntity ordersEntity) throws Exception {
        return this.orderDao.updateOrder(ordersEntity);
    }
}
