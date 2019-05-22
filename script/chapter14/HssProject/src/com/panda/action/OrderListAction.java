package com.panda.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.panda.po.OrderDetailsEntity;
import com.panda.po.ViewOrderDetailsEntity;
import com.panda.po.OrdersEntity;
import com.panda.po.ProductsEntity;
import com.panda.service.OrderDetailService;
import com.panda.service.OrderService;
import com.panda.service.ProductService;
import com.panda.util.PagerUtil;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/7.
 * TIME:18:28.
 */
public class OrderListAction extends ActionSupport {
    @Resource(name = "orderService")
    private OrderService orderService;
    @Resource(name = "orderDetailService")
    private OrderDetailService orderDetailService ;
    private ActionContext actionContext = ActionContext.getContext();

    private int pageNow = 1 ;
    private int pageSize = 6;

    private int orderId ;

    public OrderListAction(){}

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

    public int getOrderId() {

        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    //指定处理商品列表方法
    public String execute() throws Exception {
        System.out.println("进入订单列表界面");

        int list = orderService.queryOrder();

        //构造page对象并存进session
        PagerUtil pagerUtil = new PagerUtil(pageNow,list);
        pagerUtil.setPageSize(getPageSize());

        List<OrdersEntity> list2 = orderService.queryOrder(pageSize,pageNow);
        for (OrdersEntity o : list2) {
            //o.setEmployeeId(o.getEmployeesByEmployeeId().getEmployeeId());//设置订单Id
            if (o.getOrderDetailsByOrderId().size() > 0) {
                o.setHasDetail(1);
            }
        }
        HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        //将pager属性和得到的员工集合存储在request中
        request.getSession().setAttribute("pager",pagerUtil);
        request.getSession().setAttribute("orders",list2);
        System.out.println("商品集合长度:"+list2.size());
        return SUCCESS;
    }
    //指定处理商品详情
    public String showOrder() throws Exception{
        System.out.println("进入商品详情Action:"+orderId);
        boolean pp = false ;
        if (orderId == 0) {
            throw new NullPointerException("传入oderId为空");
        }
        List<ViewOrderDetailsEntity> list2 = orderDetailService.query(orderId);
        OrdersEntity ordersEntity = orderService.queryOrderByOrderId(orderId);
        HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
            PagerUtil pagerUtil = new PagerUtil(pageNow,list2.size());
            pagerUtil.setPageSize(pageSize);
            List<ViewOrderDetailsEntity> list = orderDetailService.queryOrderDetail(orderId,pageSize,pageNow);
            request.getSession().setAttribute("pager1",pagerUtil);
        request.getSession().setAttribute("pp",pp);
        request.getSession().setAttribute("orderDetailList",list);
        request.getSession().setAttribute("price",ordersEntity.getTradeReceivable());
        request.getSession().setAttribute("idOrder",ordersEntity.getOrderId());
        request.getSession().setAttribute("Mid",orderId);
        return SUCCESS ;
    }

    //用订单号查询单个订单
    public String queryOne() throws Exception {
        System.out.println("进入单个商品查询界面");
        OrdersEntity ordersEntity = orderService.queryOrderByOrderId(orderId);
        if (ordersEntity == null) {
            this.addActionError("订单号不存在");
            return INPUT;
        }
        HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        ordersEntity.setEmployeeId(ordersEntity.getEmployeesByEmployeeId().getEmployeeId());
        if (ordersEntity.getOrderDetailsByOrderId().size() > 0) {
            ordersEntity.setHasDetail(1);
        }
        request.getSession().setAttribute("orderOne",ordersEntity);
        return SUCCESS ;
    }

    //跳转到编辑界面的action
    public String queryOneById() throws Exception{
        System.out.println("进入单个商品跳转界面");
        OrdersEntity ordersEntity = orderService.queryOrderByOrderId(orderId);
        HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        ordersEntity.setEmployeeId(ordersEntity.getEmployeesByEmployeeId().getEmployeeId());
        request.getSession().setAttribute("orderOnes",ordersEntity);
        return SUCCESS ;
    }

    //删除订单
    public  String delete() throws Exception{
        System.out.println("进入删除订单界面界面");
        orderService.deleteOrder(orderId);
        return SUCCESS;
    }
}
