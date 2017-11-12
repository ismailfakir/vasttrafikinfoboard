
package net.cloudcentrik.vasttrafik;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JourneyDetailRef {

    @SerializedName("ref")
    @Expose
    private String ref;

    /**
     * No args constructor for use in serialization
     * 
     */
    public JourneyDetailRef() {
    }

    /**
     * 
     * @param ref
     */
    public JourneyDetailRef(String ref) {
        super();
        this.ref = ref;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

}
