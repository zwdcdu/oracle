package com.panda.action;

import com.opensymphony.xwork2.ActionSupport;
import com.panda.po.EmployeesEntity;
import com.panda.service.EmployeeService;
import com.panda.util.BaseFunction;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/6.
 * TIME:14:56.
 */
//处理编辑员工的Action
public class EditEmployeeAction extends ActionSupport {
    private EmployeesEntity employeesEntitys ;
    @Resource(name = "employeeService")
    private EmployeeService employeeService ;
    private String hireDate ;
    private int i = 0 ;
    private byte[] bytes = null ;
    //struts上传文件形式，必须有File参数，photo是字段名
    private File photo;
    private String photoFileName;
    private String photoContentType;

    public EditEmployeeAction(){}

    public String editEmployee() throws Exception {
        System.out.println("编辑员工2：editEmployee()");
        /*Calendar calendar = Calendar.getInstance();
        String[] strs = hireDate.split("-");
        calendar.set(Calendar.YEAR,Integer.parseInt(strs[0]));
        calendar.set(Calendar.MONTH,Integer.parseInt(strs[1])-1);
        calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(strs[2]));
        employeesEntitys.setHireDate(calendar.getTime());*/
        //Date date = new Date(hireDate);
        Date  date = BaseFunction.StringToDate(hireDate);
        employeesEntitys.setHireDate(date);
        if (photo != null) {
            FileInputStream in = new FileInputStream(photo);
            bytes = new byte[in.available()];
            i = in.read(bytes);
            //如果有文件，并且成功写入byte[]数组，那么employeesEntity对象的属性都赋值完成，即可插入数据库
            employeesEntitys.setPhoto(bytes);
            System.out.println("有文件内容");
            employeeService.updateEmployee(employeesEntitys);
            System.out.println("可以处理");
            return  SUCCESS;
        }
        else  {
            employeeService.updateEmployee(employeesEntitys);
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

    public EmployeesEntity getEmployeesEntitys() {
        return employeesEntitys;
    }

    public void setEmployeesEntitys(EmployeesEntity employeesEntitys) {
        this.employeesEntitys = employeesEntitys;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }
}
