package com.panda.daoImpl;

import com.panda.dao.OrderDao;
import com.panda.po.EmployeesEntity;
import com.panda.po.OrdersEntity;
import com.panda.po.ProductsEntity;
import org.hibernate.*;
import org.hibernate.jdbc.Work;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/10.
 * TIME:11:07.
 */
@Repository("orderDao")
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {
    //注入sessionFactory
    @Resource(name = "sessionFactory")
    public void setSF(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public Session getMySession() {
        return this.getSessionFactory().getCurrentSession();//不必手动关闭session
    }
    //添加订单
    public void addOrder(OrdersEntity ordersEntity) throws Exception {
        System.out.println("进入商品添加的Dao"+ordersEntity.getOrderId()+ordersEntity.getCustomerName()+ordersEntity.getTradeReceivable());
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        String updateSql = "INSERT INTO ORDERS(ORDER_ID,CUSTOMER_NAME,CUSTOMER_TEL,ORDER_DATE,EMPLOYEE_ID,DISCOUNT) VALUES (?,?,?,?,?,?)";
        try {
            transaction = session.beginTransaction();
            session.doWork(new Work() {
                PreparedStatement preparedStatement = null;
                @Override
                public void execute(Connection connection) throws SQLException {
                    preparedStatement = connection.prepareStatement(updateSql);
                    preparedStatement.setLong(1,ordersEntity.getOrderId());
                    preparedStatement.setString(2,ordersEntity.getCustomerName());
                    preparedStatement.setString(3,ordersEntity.getCustomerTel());
                    preparedStatement.setDate(4,new java.sql.Date(ordersEntity.getOrderDate().getTime()));
                    preparedStatement.setLong(5,ordersEntity.getEmployeeId());
                    preparedStatement.setLong(6,ordersEntity.getDiscount());
                    preparedStatement.execute();
                    System.out.println("更改成功");
                }
            });
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw(e);
        }
    }

    //分页查询所有订单
    public List<OrdersEntity> queryOrder(int pageSize, int pageNow) throws Exception {
        List<OrdersEntity> list = null ;
        Session session = null ;
        String sql = "from OrdersEntity ";
        Transaction transaction = null ;
        try {
            session = getMySession();
            transaction = session.beginTransaction();
            Query querys = session.createQuery(sql);
            querys.setFirstResult((pageNow-1)*pageSize);//设置一次查询的数量
            querys.setMaxResults(pageSize);//设置查询的起始序号
            list = querys.list();
            transaction.commit();
            return  list;
        } catch (Exception e) {
            transaction.rollback();
            throw(e);
        }
    }

    //查询所有订单
    public int queryOrder() throws Exception{
        Transaction transaction = null ;
//        List<OrdersEntity> list = null ;
        String sql = "select count(*) from OrdersEntity";
        Session session = null;
        try {
            session = getMySession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(sql);
            Number num = ((Number)query.uniqueResult()).intValue();
            transaction.commit();
            return (int)num;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    //根据订单Id查询订单
    public OrdersEntity queryOrderByOrderId(int orderId) throws Exception {
        if (orderId == 0) {
            throw new NullPointerException("传入商品id为空");
        }
        //离线查询
        String hql = "from OrdersEntity where orderId = ?";
        Session session = null;
        Transaction transaction = null ;
        try {
            session = getMySession();
            transaction = session.beginTransaction() ;
            OrdersEntity ordersEntity = (OrdersEntity) session.createQuery(hql).setParameter(0,orderId).uniqueResult();
            transaction.commit();
            return ordersEntity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    //删除订单
    public void deleteOrder(int orderId) throws Exception {
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null ;
        String hql2 = "delete  from ORDERS where ORDER_ID = ?";
        try {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(hql2);
            query.setParameter(0,orderId);
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    //更新订单
    public boolean updateOrder(OrdersEntity ordersEntity) throws Exception{
        System.out.println("进入商品编辑的Dao"+ordersEntity.getOrderId()+ordersEntity.getCustomerName()+ordersEntity.getTradeReceivable());
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        String updateSql = "update ORDERS set CUSTOMER_NAME=? ,CUSTOMER_TEL=?,ORDER_DATE=?,DISCOUNT=?WHERE ORDER_ID = ? ";
        try {
            transaction = session.beginTransaction();
            session.doWork(new Work() {
                PreparedStatement preparedStatement = null;
                @Override
                public void execute(Connection connection) throws SQLException {
                    preparedStatement = connection.prepareStatement(updateSql);
                    preparedStatement.setString(1,ordersEntity.getCustomerName());
                    preparedStatement.setString(2,ordersEntity.getCustomerTel());
                    preparedStatement.setDate(3,new java.sql.Date(ordersEntity.getOrderDate().getTime()));
                    preparedStatement.setLong(4,ordersEntity.getDiscount());
                    preparedStatement.setLong(5,ordersEntity.getOrderId());
                    preparedStatement.execute();
                    System.out.println("更改成功");
                }
            });
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw(e);
        }
    }
    }
