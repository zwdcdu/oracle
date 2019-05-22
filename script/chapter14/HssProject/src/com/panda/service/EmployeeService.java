package com.panda.service;

import com.panda.po.EmployeesEntity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/27.
 * TIME:15:09.
 */
public interface EmployeeService {
    void addEmployee(EmployeesEntity employeesEntity) throws Exception;//添加员工
    List<EmployeesEntity> queryEmployee(int pageSize,int pageNow) throws Exception;//查询所有员工
    List<EmployeesEntity> queryEmployee() throws Exception;//查询所有员工
    List<EmployeesEntity> queryEmployeeByProcedure(int pageSize, int pageNow) throws Exception;//调用存储过程分页查询员工数据
    EmployeesEntity queryEmployeeByEmployeeId(Long employeeId) throws Exception;//根据id查询单个员工
    void  deleteEmployee(Long employeeId) throws Exception;//删除员工
    void  updateEmployee(EmployeesEntity employeesEntity) throws Exception;//修改员工信息
}
