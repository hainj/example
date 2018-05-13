package pia.Safety;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hashes string
 * Created by jakub on 31.12.2016.
 */
public class Hash {
    /***
     * Vytvori hash z hesla
     * @param password heslo
     * @return hash hesla
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public String getHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
        byte[] passBytes = (password + "PIA2016/2017").getBytes("UTF-8");
        byte[] passHash = sha512.digest(passBytes);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < passHash.length; ++i) {
            sb.append(Integer.toHexString((passHash[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
        }

        return sb.toString();
    }
}
