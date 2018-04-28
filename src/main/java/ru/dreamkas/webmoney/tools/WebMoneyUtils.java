package ru.dreamkas.webmoney.tools;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Enumeration;
import java.util.stream.Collectors;

public class WebMoneyUtils {
    private static final Path CERTIFICATE_FILE_PATH = Paths.get("D:", "DOC", "WebMoney", "TRMTESTCERT.pfx");
    private static final String CERTIFICATE_PASSWORD = "1488";

    /**
     * Создает подпись по алгоритму RSA. В качестве хеша используется SHA1
     *
     * @param value
     *     Данные, которые надо подписать
     * @return Сформированная подпись
     */
    public static String calculateSignature(String value) throws Exception {
        System.out.println("Try to sign String: " + value);
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(CERTIFICATE_FILE_PATH.toFile()), CERTIFICATE_PASSWORD.toCharArray());
        Enumeration<String> aliases = ks.aliases();
        String alias = aliases.nextElement();
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
        System.out.println("Certificate: " + CERTIFICATE_FILE_PATH.toString() + "\n" +
            "X500Principal: " + cert.getIssuerX500Principal().getName() + "\n" +
            "Type: " + cert.getType() + "\n" +
            "Version: " + cert.getVersion() + "\n" +
            "IssuerDN: " + cert.getIssuerDN().getName() + "\n" +
            (cert.getIssuerAlternativeNames() != null
                ? ("IssuerAlternativeNames: " + cert.getIssuerAlternativeNames().stream().map(s -> s.stream().map(Object::toString).collect(Collectors.joining(";"))).collect(Collectors.joining("\n")) + "\n")
                : ""
            ) +
            "SigAlgOID: " + cert.getSigAlgOID() + "\n" +
            "SigAlgName: " + cert.getSigAlgName() + "\n" +
            "SerialNumber: " + cert.getSerialNumber());
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, CERTIFICATE_PASSWORD.toCharArray());
        Signature dsa = Signature.getInstance("SHA1withRSA");
        dsa.initSign(privateKey);
        dsa.update(value.getBytes());
        String result = Base64.getEncoder().encodeToString(dsa.sign());
        System.out.println("Sign is: " + result);
        return result;
    }

    public static int calculateCRC(String value) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.reset();
        digest.update(value.getBytes(StandardCharsets.US_ASCII));
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