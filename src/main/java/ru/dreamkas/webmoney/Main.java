package ru.dreamkas.webmoney;

import java.math.BigDecimal;

import ru.dreamkas.webmoney.objects.cancellation.CancelOrder;
import ru.dreamkas.webmoney.objects.check.GetOutInvoicesRequest;
import ru.dreamkas.webmoney.objects.check.GetOutInvoicesResponse;
import ru.dreamkas.webmoney.objects.init.CreateSession;
import ru.dreamkas.webmoney.objects.init.GenerateInvoiceRequest;
import ru.dreamkas.webmoney.objects.init.GenerateInvoiceResponse;
import ru.dreamkas.webmoney.objects.init.InitRequest;
import ru.dreamkas.webmoney.objects.init.InitResponse;
import ru.dreamkas.webmoney.objects.init.QRCodeRequest;
import ru.dreamkas.webmoney.objects.refund.RefundOrder;
import ru.dreamkas.webmoney.objects.refund.RefundRequest;
import ru.dreamkas.webmoney.objects.base.ServiceType;
import ru.dreamkas.webmoney.objects.cancellation.CancelOrderRequest;
import ru.dreamkas.webmoney.utils.JAXBUtils;
import ru.dreamkas.webmoney.utils.WebMoneyUtils;

public class Main {
    private static final String POS_ID = "N1_WM";
    private static final String POS_WM_ID = "025656262656";

    public static void main(String[] args) throws Exception {
        Long REQ_NO = 17111511485839L;
        Long ORDER_ID = 143890L;//RandomUtils.nextLong(100, 1000000000);
        BigDecimal AMOUNT = BigDecimal.valueOf(0.01);

        System.out.println("QR-Code: " + WebMoneyUtils.createQrCode(ServiceType.TEST, POS_ID, ORDER_ID, AMOUNT));
        System.out.println();

        {
            System.out.println("InitRequest:");
            InitRequest request = new InitRequest();
            request.setReqNumber(REQ_NO);
            request.setWmId(POS_WM_ID);
            CreateSession session = new CreateSession();
            session.setAgentId(1);
            session.setPosId(POS_ID);
            session.setOrderId(ORDER_ID);
            session.setAmount(AMOUNT);
            //session.setWmId("025899319006");
            request.setCreateSession(session);
            System.out.println(JAXBUtils.marshal(request));
        }

        {
            System.out.println("GetOutInvoicesRequest:");
            GetOutInvoicesRequest request = new GetOutInvoicesRequest();
            request.setReqNumber(REQ_NO);
            request.setWmId(POS_WM_ID);
            request.setPosId(POS_ID);
            request.setOrderId(ORDER_ID);
            System.out.println(JAXBUtils.marshal(request));
        }

        {
            System.out.println("RefundRequest:");
            RefundRequest request = new RefundRequest();
            request.setReqNumber(REQ_NO);
            request.setWmId(POS_WM_ID);
            RefundOrder order = new RefundOrder();
            order.setAmount(AMOUNT);
            order.setPosId(POS_ID);
            order.setOrderId(ORDER_ID);
            request.setOrder(order);
            System.out.println(JAXBUtils.marshal(request));
        }

        {
            System.out.println("GenerateInvoiceRequest:");
            GenerateInvoiceRequest request = new GenerateInvoiceRequest();
            request.setReqNumber(REQ_NO);
            request.setWmId(POS_WM_ID);
            request.setQrCode(
                new QRCodeRequest()
                    .setAmount(AMOUNT)
                    .setPosId(POS_ID)
            );
            //session.setWmId("025899319006");
            System.out.println(JAXBUtils.marshal(request));
        }

        {
            System.out.println("CancelOrderRequest:");
            CancelOrderRequest request = new CancelOrderRequest();
            request.setReqNumber(REQ_NO);
            request.setWmId(POS_WM_ID);
            request.setOrder(
                new CancelOrder()
                    .setPosId(POS_ID)
                    .setOrderId(ORDER_ID)
            );
            //session.setWmId("025899319006");
            System.out.println(JAXBUtils.marshal(request));
        }

        System.out.println();
        {
            String responseXML = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<w3s.response>\n" +
                "    <retval>0</retval>\n" +
                "    <retdesc>success</retdesc>\n" +
                "    <reqn>17111511485836</reqn>\n" +
                "    <invoices>\n" +
                "        <invoiceid>742248514</invoiceid>\n" +
                "        <state>3</state>\n" +
                "        <orderid>13185900005</orderid>\n" +
                "        <wmid>025899319006</wmid>\n" +
                "        <internalorderid>143874</internalorderid>\n" +
                "        <amount>0.01</amount>\n" +
                "    </invoices>\n" +
                "</w3s.response>";
            System.out.println(JAXBUtils.unmarshal(GetOutInvoicesResponse.class, responseXML).toString());
        }

        {
            String responseXML = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<w3s.response>\n" +
                "    <retval>0</retval>\n" +
                "    <retdesc>success</retdesc>\n" +
                "    <reqn>17111511485837</reqn>\n" +
                "    <invoice>\n" +
                "        <orderid>143874</orderid>\n" +
                "        <invoiceid>742248514</invoiceid>\n" +
                "        <altorderid>0</altorderid>\n" +
                "    </invoice>\n" +
                "</w3s.response>";

            System.out.println(JAXBUtils.unmarshal(InitResponse.class, responseXML).toString());
        }

        {
            String responseXML = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<w3s.response>\n" +
                "    <retval>0</retval>\n" +
                "    <retdesc>success</retdesc>\n" +
                "    <reqn>17111511485837</reqn>\n" +
                "    <getqr>\n" +
                "        <qrtext>wmk:pay-at-pos?agent_id=1&amp;pos_id=N1_WM&amp;order_id=143887&amp;auth=0&amp;l=ru</qrtext>\n" +
                "        <orderid>143887</orderid>\n" +
                "        <dateexp>20180504 15:12:11</dateexp>\n" +
                "    </getqr>\n" +
                "</w3s.response>";
            System.out.println(JAXBUtils.unmarshal(GenerateInvoiceResponse.class, responseXML).toString());
        }
    }
}
