package com.panda.action;

import com.opensymphony.xwork2.ActionSupport;
import com.panda.po.ProductsEntity;
import com.panda.service.ProductService;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/10.
 * TIME:9:24.
 */
public class ProductAddAction extends ActionSupport {
    @Resource(name = "productService")
    private ProductService productService ;
    private ProductsEntity productsEntity;

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ProductsEntity getProductsEntity() {
        return productsEntity;
    }

    public void setProductsEntity(ProductsEntity productsEntity) {
        this.productsEntity = productsEntity;
    }
    @Override
    public void validate() {
        //System.out.println("run validate...");
    }
    //添加商品
    public String execute() throws Exception {
        productService.addProduct(productsEntity);
        return SUCCESS;
        /* 由于引用了自动验证功能，下面的代码就不需要了。见ProductAddAction-validation.xml中的信息
        System.out.println("进入新增商品界面"+productsEntity.getProductId());
        if (productsEntity.getProductId() == null || "".equals(productsEntity.getProductId()) ||
                productsEntity.getProductName() == null || "".equals(productsEntity.getProductName())) {
            this.addActionError("传入空缺参数");
            return INPUT;
        }else {
            productService.addProduct(productsEntity);
            return SUCCESS;
        }*/
    }
}
