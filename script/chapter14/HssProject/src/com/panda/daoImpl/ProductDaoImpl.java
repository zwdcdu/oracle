package com.panda.daoImpl;

import com.panda.dao.ProductDao;
import com.panda.po.EmployeesEntity;
import com.panda.po.OrderDetailsEntity;
import com.panda.po.ViewOrderDetailsEntity;
import com.panda.po.ProductsEntity;
import org.eclipse.core.internal.runtime.Product;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.jdbc.Work;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/29.
 * TIME:14:55.
 */
@Repository("productDao")
public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao {
    //注入sessionFactory
    @Resource(name = "sessionFactory")
    public void setSF(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    //添加商品
    public void addProduct(ProductsEntity productsEntity) throws Exception {
        Session session = null ;
        Transaction transaction = null ;
        if (productsEntity == null)
            throw new NullPointerException("传入对象为空");
        else {
            try{
                session = this.getSessionFactory().getCurrentSession();
                transaction = session.beginTransaction();
                session.save(productsEntity);
                transaction.commit();
            }catch (Exception e) {//出现异常，事务回滚
                if (transaction != null) {
                    transaction.rollback();
                }
                throw (e);
            }
        }
    }
    //分页查询商品
    public List<ProductsEntity> queryProduct(int pageSize, int pageNow) throws Exception {
        Session session = this.getSessionFactory().getCurrentSession();
        List<ProductsEntity> list = null ;
        String sql = "from ProductsEntity ";
        Transaction transaction = null ;
        try {
            transaction = session.beginTransaction();
            Query querys = session.createQuery(sql);
            querys.setFirstResult((pageNow-1)*pageSize);//设置一次查询的数量
            querys.setMaxResults(pageSize);//设置查询的起始序号
            list = querys.list();
            transaction.commit();
            return  list;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    public List<ProductsEntity> queryProduct() throws Exception {
        /*查询所有商品*/
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null ;
        List<ProductsEntity> list = null ;
        String sql = "from ProductsEntity";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(sql);
            list = query.list();
            transaction.commit();
            return list;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    //根据商品Id查询商品
    public ProductsEntity queryProductByProductId(String productId) throws Exception {
        if (productId == null) {
            throw new NullPointerException("传入商品id为空");
        }
        //离线查询
        String hql = "from ProductsEntity where productId = ?";
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null ;
        try {
            transaction = session.beginTransaction() ;
            ProductsEntity productsEntity = (ProductsEntity) session.createQuery(hql).setParameter(0,productId).uniqueResult();
            transaction.commit();
            return productsEntity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }

    }

    public void deleteProduct(String productId) throws Exception {
        if (productId == null || "".equals(productId)) {
            throw new NullPointerException("传入员工id为空");
        }
        //采用sql语句删除
        String hql2 = "from ProductsEntity where productId = ?";
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        ProductsEntity productsEntity = (ProductsEntity) session.createQuery(hql2).setParameter(0,productId).uniqueResult();
        session.delete(productsEntity);
        transaction.commit();
    }

    public void updateProduct(ProductsEntity productsEntity) throws Exception {
        System.out.println("进入"+productsEntity.getProductId()+productsEntity.getProductName()+productsEntity.getProductType());
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        String updateSql = "update PRODUCTS set PRODUCT_NAME=? ,PRODUCT_TYPE=? WHERE PRODUCT_ID = ? ";
        try {
            transaction = session.beginTransaction();
            session.doWork(new Work() {
                PreparedStatement preparedStatement = null;
                @Override
                public void execute(Connection connection) throws SQLException {
                    preparedStatement = connection.prepareStatement(updateSql);
                    preparedStatement.setString(1,productsEntity.getProductName());
                    preparedStatement.setString(2,productsEntity.getProductType());
                    preparedStatement.setString(3,productsEntity.getProductId().trim());
                    preparedStatement.executeUpdate();
                }
            });
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    @Override
    public boolean checkProduct(String productId) throws Exception {
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        //产看当前产品是否被卖出，即在订单详单表中有记录，那么该产品不能更改和删除
        String updateSql = "select * from VIEW_ORDER_DETAILS where PRODUCT_ID = ? and rownum < 2";
        List<ViewOrderDetailsEntity> list = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            session.doWork(new Work() {
                ViewOrderDetailsEntity orderDetailsEntity = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                @Override
                public void execute(Connection connection) throws SQLException {
                    preparedStatement = connection.prepareStatement(updateSql);
                    preparedStatement.setString(1,productId);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        orderDetailsEntity = new ViewOrderDetailsEntity();
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
            transaction.commit();
            if (list.size() > 0) {
                return true;
            }else
                return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }
}
