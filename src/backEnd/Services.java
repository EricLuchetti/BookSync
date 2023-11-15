package backEnd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Services {

    //Dentro dessa classe estarão inclusas todas as funções genericas que são utilizadas em multiplas partes do programa

    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Error encrypting password.");
        }
    }

}
