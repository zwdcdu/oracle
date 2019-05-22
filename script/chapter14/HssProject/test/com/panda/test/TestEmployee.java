package com.panda.test;

import com.panda.dao.EmployeeDao;
import com.panda.po.DepartmentsEntity;
import com.panda.po.EmployeesEntity;
import com.panda.service.EmployeeService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/27.
 * TIME:15:27.
 */
public class TestEmployee {
    @Test
    public void test() throws Exception{
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("spring/applicationContext.xml");
        EmployeeDao employeeService = (EmployeeDao) applicationContext.getBean("employee");
        /*List<EmployeesEntity> list = employeeService.queryEmployee(5,1);
        for (EmployeesEntity employeesEntity : list) {
            System.out.println(employeesEntity.getEmployeeId());
        }*/

        //添加员工的操作
       /* SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
//        //添加员工
        EmployeesEntity employeesEntity = new EmployeesEntity();
        employeesEntity.setEmployeeId(18L);
        employeesEntity.setName("沈诚");
        employeesEntity.setEmail("974927257@qq.com");
        employeesEntity.setPhoneNumber("18482334895");
        employeesEntity.setSalary(29854L);
        employeesEntity.setHireDate(new Date());
        employeesEntity.setEmployeesByManagerId((EmployeesEntity) session.get(EmployeesEntity.class,1L));
        DepartmentsEntity departmentsEntity = (DepartmentsEntity) session.get(DepartmentsEntity.class,1L);
        departmentsEntity.getEmployeesByDepartmentId().add(employeesEntity);
//        session.save(departmentsEntity);
        employeesEntity.setDepartmentsByDepartmentId(departmentsEntity);
        session.save(employeesEntity);
        transaction.commit();*/

       /*查询单个员工
         EmployeesEntity employeesEntity = employeeService.queryEmployeeByEmployeeId(16L);
         System.out.print(employeesEntity.getName());*/

       /*删除单个员工
         employeeService.deleteEmployee(18L);*/
       /*
       *更新员工信息
       */
//        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        EmployeesEntity employeesEntity = new EmployeesEntity();
//        employeesEntity.setEmployeeId(18L);
//        employeesEntity.setName("沈诚");
//        employeesEntity.setEmail("974927257@qq.com");
//        employeesEntity.setPhoneNumber("18482334895");
//        employeesEntity.setSalary(29854L);
//        employeesEntity.setHireDate(new Date());
//        employeesEntity.setEmployeesByManagerId((EmployeesEntity) session.get(EmployeesEntity.class,1L));
//        DepartmentsEntity departmentsEntity = (DepartmentsEntity) session.get(DepartmentsEntity.class,1L);
//        departmentsEntity.getEmployeesByDepartmentId().add(employeesEntity);
//        employeesEntity.setDepartmentsByDepartmentId(departmentsEntity);
//        session.save(employeesEntity);
//        transaction.commit();
        EmployeesEntity employeesEntity = employeeService.queryEmployeeByEmployeeId(112L);
        employeesEntity.setName("章义杰");
        employeesEntity.setManagerId(employeeService.queryEmployeeByEmployeeId(12L).getEmployeeId());
        employeeService.updateEmployee(employeesEntity);

    }
}
