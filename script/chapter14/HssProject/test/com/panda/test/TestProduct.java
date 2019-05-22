package com.panda.test;

import com.panda.po.ProductsEntity;
import com.panda.service.ProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/29.
 * TIME:15:17.
 */
public class TestProduct {
    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("spring/applicationContext.xml");
        ProductService productService = (ProductService) applicationContext.getBean("productService");
        /*
        测试商品的查询
        */
        List<ProductsEntity> list = productService.queryProduct();
        for (ProductsEntity productsEntity : list) {
            System.out.println(productsEntity.getProductId());
        }
        List<ProductsEntity> list2 = productService.queryProduct(5, 1);
        for (ProductsEntity productsEntity : list2) {
            System.out.println(productsEntity.getProductId());
        }
        ProductsEntity productsEntity = productService.queryProductByProductId("phone2");
        System.out.print(productsEntity.getProductName());

        /*
        测试商品的删除
        */
        //   productService.deleteProduct("ab");
        /*
        测试商品的添加
        ProductsEntity productsEntity = new ProductsEntity();
        productsEntity.setProductId("pager4");
        productsEntity.setProductName("耗材4");
        productsEntity.setProductType("耗材");
        productService.addProduct(productsEntity);
        */


    }
}
