package com.panda.service;

import com.panda.po.DepartmentsEntity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/6.
 * TIME:11:29.
 */
public interface DepartmentService {
    //查询所有部门
    List<DepartmentsEntity> queryDepartments() throws Exception;
    //添加部门
    boolean addDepartmentEntity(DepartmentsEntity departmentsEntity) throws Exception;
    //删除部门
    boolean deleteDepartmentEntity(Long departmentId) throws Exception;
    //根据Id查询部门
    DepartmentsEntity queryById(Long departmentId) throws Exception;
    //分页查询所有部门
    List<DepartmentsEntity> queryDepartmentEntity(int pageSize,int pageNow) throws Exception;
    //修改部门信息
    boolean updateDepartment(DepartmentsEntity departmentsEntity) throws Exception;
}
