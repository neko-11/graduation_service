package cn.edu.ahut.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

public class User {
    @Id
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户头像路径
     */
    @Column(name = "user_img")
    private String userImg;

    /**
     * 用户code
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * 部门id
     */
    @Column(name = "department_id")
    private Integer departmentId;

    /**
     * 角色 admin/user
     */
    private String role;

    @Transient
    @Column(name = "department_name")
    private String departmentName;

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
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户头像路径
     *
     * @return user_img - 用户头像路径
     */
    public String getUserImg() {
        return userImg;
    }

    /**
     * 设置用户头像路径
     *
     * @param userImg 用户头像路径
     */
    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    /**
     * 获取用户code
     *
     * @return user_code - 用户code
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置用户code
     *
     * @param userCode 用户code
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * 获取部门id
     *
     * @return department_id - 部门id
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置部门id
     *
     * @param departmentId 部门id
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取角色 admin/user
     *
     * @return role - 角色 admin/user
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置角色 admin/user
     *
     * @param role 角色 admin/user
     */
    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}