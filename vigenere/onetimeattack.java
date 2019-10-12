/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package onetimeattack;

import java.io.*;
import java.util.*;

/**
 *
 * @author Student
 */
public class onetimeattack {
    
    vigenereDecrypt de;
    Vector<String> p = new Vector<String>();
    Vector<String> k = new Vector<String>();
    
    void solve(String c, String key){
        if(c.length() == key.length()){
            de = new vigenereDecrypt(new vigenerekey(key));
            p.add(de.decrypt(c));
            k.add(key);
            return ;
        }
        for(char i = 'A'; i <= 'Z'; i++)
            solve(c, key+i);
    }
    String attack(String c){
        String res = "";
        solve(c, "");
        for(int i = 0; i < p.size(); i++){
            String str = "key: " + k.get(i) + "\r\n";
            str += "plain: " + p.get(i) + "\r\n";
            res += str;
        }
        System.out.println(res);
        return res;
    }
    
    // ordinary solution
//    String attack(String en, String de){
//        String res = "";
//        en = en.toUpperCase().replace(" ", "");
//        de = de.toUpperCase().replace(" ", "");
//        for(int i = 0; i < en.length(); i++){
//            char ch = (char)('A' + ( (en.charAt(i)-de.charAt(i) + 26) % 26));
//            res += ch;
//        }
//        return res;
//    }
    String attack(File f) throws IOException {
        String res = "";
        Scanner in = new Scanner(f);
        String en = "";
        while(in.hasNext()){
            en += in.nextLine();
            res += attack(en);
        }
        return res;
    }
}
