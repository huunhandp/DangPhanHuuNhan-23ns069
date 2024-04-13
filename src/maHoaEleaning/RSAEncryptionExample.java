package maHoaEleaning;

import java.security.*;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAEncryptionExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Tạo cặp khóa RSA
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // Kích thước khóa 2048 bit
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Nhập văn bản cần mã hóa từ người dùng
            System.out.println("Nhập văn bản cần mã hóa: ");
            String plainText = scanner.nextLine();

            // Mã hóa văn bản
            byte[] encryptedBytes = encrypt(plainText, publicKey);

            // Giải mã văn bản
            String decryptedText = decrypt(encryptedBytes, privateKey);

            // In ra kết quả
            System.out.println("Văn bản đã được mã hóa: " + new String(encryptedBytes));
            System.out.println("Văn bản đã được giải mã: " + decryptedText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    // Mã hóa văn bản sử dụng khóa công khai
    public static byte[] encrypt(String plainText, PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plainText.getBytes());
    }

    // Giải mã văn bản sử dụng khóa riêng tư
    public static String decrypt(byte[] encryptedBytes, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}

