package cn.edu.ahut.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

public class Record {
    @Id
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 签到时间
     */
    @Column(name = "arrive_time")
    private Date arriveTime;

    @Transient
    @Column(name = "user_name")
    private String userName;

    @Transient
    @Column(name = "user_code")
    private String userCode;

    @Transient
    @Column(name = "department_name")
    private String departmentName;

    /**
     * 签到图片
     */
    private String image;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取签到时间
     *
     * @return arrive_time - 签到时间
     */
    public Date getArriveTime() {
        return arriveTime;
    }

    /**
     * 设置签到时间
     *
     * @param arriveTime 签到时间
     */
    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * 获取签到图片
     *
     * @return image - 签到图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置签到图片
     *
     * @param image 签到图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", userId=" + userId +
                ", arriveTime=" + arriveTime +
                ", userName='" + userName + '\'' +
                ", userCode='" + userCode + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}