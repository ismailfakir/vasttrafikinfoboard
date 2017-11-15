
package net.cloudcentrik.vasttrafik;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Departure {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sname")
    @Expose
    private String sname;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("stopid")
    @Expose
    private String stopid;
    @SerializedName("stop")
    @Expose
    private String stop;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("journeyid")
    @Expose
    private String journeyid;
    @SerializedName("direction")
    @Expose
    private String direction;
    @SerializedName("track")
    @Expose
    private String track;
    @SerializedName("fgColor")
    @Expose
    private String fgColor;
    @SerializedName("bgColor")
    @Expose
    private String bgColor;
    @SerializedName("stroke")
    @Expose
    private String stroke;
    @SerializedName("JourneyDetailRef")
    @Expose
    private JourneyDetailRef journeyDetailRef;
    @SerializedName("accessibility")
    @Expose
    private String accessibility;
    @SerializedName("booking")
    @Expose
    private String booking;
    @SerializedName("rtTime")
    @Expose
    private String rtTime;
    @SerializedName("rtDate")
    @Expose
    private String rtDate;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Departure() {
    }

    /**
     * 
     * @param fgColor
     * @param stop
     * @param booking
     * @param journeyDetailRef
     * @param direction
     * @param track
     * @param sname
     * @param type
     * @param date
     * @param bgColor
     * @param rtDate
     * @param stroke
     * @param time
     * @param name
     * @param rtTime
     * @param stopid
     * @param journeyid
     * @param accessibility
     */
    public Departure(String name, String sname, String type, String stopid, String stop, String time, String date, String journeyid, String direction, String track, String fgColor, String bgColor, String stroke, JourneyDetailRef journeyDetailRef, String accessibility, String booking, String rtTime, String rtDate) {
        super();
        this.name = name;
        this.sname = sname;
        this.type = type;
        this.stopid = stopid;
        this.stop = stop;
        this.time = time;
        this.date = date;
        this.journeyid = journeyid;
        this.direction = direction;
        this.track = track;
        this.fgColor = fgColor;
        this.bgColor = bgColor;
        this.stroke = stroke;
        this.journeyDetailRef = journeyDetailRef;
        this.accessibility = accessibility;
        this.booking = booking;
        this.rtTime = rtTime;
        this.rtDate = rtDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStopid() {
        return stopid;
    }

    public void setStopid(String stopid) {
        this.stopid = stopid;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJourneyid() {
        return journeyid;
    }

    public void setJourneyid(String journeyid) {
        this.journeyid = journeyid;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getFgColor() {
        return fgColor;
    }

    public void setFgColor(String fgColor) {
        this.fgColor = fgColor;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public JourneyDetailRef getJourneyDetailRef() {
        return journeyDetailRef;
    }

    public void setJourneyDetailRef(JourneyDetailRef journeyDetailRef) {
        this.journeyDetailRef = journeyDetailRef;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public String getRtTime() {
        return rtTime;
    }

    public void setRtTime(String rtTime) {
        this.rtTime = rtTime;
    }

    public String getRtDate() {
        return rtDate;
    }

    public void setRtDate(String rtDate) {
        this.rtDate = rtDate;
    }

    @Override
    public String toString() {
        return "Departure{" +
                "name='" + name + '\'' +
                ", sname='" + sname + '\'' +
                ", type='" + type + '\'' +
                ", stopid='" + stopid + '\'' +
                ", stop='" + stop + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", journeyid='" + journeyid + '\'' +
                ", direction='" + direction + '\'' +
                ", track='" + track + '\'' +
                ", fgColor='" + fgColor + '\'' +
                ", bgColor='" + bgColor + '\'' +
                ", stroke='" + stroke + '\'' +
                ", journeyDetailRef=" + journeyDetailRef +
                ", accessibility='" + accessibility + '\'' +
                ", booking='" + booking + '\'' +
                ", rtTime='" + rtTime + '\'' +
                ", rtDate='" + rtDate + '\'' +
                '}';
    }
}
