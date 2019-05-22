package com.panda.serviceImpl;

import com.panda.dao.OrderDetailDao;
import com.panda.po.OrderDetailsEntity;
import com.panda.po.ViewOrderDetailsEntity;
import com.panda.service.OrderDetailService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/12.
 * TIME:10:01.
 */
@Component("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {
    @Resource(name = "orderDetailDao")
    private OrderDetailDao orderDetailDao;

    public OrderDetailDao getOrderDetailDao() {
        return orderDetailDao;
    }

    public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
    }

    @Override
    public List<ViewOrderDetailsEntity> queryOrderDetail(int orderId, int pageSize, int pageNow) throws Exception {
        return this.orderDetailDao.queryOrderDetail(orderId,pageSize,pageNow);
    }

    @Override
    public List<ViewOrderDetailsEntity> query(int orderId) throws Exception {
        return this.orderDetailDao.query(orderId);
    }

    //删除订单详单
    public boolean deleteOrderDetail(Integer OrderDetailId) throws Exception {
        //如果传入ID为空，则返回false
        if (OrderDetailId == null) {
            return false;
        }
        else {
            return this.orderDetailDao.deleteOrderDetail(OrderDetailId);
        }
    }

   //更新
    public boolean updateOrderDetail(OrderDetailsEntity orderDetailsEntity) throws Exception {
        //首先验证是否有必需属性，如果没有，则返回false
       /* if (orderDetailsEntity.getId() == 0 ||orderDetailsEntity.getProductId() == null ||
               "".equals(orderDetailsEntity.getProductId()) || orderDetailsEntity.getProductNum() == 0 ||
                orderDetailsEntity.getProductPrice() == 0 ) {
            return false;
        }
        else
            return this.orderDetailDao.updateOrderDetail(orderDetailsEntity);*/
        return !(orderDetailsEntity.getId() == 0 ||orderDetailsEntity.getProductId() == null || "".equals(orderDetailsEntity.getProductId()) ||
                orderDetailsEntity.getProductNum() == 0 || orderDetailsEntity.getProductPrice() == 0)
                && this.orderDetailDao.updateOrderDetail(orderDetailsEntity);
    }

    //添加订单
    public boolean addOrderDetail(OrderDetailsEntity orderDetailsEntity)  throws Exception{
        if(orderDetailsEntity.getProductId() == null || "".equals(orderDetailsEntity.getProductId()) ||
                orderDetailsEntity.getProductNum() == 0 || orderDetailsEntity.getProductPrice() == 0 || orderDetailsEntity.getOrderId() == 0)
        {
            System.out.println("有空缺参数");
            return false;
        }
        else {
            System.out.println("没有空缺参数");
            /*if (orderDetailsEntity.getProductType() == null ) {
                orderDetailsEntity.setProductType("");
            }
            if (orderDetailsEntity.getProductName() == null) {
                orderDetailsEntity.setProductName("");
            }*/
            return this.orderDetailDao.addOrderDetail(orderDetailsEntity);
        }
    }

    //查询订单
    public ViewOrderDetailsEntity queryById(Integer id) throws Exception {
       if (id == null) {
           throw new NullPointerException("传入参数为空");
       }
       return this.orderDetailDao.queryById(id);
    }

    @Override
    public int countAll() throws Exception {
        return this.orderDetailDao.countAll();
    }

}
