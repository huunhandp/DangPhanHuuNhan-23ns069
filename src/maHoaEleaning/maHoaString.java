package maHoaEleaning;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class maHoaString {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập vào đoạn text muốn mã hóa : ");
		String text = sc.nextLine();

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(text.getBytes());

			StringBuilder hexString = new StringBuilder();

			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			System.out.println("đoạn text đã được mã hóa : " + hexString.toString());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}
}
