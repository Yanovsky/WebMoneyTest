package ru.dreamkas.webmoney.objects.adapters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");

    @Override
    public LocalDateTime unmarshal(String value) {
        return LocalDateTime.parse(value, formatter);
    }

    @Override
    public String marshal(LocalDateTime value) {
        return format(value);
    }

    public static String format(LocalDateTime value) {
        return value.format(formatter);
    }

}
