package com.yyyu.mmall.uitls.codec;

/**
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class CoderTest {


	public static void main(String[] args) throws Exception {
	/*	String inputStr = "简单加密123123";
		System.err.println("原文:\n" + inputStr);

		byte[] inputData = inputStr.getBytes();
		String code = Coder.encryptBASE64(inputData);

		System.err.println("BASE64加密后:\n" + code);

		byte[] output = Coder.decryptBASE64(code);

		String outputStr = new String(output);

		System.err.println("BASE64解密后:\n" + outputStr);

		byte[] bytes = Coder.encryptMD5("123".getBytes());
		System.err.println("MD5解密后:\n" + new String(bytes));*/

		String originStr = "{\"userId\":\"2\",\"iat\":\"Mar 19, 2018 4:12:34 PM\",\"exp\":\"Mar 19, 2018 4:13:04 PM\"}";
		String desKey =DESCoder.initKey("mall-ae");
		System.out.println("desKey："+desKey);
		String encryptStr = DESCoder.encrypt(originStr, desKey);
		System.out.println("encryptStr："+encryptStr);
		String decryptDes = DESCoder.decrypt(encryptStr, desKey);
		System.out.println("decryptDes："+decryptDes);

	}

}
