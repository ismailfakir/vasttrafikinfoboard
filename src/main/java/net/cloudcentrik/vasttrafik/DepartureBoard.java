
package net.cloudcentrik.vasttrafik;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartureBoard {

    @SerializedName("DepartureBoard")
    @Expose
    private DepartureBoard_ departureBoard;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DepartureBoard() {
    }

    /**
     * 
     * @param departureBoard
     */
    public DepartureBoard(DepartureBoard_ departureBoard) {
        super();
        this.departureBoard = departureBoard;
    }

    public DepartureBoard_ getDepartureBoard() {
        return departureBoard;
    }

    public void setDepartureBoard(DepartureBoard_ departureBoard) {
        this.departureBoard = departureBoard;
    }

}
