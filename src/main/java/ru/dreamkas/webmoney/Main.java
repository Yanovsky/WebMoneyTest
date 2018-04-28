package ru.dreamkas.webmoney;

import java.math.BigDecimal;

import ru.dreamkas.webmoney.objects.check.GetOutInvoicesRequest;
import ru.dreamkas.webmoney.objects.check.GetOutInvoicesResponse;
import ru.dreamkas.webmoney.objects.init.CreateSession;
import ru.dreamkas.webmoney.objects.init.InitRequest;
import ru.dreamkas.webmoney.objects.refund.RefundOrder;
import ru.dreamkas.webmoney.objects.refund.RefundRequest;
import ru.dreamkas.webmoney.objects.tools.ServiceType;
import ru.dreamkas.webmoney.objects.tools.WebMoneyUtils;

public class Main {
    private static final String POS_ID = "N1_WM";
    private static final String POS_WM_ID = "025656262656";

    public static void main(String[] args) throws Exception {
        Long REQ_NO = 17111511485837L;
        Long ORDER_ID = 13185900005L;//RandomUtils.nextLong(100, 1000000000);
        BigDecimal AMOUNT = BigDecimal.valueOf(0.01);

        System.out.println("QR-Code: " + WebMoneyUtils.createQrCode(ServiceType.TEST, POS_ID, ORDER_ID, AMOUNT));
        System.out.println();

        InitRequest initRequest = new InitRequest();
        initRequest.setReqNumber(REQ_NO);
        initRequest.setWmId(POS_WM_ID);
        CreateSession session = new CreateSession();
        session.setAgentId(1);
        session.setPosId(POS_ID);
        session.setOrderId(ORDER_ID);
        session.setAmount(AMOUNT);
        //session.setWmId("025899319006");

        initRequest.setCreateSession(session);
        System.out.println(WebMoneyUtils.marshal(InitRequest.class, initRequest));

        GetOutInvoicesRequest request = new GetOutInvoicesRequest();
        request.setReqNumber(REQ_NO);
        request.setWmId(POS_WM_ID);
        request.setPosId(POS_ID);
        request.setOrderId(ORDER_ID);
        System.out.println(WebMoneyUtils.marshal(GetOutInvoicesRequest.class, request));

        RefundRequest refund = new RefundRequest();
        refund.setReqNumber(REQ_NO);
        refund.setWmId(POS_WM_ID);
        RefundOrder order = new RefundOrder();
        order.setAmount(AMOUNT);
        order.setPosId(POS_ID);
        order.setOrderId(ORDER_ID);
        refund.setOrder(order);
        System.out.println(WebMoneyUtils.marshal(RefundRequest.class, refund));

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
        GetOutInvoicesResponse response = WebMoneyUtils.unmarshal(GetOutInvoicesResponse.class, responseXML);
        System.out.println(response.toString());
    }
}
