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
public class function {

    public static Vector<Integer> mod(Vector<Integer> f, Vector<Integer> g, int Zp) {
        while (g.size() > 0) {
            int len = g.size();
            if (g.get(len - 1) == 0) {
                g.remove(len - 1);
            } else {
                break;
            }
        }
        Vector<Integer> res = new Vector<>();
        int len = f.size();
        int invG = inv(g.get(g.size() - 1) % Zp, Zp);
        for (int i = 0; i < len; i++) {
            res.add(0);
        }
        while (len >= g.size()) {
            int x = (invG * f.get(len - 1)) % Zp;
            int order = len - g.size();
            res.set(order, x);
            for (int i = 0; i < g.size(); i++) {
                int tmp = f.get(order + i) - x * g.get(i);
                while (tmp < 0) {
                    tmp += Zp;
                }
                f.set(order + i, tmp);
            }
            f.remove(len - 1);
            len--;
        }
        while (res.size() > 0) {
            len = res.size();
            if (res.get(len - 1) == 0) {
                res.remove(len - 1);
            } else {
                break;
            }
        }
        while (f.size() > 0) {
            len = f.size();
            if (f.get(len - 1) == 0) {
                f.remove(len - 1);
            } else {
                break;
            }
        }
        return f;
    }

    public static Vector<Integer> div(Vector<Integer> f, Vector<Integer> g, int Zp) {
        while (g.size() > 0) {
            int len = g.size();
            if (g.get(len - 1) == 0) {
                g.remove(len - 1);
            } else {
                break;
            }
        }
        Vector<Integer> res = new Vector<>();
        int len = f.size();
        int invG = inv(g.get(g.size() - 1) % Zp, Zp);
        for (int i = 0; i < len; i++) {
            res.add(0);
        }
        while (len >= g.size()) {
            int x = (invG * f.get(len - 1)) % Zp;
            int order = len - g.size();
            res.set(order, x);
            for (int i = 0; i < g.size(); i++) {
                int tmp = f.get(order + i) - x * g.get(i);
                while (tmp < 0) {
                    tmp += Zp;
                }
                f.set(order + i, tmp);
            }
            f.remove(len - 1);
            len--;
        }
        while (res.size() > 0) {
            len = res.size();
            if (res.get(len - 1) == 0) {
                res.remove(len - 1);
            } else {
                break;
            }
        }
        while (f.size() > 0) {
            len = f.size();
            if (f.get(len - 1) == 0) {
                f.remove(len - 1);
            } else {
                break;
            }
        }
        return res;
    }

    public static Vector<Integer> addfun(Vector<Integer> f, Vector<Integer> g, int Zp) {
        if (g.size() > f.size()) {
            return addfun(g, f, Zp);
        }
        for (int i = 0; i < g.size(); i++) {
            f.set(i, (f.get(i) + g.get(i)) % Zp);
        }
        return f;
    }

    public static Vector<Integer> mulfun(Vector<Integer> f, Vector<Integer> g, int Zp) {
        Vector<Integer> res = new Vector<>();
        for (int i = 0; i < f.size() + g.size(); i++) {
            res.add(0);
        }
        for (int i = 0; i < f.size(); i++) {
            for (int j = 0; j < g.size(); j++) {
                int tmp = res.get(i + j) + f.get(i) * g.get(j);
                while (tmp < 0) {
                    tmp += Zp;
                }
                res.set(i + j, tmp % Zp);
            }
        }
        while (res.size() > 0) {
            int len = res.size();
            if (res.get(len - 1) == 0) {
                res.remove(len - 1);
            } else {
                break;
            }
        }
        return res;
    }

    private static int pow(int n, int p, int Zp) {
        int res = 1;
        while (p > 0) {
            if (p % 2 == 1) {
                res = (res * n) % Zp;
            }
            n = (n * n) % Zp;
            p /= 2;
        }
        return res;
    }

    private static int inv(int n, int Zp) {
        return pow(n, Zp - 2, Zp);
    }
}
