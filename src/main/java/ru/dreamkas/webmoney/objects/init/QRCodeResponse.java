package ru.dreamkas.webmoney.objects.init;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ru.dreamkas.webmoney.objects.adapters.LocalDateTimeAdapter;

@XmlType(name = "getqr")
@XmlAccessorType(XmlAccessType.FIELD)
public class QRCodeResponse {
    @XmlElement(name = "qrtext")
    private String qrCode;

    @XmlElement(name = "orderid")
    private long orderId;

    @XmlElement(name = "dateexp")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime expire;

    public String getQrCode() {
        return qrCode;
    }

    public QRCodeResponse setQrCode(String qrCode) {
        this.qrCode = qrCode;
        return this;
    }

    public long getOrderId() {
        return orderId;
    }

    public QRCodeResponse setOrderId(long orderId) {
        this.orderId = orderId;
        return this;
    }

    public LocalDateTime getExpire() {
        return expire;
    }

    public QRCodeResponse setExpire(LocalDateTime expire) {
        this.expire = expire;
        return this;
    }

    @Override
    public String toString() {
        return "QRCodeResponse{" +
            "qrCode='" + qrCode + '\'' +
            ", orderId=" + orderId +
            ", expire=" + LocalDateTimeAdapter.format(expire) +
            '}';
    }
}