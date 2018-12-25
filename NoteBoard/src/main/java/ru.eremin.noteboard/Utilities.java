package ru.eremin.noteboard;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @autor Artem Eremin on 23.12.2018.
 */

public class Utilities {

    @SneakyThrows
    public static String getHash(@NotNull final String s) {
        final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        final byte[] bytes = ("1112_54$%" + s).getBytes(StandardCharsets.UTF_8);
        final byte[] hex = messageDigest.digest(bytes);
        return DatatypeConverter.printHexBinary(hex);
    }
}
