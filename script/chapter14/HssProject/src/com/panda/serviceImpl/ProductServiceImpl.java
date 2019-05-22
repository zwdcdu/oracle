package com.panda.serviceImpl;

import com.panda.dao.ProductDao;
import com.panda.po.ProductsEntity;
import com.panda.service.ProductService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/29.
 * TIME:15:19.
 */
@Component(value = "productService")
public class ProductServiceImpl implements ProductService {
    @Resource(name = "productDao")
    private ProductDao productDao ;
    //调用dao层的添加商品的方法
    public void addProduct(ProductsEntity productsEntity) throws Exception {
        this.productDao.addProduct(productsEntity);
    }

    //调用dao层的分页查询
    public List<ProductsEntity> queryProduct(int pageSize, int pageNow) throws Exception {
        return this.productDao.queryProduct(pageSize,pageNow);
    }

    //调用dao层的查询
    public List<ProductsEntity> queryProduct() throws Exception {
        return this.productDao.queryProduct();
    }

    //调用dao层的根据Id查询
    public ProductsEntity queryProductByProductId(String productId) throws Exception{
        return this.productDao.queryProductByProductId(productId);
    }

    //调用dao层的根据Id删除
    public void deleteProduct(String productId) throws Exception {
        this.productDao.deleteProduct(productId);
    }

    //调用dao层的删除
    public void updateProduct(ProductsEntity productsEntity) throws Exception {
        this.productDao.updateProduct(productsEntity);
    }

    @Override
    public boolean checkProduct(String productId) throws Exception {
        return this.productDao.checkProduct(productId);
    }
}
