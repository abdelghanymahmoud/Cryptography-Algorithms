/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onetimeattack;

/**
 *
 * @author Student
 */
public class vigenerekey {
    String key;
    vigenerekey(String str){
        key = str.toUpperCase();
    }
    char getidx(int I){
        int x = I % (key.length());
        return key.charAt(x);
    }
    public static void main(String[] args) {
        vigenerekey key = new vigenerekey("leg");
        vigenereEncrypt e = new vigenereEncrypt(key);
        vigenereDecrypt d = new vigenereDecrypt(key);
        String en = e.encrypt("abdelhamid");
        String de = d.decrypt(en);
        System.out.println(en);
        System.out.println(de);
        vigenereattck k = new vigenereattck();
        System.out.println(k.attack(en, de));
    }
}
