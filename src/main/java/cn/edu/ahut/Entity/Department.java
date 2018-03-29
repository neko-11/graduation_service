package cn.edu.ahut.Entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class Department {
    @Id
    private Integer id;

    /**
     * 部门名称
     */
    @Column(name = "department_name")
    private String departmentName;

    /**
     * 部门code
     */
    @Column(name = "department_code")
    private String departmentCode;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取部门名称
     *
     * @return department_name - 部门名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 设置部门名称
     *
     * @param departmentName 部门名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * 获取部门code
     *
     * @return department_code - 部门code
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * 设置部门code
     *
     * @param departmentCode 部门code
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}