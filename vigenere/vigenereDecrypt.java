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
public class vigenereDecrypt {
    vigenerekey key;
    vigenereDecrypt(vigenerekey ob){
        key = ob;
    }
    String decrypt(String text){
        String res = "";
        text = text.toUpperCase().trim();
        for(int i = 0; i < text.length(); i++){
            char ch = key.getidx(i);
            res += (char)('A' + ((text.charAt(i) - ch + 26) % 26));
        }
        return res;
    }
    String decrypt(File f) throws IOException {
        String res = "";
        Scanner in = new Scanner(f);
        while(in.hasNext()){
            res += decrypt(in.nextLine());
            res += "\r\n";
        }
        return res;
    }
}
