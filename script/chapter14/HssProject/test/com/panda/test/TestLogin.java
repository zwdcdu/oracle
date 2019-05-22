package com.panda.test;

import com.panda.po.DepartmentsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/26.
 * TIME:12:37.
 */
public class TestLogin {
    @Test
    public void test() throws Exception{
       /* ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("spring/applicationContext.xml");
        LoginAction loginAction = (LoginAction) applicationContext.getBean("login");
        System.out.print(loginAction);*/
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("spring/applicationContext.xml");
        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        DepartmentsEntity departmentsEntity = (DepartmentsEntity) session.get(DepartmentsEntity.class,11L);
        System.out.print(departmentsEntity);
    }
}
