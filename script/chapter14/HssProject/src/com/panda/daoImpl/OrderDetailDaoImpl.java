package com.panda.daoImpl;

import com.panda.dao.OrderDetailDao;
import com.panda.po.OrderDetailsEntity;
import com.panda.po.ViewOrderDetailsEntity;
import com.panda.po.OrdersEntity;
import com.panda.po.ProductsEntity;
import org.hibernate.*;
import org.hibernate.jdbc.Work;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/12.
 * TIME:9:51.
 */
@Repository(value = "orderDetailDao")
public class OrderDetailDaoImpl extends HibernateDaoSupport implements OrderDetailDao {
    //注入sessionFactory
    @Resource(name = "sessionFactory")
    public void setSF(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public List<ViewOrderDetailsEntity> queryOrderDetail(int orderId,int pageSize,int pageNow) throws Exception {
        Session session = this.getSessionFactory().getCurrentSession();
        List<ViewOrderDetailsEntity> list = new ArrayList<>();
        Transaction transaction = null ;
        String sql = "SELECT * FROM VIEW_ORDER_DETAILS WHERE ORDER_ID = ?";
        try {
            transaction = session.beginTransaction();
            session.doWork(new Work() {
                ViewOrderDetailsEntity orderDetailsEntity = null;
                public void execute(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1,orderId);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        orderDetailsEntity  = new ViewOrderDetailsEntity();
                        System.out.println(resultSet.getInt("ID"));
                        orderDetailsEntity.setId(resultSet.getInt("ID"));
                        orderDetailsEntity.setOrderId(resultSet.getInt("ORDER_ID"));
                        orderDetailsEntity.setProductId(resultSet.getString("PRODUCT_ID"));
                        orderDetailsEntity.setProductName(resultSet.getString("PRODUCT_NAME"));
                        orderDetailsEntity.setProductType(resultSet.getString("PRODUCT_TYPE"));
                        orderDetailsEntity.setProductNum(resultSet.getLong("PRODUCT_NUM"));
                        orderDetailsEntity.setProductPrice(resultSet.getLong("PRODUCT_PRICE"));
                        list.add(orderDetailsEntity);
                    }
                }
            });
            System.out.println("查询出的订单大小是:"+list.size());
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    public List<ViewOrderDetailsEntity> query(int orderId) throws Exception {
        Session session = this.getSessionFactory().getCurrentSession();
        System.out.println("进入查询的DaoImpl"+orderId);
        List<ViewOrderDetailsEntity> list = new ArrayList<>();
        Transaction transaction = null ;
        String sql = "SELECT * FROM VIEW_ORDER_DETAILS WHERE ORDER_ID = ?";
        try {
            transaction = session.beginTransaction();
            session.doWork(new Work() {
                ViewOrderDetailsEntity orderDetailsEntity = null;
                public void execute(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1,orderId);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        orderDetailsEntity  = new ViewOrderDetailsEntity();
                        System.out.println(resultSet.getInt("ID"));
                        orderDetailsEntity.setId(resultSet.getInt("ID"));
                        orderDetailsEntity.setOrderId(resultSet.getInt("ORDER_ID"));
                        orderDetailsEntity.setProductId(resultSet.getString("PRODUCT_ID"));
                        orderDetailsEntity.setProductName(resultSet.getString("PRODUCT_NAME"));
                        orderDetailsEntity.setProductType(resultSet.getString("PRODUCT_TYPE"));
                        orderDetailsEntity.setProductNum(resultSet.getLong("PRODUCT_NUM"));
                        orderDetailsEntity.setProductPrice(resultSet.getLong("PRODUCT_PRICE"));
                        list.add(orderDetailsEntity);
                    }
                }
            });
            System.out.println("查询出的订单大小是:"+list.size());
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }
    //删除订单详单
    public boolean deleteOrderDetail(Integer OrderDetailId) throws Exception {
        String hql2 = "delete  from ORDER_DETAILS where ID = ?";
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null ;
        try {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(hql2);
            query.setParameter(0,OrderDetailId);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    //更新订单
    public boolean updateOrderDetail(OrderDetailsEntity orderDetailsEntity) throws Exception {
        System.out.println("进入");
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        //更新除订单详单Id和订单Id以外的属性
        String updateSql = "update ORDER_DETAILS set PRODUCT_ID=? ,PRODUCT_NUM=?,PRODUCT_PRICE=? WHERE ID = ? ";
        try {
            transaction = session.beginTransaction();
            session.doWork(new Work() {
                PreparedStatement preparedStatement = null;
                @Override
                public void execute(Connection connection) throws SQLException {
                    preparedStatement = connection.prepareStatement(updateSql);
                    preparedStatement.setString(1,orderDetailsEntity.getProductId());
                    preparedStatement.setLong(2,orderDetailsEntity.getProductNum());
                    preparedStatement.setLong(3,orderDetailsEntity.getProductPrice());
                    preparedStatement.setInt(4,orderDetailsEntity.getId());
                    preparedStatement.executeUpdate();
                    System.out.println("更改成功");
                }
            });
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
            transaction.rollback();
            }
            throw(e);
    }
    }

    //增加订单详单
    public boolean addOrderDetail(OrderDetailsEntity orderDetailsEntity) throws Exception {
        System.out.println("进入添加详单");
        System.out.println(orderDetailsEntity.getOrderId()+" "+orderDetailsEntity.getProductId()
        +" "+orderDetailsEntity.getProductNum()+" "+orderDetailsEntity.getProductPrice());
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        //更新除订单详单Id和订单Id以外的属性
//        String insertSql = "insert into ORDER_DETAILS(ID,ORDER_ID,PRODUCT_ID,PRODUCT_NUM,PRODUCT_PRICE)" +
//                "values (?,?,?,?,?)";
        try {
            transaction = session.beginTransaction();
           /* session.doWork(new Work() {
                PreparedStatement preparedStatement = null;
                @Override
                public void execute(Connection connection) throws SQLException {
                    preparedStatement = connection.prepareStatement(insertSql);
                    preparedStatement.setInt(1,orderDetailsEntity.getOrderId());
                    preparedStatement.setString(2,orderDetailsEntity.getProductId());
                    preparedStatement.setLong(3,orderDetailsEntity.getProductNum());
                    preparedStatement.setLong(4,orderDetailsEntity.getProductPrice());
                    System.out.println("添加成功");
                    preparedStatement.execute();
                    connection.commit();
                }
            });*/
            int orderId = orderDetailsEntity.getOrderId();
            orderDetailsEntity.setOrdersByOrderId((OrdersEntity) session.get(OrdersEntity.class,orderId));
            orderDetailsEntity.setProductsByProductId((ProductsEntity) session.get(ProductsEntity.class,orderDetailsEntity.getProductId()));
            session.save(orderDetailsEntity);
            transaction.commit();
            System.out.println("新增成功");
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    @Override
    public ViewOrderDetailsEntity queryById(Integer id) throws Exception {
       /* String hql = "from OrderDetailsEntity where id = ?";
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null ;
        try {
            transaction = session.beginTransaction();
            OrderDetailsEntity employeesEntity = (OrderDetailsEntity) session.createQuery(hql).setParameter(0,id).uniqueResult();
            transaction.commit();
            return employeesEntity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return  null ;
        }
    }*/
        Session session = this.getSessionFactory().getCurrentSession();
        System.out.println("进入查询的DaoImpl" + id);
        ViewOrderDetailsEntity orderDetailsEntity = new ViewOrderDetailsEntity();
        Transaction transaction = null;
        String sql = "SELECT * FROM VIEW_ORDER_DETAILS WHERE ID = ?";
        try {
            transaction = session.beginTransaction();
            session.doWork(new Work() {
                public void execute(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, id);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        orderDetailsEntity.setId(resultSet.getInt("ID"));
                        orderDetailsEntity.setOrderId(resultSet.getInt("ORDER_ID"));
                        orderDetailsEntity.setProductId(resultSet.getString("PRODUCT_ID"));
                        orderDetailsEntity.setProductName(resultSet.getString("PRODUCT_NAME"));
                        orderDetailsEntity.setProductType(resultSet.getString("PRODUCT_TYPE"));
                        orderDetailsEntity.setProductNum(resultSet.getLong("PRODUCT_NUM"));
                        orderDetailsEntity.setProductPrice(resultSet.getLong("PRODUCT_PRICE"));
                    }
                }
            });
            transaction.commit();
            return orderDetailsEntity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    @Override
    public int countAll() throws Exception{
        Transaction transaction = null ;
//        List<OrdersEntity> list = null ;
        String sql = "select max (id) from OrderDetailsEntity";
        Session session = null;
        try {
            session = this.getSessionFactory().getCurrentSession();
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
}
