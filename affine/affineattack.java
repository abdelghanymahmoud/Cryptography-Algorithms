/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affine;

import java.io.*;
import java.util.*;

/**
 *
 * @author Abdelgany
 */
public class affineattack {
    
    private Vector<Integer> vec;
    private affinekey key;
    private affine solver;
    
    int GCD(int a, int b){
        if(a == 0) return b;
        return GCD(b%a, a);
    }
    affineattack(){
        vec = new Vector<Integer>();
        for(int i = 1; i <= 26; i++)
            if(GCD(i, 26) == 1) vec.add(i);
    }
    String attack(String text){
        String res = "";
        for(int a = 0; a < vec.size(); a++){
            for(int b = 0; b < 26; b++){
                res += "a = " + vec.get(a) + ", b = " + b + "\r\n";
                key = new affinekey(vec.get(a), b);
                solver =  new affine(key);
                res += solver.decrypt(text);
                res += "\r\n\t----------------\r\n";
            }
        }
        return res;
    }
    String attack(File f) throws IOException {
        String res = "";
        Scanner in = new Scanner(f);
        while(in.hasNext()) res += in.nextLine() + "\r\n";
        return attack(res);
    }
//    public static void main(String[] args) throws IOException {
//        affineattack ob = new affineattack();
//        File f = new File("D:\\Study\\4th year\\2nd\\CS402\\in.txt");
//        System.out.println(ob.attack(f));
//    }
}
