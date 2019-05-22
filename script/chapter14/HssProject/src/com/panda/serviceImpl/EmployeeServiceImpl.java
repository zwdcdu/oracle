package com.panda.serviceImpl;

import com.panda.dao.EmployeeDao;
import com.panda.po.EmployeesEntity;
import com.panda.service.EmployeeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/27.
 * TIME:15:10.
 */
@Component("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Resource(name = "employee")
    private EmployeeDao employeeDao ;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
    //添加员工
    public void addEmployee(EmployeesEntity employeesEntity) throws Exception {
        this.employeeDao.addEmployee(employeesEntity);
    }
    //分页查询所有员工
    public List<EmployeesEntity> queryEmployee(int pageSize,int pageNow) throws Exception {
        return this.employeeDao.queryEmployee( pageSize, pageNow);
    }

    //查询所有员工
    public List<EmployeesEntity> queryEmployee() throws Exception {
        return this.employeeDao.queryEmployee();
    }

    @Override
    public List<EmployeesEntity> queryEmployeeByProcedure(int pageSize, int pageNow) throws Exception {
        return this.employeeDao.queryEmployeeByProcedure(pageSize,pageNow);
    }

    //根据Id查询单个员工信息
    public EmployeesEntity queryEmployeeByEmployeeId(Long employeeId) throws Exception {
        return this.employeeDao.queryEmployeeByEmployeeId(employeeId);
    }

    //删除员工
    public void deleteEmployee(Long employeeIdty) throws Exception {
        this.employeeDao.deleteEmployee(employeeIdty);
    }

    //修改员工信息
    public void updateEmployee(EmployeesEntity employeesEntity) throws Exception {
        this.employeeDao.updateEmployee(employeesEntity);
    }
}
