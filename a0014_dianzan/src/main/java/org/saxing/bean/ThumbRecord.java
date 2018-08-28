package org.saxing.bean;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author saxing
 * @description 点赞记录实体类
 * @time 2017/10/30 0030 14:27
 */
public class ThumbRecord {

    private static final long serialVersionUID = 2120869894112984148L;

    private String fromUserId;
    private String fromUsername;
    private String fromDepartName;
    private String fromPosition;
    private String fromMobile;
    private String fromGender;
    private String fromEmail;
    private String fromAvatar;
    private String fromTelephone;
    private int fromStatus;
    private int thumbUp;
    private String reason;
    private String toIsleader;
    private int toDepartmentId;
    private String toDepartmentName;
    private String toUserId;
    private String toUserName;
    private String toPosition;
    private String toMobile;
    private String toGender;
    private String toEmail;
    private String toAvatar;
    private String toTelephone;
    private int toStatus;
    private String createTime;

    public int getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(int fromStatus) {
        this.fromStatus = fromStatus;
    }

    public int getToStatus() {
        return toStatus;
    }

    public void setToStatus(int toStatus) {
        this.toStatus = toStatus;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getFromPosition() {
        return fromPosition;
    }

    public void setFromPosition(String fromPosition) {
        this.fromPosition = fromPosition;
    }

    public String getFromMobile() {
        return fromMobile;
    }

    public void setFromMobile(String fromMobile) {
        this.fromMobile = fromMobile;
    }

    public String getFromGender() {
        return fromGender;
    }

    public void setFromGender(String fromGender) {
        this.fromGender = fromGender;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public String getFromTelephone() {
        return fromTelephone;
    }

    public void setFromTelephone(String fromTelephone) {
        this.fromTelephone = fromTelephone;
    }

    public int getThumbUp() {
        return thumbUp;
    }

    public void setThumbUp(int thumbUp) {
        this.thumbUp = thumbUp;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getToIsleader() {
        return toIsleader;
    }

    public void setToIsleader(String toIsleader) {
        this.toIsleader = toIsleader;
    }

    public int getToDepartmentId() {
        return toDepartmentId;
    }

    public void setToDepartmentId(int toDepartmentId) {
        this.toDepartmentId = toDepartmentId;
    }

    public String getToDepartmentName() {
        return toDepartmentName;
    }

    public void setToDepartmentName(String toDepartmentName) {
        this.toDepartmentName = toDepartmentName;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getToPosition() {
        return toPosition;
    }

    public void setToPosition(String toPosition) {
        this.toPosition = toPosition;
    }

    public String getToMobile() {
        return toMobile;
    }

    public void setToMobile(String toMobile) {
        this.toMobile = toMobile;
    }

    public String getToGender() {
        return toGender;
    }

    public void setToGender(String toGender) {
        this.toGender = toGender;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getToAvatar() {
        return toAvatar;
    }

    public void setToAvatar(String toAvatar) {
        this.toAvatar = toAvatar;
    }

    public String getToTelephone() {
        return toTelephone;
    }

    public void setToTelephone(String toTelephone) {
        this.toTelephone = toTelephone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFromDepartName() {
        return fromDepartName;
    }

    public void setFromDepartName(String fromDepartName) {
        this.fromDepartName = fromDepartName;
    }

    public static class ThumbRecordRowMapper implements RowMapper<ThumbRecord>{

        @Override
        public ThumbRecord mapRow(ResultSet resultSet, int i) throws SQLException {
            ThumbRecord thumbRecord = new ThumbRecord();
            thumbRecord.setFromUserId(resultSet.getString("from_userid"));
            thumbRecord.setFromUsername(resultSet.getString("from_username"));
            thumbRecord.setFromDepartName(resultSet.getString("from_depart"));
            thumbRecord.setFromPosition(resultSet.getString("from_position"));
            thumbRecord.setFromMobile(resultSet.getString("from_mobile"));
            thumbRecord.setFromGender(resultSet.getString("from_gender"));
            thumbRecord.setFromEmail(resultSet.getString("from_email"));
            thumbRecord.setFromAvatar(resultSet.getString("from_avatar"));
            thumbRecord.setFromTelephone(resultSet.getString("from_telephone"));
            thumbRecord.setFromStatus(resultSet.getInt("from_status"));
            thumbRecord.setThumbUp(resultSet.getInt("thumb_up"));
            thumbRecord.setReason(resultSet.getString("reason"));
            thumbRecord.setToIsleader(resultSet.getString("to_isleader"));
            thumbRecord.setToDepartmentId(resultSet.getInt("to_department_id"));
            thumbRecord.setToDepartmentName(resultSet.getString("to_department_name"));
            thumbRecord.setToUserId(resultSet.getString("to_userid"));
            thumbRecord.setToUserName(resultSet.getString("to_username"));
            thumbRecord.setToPosition(resultSet.getString("to_position"));
            thumbRecord.setToMobile(resultSet.getString("to_mobile"));
            thumbRecord.setToGender(resultSet.getString("to_gender"));
            thumbRecord.setToEmail(resultSet.getString("to_email"));
            thumbRecord.setToAvatar(resultSet.getString("to_avatar"));
            thumbRecord.setToTelephone(resultSet.getString("to_telephone"));
            thumbRecord.setToStatus(resultSet.getInt("to_status"));
            thumbRecord.setCreateTime(resultSet.getString("create_time"));
            return thumbRecord;
        }
    }
}
