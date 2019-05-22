package com.panda.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.panda.po.EmployeesEntity;
import com.panda.po.ProductsEntity;
import com.panda.service.EmployeeService;
import com.panda.service.ProductService;
import com.panda.util.PagerUtil;
import org.apache.struts2.ServletActionContext;
import org.aspectj.org.eclipse.jdt.core.IField;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/7.
 * TIME:18:28.
 */
public class ProductListAction extends ActionSupport {
    @Resource(name = "productService")
    private ProductService productService ;

    private ActionContext actionContext = ActionContext.getContext();

    private int pageNow = 1 ;
    private int pageSize = 6;

    private String productId ;

    public ProductListAction(){}

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    //指定处理商品列表方法
    public String execute() throws Exception {
        System.out.println("进入商品列表界面");
        try {
            List<ProductsEntity> list = productService.queryProduct();
            //构造page对象并存进session
            PagerUtil pagerUtil = new PagerUtil(pageNow,list.size());
            pagerUtil.setPageSize(getPageSize());

            List<ProductsEntity> list2 = productService.queryProduct(pageSize,pageNow);
            for (ProductsEntity o:list2) {
                if (productService.checkProduct(o.getProductId())) {
                    o.setCheck(1);
                }
            }
            HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
            //将pager属性和得到的员工集合存储在request中
            request.getSession().setAttribute("pager",pagerUtil);
            request.getSession().setAttribute("products",list2);
            System.out.println("商品集合长度:"+list2.size());
            return SUCCESS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
            //return ERROR;
        }
    }

    //删除商品
    public String deleteProduct() throws  Exception {
        if ("".equals(productId) || productId == null) {
            throw new Exception("productID不能为空");
        }
        productService.deleteProduct(productId);
        return SUCCESS ;
    }

    //跳转到商品编辑页面
    public String redirectShow() throws Exception {
        if ("".equals(productId) || productId == null) {
            throw new Exception("productID不能为空");
        }
        try {
            ProductsEntity productsEntity = productService.queryProductByProductId(productId);
            HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
            request.getSession().setAttribute("poduct2",productsEntity);
            return  SUCCESS ;
        } catch (Exception e) {
            throw (e);
            //return ERROR;
        }

    }
}
