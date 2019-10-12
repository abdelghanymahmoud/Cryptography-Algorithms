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
public class onetimeEncrypt {
    onetimekey key;
    onetimeEncrypt(onetimekey ob){
        key = ob;
    }
    String encrypt(String text){
        String res = "";
        text = text.toUpperCase().trim();
        for(int i = 0; i < text.length(); i++){
            int ch = key.getidx(i);
            res += (char)('A' + ((ch + text.charAt(i) - 2 * 'A') % 26));
        }
        return res;
    }
    String encrypt(File f) throws IOException {
        String res = "";
        Scanner in = new Scanner(f);
        while(in.hasNext()){
            res += encrypt(in.nextLine());
            res += "\r\n";
        }
        return res;
    }
}
