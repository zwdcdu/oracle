package com.panda.po;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "DEPARTMENTS", schema = "STUDY", catalog = "")
public class DepartmentsEntity {
    private long departmentId;
    private String departmentName;
    private Collection<EmployeesEntity> employeesByDepartmentId;

    public int getHasEmployee() {
        return hasEmployee;
    }

    public void setHasEmployee(int hasEmployee) {
        this.hasEmployee = hasEmployee;
    }

    private int hasEmployee;
    @Id
    @Column(name = "DEPARTMENT_ID")
    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "DEPARTMENT_NAME")
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentsEntity that = (DepartmentsEntity) o;

        if (departmentId != that.departmentId) return false;
        if (departmentName != null ? !departmentName.equals(that.departmentName) : that.departmentName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (departmentId ^ (departmentId >>> 32));
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "departmentsByDepartmentId")
    public Collection<EmployeesEntity> getEmployeesByDepartmentId() {
        return employeesByDepartmentId;
    }

    public void setEmployeesByDepartmentId(Collection<EmployeesEntity> employeesByDepartmentId) {
        this.employeesByDepartmentId = employeesByDepartmentId;
    }
}
