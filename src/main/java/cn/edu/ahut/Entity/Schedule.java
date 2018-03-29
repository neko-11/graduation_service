package cn.edu.ahut.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Schedule {
    @Id
    private Integer id;

    /**
     * 规定签到时间
     */
    @Column(name = "arrive_time")
    private Date arriveTime;

    /**
     * 规定签退时间
     */
    @Column(name = "leave_time")
    private Date leaveTime;

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
     * 获取规定签到时间
     *
     * @return arrive_time - 规定签到时间
     */
    public Date getArriveTime() {
        return arriveTime;
    }

    /**
     * 设置规定签到时间
     *
     * @param arriveTime 规定签到时间
     */
    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * 获取规定签退时间
     *
     * @return leave_time - 规定签退时间
     */
    public Date getLeaveTime() {
        return leaveTime;
    }

    /**
     * 设置规定签退时间
     *
     * @param leaveTime 规定签退时间
     */
    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }
}