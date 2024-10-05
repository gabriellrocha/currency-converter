package model;

import enumeration.Currency;
import model.dto.ExchangeDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exchange {

    private Currency baseCurrency;
    private Currency targetCurrency;
    private double conversionRate;
    private double conversionResult;
    private LocalDateTime localDateTime;

    public Exchange(ExchangeDTO dto){
        baseCurrency = dto.getBaseCurrency();
        targetCurrency = dto.getTargetCurrency();
        conversionRate = dto.getConversionRate();
        conversionResult = dto.getConversionResult();
        localDateTime = LocalDateTime.now();
    }

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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }


    private String formatDate() {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm");
        return localDateTime.format(dt);
    }

    @Override
    public String toString() {
        return "The amount " + Math.round(conversionResult / conversionRate) +
                " [" + baseCurrency + "] corresponds to approximately " +
                conversionResult + " [" + targetCurrency +"] as of " + formatDate();
    }
}
