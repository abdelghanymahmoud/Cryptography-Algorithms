/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playfair;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Abdelgany
 */
public class playfair {
    
    private playfairkey key;
    
    playfair(playfairkey ob){
        key = ob;
    }
    
    char encrypt(char c1, char c2){
        playfairposition p1 = key.getpos(c1);
        playfairposition p2 = key.getpos(c2);
        if(p1.I == p2.I && p1.J == p2.J){
//            System.out.println("you cannot be here");
        }else if(p1.I == p2.I){
            int J = p1.J + 1;
            if(J == 5) J = 0;
            return key.mat[p1.I][J];
        }else if(p1.J == p2.J){
            int I = p1.I + 1;
            if(I == 5) I = 0;
            return key.mat[I][p1.J];
        }else {
            return key.mat[p1.I][p2.J];
        }
        return '?';//input must be wrong
    }
    
    char decrypt(char c1, char c2){
        playfairposition p1 = key.getpos(c1);
        playfairposition p2 = key.getpos(c2);
        if(p1.I == p2.I && p1.J == p2.J){
//            System.out.println("you cannot be here");
        }else if(p1.I == p2.I){
            int J = p1.J - 1;
            if(J == -1) J = 4;
            return key.mat[p1.I][J];
        }else if(p1.J == p2.J){
            int I = p1.I - 1;
            if(I == -1) I = 4;
            return key.mat[I][p1.J];
        }else {
            return key.mat[p1.I][p2.J];
        }
        return '?';//input must be wrong
    }
    
    String encrypt(String text){
        text = text.toUpperCase();
        String text2 = "";
        for(int i = 0; i < text.length(); i++){
            char ch = text.charAt(i);
            if(ch < 'A' || ch > 'Z') continue;
            if(i == 0){
                text2 += ch;
                continue;
            }
            if(ch == text.charAt(i-1)) {
                if(ch != 'X') text2 += "X";
                else text2 += "Z";
            }
            text2 += ch;
        }
        int len = text2.length();
        if(len %2 == 1) {
            if(text2.charAt(len-1) != 'X') text2 += "X";
            else text2 += "Z";
            len++;
        }
        String res = "";
        for(int i = 0; i < len; i+=2){
            res += encrypt(text2.charAt(i), text2.charAt(i+1));
            res += encrypt(text2.charAt(i+1), text2.charAt(i));
        }
        return res;
    }
    
    String decrypt(String text){
        int len = text.length();
        String res = "";
        for(int i = 0; i < len; i+=2){
            res += decrypt(text.charAt(i), text.charAt(i+1));
            res += decrypt(text.charAt(i+1), text.charAt(i));
        }
        return res;
    }
    String encrypt(File f) throws IOException {
        String res = "";
        Scanner in = new Scanner(f);
        while(in.hasNext()) res += in.nextLine();
        return encrypt(res);
    }
    String decrypt(File f) throws IOException {
        String res = "";
        Scanner in = new Scanner(f);
        while(in.hasNext()) res += in.nextLine();
        return decrypt(res);
    }
    public static void main(String[] args) {
        playfair ob = new playfair(new playfairkey("largest"));
        String en = ob.encrypt("An array is a container object that holds a fixed number ");
        System.out.println(en);
        System.out.println(ob.decrypt(en));
    }
}
