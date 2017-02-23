package app.doordash.demo.http.models.restaurants;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladi on 2/21/17.
 */

public class Address { //holds info about location
    private String city;
    private String state;
    private float lat;
    private float lng;
    @SerializedName("printable_address")
    private String printableAddress;

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public String getPrintableAddress() {
        return printableAddress;
    }
}
