package ru.dreamkas.webmoney.objects.tools;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import ru.dreamkas.webmoney.tools.BigDecimalAdapter;

public class WebMoneyUtils {
    private static final Path CERTIFICATE_FILE_PATH = Paths.get("D:", "DOC", "WebMoney", "TRMTESTCERT.pfx");
    private static final String CERTIFICATE_PASSWORD = "1488";
    private static final String SECRET_KEY = "LvELxIkFXvFCVRyGkyZ_YIHdyvuK2A";
    private static final String CRC_FORMAT = "p=%s&order_id=%d&s=%s&%s";
    private static final String QR_CODE_FORMAT = "wmk:pay-at-pos?a=%d&p=%s&order_id=%d&s=%s&c=%d";

    private static final Map<Class<?>, Marshaller> marshallers = new ConcurrentHashMap<>();
    private static final Map<Class<?>, Unmarshaller> unmarshallers = new ConcurrentHashMap<>();

    public static <T> String marshal(Class<T> clazz, T value) throws JAXBException, IOException {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            getMarshaller(clazz).marshal(value, stream);
            return stream.toString();
        }
    }

    public static <T> T unmarshal(Class<T> clazz, String value) throws JAXBException, XMLStreamException {
        try (StringReader reader = new StringReader(value)) {
            XMLStreamReader xml = XMLInputFactory.newInstance().createXMLStreamReader(reader);
            return getUnmarshaller(clazz).unmarshal(xml, clazz).getValue();
        }
    }

    private static Marshaller getMarshaller(Class<?> clazz) {
        return marshallers.computeIfAbsent(clazz, WebMoneyUtils::createMarshaller);
    }

    private static Unmarshaller getUnmarshaller(Class<?> clazz) {
        return unmarshallers.computeIfAbsent(clazz, WebMoneyUtils::createUnmarshaller);
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

    private static Unmarshaller createUnmarshaller(Class<?> c) {
        try {
            return JAXBContext.newInstance(c).createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

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

    public static int calculateCRC(String posId, long orderId, String amount) throws NoSuchAlgorithmException {
        return calculateCRC(String.format(CRC_FORMAT, posId, orderId, amount, SECRET_KEY));
    }

    /**
     * Создает подпись по алгоритму RSA. В качестве хеша используется SHA1
     *
     * @param text
     *     Данные, которые надо подписать
     * @return Сформированная подпись
     */
    public static String calculateSignature(String text) throws Exception {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(CERTIFICATE_FILE_PATH.toFile()), CERTIFICATE_PASSWORD.toCharArray());
        Enumeration<String> aliases = ks.aliases();
        String alias = aliases.nextElement();
        //X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, CERTIFICATE_PASSWORD.toCharArray());
        Signature dsa = Signature.getInstance("SHA1withRSA");
        dsa.initSign(privateKey);
        dsa.update(text.getBytes());
        return Base64.getEncoder().encodeToString(dsa.sign());
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