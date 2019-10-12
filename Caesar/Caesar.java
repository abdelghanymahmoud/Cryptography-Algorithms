/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caesar;

import java.io.*;
import java.util.*;

/**
 *
 * @author Abdelgany
 */
public class Caesar {

    private int key;

    public Caesar() {
        key = 0;
    }

    public Caesar(int k) {
        key = k;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int k) {
        key = k;
    }

    public String encrept(String text) {
        String res = "";
        int idx;
        char ch;
        for (int i = 0; i < text.length(); i++) {
            ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                idx = ((int) ch - 'A' + key) % 26;
                res += (char) ('A' + idx);
            } else if (ch >= 'a' && ch <= 'z') {
                idx = ((int) ch - 'a' + key) % 26;
                res += (char) ('a' + idx);
            } else {
                res += ch;
            }
        }
        return res;
    }

    public String decrept(String text) {
        String res = "";
        int idx;
        char ch;
        for (int i = 0; i < text.length(); i++) {
            ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                idx = ((int) ch - 'A' - key + 26) % 26;
                res += (char) ('A' + idx);
            } else if (ch >= 'a' && ch <= 'z') {
                idx = ((int) ch - 'a' - key + 26) % 26;
                res += (char) ('a' + idx);
            } else {
                res += ch;
            }
        }
        return res;
    }

    public String encrept(File f) throws IOException {
        Scanner in = new Scanner(f);
        String res = "", text;
        int idx;
        char ch;
        while (in.hasNext()) {
            text = in.nextLine();
            for (int i = 0; i < text.length(); i++) {
                ch = text.charAt(i);
                if (ch >= 'A' && ch <= 'Z') {
                    idx = ((int) ch - 'A' + key) % 26;
                    res += (char) ('A' + idx);
                } else if (ch >= 'a' && ch <= 'z') {
                    idx = ((int) ch - 'a' + key) % 26;
                    res += (char) ('a' + idx);
                } else {
                    res += ch;
                }
            }
            res += "\n";
        }
        return res;
    }

    public String decrept(File f) throws IOException {
        Scanner in = new Scanner(f);
        String res = "", text;
        int idx;
        char ch;
        while (in.hasNext()) {
            text = in.nextLine();
            for (int i = 0; i < text.length(); i++) {
                ch = text.charAt(i);
                if (ch >= 'A' && ch <= 'Z') {
                    idx = ((int) ch - 'A' - key + 26) % 26;
                    res += (char) ('A' + idx);
                } else if (ch >= 'a' && ch <= 'z') {
                    idx = ((int) ch - 'a' - key + 26) % 26;
                    res += (char) ('a' + idx);
                } else {
                    res += ch;
                }
            }
            res += "\n";
        }
        return res;
    }

    public String decreptWithoutKey(String text) {
        String res = "";
        int idx;
        char ch;
        for (int k = 1; k < 26; k++) {
            for (int i = 0; i < text.length(); i++) {
                ch = text.charAt(i);
                if (ch >= 'A' && ch <= 'Z') {
                    idx = ((int) ch - 'A' - k + 26) % 26;
                    res += (char) ('A' + idx);
                } else if (ch >= 'a' && ch <= 'z') {
                    idx = ((int) ch - 'a' - k + 26) % 26;
                    res += (char) ('a' + idx);
                } else {
                    res += ch;
                }
            }
            res += "\n--------------\n";
        }
        return res;
    }

    public String decreptWithoutKey(File f) throws IOException {
        String res = "", text;
        int idx;
        char ch;
        for (int k = 1; k < 26; k++) {
            Scanner in = new Scanner(f);
            while (in.hasNext()) {
                text = in.nextLine();
                for (int i = 0; i < text.length(); i++) {
                    ch = text.charAt(i);
                    if (ch >= 'A' && ch <= 'Z') {
                        idx = ((int) ch - 'A' - k + 26) % 26;
                        res += (char) ('A' + idx);
                    } else if (ch >= 'a' && ch <= 'z') {
                        idx = ((int) ch - 'a' - k + 26) % 26;
                        res += (char) ('a' + idx);
                    } else {
                        res += ch;
                    }
                }
                res += "\n";
            }
            res += "\n--------------\n";
        }
        return res;
    }
}
