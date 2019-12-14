package com.example.radiusagent;

public class DataModel {
    public DataModel(){}
    private String from,to,fromTime,toTime,totalTimeDuration;
    private String costValue;
    public DataModel(String from
    , String  to , String fromTime,String toTime,String totalTimeDuration,String costValue
    ){

        this.from = from;
        this.to = to;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.totalTimeDuration = totalTimeDuration;
        this.costValue = costValue;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getTotalTimeDuration() {
        return totalTimeDuration;
    }

    public void setTotalTimeDuration(String totalTimeDuration) {
        this.totalTimeDuration = totalTimeDuration;
    }

    public String getCostValue() {
        return costValue;
    }

    public void setCostValue(String costValue) {
        this.costValue = costValue;
    }
}
