/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onetimeattack;

import java.util.*;

/**
 *
 * @author Student
 */
public class onetimekey {
    
    int sz;
    Vector<Integer> vec = new Vector<Integer>();
    onetimekey(int len){
        sz = len;
        for(int i = 0; i < sz; i++){
            int r = (int)('A' + Math.random() * ('Z' + 1 - 'A'));
            vec.add(r);
        }
    }
    int getidx(int I){
        return vec.get(I % sz);
    }
    public static void main(String[] args) {
           onetimekey key = new onetimekey(3);
           onetimeEncrypt e = new onetimeEncrypt(key);
           onetimeDecrypt d = new onetimeDecrypt(key);
           onetimeattack a = new onetimeattack();
           String en = e.encrypt("bd");
           String de = d.decrypt(en);
           System.out.println(en + " " + de);
           System.out.println("----------");
           System.out.println(a.attack(en));
       }
}
