package com.panda.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.panda.po.ProductsEntity;
import com.panda.service.ProductService;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/9.
 * TIME:10:57.
 */
//用户编辑商品的Action
public class EditProductAction extends ActionSupport {
    @Resource(name = "productService")
    private ProductService productService ;
    private ProductsEntity productsEntity;

    public String execute() throws Exception {
        System.out.println("商品的编号是:"+productsEntity.getProductId().trim());
        if (productsEntity.getProductType() == null || "".equals(productsEntity.getProductType()) ||
                productsEntity.getProductName() == null || "".equals(productsEntity.getProductName())) {
            this.addActionError("请传入空缺参数");
            return  INPUT  ;
        }
        productService.updateProduct(productsEntity);
        return SUCCESS ;
    }

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

}
