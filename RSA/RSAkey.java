/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA;

import java.util.*;
import java.math.*;

/**
 *
 * @author Abdelgany
 */
public class RSAkey {

    public BigInteger e, d, n;
    public String sp,sq;
    public static void main(String[] args) {
        RSAkey ob = new RSAkey(new BigInteger("3"), new BigInteger("7"));
        RSAencrypt ob1 = new RSAencrypt(ob);
        String s = ob1.encrypt("abdelgany");
        RSAdecrypt ob2 = new RSAdecrypt(ob);
        String s1 = ob2.decrypt(s);
        System.out.println(s + " " + s1);
    }

    public RSAkey() {
        BigInteger p = BigInteger.probablePrime(200, new Random());
        BigInteger q = BigInteger.probablePrime(200, new Random());
        sp = p.toString();
        sq = q.toString();
        Build(p, q);
    }

    public RSAkey(BigInteger p, BigInteger q) {
        Build(p, q);
    }

    private void Build(BigInteger p, BigInteger q) {
        n = p.multiply(q);
        BigInteger Q = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
        e = BigInteger.probablePrime(400, new Random()).remainder(Q);
        while (!com(e.gcd(Q), BigInteger.ONE) || (com(e, BigInteger.ONE)) || (com(e, new BigInteger("2")))) {
            e = BigInteger.probablePrime(400, new Random()).remainder(Q);
        }
        d = e.modInverse(Q);
    }

    private boolean com(BigInteger a, BigInteger b) {
        return a.toString().equals(b.toString());
    }

}
