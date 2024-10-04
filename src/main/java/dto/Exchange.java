package dto;

import com.google.gson.annotations.SerializedName;
import enumeration.Currency;

public record Exchange(@SerializedName("base_code") Currency baseCurrency,
                       @SerializedName("target_code") Currency targetCurrency,
                       @SerializedName("conversion_rate") double conversionRate,
                       @SerializedName("conversion_result") double conversionResult) {


    @Override
    public String toString() {
        return "The amount " + Math.round(conversionResult() / conversionRate()) +
        " [" + baseCurrency() + "] corresponds to approximately " +
         conversionResult() + " [" + targetCurrency() +"]";
    }
}
