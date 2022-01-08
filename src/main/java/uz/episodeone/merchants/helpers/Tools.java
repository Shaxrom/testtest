package uz.episodeone.merchants.helpers;

import com.google.common.io.ByteStreams;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.io.Resource;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by mansurmuzaffarov on 24/10/17.
 */
public class Tools {

    public static String getMD5(String s) {

        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(s.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            StringBuilder hashtext = new StringBuilder(bigInt.toString(16));
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }

            return hashtext.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean emptyString(String str) {
        return (str == null || str.trim().length() == 0);
    }

    public static boolean emptyCollection(Collection col) {
        return col == null || col.size() == 0;
    }

    public static boolean emptyMap(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean emptyArray(Object[] arr) {
        return arr == null || arr.length == 0;
    }

    public static String getStackTrace(Throwable throwable) {
        return throwable.getMessage() + "\n" + ExceptionUtils.getStackTrace(throwable);
    }

//    public static ResponseEntity responseEntity(HttpStatus status, Object body, Throwable throwable) {
//
//        JSONRPCResponse responseBody = new JSONRPCResponse();
//
//        responseBody.setCode(status.value());
//        responseBody.setResult(body);
//        if (throwable != null) {
//            responseBody.setError(throwable);
//        }
//
//        return new ResponseEntity(responseBody, status);
//    }

//    public static ResponseEntity responseEntity(HttpStatus status, Object body) {
//
//        return responseEntity(status, body, null);
//    }
//
//    public static ResponseEntity responseEntity(HttpStatus status) {
//
//        return responseEntity(status, null, null);
//    }
//
//    public static ResponseEntity responseEntity() {
//
//        return responseEntity(HttpStatus.OK, null, null);
//    }

    public static Date dateFormatter(String val) throws ParseException {
        return !Tools.emptyString(val) ?
                new SimpleDateFormat("dd.MMMM.yyyy", Locale.getDefault()).parse(val) : null;
    }

    public static String phoneFormatterToDB(final String val) {
        return !Tools.emptyString(val) ? "+998" + val.replace(" ", "") : null;
    }

    public static String phoneFormatterFromDB(final String val) {
        return !Tools.emptyString(val) ? val.replace("+998", "") : null;
    }

    /**
     * Create standard Date from LocalDateTime
     */
    public static Date toDate(LocalDateTime date) {
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }



    /**
     * Needed for remote HUMO request
     *
     * @return the BANK_C parameter:
     * 9000090102421809
     * ^^ - will be 09
     */
    public static String getBankNumFromCardNumber(String fullCardNumber) {
        return fullCardNumber.substring(4, 6);
    }

    /**
     * Check if particular card is in trusted list
     *
     * @param trustedCards - list of Trusted cards to check
     * @param card
     * @return true the cardId presents in trusted
     */
//    public static boolean isCardTrusted(List<TrustedCard> trustedCards, Card card) {
//
//        for (TrustedCard trustedCard : trustedCards)
//            if (trustedCard.getCardId().equals(card.getId()))
//                return true;
//
//        return false;
//    }

    /**
     * Check if particular customer is in trusted list
     *
//     * @param trustedCards - list of Trusted cards to check
//     * @param customer
     * @return true the customerId presents in trusted
     */

    public static String hideCardNumber(String number) {
        return number.substring(0, 6) + "******" + number.substring(12);
    }


    public static Instant toInstant(LocalDateTime ldt, String timeZone) {
        return ldt.atZone(ZoneId.of(timeZone)).toInstant();
    }
    // Алгоритм шифрования
    public static String encrypto(String original, Resource certFile) throws IOException, IllegalBlockSizeException, BadPaddingException {
        String result = null ;
        if(original != null) {
            try {
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                byte[] keyBytes = ByteStreams.toByteArray(certFile.getInputStream());
                String algorithm  = "AES";
                SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
                try {
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    byte[] plainText = original.getBytes("UTF-8");
                    byte[] value = cipher.doFinal(plainText);
                    result = new String(Base64.encodeBase64(value));
                } catch (InvalidKeyException e) {
                    System.out.println("error " + e );
                }
            } catch (NoSuchAlgorithmException e) {
                System.out.println("error " + e );
            } catch (NoSuchPaddingException e) {
                System.out.println("error " + e );
            }
        }
        return result;
    }

    // Алгоритм дешифрования
    public static String decrypto(String crypto, Resource certFile) throws IOException, IllegalBlockSizeException, BadPaddingException {
        String result = null;
        if(certFile != null) {
            try {
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                byte[] keyBytes = ByteStreams.toByteArray(certFile.getInputStream());
                String algorithm  = "AES";
                SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
                try {
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    byte[] base64decodedTokenArr = Base64.decodeBase64(crypto.getBytes());
                    byte[] decryptedStr = cipher.doFinal(base64decodedTokenArr);
                    result =  new String(decryptedStr) ;
                } catch (InvalidKeyException e) {
                    System.out.println("error " + e );
                }
            } catch (NoSuchAlgorithmException e) {
                System.out.println("error " + e );
            } catch (NoSuchPaddingException e) {
                System.out.println("error " + e );
            }
        }
        return result;
    }
}
