package com.panda.daoImpl;

import com.panda.dao.DepartmentDao;
import com.panda.po.DepartmentsEntity;
import com.panda.po.EmployeesEntity;
import org.hibernate.*;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/6.
 * TIME:11:20.
 */
@Repository("departmentDao")
public class DepartmentDaoImpl  extends HibernateDaoSupport implements DepartmentDao{
    //注入sessionFactory
    @Resource(name = "sessionFactory")
    public void setSF(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    //离线查询所有部门
    public List<DepartmentsEntity> queryDepartments() throws Exception {
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null ;
        List<DepartmentsEntity> list = null ;
        String sql = "from DepartmentsEntity";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(sql);
            list = query.list();

            //设置每个部门是否有所属员工
            for(DepartmentsEntity d: list) {
                sql = "select count(*) cnt from employees where DEPARTMENT_ID=" + d.getDepartmentId();
                int cnt = (int) session.createSQLQuery(sql).addScalar("cnt", StandardBasicTypes.INTEGER).uniqueResult();
                if (cnt > 0)
                    d.setHasEmployee(1);
            }
            transaction.commit();
            return list;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

   //增加部门
    public boolean addDepartmentEntity(DepartmentsEntity departmentsEntity) throws Exception {
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null ;
        try {
            transaction = session.beginTransaction();
            session.save(departmentsEntity);
            transaction.commit();
            return true;
        }catch (Exception  e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    //删除部门
    public boolean deleteDepartmentEntity(Long departmentId) throws Exception{
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null ;
        String hql2 = "delete  from DEPARTMENTS where DEPARTMENT_ID = ?";
        try {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(hql2);
            query.setParameter(0,departmentId);
            query.executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    //根据部门Id查询
    public DepartmentsEntity queryById(Long departmentId) throws Exception {
        String hql = "from DepartmentsEntity where departmentId = ?";
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null ;
        try {
            transaction = session.beginTransaction();
            DepartmentsEntity employeesEntity = (DepartmentsEntity) session.createQuery(hql).setParameter(0,departmentId).uniqueResult();
            transaction.commit();
            return employeesEntity;
        } catch (Exception e) {
            if (transaction != null ) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    //分页查询部门
    public List<DepartmentsEntity> queryDepartmentEntity(int pageSize, int pageNow) throws Exception{
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null ;
        List<DepartmentsEntity> list = null ;
        String sql = "from DepartmentsEntity";
        try {
            transaction = session.beginTransaction() ;
            Query query = session.createQuery(sql);
            query.setFirstResult((pageNow-1)*pageSize);
            query.setMaxResults(pageSize);
            list = query.list();
            transaction.commit();
            return  list;
        } catch (Exception e) {
            transaction.rollback();
            throw(e);
        }
    }

    //更新部门
    public boolean updateDepartment(DepartmentsEntity departmentsEntity) throws Exception {
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null ;
        try {
            transaction = session.beginTransaction();
            DepartmentsEntity departmentsEntity1 = this.getHibernateTemplate().get(DepartmentsEntity.class,departmentsEntity.getDepartmentId());
            departmentsEntity1.setDepartmentName(departmentsEntity.getDepartmentName());
            session.update(departmentsEntity1);
            transaction.commit();
            return  true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }

    //统计部门数量
    public Integer countDepartments() throws Exception  {
        Transaction transaction = null ;
        String sql = "select count(*) from DepartmentsEntity";
        Session session = null;
        try {
            session = this.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(sql);
            Number num = ((Number)query.uniqueResult()).intValue();
            transaction.commit();
            return (Integer) num;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw(e);
        }
    }
}
