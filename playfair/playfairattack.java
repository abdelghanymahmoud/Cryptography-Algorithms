/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playfair;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Abdelgany
 */
public class playfairattack {
    char mat[][] = new char[26][26];
    playfairattack(){
        for(int i = 0; i < 26; i++){
            for(int j = 0; j < 26; j++) mat[i][j] = '?';
        }
    }
    void learn(String en, String de){
        en = en.toUpperCase(); de = de.toUpperCase();
        //System.out.println("le ");
        for(int i = 0; i < en.length()-1; i+=2){//System.out.println(i);
            int c1 = en.charAt(i)-'A', c2 = en.charAt(i+1)-'A';
            int c3 = de.charAt(i)-'A', c4 = de.charAt(i+1)-'A';
            mat[c1][c2] = (char)(c3+'A');
            mat[c2][c1] = (char)(c4+'A');
            mat[c3][c4] = (char)(c1+'A');
            mat[c4][c3] = (char)(c2+'A');
            

        }
    }
    
     void learn(File f) throws IOException {
         String en, de;
         java.util.Scanner in = new Scanner(f);
         while(in.hasNext()){
            en = in.next();
            de = in.next();
            en = en.toUpperCase(); de = de.toUpperCase();
            learn(en, de);
         }
    }
    String attack(String text){
        text = text.toUpperCase();
        String res = "";
        for(int i = 0; i < text.length()-1; i+=2){
            int c1 = text.charAt(i) - 'A', c2 = text.charAt(i+1) - 'A';
            res += mat[c1][c2];
            res += mat[c2][c1];
        }

        return res;
    }
    String attack(File f) throws IOException {
        String res = "";
        Scanner in = new Scanner(f);
        while(in.hasNext()) res += in.nextLine();
        return attack(res);
    }
    public static void main(String[] args) {
        playfairattack ob = new playfairattack();
        ob.learn("ab", "cd");
        System.out.println(ob.attack("ab"));
    }
}
