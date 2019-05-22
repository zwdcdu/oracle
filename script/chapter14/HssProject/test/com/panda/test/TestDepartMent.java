package com.panda.test;

import com.panda.po.DepartmentsEntity;
import com.panda.service.DepartmentService;
import com.panda.service.ProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/5/10.
 * TIME:10:43.
 */
public class TestDepartMent {
    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("spring/applicationContext.xml");
        DepartmentService productService = (DepartmentService) applicationContext.getBean("departmentService");
//      System.out.print(productService.countDepartments());
         /* DepartmentsEntity departmentsEntity = new DepartmentsEntity();
        departmentsEntity.setDepartmentId(123);
        departmentsEntity.setDepartmentName("总经办2");
        productService.updateDepartment(departmentsEntity);*/
        /*List<DepartmentsEntity> list = productService.queryDepartmentEntity(2,1);
        System.out.print(list.size());*/
        System.out.print(productService.queryById(1L).getDepartmentName());
    }
}
