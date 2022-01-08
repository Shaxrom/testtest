package uz.episodeone.merchants.helpers;

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


    public static Instant toInstant(LocalDateTime ldt, String timeZone) {
        return ldt.atZone(ZoneId.of(timeZone)).toInstant();
    }

}
