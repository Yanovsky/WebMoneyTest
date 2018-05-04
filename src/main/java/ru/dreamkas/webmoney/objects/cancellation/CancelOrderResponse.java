package ru.dreamkas.webmoney.objects.cancellation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ru.dreamkas.webmoney.objects.base.BaseResponse;

@XmlRootElement(name = "w3s.response")
@XmlAccessorType(XmlAccessType.FIELD)
public class CancelOrderResponse extends BaseResponse {
}
