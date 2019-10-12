/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AES;
import java.util.Vector;

/**
 *
 * @author Abdelgany
 */
public class Binary {

    public static Vector<Integer> toBin(String str) {
        str = str.toUpperCase();
        Vector<Integer> res = new Vector<>();
        for (int i = 0; i < str.length(); i++) {
            Vector<Integer> tp = convertchar(str.charAt(i));
            for (int j = 0; j < tp.size(); j++) {
                res.add(tp.get(j));
            }
        }
        return res;
    }

    private static Vector<Integer> convertchar(char ch) {
        Vector<Integer> res = new Vector<>();
        int n = value(ch);
        while (n > 0) {
            res.add(0, n % 2);
            n /= 2;
        }
        while (res.size() < 4) {
            res.add(0, 0);
        }
        return res;
    }

    private static int value(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - '0';
        }
        return ch - 'A' + 10;
    }
}
