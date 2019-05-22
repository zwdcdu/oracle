package com.panda.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.panda.po.EmployeesEntity;
import com.panda.service.EmployeeService;
import com.panda.util.PagerUtil;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.management.Query;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/28.
 * TIME:18:41.
 */
/*处理员工信息的Action*/
public class EmployeeListAction extends ActionSupport {
    @Resource(name = "employeeService")
    private EmployeeService employeeService ;

    private ActionContext actionContext = ActionContext.getContext();

    private int pageNow = 1 ;
    private int pageSize = 6;

    private long employeeId ;

    public EmployeeListAction(){}

    //指定处理员工列表方法
    public String execute() throws Exception{
        List<EmployeesEntity> list = employeeService.queryEmployee();
        //构造page对象并存进session
        PagerUtil pagerUtil = new PagerUtil(pageNow,list.size());
        pagerUtil.setPageSize(getPageSize());

        List<EmployeesEntity> list2 = employeeService.queryEmployeeByProcedure(getPageSize(),getPageNow());
        //System.out.println(list2.size());
        HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        //将pager属性和得到的员工集合存储在request中
        request.getSession().setAttribute("pager",pagerUtil);
        request.getSession().setAttribute("employees1",list2);
        return SUCCESS;

    }

    public String showEmployeeById() throws Exception{
        if (employeeId == 0) {
            throw new Exception("员工ID不能为0");
        }
        EmployeesEntity employeesEntity = employeeService.queryEmployeeByEmployeeId(employeeId);
        if (employeesEntity.getEmployeesByManagerId() != null) {
            employeesEntity.setManagerId(employeesEntity.getEmployeesByManagerId().getEmployeeId());
        }
        employeesEntity.setDepartmentId(employeesEntity.getDepartmentsByDepartmentId().getDepartmentId());
        HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        request.getSession().setAttribute("employee1",employeesEntity);
        return SUCCESS ;
    }

    //跳转到编辑员工界面
    public String editEmployeeById() throws Exception {
        System.out.println("到达编辑的Action,编辑员工1：editEmployeeById()");
        if (employeeId == 0) {
            throw new Exception("员工ID不能为0");
        }
        EmployeesEntity employeesEntity = employeeService.queryEmployeeByEmployeeId(employeeId);
        if (employeesEntity.getEmployeesByManagerId() != null) {
            employeesEntity.setManagerId(employeesEntity.getEmployeesByManagerId().getEmployeeId());
        }
        employeesEntity.setDepartmentId(employeesEntity.getDepartmentsByDepartmentId().getDepartmentId());
        HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        request.getSession().setAttribute("employee2",employeesEntity);
        List<EmployeesEntity> lists = (List<EmployeesEntity>)request.getSession().getAttribute("employees");
        List<String> ListEmployeesID = new ArrayList<String>(); ;
        ListEmployeesID.add("");
        for(EmployeesEntity  data : lists) {
            if (data.getEmployeeId()!=employeesEntity.getEmployeeId())
                ListEmployeesID.add(String.valueOf(data.getEmployeeId()));
        }
        request.getSession().setAttribute("ListEmployeesID",ListEmployeesID);
        return SUCCESS ;
    }

//删除员工
public String deleteEmployee() throws Exception {
    System.out.println("Action得到的员工ID是:"+employeeId);
    if (getEmployeeId() == 0) {
        throw new Exception("员工ID不能为0");
    }
    employeeService.deleteEmployee(employeeId);
    return SUCCESS;
}
    public String showImage() throws Exception{
        HttpServletResponse response = null;
        ServletOutputStream out = null;
        try {
            EmployeesEntity employeesEntity = employeeService.queryEmployeeByEmployeeId(employeeId);
            response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
            response.setContentType("image/*");//设置输出格式为image类型
            out = response.getOutputStream();//获得输出流
            if (employeesEntity.getPhoto() != null) {
                out.write(employeesEntity.getPhoto());
            }
        } catch (Exception e) {
            throw (e);
            //return ERROR;
        } finally {
            if (out != null) {
                try {
                    out.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null ;
    }

    /*get、set方法*/
    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
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

}
