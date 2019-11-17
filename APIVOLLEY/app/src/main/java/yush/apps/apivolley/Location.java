package yush.apps.apivolley;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("Name")
    String name;

    @SerializedName("SubRegion")
    String subRegion;

    @SerializedName("CurrencyName")
    String currency;

    @SerializedName("CurrencySymbol")
    String currency_symbol;

}
