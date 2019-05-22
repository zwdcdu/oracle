package com.panda.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.panda.po.EmployeesEntity;
import com.panda.service.EmployeeService;
import com.panda.util.BaseFunction;
import com.panda.util.PagerUtil;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/28.
 * TIME:18:41.
 */
/*处理员工信息的Action*/
public class EmployeeAction extends ActionSupport {
    private EmployeesEntity employeesEntity ;
    @Resource(name = "employeeService")
    private EmployeeService employeeService ;
    private String hireDate ;
    private long employeeId;
    private int i = 0 ;
    private byte[] bytes = null ;
    //struts上传文件形式，必须有File参数，photo是字段名
    private File photo;
    private String photoFileName;
    private String photoContentType;

    private int pageNow = 1 ;
    private int pageSize = 6;

    public EmployeeAction(){}

    public String addEmployees() throws Exception {
        //Date date = new Date(hireDate);
        Date  date = BaseFunction.StringToDate(hireDate);
        employeesEntity.setHireDate(date);
        System.out.println(hireDate+"  " + date);
        if (photo != null) {
            FileInputStream in = new FileInputStream(photo);
            bytes = new byte[in.available()];
            i = in.read(bytes);
            employeesEntity.setPhoto(bytes);
            System.out.println("有文件内容");
            employeeService.addEmployee(employeesEntity);
            System.out.println("可以处理照片");
            return  SUCCESS;
        }
        else {
            employeeService.addEmployee(employeesEntity);
            return SUCCESS;
        }

    }
    /*get、set方法*/
    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public EmployeesEntity getEmployeesEntity() {
        return employeesEntity;
    }

    public void setEmployeesEntity(EmployeesEntity employeesEntity) {
        this.employeesEntity = employeesEntity;
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

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }
}
