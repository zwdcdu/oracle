package com.panda.action;

import com.opensymphony.xwork2.ActionSupport;
import com.panda.po.DepartmentsEntity;
import com.panda.po.EmployeesEntity;
import com.panda.po.ProductsEntity;
import com.panda.service.DepartmentService;
import com.panda.service.EmployeeService;
import com.panda.service.ProductService;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/26.
 * TIME:11:49.
 */

public class LoginAction extends ActionSupport {
    private String username ;
    private String password ;
    @Resource(name = "employeeService")
    private EmployeeService employeeService ;
    @Resource(name = "departmentService")
    private DepartmentService departmentService ;
    @Resource(name = "productService")
    private ProductService productService;
    private  HttpServletRequest request = null ;

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String execute() throws Exception {
        if (username == null || password == null) {
            addActionError("用户名或密码为空");
            return INPUT;
        }
        if ("".equals(username) || "".equals(password)) {
            addActionError("用户名或密码为空");
            return INPUT;
        }
        if ("admin".equals(username) && "admin".equals(password)) {
            request = ServletActionContext.getRequest();
            request.getSession().setAttribute("user", username);
            List<EmployeesEntity> lists = employeeService.queryEmployee();
            List<DepartmentsEntity> list2 = departmentService.queryDepartments();
            List<ProductsEntity> list3 = productService.queryProduct();
            request.getSession().setAttribute("employees", lists);
            request.getSession().setAttribute("departments", list2);
            request.getSession().setAttribute("products2", list3);
            System.out.println(request.getSession().getAttribute("user"));
            return SUCCESS;
        } else {
            throw new Exception("登录错误");
        }
    }
}
