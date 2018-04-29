package ru.dreamkas.webmoney.utils;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;
import java.util.Enumeration;

import ru.dreamkas.webmoney.objects.adapters.BigDecimalAdapter;
import ru.dreamkas.webmoney.objects.base.ServiceType;

public class WebMoneyUtils {
    private static final Path CERTIFICATE_FILE_PATH = Paths.get("D:", "DOC", "WebMoney", "TRMTESTCERT.pfx");
    private static final String CERTIFICATE_PASSWORD = "1488";
    private static final String SECRET_KEY = "LvELxIkFXvFCVRyGkyZ_YIHdyvuK2A";
    private static final String CRC_FORMAT = "p=%s&order_id=%d&s=%s&%s";
    private static final String QR_CODE_FORMAT = "wmk:pay-at-pos?a=%d&p=%s&order_id=%d&s=%s&c=%d";

    /**
     * Создает QR-код для покупателя
     *
     * @param type
     *     Идентификатор сервиса (константа) 1 – тестовый, 5 - боевой
     * @param posId
     *     Идентификатор кассы
     * @param orderId
     *     Идентификатор заказа в кассовой системе
     * @param amount
     *     Сумма заказа
     */
    public static String createQrCode(ServiceType type, String posId, long orderId, BigDecimal amount) throws NoSuchAlgorithmException {
        String amountString = BigDecimalAdapter.format(amount);
        return String.format(QR_CODE_FORMAT,
            type.getType(),
            posId,
            orderId,
            amountString,
            calculateCRC(posId, orderId, amountString)
        );
    }

    /**
     * Создает подпись по алгоритму RSA. В качестве хеша используется SHA1
     *
     * @param value
     *     Данные, которые надо подписать
     * @return Сформированная подпись
     */
    public static String calculateSignature(String value) throws Exception {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(CERTIFICATE_FILE_PATH.toFile()), CERTIFICATE_PASSWORD.toCharArray());
        Enumeration<String> aliases = ks.aliases();
        String alias = aliases.nextElement();
        //X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, CERTIFICATE_PASSWORD.toCharArray());
        Signature dsa = Signature.getInstance("SHA1withRSA");
        dsa.initSign(privateKey);
        dsa.update(value.getBytes());
        return Base64.getEncoder().encodeToString(dsa.sign());
    }

    public static int calculateCRC(String posId, long orderId, String amount) throws NoSuchAlgorithmException {
        return calculateCRC(String.format(CRC_FORMAT, posId, orderId, amount, SECRET_KEY));
    }

    private static int calculateCRC(String value) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.reset();
        digest.update(value.toLowerCase().getBytes(StandardCharsets.US_ASCII));
        return calculateCRC8(digest.digest());
    }

    private static int calculateCRC8(byte[] data) {
        byte sum = 0;
        for (byte b : data) {
            sum += Byte.toUnsignedInt(b);
        }
        return Byte.toUnsignedInt(sum);
    }
}