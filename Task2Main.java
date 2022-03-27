import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Task2Main {
    public static void main(String[] args) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {


        byte[] encodedBytesFirst = new byte[0];
        byte[] encodedBytes = new byte[0];
        Task2Cbc task = null;

        try {
            task = new Task2Cbc();
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            assert task != null;
            encodedBytesFirst = task.encode("alert(\"You are pwned!\");// ");
            encodedBytes = task.encode("alert(\"Hello world!\"); ");
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | InvalidKeyException e) {
            e.printStackTrace();
        }

        System.out.println(encodedBytes.length + encodedBytesFirst.length);

        byte[] newArray = new byte[encodedBytes.length + encodedBytesFirst.length];

        for (int i = 0; i < encodedBytesFirst.length; i++) {
            newArray[i] = encodedBytesFirst[i];
            newArray[i + encodedBytesFirst.length] = encodedBytes[i];
        }

        System.out.println(new String(encodedBytes));
        System.out.println(new String(task.decode(newArray)));
    }
}
