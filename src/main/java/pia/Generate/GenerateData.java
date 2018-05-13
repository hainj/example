package pia.Generate;

import pia.Safety.Hash;
import pia.data.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Generuje uzivatele, prava, ucty a transakce pokud je povoleno - neni nikde pouzito
 * Created by jakub on 04.01.2017.
 */
public class GenerateData {

    public UserJ GenerateUser(RightsJ r) throws UnsupportedEncodingException, NoSuchAlgorithmException, ParseException {
        UserJ user = new UserJ();
        Hash hash = new Hash();
        user.setName("Jakub Hain");
        String pw = hash.getHash("1234");
        user.setPassword(pw);
        user.setGender("Male");
        user.setNid("931111/1201");
        user.setAddress("gregerg 122");
        user.setEmail("a@b.c");
        user.setPhone("721016873");
        user.setLogin("Admin001");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse("1993-04-17");
        user.setBirthDate(new java.sql.Date(parsed.getTime()));
        user.setIdRights(r);

    return user;
    }
    public UserJ GenerateUserN(RightsJ r) throws UnsupportedEncodingException, NoSuchAlgorithmException, ParseException {
        UserJ user = new UserJ();
        Hash hash = new Hash();
        user.setName("Jakub Hain");
        String pw = hash.getHash("0001");
        user.setPassword(pw);
        user.setGender("Male");
        user.setNid("910314/1234");
        user.setAddress("gregerg 122");
        user.setEmail("ab@b.c");
        user.setPhone("721016873");
        user.setLogin("User0002");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse("1993-04-17");
        user.setBirthDate(new java.sql.Date(parsed.getTime()));
        user.setIdRights(r);

        return user;
    }
    public UserJ GenerateUserN1(RightsJ r) throws UnsupportedEncodingException, NoSuchAlgorithmException, ParseException {
        UserJ user = new UserJ();
        Hash hash = new Hash();
        user.setName("Jakub Hain");

        String pw = hash.getHash("0001");
        user.setPassword(pw);
        user.setGender("Male");
        user.setNid("910514/1234");
        user.setAddress("gregerg 122");
        user.setEmail("abb@b.c");
        user.setPhone("721016873");
        user.setLogin("User0001");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse("1993-04-17");
        user.setBirthDate(new java.sql.Date(parsed.getTime()));
        user.setIdRights(r);

        return user;
    }


    public List<RightsJ> GenerateRights() throws UnsupportedEncodingException, NoSuchAlgorithmException, ParseException {
        List<RightsJ> rights= new ArrayList<>();
        RightsJ r1 = new RightsJ();
        r1.setAddUser(1);
        r1.setDeleteUser(1);
        r1.setModifyUser(1);
        r1.setModifSelf(1);
        r1.setRole("Admin");
        r1.setTransaction(0);
        RightsJ r2 = new RightsJ();
        r2.setAddUser(0);
        r2.setDeleteUser(0);
        r2.setModifyUser(0);
        r2.setModifSelf(1);
        r2.setRole("User");
        r2.setTransaction(1);
        rights.add(r1);
        rights.add(r2);
        return rights;
    }
    public List<AccountJ> generateAccounts(UserJ user, UserJ user1){
        List<AccountJ> accountJList = new ArrayList<>();
        AccountJ acc1 = new AccountJ();
        acc1.setAccNumber("1122334455");
        acc1.setCardNumber("1234123412341234");
        acc1.setBalance(1000000.0);
        acc1.setIdUser(user);

        AccountJ acc2 = new AccountJ();
        acc2.setAccNumber("1122334466");
        acc2.setCardNumber("1235123512351235");
        acc2.setBalance(1000000.0);
        acc2.setIdUser(user1);
        accountJList.add(acc1);
        accountJList.add(acc2);
        return accountJList;

    }
    public List<TransactionJ> generateTransactions(AccountJ acc){
        List<TransactionJ> tj = new ArrayList<>();
        TransactionJ ts1 = new TransactionJ();
        ts1.setAccId(acc);
        ts1.setAccNumber("1234569870");
        ts1.setDate(new Timestamp((System.currentTimeMillis())));
        ts1.setBankCode("0000");
        ts1.setAmount(1000.00);
        ts1.setProcessed(true);

        TransactionJ ts2 = new TransactionJ();
        ts2.setAccId(acc);
        ts2.setAccNumber("1234569870");
        ts2.setDate(new Timestamp((System.currentTimeMillis())));
        ts2.setBankCode("0000");
        ts2.setAmount(1000.00);
        ts2.setProcessed(true);

        TransactionJ ts3 = new TransactionJ();
        ts3.setAccId(acc);
        ts3.setAccNumber("1234569870");
        ts3.setDate(new Timestamp((System.currentTimeMillis())));
        ts3.setBankCode("0000");
        ts3.setAmount(1000.00);
        ts3.setProcessed(true);
        TransactionJ ts4 = new TransactionJ();
        ts4.setAccId(acc);
        ts4.setAccNumber("1234569870");
        ts4.setDate(new Timestamp((System.currentTimeMillis())));
        ts4.setBankCode("0300");
        ts4.setMessage("Transakce 4");
        ts4.setAmount(1000.00);
        ts4.setProcessed(true);
        TransactionJ ts5 = new TransactionJ();
        ts5.setAccId(acc);
        ts5.setAccNumber("1234569870");
        ts5.setDate(new Timestamp((System.currentTimeMillis())));
        ts5.setBankCode("0100");
        ts5.setAmount(1000.00);
        ts5.setProcessed(true);

        tj.add(ts1);
        tj.add(ts2);
        tj.add(ts3);
        tj.add(ts4);
        tj.add(ts5);


        return tj;

    }

}
