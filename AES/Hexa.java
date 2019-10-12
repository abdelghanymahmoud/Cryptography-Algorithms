/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AES;

import java.util.*;

/**
 *
 * @author Abdelgany
 */
public class Hexa {

    public static String toHexa(String str) {
        String res = "";
        Vector<Integer> vec = new Vector<>();
        for (int i = 0; i < str.length(); i++) {
            int val = (int) str.charAt(i);
            Vector<Integer> tp = new Vector<>();
            while (val > 0) {
                tp.add(0, val % 2);
                val /= 2;
            }
            while (tp.size() < 8) {
                tp.add(0, 0);
            }
            for (int j = 0; j < tp.size(); j++) {
                vec.add(tp.get(j));
            }
        }
        for (int i = 3; i < vec.size(); i += 4) {
            int val = 0;
            int pos = 0;
            for (int j = i; j > i - 4; j--) {
                val += vec.get(j) * (1 << pos++);
            }
            res += hexach(val);
        }
        return res;
    }

    public static String BintoHex(String str) {
        String res = "";
        for (int i = 7; i < str.length(); i += 8) {
            int r = 0, c = 0, pos = 0;
            for (int j = i - 4; j > i - 8; j--) {
                r += (str.charAt(j) - '0') * (1 << pos++);
            }
            pos = 0;
            for (int j = i; j > i - 4; j--) {
                c += (str.charAt(j) - '0') * (1 << pos++);
            }
            res += hexach(r);
            res += hexach(c);
        }
        return res;
    }

    private static char hexach(int x) {
        if (x >= 0 && x <= 9) {
            return (char) (x + (int) '0');
        }
        return (char) (x - 10 + 'A');
    }

    private static int hexval(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ((int) ch - '0');
        }
        return (ch - 'A' + 10);
    }

    public static String toStr(String hex) {
        String res = "";
        Vector<Integer> vec = new Vector<>();
        for (int i = 0; i < hex.length(); i++) {
            int val = hexval(hex.charAt(i));
            Vector<Integer> tp = new Vector<>();
            while (val > 0) {
                tp.add(0, val % 2);
                val /= 2;
            }
            while (tp.size() < 4) {
                tp.add(0, 0);
            }
            for (int k = 0; k < tp.size(); k++) {
                vec.add(tp.get(k));
            }
        }
        for (int i = 7; i < vec.size(); i += 8) {
            int val = 0;
            int pos = 0;
            for (int j = i; j > i - 8; j--) {
                val += vec.get(j) * (1 << pos++);
            }
            res += (char) val;
        }
        return res;
    }
}
