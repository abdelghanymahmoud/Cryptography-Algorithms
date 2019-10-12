/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hill;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Abdelgany
 */
public class hill {
    
    private hillkey key;
    
    hill(hillkey ob){
        key = ob;
    }
    private int[] mul(int a[][], int b[]){
        int n = b.length;
        int res[] = new int[n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                res[i] = (res[i] + a[i][j] * b[j]) % 26;
            }
        }
        return res;
    }
    private String Encrypt(String str){
        str = str.toUpperCase();
        int len = str.length();
        if(len != key.N) return "wrong";
        String res = "";
        int a[] = new int[len];
        for(int i = 0; i < len; i++) a[i] = str.charAt(i) - 'A';
        a = mul(key.p, a);
        for(int i = 0; i < len; i++) res += (char)('A'+a[i]);
        return res;
    }
    private String Decrypt(String str){
        str = str.toUpperCase();
        int len = str.length();
        if(len != key.N) return "wrong";
        String res = "";
        int a[] = new int[len];
        for(int i = 0; i < len; i++) a[i] = str.charAt(i) - 'A';
        a = mul(key.invp, a);
        for(int i = 0; i < len; i++) res += (char)('A'+a[i]);
        return res;
    }
    
    String encrypt(String txt){
        txt = txt.toUpperCase();
        String text = "";
        for(int i = 0; i < txt.length(); i++){
            if(txt.charAt(i) != ' ') text += txt.charAt(i);
        }
        String res = "";
        int len = text.length();
        while(len % key.N != 0){
            text += "X";
            len++;
        }
        for(int i = 0; i < len; i+=key.N)
            res += Encrypt(text.substring(i, i+key.N));
        return res;
    }
    
    String decrypt(String txt){
        txt = txt.toUpperCase();
        String text = "";
        for(int i = 0; i < txt.length(); i++){
            if(txt.charAt(i) != ' ') text += txt.charAt(i);
        }
        String res = "";
        int len = text.length();
        while(len % key.N != 0){
            text += "X";
            len++;
        }
        for(int i = 0; i < len; i+=key.N)
            res += Decrypt(text.substring(i, i+key.N));
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
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[][] = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                a[i][j] = in.nextInt();
            }
        }
        hill ob = new hill(new hillkey(a));
        String en = ob.encrypt(in.next());
        String de = ob.decrypt(en);
        System.out.println(en);
        System.out.println(de);
        
        int e[][] = new int[n][n];
        int d[][] = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                e[j][i] = en.charAt(i*n+j) - 'A';
                d[j][i] = de.charAt(i*n+j) - 'A';
            }
        }
        hillattack att = new hillattack();
        System.out.println(att.attack(e, d));
    }
}
/*
3
4 5 7
25 5 3
1 18 7
abdplganu
*/

/*

3
5 4 7
5 7 8
4 7 5

*/




/*

ABDPLGANU
AONBGVXVK

*/
