package com.happy.springboot.service.model.entity.happy_springboot;

import java.util.Date;

public class UserInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.user_id
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.user_name
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.insert_time
     *
     * @mbggenerated
     */
    private Date insertTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.is_active
     *
     * @mbggenerated
     */
    private Boolean isActive;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.id
     *
     * @return the value of t_user_info.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.id
     *
     * @param id the value for t_user_info.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.user_id
     *
     * @return the value of t_user_info.user_id
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.user_id
     *
     * @param userId the value for t_user_info.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.user_name
     *
     * @return the value of t_user_info.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.user_name
     *
     * @param userName the value for t_user_info.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.insert_time
     *
     * @return the value of t_user_info.insert_time
     *
     * @mbggenerated
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.insert_time
     *
     * @param insertTime the value for t_user_info.insert_time
     *
     * @mbggenerated
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.update_time
     *
     * @return the value of t_user_info.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.update_time
     *
     * @param updateTime the value for t_user_info.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.is_active
     *
     * @return the value of t_user_info.is_active
     *
     * @mbggenerated
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.is_active
     *
     * @param isActive the value for t_user_info.is_active
     *
     * @mbggenerated
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}