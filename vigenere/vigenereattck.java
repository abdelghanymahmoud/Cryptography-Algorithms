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
public class vigenereattck {

    private Vector<Integer> F(String pat) {
        Vector<Integer> f = new Vector<>();
        for (int i = 0; i < pat.length() + 1; i++) {
            f.add(0);
        }
        for (int i = 2; i <= (int) pat.length(); i++) {
            int j = f.get(i - 1);
            while (j != 0 && pat.charAt(i - 1) != pat.charAt(j)) {
                j = f.get(j);
            }
            if (pat.charAt(i - 1) == pat.charAt(j)) {
                f.set(i, j + 1);
            }
        }
        return f;
    }
    
    String attack(String en, String de){
        String res = "";
        en = en.toUpperCase().replace(" ", "");
        de = de.toUpperCase().replace(" ", "");
        for(int i = 0; i < en.length(); i++){
            char ch = (char)('A' + ( (en.charAt(i)-de.charAt(i) + 26) % 26));
            res += ch;
        }
        Vector<Integer> vec = F(res);
        for(int i = 2; i < vec.size(); i++){
            if(vec.get(i) == 1){
                int len = i-1;
                while(++i < vec.size() && vec.get(i) != len){
                    if(vec.get(i) == 0) break;
                }
                if(i < vec.size() && vec.get(i) == len) return res.substring(0, len);
            }
        }
        return res;
    }
    String attack(File f) throws IOException {
        Scanner in = new Scanner(f);
        String en = in.nextLine(),de = in.nextLine();
        return attack(en, de);
    }
}
