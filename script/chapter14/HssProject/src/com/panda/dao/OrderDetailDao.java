package com.panda.dao;

import com.panda.po.OrderDetailsEntity;
import com.panda.po.ViewOrderDetailsEntity;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/12.
 * TIME:9:32.
 */
public interface OrderDetailDao {
    //根据订单Id查询出订单详细
    public List<ViewOrderDetailsEntity> queryOrderDetail(int orderId, int pageSize, int pageNow) throws Exception;
    //根据Id查询出所有订单详细
    public List<ViewOrderDetailsEntity> query(int orderId) throws Exception;
    //根据Id删除详单表
    boolean deleteOrderDetail(Integer OrderDetailId) throws Exception;
    //更新订单详单表
    boolean updateOrderDetail(OrderDetailsEntity orderDetailsEntity) throws Exception;
    //添加详单表
    boolean addOrderDetail(OrderDetailsEntity orderDetailsEntity) throws Exception;
    //查询订单详单
    public ViewOrderDetailsEntity queryById(Integer id) throws Exception;
    //统计所有数量
    int countAll() throws Exception;
}
