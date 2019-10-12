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
public class affine {
    
    private affinekey key;
    
    affine(affinekey ob){
        key = ob;
    }
    
    private char encrypt(char ch){
        int res = ch;
        if(ch >= 'A' && ch <= 'Z'){
            res = ((ch-'A')*key.a + key.b)%26;
            res += 'A';
        }else if(ch >= 'a' && ch <= 'z'){
            res = ((ch-'a')*key.a + key.b)%26;
            res += 'a';
        }
        return (char)(res);
    }
    
    private char decrypt(char ch){
        int res = ch;
        if(ch >= 'A' && ch <= 'Z'){
            res = ((key.inv * ((ch-'A')-key.b)) + 26*(int)1e6) % 26;
            res += 'A';
        }else if(ch >= 'a' && ch <= 'z'){
            res = ((key.inv * ((ch-'a')-key.b)) + 26*(int)1e6) % 26;
            res += 'a';
        }
        return (char)(res);
    }
    
    String encrypt(String text){
        String res = "";
        for(int i = 0; i < text.length(); i++)
            res += encrypt(text.charAt(i));
        return res;
    }
    
    String decrypt(String text){
        String res = "";
        for(int i = 0; i < text.length(); i++)
            res += decrypt(text.charAt(i));
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
    
    String decrypt(File f) throws IOException {
        String res = "";
        Scanner in = new Scanner(f);
        while(in.hasNext()){
            res += decrypt(in.nextLine());
            res += "\r\n";
        }
        return res;
    }
    
//    public static void main(String[] args) throws IOException {
//        File f = new File("C:\\Users\\Abdelgany\\Desktop\\in1.txt");
//        File f1 = new File("C:\\Users\\Abdelgany\\Desktop\\in.txt");
//        affine ob = new affine(new affinekey(21, 19));
//        String en = ob.encrypt(f);
//        
//        PrintWriter pt = new PrintWriter(f1);
//        pt.println(en);
//        System.out.println(ob.decrypt(en));
//        pt.flush();
//    }
}
