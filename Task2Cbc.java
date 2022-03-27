import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class Task2Cbc {

    private final SecretKey key;
    private final Cipher cipher;
    private final byte[] iv;

    public Task2Cbc() throws NoSuchPaddingException, NoSuchAlgorithmException {
        key = new SecretKeySpec("YELLOW SUBMARINE".getBytes(StandardCharsets.UTF_8), "AES");
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        iv = "0".repeat(16).getBytes(StandardCharsets.UTF_8);
    }

    public byte[] encode(String text) throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal((text).getBytes(StandardCharsets.UTF_8));
    }

    public byte[] decode(byte[] byteArray) throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(byteArray);
    }
} 