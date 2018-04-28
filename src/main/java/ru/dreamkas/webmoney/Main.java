package ru.dreamkas.webmoney;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ru.dreamkas.webmoney.objects.check.GetOutInvoicesRequest;
import ru.dreamkas.webmoney.objects.check.Order;
import ru.dreamkas.webmoney.objects.refund.RefundOrder;
import ru.dreamkas.webmoney.objects.refund.RefundRequest;
import ru.dreamkas.webmoney.objects.tools.BigDecimalAdapter;

public class Main {

    private static final String CRC_FORMAT = "p=%s&order_id=%d&s=%s&%s";
    private static final String QR_CODE_FORMAT = "wmk:pay-at-pos?a=%d&p=%s&order_id=%d&s=%s&c=%d";
    private static final String SECRET_KEY = "LvELxIkFXvFCVRyGkyZ_YIHdyvuK2A";
    private static final String POS_ID = "N1_WM";
    private static final Map<Class<?>, Marshaller> marshallers = new ConcurrentHashMap<>();

    private static Marshaller getMarshaller(Class<?> clazz) {
        return marshallers.computeIfAbsent(clazz, Main::createMarshaller);
    }

    private static Marshaller createMarshaller(Class<?> c) {
        try {
            Marshaller marshaller = JAXBContext.newInstance(c).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "windows-1251");
            //marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            return marshaller;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Long REQ_NO = 17111511485836L;
        Long ORDER_ID = 13185900004L;//RandomUtils.nextLong(100, 1000000000);
        String WM_ID = "025899319006";
        BigDecimal AMOUNT = BigDecimal.valueOf(0.01);
        String AMOUNT_STRING = BigDecimalAdapter.format(AMOUNT);

        String qr = String.format(QR_CODE_FORMAT,
            1,
            POS_ID,
            ORDER_ID,
            AMOUNT_STRING,
            WebMoneyUtils.calculateCRC(String.format(CRC_FORMAT, POS_ID, ORDER_ID, AMOUNT_STRING, SECRET_KEY).toLowerCase())
        );
        System.out.println("QR-Code: " + qr);
        System.out.println();

        GetOutInvoicesRequest request = new GetOutInvoicesRequest();
        request.setReqNumber(REQ_NO);
        request.setWmId(WM_ID);
        request.setPosId(POS_ID);
        request.getOutInvoices().getOutInvoices().addAll(Collections.singletonList(new Order().setOrderId(ORDER_ID)));

        RefundRequest refund = new RefundRequest();
        refund.setReqNumber(REQ_NO);
        refund.setWmId(WM_ID);
        RefundOrder order = new RefundOrder();
        order.setAmount(AMOUNT);
        order.setPosId(POS_ID);
        order.setOrderId(ORDER_ID);
        refund.setOrder(order);

        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            getMarshaller(GetOutInvoicesRequest.class).marshal(request, stream);
            System.out.println();
            System.out.println(request.getClass().getSimpleName() + ":");
            System.out.println(stream.toString());

            stream.reset();

            getMarshaller(RefundRequest.class).marshal(refund, stream);
            System.out.println();
            System.out.println(refund.getClass().getSimpleName() + ":");
            System.out.println(stream.toString());
        }
    }
}
