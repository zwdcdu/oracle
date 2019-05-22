package com.panda.serviceImpl;

import com.panda.dao.DepartmentDao;
import com.panda.po.DepartmentsEntity;
import com.panda.service.DepartmentService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/4/6.
 * TIME:11:30.
 */
@Component("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Resource(name = "departmentDao")
    private DepartmentDao departmentDao ;

    //查询所有部门
    public List<DepartmentsEntity> queryDepartments() throws Exception {
        return this.departmentDao.queryDepartments();
    }

    //增加部门
    public boolean addDepartmentEntity(DepartmentsEntity departmentsEntity) throws Exception {
        //如果ID为空，或者部门名字为空，则返回false
        if (departmentsEntity.getDepartmentId() == 0 || departmentsEntity.getDepartmentName() == null ||
                "".equals(departmentsEntity.getDepartmentName())) {
            return false;
        }else {
           return this.departmentDao.addDepartmentEntity(departmentsEntity);
        }
    }

    @Override
    public boolean deleteDepartmentEntity(Long departmentId) throws Exception {
        if (departmentId == null) {
            return false;
        } else  {
            return this.departmentDao.deleteDepartmentEntity(departmentId);
        }
    }

    @Override
    public DepartmentsEntity queryById(Long departmentId) throws Exception {
        if (departmentId == null) {
            throw new NullPointerException("传入参数为空");
        }
        else {
            return this.departmentDao.queryById(departmentId);
        }
    }

    @Override
    public List<DepartmentsEntity> queryDepartmentEntity(int pageSize, int pageNow)  throws Exception{
        return this.departmentDao.queryDepartmentEntity(pageSize,pageNow);
    }

    @Override
    public boolean updateDepartment(DepartmentsEntity departmentsEntity) throws Exception {
        //如果ID为空，或者部门名字为空，则返回false
        if (departmentsEntity.getDepartmentId() == 0 || departmentsEntity.getDepartmentName() == null ||
                "".equals(departmentsEntity.getDepartmentName())) {
            return false;
        }else {
            return this.departmentDao.updateDepartment(departmentsEntity);
        }
    }

    public DepartmentDao getDepartmentDao() throws Exception {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) throws Exception {
        this.departmentDao = departmentDao;
    }
}
