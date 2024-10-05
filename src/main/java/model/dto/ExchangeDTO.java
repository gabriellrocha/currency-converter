package model.dto;

import com.google.gson.annotations.SerializedName;
import enumeration.Currency;

public class ExchangeDTO {

    @SerializedName("base_code")
    private Currency baseCurrency;

    @SerializedName("target_code")
    private Currency targetCurrency;

    @SerializedName("conversion_rate")
    private double conversionRate;

    @SerializedName("conversion_result")
    private double conversionResult;

    private ExchangeDTO(){}

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public double getConversionResult() {
        return conversionResult;
    }

}
