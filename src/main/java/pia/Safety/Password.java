package pia.Safety;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Generates random char sequences
 * Created by jakub on 31.12.2016.
 */
public class Password {
    /**
     * Numbers 0-9 used to generate random string containing only numbers
     */
    static final String NUMBERS = "0123456789";

    /**
     * Generuje posloupnosti o urcitem poctu cisel
     * @param digits pocet cisel
     * @return vygenerovane cislo
     */
    public String generatePin(int digits){
            StringBuilder sb = new StringBuilder( digits );
            for( int i = 0; i < digits; i++ )
                sb.append( NUMBERS.charAt( rnd.nextInt(NUMBERS.length()) ) );
            return sb.toString();
    }

    /**
     * String containing small, capital pismena and numbers 0-9
      */
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    /**
     * Generates random number
     */
    static SecureRandom rnd = new SecureRandom();

    /**
     * Generates random alphanumeric code
     * @param len
     * @return
     */
    public String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
}
