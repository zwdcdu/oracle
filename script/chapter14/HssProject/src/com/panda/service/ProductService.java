package com.panda.service;

import com.panda.po.ProductsEntity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/29.
 * TIME:15:18.
 */
public interface ProductService {
    void addProduct(ProductsEntity productsEntity) throws Exception;//添加商品
    List<ProductsEntity> queryProduct(int pageSize, int pageNow) throws Exception;//分页查询所有商品
    List<ProductsEntity> queryProduct() throws Exception;//查询所有商品
    ProductsEntity queryProductByProductId(String productId) throws Exception;//根据id查询单个商品
    void  deleteProduct(String productId) throws Exception;//删除商品
    void  updateProduct(ProductsEntity productsEntity) throws Exception;//修改商品
    boolean checkProduct(String productId) throws Exception;//查看是否能够删除商品
}
