/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA;

import java.io.*;
import java.math.*;
import java.util.*;

/**
 *
 * @author Abdelgany
 */
public class RSAdecrypt {

    private RSAkey key;

    public RSAdecrypt(RSAkey ob) {
        key = ob;
    }

    public String decrypt(File f) throws IOException {
        String res = "";
        Scanner in = new Scanner(f);
        while (in.hasNext()) {
            res += decrypt(in.nextLine());
        }
        return res;
    }

    public String decrypt(String text) {
        String res = "";
        Scanner in = new Scanner(text);
        while (in.hasNext()) {
            res += decryptBlock(in.next());
        }
        return res;
    }

    private String decryptBlock(String block) {
        BigInteger plaintext = new BigInteger(block).modPow(key.d, key.n);
        return convertNumberToText(plaintext.toString());
    }

    private String convertNumberToText(String block) {
        BigInteger p = new BigInteger(block);
        String res = "";
        while (!(p.toString().equals("0"))) {
            res += (char) (p.mod(new BigInteger("256")).intValue());
            p = p.divide(new BigInteger("256"));
        }
        return res;
    }
}
