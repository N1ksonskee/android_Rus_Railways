package com.example.rgd_monitor;

public class RusRailwaysInfo {
    String status;
    String tickedId;
    String reportedBy;
    String classIdMain;
    int criticLevel;
    String isKnownErrorDate;
    String targetFinish;
    String description;
    String extSysName;
    double norm;
    int lNorm;

    public String getTargetFinish() {
        return targetFinish;
    }

    public void setTargetFinish(String targetFinish) {
        this.targetFinish = targetFinish;
    }

    public RusRailwaysInfo(String status,
                           String tickedId,
                           String reportedBy,
                           String classIdMain,
                           int criticLevel,
                           String isKnownErrorDate,
                           String targetFinish,
                           String description,
                           String extSysName,
                           double norm,
                           int lNorm) {
        this.status = status;
        this.tickedId = tickedId;
        this.reportedBy = reportedBy;
        this.classIdMain = classIdMain;
        this.criticLevel = criticLevel;
        this.isKnownErrorDate = isKnownErrorDate;
        this.targetFinish = targetFinish;
        this.description = description;
        this.extSysName = extSysName;
        this.norm = norm;
        this.lNorm = lNorm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTickedId() {
        return tickedId;
    }

    public void setTickedId(String tickedId) {
        this.tickedId = tickedId;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getClassIdMain() {
        return classIdMain;
    }

    public void setClassIdMain(String classIdMain) {
        this.classIdMain = classIdMain;
    }

    public int getCriticLevel() {
        return criticLevel;
    }

    public void setCriticLevel(int criticLevel) {
        this.criticLevel = criticLevel;
    }

    public String getIsKnownErrorDate() {
        return isKnownErrorDate;
    }

    public void setIsKnownErrorDate(String isKnownErrorDate) {
        this.isKnownErrorDate = isKnownErrorDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtSysName() {
        return extSysName;
    }

    public void setExtSysName(String extSysName) {
        this.extSysName = extSysName;
    }

    public double getNorm() {
        return norm;
    }

    public void setNorm(double norm) {
        this.norm = norm;
    }

    public int getlNorm() {
        return lNorm;
    }

    public void setlNorm(int lNorm) {
        this.lNorm = lNorm;
    }
}
