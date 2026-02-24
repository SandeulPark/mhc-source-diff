package kr.or.khealth.smhc.common.util;

import java.io.File;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import com.dreamsecurity.magicvkeypad.MagicVKeypadServer;
import com.extrus.exafe.e2e.api.E2EApiManager;


public class CryptoUtil {

    public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
    public static String secretKey = "eL7P9GjA1ZdZnfN8Zra70A==";

    //AES256 암호화
    public static String AES_Encode(String str)	{

    	try {
	        byte[] textBytes = str.getBytes("UTF-8");
	        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
	        SecretKeySpec newKey = new SecretKeySpec(Base64.decodeBase64(secretKey), "AES");
	        Cipher cipher = null;
	        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);       
	        
	
	        return Base64.encodeBase64String(cipher.doFinal(textBytes));
        
    	}catch(Exception e){
            e.printStackTrace();
        }
    	return str;
    }

    //AES256 복호화
    public static String AES_Decode(String str) {

    	try {
	        byte[] textBytes =Base64.decodeBase64(str);
	        //byte[] textBytes = str.getBytes("UTF-8");
	        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
	        SecretKeySpec newKey = new SecretKeySpec(Base64.decodeBase64(secretKey), "AES");
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
	        return new String(cipher.doFinal(textBytes), "UTF-8");
        
    	}catch(Exception e){
            e.printStackTrace();
        }
    	return str;
    }
    
    

	public static Map decryptMap(Map rtParam, String paramKey){
		if(paramKey == null){
			System.out.println("decryptMap:: paramKey is null~");
			return rtParam;
		}
		if(rtParam == null){
			System.out.println("decryptMap:: rtParam is null~");
			return rtParam;
		}
		Iterator<String> keySet = rtParam.keySet().iterator();
		String key = "";
		String val = "";
		for(; keySet.hasNext();){
			key = keySet.next();
			val = rtParam.get(key).toString();
			if(key.equals(paramKey)){
				rtParam.put(key, decryptStr(val));
				break;
			}
		}       
		return rtParam;
	}
	
	// ================== MagicVKeypad 관련 필드/메서드 시작 ==================
		private static MagicVKeypadServer keypad;
		private static boolean isSuccessKeypad = false;

		private static final String KEY_PASSWORD = "1q2w3e4r";

		private static final String BASE64_PRI_KEY = "MIIFHjBIBgkqhkiG9w0BBQ0wOzAbBgkqhkiG9w0BBQwwDgQIMLD6QDaj5eACAgQAMBwGCCqDGoyaRAEEBBAkk/7WMJ8ctzOHMxwFZ8xuBIIE0GebMkvrUmxt4nl3ZS7+Klm37Azf6bKMiGM7pBaLq7W+4B10etDY7PKpf1trMMOLuCLGZOfdfTB/Z4ytdyhf6tP9YWj98CyKxANY2hEgKiTW9Fdx6m8ny58Y4zpzbjJI1ADY50Gl+cUQbw8mn+nhODpA2vB7zPothO0yV0/I6Hdh12LJtu1E7xiTIsB1gcyAfBkrAkry1csexgHvJO72mo6IFlty/aaCO77b8LLxxcVjfBiXaQw1Flsw3VckKaofjSGq6cYpwhE3INHIQadRvVHjzz6LCTt0hEgGjvLybT8QYgTukt9paDEtxKBz1UL1/5eP3Fej7mhRsHEWMcLBQ57HP9FUL6vUjRkr8Ln4eO4jtv4MSpf2jomvWf0o1VuPJZ3Qf/Wwyo+ZUQDvZy3EqTU9Jifnth/a7UWc9m7b6mY2gIfuHNkDJULkfngZvTN+9kI48Zc7aL/1Nu58ZeirbuOWBkpoPIZrfYBDxjtrft04V9JSe1A+oOmSRsUPvc7fuSakdRwqDuJ7nH8JKXbiiQEyYNgXTSFaJsfdDF+nioRtU9xX5WGQW9Wq+ILmf9Q4rKIdm3Znx0jraAuucVGeQP82ux3cCcHAP3mvZBJX+nWw6SVZEHlg4HtsPS5X+TbIXPjaL7Oe8YXPXsjMnFoi/WPGcSk08iXCYAcYLJ8FHrZMKRgoaH46KCtES2S9H8DGAashFO3+rswT0BJGa+2sOfpuE2a4rTHA4KGkGZnk2U72KTv0fCRLenPmlXwmRP8IA7yHlmrIHOhhgt1UCHDqfaIDqTEkRgw9oep5RNTx4MbEoPNh/zJqrZRBlHsU5HZTdrbILj16mdO75/9nmjrnpuA7rAxNvcBBDqA57Wg4iXKUJkU5rjyqZQeWqVUhVOCWdFPqGLwzpob5zt2MAQgtty5TWSlDTHlDaYa5PHWuSgte0pM6sA784pw7oichykxOgZfQzggJm6cxCiTUPwWCGVqCoWVrwnLn142Ox570R/WDDSjPrObOr2+t5Ggksy/5JbipdI4xMK5gM6mTZ/SgqaM45OjF1p6K8VA9IISWoYub0v6GIAq8imoclm74eZbJqcpQ2TgAlwO8AQR25fmMBWrn5vgdq2y9AfvWVMEiDcfHq4I2yJIEPZWgsM/sCfBR9SjfAPKtMqzK8v6lC6PVF/tOB+nx8YHQ7LouWhfpUP5/bTBKMO0aqT0cqLNS+XoYklIVHBzzQSEOxsjh8Lwb0Xo/bHPwfy8pJZAuxJxhbUWP8uoQrHR9LNvMNoZdSuSK+sUDEfwt5l9SwDn40L8WR302Y4yDET6XsAok1RpBY8kG7D86CRVibSljyvPsFBUF1U6gVLtJqmWPzngQ1MFO+Wp+D5KZEyBZZFuwG1qycBw+jvDT1W3QXIF2LBqam3vrYmn3YqxO4qS6IbmYNfIhvJPLBPsQTTp1PZVUtSMs5CixVEe470s00DCrvVBIvOe2l9SYHdYZsriv42c7ogno3fTwczB8AV5XfjZ09dJlkXcjsJwhztVzxW7CpGq5N9vXN8GsBAuSYPXqe2Y5rNo7qDm3cuYB9zGJEpo+uIn5GhUSnI07KmvXxv8bX1Sn/HD/aZXlRdaQ7664p+OLWD2MP9b7SHIXpBiKzOfIBsTFxftj";
				
		private static final String BASE64_PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhZ4u78x33Yl/v/BEIQUMvkeUBBxdT1wsKtXzIH9tRFH69/wWU3EkdzpR04VP7VEgmIxkvMXF6L/DaJRwq92JWQLnd8LSFkt6DJRHXixExb6zeXjBdExhugu75R625D35zHsiz7jiwlUB5BSS9cs6zuezAI1iWRWZ/AankTBr3IpR248KBF9hdI19ueZyuBK3vSquRpfAx9MZy4MMDVPfhmtbGeS3CqST6MDZouI2bYQbK3p8RVOLLvZLWZjgTS0ZKYrAtRF39JvuCnwHWN9KFFxR5fsnrgtXChZplNEAAqtEH9GlT8MOfFKmWo0NFu5fdA/8Yg8bfYys8yTkXJPyAQIDAQAB";
				
		/**
		 * MagicVKeypadServer 초기화 (방법2 - Base64 키 방식)
		 */
		private static void initMagicVKeypad() {
		    if (isSuccessKeypad && keypad != null) return;

		    try {
		        String basePath = getKeypadLibPath();
		        String libPath = basePath + "lib";

		        keypad = new MagicVKeypadServer();
		        keypad.setLicensePath(libPath);

		        // Base64 문자열 기반 키 설정 (PDF 방법2)
		        keypad.setBase64PrivateKey(BASE64_PRI_KEY, KEY_PASSWORD);
		        keypad.setBase64PublicKey(BASE64_PUB_KEY);

		        isSuccessKeypad = true;
		        System.out.println("[MagicVKeypad] init SUCCESS (Base64 방식)");

		    } catch (Exception e) {
		        isSuccessKeypad = false;
		        System.out.println("[MagicVKeypad] init FAILED");
		    }
		}


		/**
		 * MagicVKeypad 복호화
		 */
		public static String keypadDecrypt(String encStr) {
			
			//복호화 전
			System.out.println("[MagicVKeypad] RAW encStr = " + encStr);

		    if (encStr == null || encStr.trim().isEmpty()) {
		        return encStr;
		    }

		    initMagicVKeypad();
		    if (!isSuccessKeypad || keypad == null) {
		        return encStr;
		    }

		    try {
		        byte[] data = encStr.getBytes("UTF-8");
		        
		        System.out.println("[MagicVKeypad] BEFORE decrypt, data.length=" + data.length); 
		        
		        String plain = keypad.decryptMagicVKeypadRecord(data);
		        
		        //복호화 결과 
		        System.out.println("##### [MagicVKeypad] DECRYPT RESULT (plain PIN) = " + plain);
		        
		        return plain;

		    } catch (Exception e) {
		    	System.out.println("[MagicVKeypad] decrypt error");
		        return encStr;
		    }
		}
	
	private static String getKeypadLibPath(){
		String[] libPaths = new String[]{
										"/home/jeus/MagicKeypad/"   //운영/개발
										,"/home/jeus/MagicVKeypad/"   //운영/개발
										,"D:/MagicKeypad/"			//테스트1
										,"C:/MagicKeypad/"};		//테스트2
		String rtPath = libPaths[0];
		for(String path : libPaths){
			File file = new File(path); 
			if(file.isDirectory()){
				rtPath = path;
				break;
			}
		}		
		return rtPath;
	}
	
	public static String decryptStr(String encStr){
		String decStr = encStr;
		if(encStr != null && encStr.indexOf("ENCINFO") == 0){ 
			try{
				E2EApiManager e2eManager = new E2EApiManager();
				decStr = e2eManager.e2eDecrypt(encStr);
				
				System.out.println("decStr===="+decStr);
			
			}catch(Exception e){
				if(encStr != null && encStr.indexOf("ENCINFO") == 0) e.printStackTrace();
			}
		}else{ //MagicKeypad
			
			decStr = keypadDecrypt(encStr);
		}
		
		return decStr;
	}
	

}
