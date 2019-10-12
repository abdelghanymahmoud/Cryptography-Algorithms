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
public class RSAencrypt {

    private RSAkey key;

    public RSAencrypt(RSAkey ob) {
        key = ob;
    }

    public String encrypt(File f) throws IOException {
        String res = "";
        Scanner in = new Scanner(f);
        while(in.hasNext()){
            res += encrypt(in.nextLine());
        }
        return res;
    }

    public String encrypt(String text) {
        String res = "";
        for (int i = 0; i < text.length(); i += 5) {
            if (i + 5 < text.length()) {
                res += encryptBlock(text.substring(i, i + 5)) + " ";
            } else {
                res += encryptBlock(text.substring(i));
            }
        }
        return res;
    }

    private String encryptBlock(String block) {
        String p = convertTextToNumber(block);
        BigInteger plaintext = new BigInteger(p);
        BigInteger cipher = plaintext.modPow(key.e, key.n);
        return cipher.toString();
    }

    private String convertTextToNumber(String block) {
        BigInteger num = new BigInteger("0");
        BigInteger p = new BigInteger("1");
        for(int i = 0; i < block.length(); i++){
            BigInteger ch = new BigInteger(((int)block.charAt(i)) + "");
            num = num.add(p.multiply(ch));
            p = p.multiply(new BigInteger("256"));
        }
        return num.toString();
    }
}
