package com.panda.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.panda.po.DepartmentsEntity;
import com.panda.service.DepartmentService;
import org.apache.struts2.ServletActionContext;
import org.hibernate.exception.ConstraintViolationException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/5/10.
 * TIME:14:19.
 */
public class DepartmentListAction extends ActionSupport {
    @Resource(name = "departmentService")
    private DepartmentService departmentService ;
    private long departmentId ;
    private DepartmentsEntity departmentsEntity ;
    private ActionContext actionContext = ActionContext.getContext();

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public DepartmentsEntity getDepartmentsEntity() {
        return departmentsEntity;
    }

    public void setDepartmentsEntity(DepartmentsEntity departmentsEntity) {
        this.departmentsEntity = departmentsEntity;
    }


    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public DepartmentListAction() {}

    //查询出所有的部门
    public  String showAllList() throws Exception {
        List<DepartmentsEntity> list = departmentService.queryDepartments();
        HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        request.getSession().setAttribute("depart",list);
        System.out.println("查询出的部门集合长度为"+list.size());
        return SUCCESS;
    }

    //根据部门ID删除部门
    public String delete() throws Exception {
        System.out.println("进入删除界面"+departmentId);
        departmentService.deleteDepartmentEntity(departmentId);
        return SUCCESS;
    }

    //根据Id跳转到编辑部门界面
    public String toEdit() throws Exception {
        System.out.println("进入编辑界面"+departmentId);
        DepartmentsEntity departmentsEntity = departmentService.queryById(departmentId);
        System.out.println(departmentsEntity.getDepartmentId()+departmentsEntity.getDepartmentName());
        HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        request.getSession().setAttribute("depart1",departmentsEntity);
        return SUCCESS;
    }

    //编辑部门
    public String editDepart()  throws Exception{
        if (departmentsEntity.getDepartmentName() == null || "".equals(departmentsEntity.getDepartmentName())) {
            this.addActionError("请填写部门名字");
            return INPUT;
        }else {
            departmentService.updateDepartment(departmentsEntity);
            return SUCCESS;
        }
    }
    //新增部门
    public String addDepart() throws Exception {
        System.out.println("进入新增部门界面"+departmentsEntity.getDepartmentId());
        if (departmentsEntity.getDepartmentName() == null || "".equals(departmentsEntity.getDepartmentName()) ||
                departmentsEntity.getDepartmentId() == 0) {
            this.addActionError("传入空缺参数");
            return INPUT;
        }else {
            departmentService.addDepartmentEntity(departmentsEntity);
            return SUCCESS;
        }
    }
}
