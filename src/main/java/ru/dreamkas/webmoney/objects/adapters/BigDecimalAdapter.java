package ru.dreamkas.webmoney.objects.adapters;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    static {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
    }

    public static String format(BigDecimal value) {
        return decimalFormat.format(value.doubleValue());
    }

    @Override
    public BigDecimal unmarshal(String value) throws Exception {
        return BigDecimal.valueOf(decimalFormat.parse(value).doubleValue());
    }

    @Override
    public String marshal(BigDecimal value) throws Exception {
        return format(value);
    }

}
