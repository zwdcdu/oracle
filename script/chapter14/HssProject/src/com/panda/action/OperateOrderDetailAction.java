package com.panda.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.panda.po.OrderDetailsEntity;
import com.panda.po.ViewOrderDetailsEntity;
import com.panda.po.OrdersEntity;
import com.panda.service.OrderDetailService;
import com.panda.service.OrderService;
import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.ORB;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/5/10.
 * TIME:16:29.
 */
public class OperateOrderDetailAction extends ActionSupport {
    @Resource(name = "orderDetailService")
    private OrderDetailService orderDetailService;
    @Resource(name = "orderService")
    private OrderService orderService;

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    private int id ;
    private ActionContext actionContext = ActionContext.getContext();
    public OrderDetailService getOrderDetailService() {
        return orderDetailService;
    }

    public void setOrderDetailService(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OperateOrderDetailAction(){}

    //处理删除
    public String delete() throws  Exception{
        System.out.println("进入订单详单删除界面");
        orderDetailService.deleteOrderDetail(id);
        return SUCCESS;
    }

    //跳转到编辑界面
    public String toEdit() throws Exception {
        System.out.println("进入跳转编辑界面");
        ViewOrderDetailsEntity orderDetailsEntity = orderDetailService.queryById(id);
        HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        System.out.println(orderDetailsEntity.getId()+" "+orderDetailsEntity.getOrderId()+" "+orderDetailsEntity.getProductId());
        request.getSession().setAttribute("orderDetail1",orderDetailsEntity);
        return SUCCESS;
    }

    //删除之后跳转到的Action
    public String toShow() throws Exception {
        HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        int orderId = (int) request.getSession().getAttribute("Mid");
        System.out.println("得到的orderId是"+orderId);
        if (orderId != 0 ) {
            List<ViewOrderDetailsEntity> list2 = orderDetailService.query(orderId);
            OrdersEntity ordersEntity = orderService.queryOrderByOrderId(orderId);
            request.getSession().setAttribute("price",ordersEntity.getTradeReceivable());
            request.getSession().setAttribute("orderDetailList",list2);
            return SUCCESS;
        }else {
            throw new Exception("orderID不能为0");
        }
    }

    //跳转到添加界面
    public String toAdd() throws Exception{
        System.out.println("进入跳转添加界面");
        ViewOrderDetailsEntity orderDetailsEntity = orderDetailService.queryById(id);
        HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        System.out.println(orderDetailsEntity.getId()+" "+orderDetailsEntity.getOrderId()+" "+orderDetailsEntity.getProductId());
        request.getSession().setAttribute("orderDetail2",orderDetailsEntity);
        return SUCCESS;
    }
}
