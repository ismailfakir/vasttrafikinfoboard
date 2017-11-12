
package net.cloudcentrik.vasttrafik;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartureBoard_ {

    @SerializedName("noNamespaceSchemaLocation")
    @Expose
    private String noNamespaceSchemaLocation;
    @SerializedName("servertime")
    @Expose
    private String servertime;
    @SerializedName("serverdate")
    @Expose
    private String serverdate;
    @SerializedName("Departure")
    @Expose
    private List<Departure> departure = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DepartureBoard_() {
    }

    /**
     * 
     * @param serverdate
     * @param servertime
     * @param departure
     * @param noNamespaceSchemaLocation
     */
    public DepartureBoard_(String noNamespaceSchemaLocation, String servertime, String serverdate, List<Departure> departure) {
        super();
        this.noNamespaceSchemaLocation = noNamespaceSchemaLocation;
        this.servertime = servertime;
        this.serverdate = serverdate;
        this.departure = departure;
    }

    public String getNoNamespaceSchemaLocation() {
        return noNamespaceSchemaLocation;
    }

    public void setNoNamespaceSchemaLocation(String noNamespaceSchemaLocation) {
        this.noNamespaceSchemaLocation = noNamespaceSchemaLocation;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public String getServerdate() {
        return serverdate;
    }

    public void setServerdate(String serverdate) {
        this.serverdate = serverdate;
    }

    public List<Departure> getDeparture() {
        return departure;
    }

    public void setDeparture(List<Departure> departure) {
        this.departure = departure;
    }

}
