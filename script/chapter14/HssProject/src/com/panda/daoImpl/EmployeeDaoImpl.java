package com.panda.daoImpl;

import com.panda.dao.EmployeeDao;
import com.panda.po.DepartmentsEntity;
import com.panda.po.EmployeesEntity;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.*;
import org.hibernate.jdbc.Work;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/27.
 * TIME:14:39.
 */
@Repository("employee")
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {
    //注入sessionFactory
    @Resource(name = "sessionFactory")
    public void setSF(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    //添加员工
    public void addEmployee(EmployeesEntity employeesEntity)  throws Exception {
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            if (employeesEntity.getManagerId() != null) {
                Long managerId = employeesEntity.getManagerId();
                employeesEntity.setEmployeesByManagerId((EmployeesEntity) session.get(EmployeesEntity.class, managerId));
            }
            Long departmrntId = employeesEntity.getDepartmentId();
            employeesEntity.setDepartmentsByDepartmentId((DepartmentsEntity) session.get(DepartmentsEntity.class, departmrntId));
            session.save(employeesEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw (e);
        }
    }

    @Override
    public List<EmployeesEntity> queryEmployee()  throws Exception{
        /*
            离线查询所有员工信息
         */
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null ;
        List<EmployeesEntity> list = null ;
        String sql = "from EmployeesEntity";
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
            throw (e);
        }
    }
    //分页查询所有员工,pageNow是要查询的页码，从1开始
    public List<EmployeesEntity> queryEmployee(int pageSize,int pageNow) throws Exception {
        /*DetachedCriteria employeesEntity = DetachedCriteria.forClass(EmployeesEntity.class);
        List<EmployeesEntity> list = (List<EmployeesEntity>) this.getHibernateTemplate().findByCriteria(employeesEntity);*/
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null ;
        List<EmployeesEntity> list = null ;
        String sql = "from EmployeesEntity";
        try {
            transaction = session.beginTransaction() ;
            Query query = session.createQuery(sql);
            query.setFirstResult((pageNow-1)*pageSize);
            query.setMaxResults(pageSize);
            list = query.list();
            //依次设定每个员工是否包含下属
            for (EmployeesEntity emp : list) {
                sql = "select count(*) cnt from employees where MANAGER_ID=" + emp.getEmployeeId();
                int cnt = (int) session.createSQLQuery(sql).addScalar("cnt", StandardBasicTypes.INTEGER).uniqueResult();
                if (cnt > 0)
                    emp.setHasEmployee(1);
            }
            transaction.commit();
        } catch (Exception e) {
            if(transaction!=null)
                transaction.rollback();
            throw(e);
        }
        return  list;
    }
    //利用存储过程分页查询员工
public List<EmployeesEntity> queryEmployeeByProcedure(int pageSize, int pageNow) throws Exception {
    SessionFactory sessionFactory = this.getSessionFactory();
    List<EmployeesEntity> list = new ArrayList<EmployeesEntity>();
    Session session = this.getSessionFactory().getCurrentSession();
    Connection conn=null;
    conn = SessionFactoryUtils.getDataSource(this.getSessionFactory()).getConnection();
    CallableStatement   statement = conn.prepareCall("{Call MyPack.Get_EmployeeByPage_P(?,?,?) }");
    statement.setInt(1, pageNow);
    statement.setInt(2, pageSize);
    statement.registerOutParameter(3, OracleTypes.CURSOR);
    statement.execute();
    ResultSet resultSet = (ResultSet) statement.getObject(3);
    EmployeesEntity employeesEntity = null;
    while (resultSet!=null && resultSet.next()) {
        employeesEntity = new EmployeesEntity();
        employeesEntity.setEmployeeId(resultSet.getLong("EMPLOYEE_ID"));
        employeesEntity.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
        employeesEntity.setName(resultSet.getString("NAME"));
        employeesEntity.setHireDate(resultSet.getDate("HIRE_DATE"));
        employeesEntity.setDepartmentId(resultSet.getLong("DEPARTMENT_ID"));
        employeesEntity.setEmail(resultSet.getString("EMAIL"));
        employeesEntity.setSalary(resultSet.getLong("SALARY"));
        long managerID = resultSet.getLong("MANAGER_ID");
        if(resultSet.wasNull()==false)
            employeesEntity.setManagerId(managerID);
        else
            employeesEntity.setManagerId(null);
        employeesEntity.setPhoto(resultSet.getBytes("PHOTO"));
        list.add(employeesEntity);
    }
    statement.close();
    //依次设定每个员工是否包含下属
    //String sql;
    //sql = "select count(*) cnt from employees where MANAGER_ID=" + emp.getEmployeeId();
    PreparedStatement   statement2;
    statement2 = conn.prepareStatement("select count(*) cnt from employees where MANAGER_ID=?");
    for (EmployeesEntity emp : list) {
        statement2.setLong(1, emp.getEmployeeId());
        resultSet=statement2.executeQuery();
        resultSet.next();
        int cnt=parseInt(resultSet.getObject(1).toString());
        if (cnt > 0)
            emp.setHasEmployee(1);
    }
    statement2.close();
    return list;
}

    //根据传入的员工id，查询的单个员工
    public EmployeesEntity queryEmployeeByEmployeeId(Long employeeId)  throws Exception{
        if (employeeId == null) {
            throw new NullPointerException("传入员工id为空");
        }
        //离线查询
        String hql = "from EmployeesEntity where employeeId = ?";
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null ;
        try {
            transaction = session.beginTransaction();
            EmployeesEntity employeesEntity = (EmployeesEntity) session.createQuery(hql).setParameter(0,employeeId).uniqueResult();
            transaction.commit();
            return employeesEntity;
        } catch (Exception e) {
            transaction.rollback();
            throw (e);
        }

    }

    //删除某个员工
    public void deleteEmployee(Long employeeId) throws Exception {
        System.out.println("传入的职工ID是"+employeeId);
        if (employeeId == null) {
            throw new NullPointerException("传入员工id为空");
        }
//        采用sql语句删除
        String hql2 = "delete  from EMPLOYEES where EMPLOYEE_ID = ?";
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null ;
        try {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(hql2);
            query.setParameter(0,employeeId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw (e);
        }
    }

    //更新员工信息
    public void updateEmployee(EmployeesEntity employeesEntity) throws Exception {
        System.out.println("进入updateEmployee()");
        Session session = this.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        String updateSql = "update EMPLOYEES set NAME=? ,EMAIL=?,PHONE_NUMBER=?,HIRE_DATE=?,SALARY=?," +
            " MANAGER_ID =? ,DEPARTMENT_ID =? ,PHOTO=? WHERE EMPLOYEE_ID = ? ";
        try {
            transaction = session.beginTransaction();
            session.doWork(new Work() {
                PreparedStatement preparedStatement = null;
                @Override
                public void execute(Connection connection) throws SQLException {
                    preparedStatement = connection.prepareStatement(updateSql);
                    preparedStatement.setString(1,employeesEntity.getName());
                    preparedStatement.setString(2,employeesEntity.getEmail());
                    preparedStatement.setString(3,employeesEntity.getPhoneNumber());
                    preparedStatement.setDate(4,new java.sql.Date(employeesEntity.getHireDate().getTime()));
                    preparedStatement.setLong(5,employeesEntity.getSalary());
                    if(employeesEntity.getManagerId()==null)//员工的管理员ID可以为空
                        preparedStatement.setNull(6,Types.INTEGER);
                    else
                        preparedStatement.setLong(6,employeesEntity.getManagerId());
                    preparedStatement.setLong(7,employeesEntity.getDepartmentId());
                    preparedStatement.setBytes(8,employeesEntity.getPhoto());
                    preparedStatement.setLong(9,employeesEntity.getEmployeeId());
                    preparedStatement.executeUpdate();
                    }
            });
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw (e);
        }
    }
}
