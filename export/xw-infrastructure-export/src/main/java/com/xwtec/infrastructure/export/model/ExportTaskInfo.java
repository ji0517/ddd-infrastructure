package com.xwtec.infrastructure.export.model;


import java.io.Serializable;

/**
 * 导出任务model
 **/
public class ExportTaskInfo implements Serializable {

    private static final long serialVersionUID = -2026566288888881L;
    /**
     * taskId
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 导出类型
     */
    private String exportFormat;

    /**
     * 任务状态 0、失败 1、待开始 2、进行中 3、完成
     */
    private int taskStatus;


    private String taskStatusName;

    /**
     * 重试次数
     */
    private int retryNum;

    /**
     * 执行的sql
     */
    private String sqlStatement;

    /**
     * sql中的select内容,用来做列头
     */
    private String sqlSelected;

    /**
     * 是否生成头
     */
    private String printFormat;

    /**
     * 生成的文件名
     */
    private String exportFileName;

    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 租户编码（提供者)
     */
    private String tenantNum;
    /**
     * 创建人编码
     */
    private String createMancode;
    /**
     * 创建人名称
     */
    private String createManname;

    /**
     * 最后执行时间
     */
    private String lastTime;


    private String queryEndTime;

    private String queryBeginTime;


    private String querySort;

    /**
     * 处理消息
     */
    private String taskStatusMsg;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatusName = ExportTaskStatus.name(taskStatus);
        this.taskStatus = taskStatus;
    }

    public int getRetryNum() {
        return retryNum;
    }

    public void setRetryNum(int retryNum) {
        this.retryNum = retryNum;
    }

    public String getSqlStatement() {
        return sqlStatement;
    }

    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    public String getSqlSelected() {
        return sqlSelected;
    }

    public void setSqlSelected(String sqlSelected) {
        this.sqlSelected = sqlSelected;
    }

    public String getExportFileName() {
        return exportFileName;
    }

    public void setExportFileName(String exportFileName) {
        this.exportFileName = exportFileName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTenantNum() {
        return tenantNum;
    }

    public void setTenantNum(String tenantNum) {
        this.tenantNum = tenantNum;
    }

    public String getCreateMancode() {
        return createMancode;
    }

    public void setCreateMancode(String createMancode) {
        this.createMancode = createMancode;
    }

    public String getCreateManname() {
        return createManname;
    }

    public void setCreateManname(String createManname) {
        this.createManname = createManname;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getPrintFormat() {
        return printFormat;
    }

    public void setPrintFormat(String printFormat) {
        this.printFormat = printFormat;
    }

    public String getTaskStatusMsg() {
        return taskStatusMsg;
    }

    public void setTaskStatusMsg(String taskStatusMsg) {
        this.taskStatusMsg = taskStatusMsg;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getExportFormat() {
        return exportFormat;
    }

    public void setExportFormat(String exportFormat) {
        this.exportFormat = exportFormat;
    }

    public String getQueryEndTime() {
        return queryEndTime;
    }

    public void setQueryEndTime(String queryEndTime) {
        this.queryEndTime = queryEndTime;
    }

    public String getQueryBeginTime() {
        return queryBeginTime;
    }

    public void setQueryBeginTime(String queryBeginTime) {
        this.queryBeginTime = queryBeginTime;
    }

    public String getQuerySort() {
        return querySort;
    }

    public void setQuerySort(String querySort) {
        this.querySort = querySort;
    }

    public String getTaskStatusName() {
        return taskStatusName;
    }

    public void setTaskStatusName(String taskStatusName) {
        this.taskStatusName = taskStatusName;
    }

}
