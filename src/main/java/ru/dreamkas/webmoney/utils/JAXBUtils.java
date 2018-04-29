package ru.dreamkas.webmoney.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class JAXBUtils {
    private static final Map<Class<?>, Marshaller> marshallers = new ConcurrentHashMap<>();
    private static final Map<Class<?>, Unmarshaller> unmarshallers = new ConcurrentHashMap<>();

    public static <T> String marshal(T value) throws JAXBException, IOException {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            getMarshaller(value.getClass()).marshal(value, stream);
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
        return marshallers.computeIfAbsent(clazz, JAXBUtils::createMarshaller);
    }

    private static Unmarshaller getUnmarshaller(Class<?> clazz) {
        return unmarshallers.computeIfAbsent(clazz, JAXBUtils::createUnmarshaller);
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

}
