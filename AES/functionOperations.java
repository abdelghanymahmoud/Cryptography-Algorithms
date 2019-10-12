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
public class functionOperations {

    Vector<Integer> prime;

    public functionOperations(Vector<Integer> p) {
        prime = p;
    }

    public Vector<Integer> add(Vector<Integer> f, Vector<Integer> g) {
        if (g.size() > f.size()) {
            return add(g, f);
        }
        Vector<Integer> res = new Vector<>();
        res.setSize(Math.max(f.size(), g.size()));
        for (int i = 0; i < res.size(); i++) {
            res.set(i, 0);
        }
        for (int i = 0; i < g.size(); i++) {
            res.set(i, (f.get(i) + g.get(i)) % 2);
        }
        return res;
    }

    public Vector<Integer> mult(Vector<Integer> f, Vector<Integer> g) {
        int len = f.size();
        Vector<Integer> res = new Vector<>();
        for (int i = 0; i < len; i++) {
            res.add(0);
        }
        for (int i = 0; i < len; i++) {
            if (f.get(i) == 0) {
                continue;
            }
            Vector<Integer> vec = shift((Vector<Integer>) g.clone(), i);
            for (int j = 0; j < len; j++) {
                res.set(j, (res.get(j) + vec.get(j)) % 2);
            }
        }
        return res;
    }

    private void Xor(Vector<Integer> f) {
        for (int i = 0; i < f.size(); i++) {
            f.set(i, (f.get(i) + prime.get(i)) % 2);
        }
    }

    private Vector<Integer> shift(Vector<Integer> f, int n) {
        int len = f.size();
        while (n-- > 0) {
            f.add(0, 0);
            if (f.get(len) == 1) {
                Xor(f);
            }
            f.remove(len);
        }
        return f;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Vector<Integer> p = new Vector<>();
        for (int i = 0; i < 4; i++) {
            //p.add(in.nextInt());
        }
        functionOperations ob = new functionOperations(p);
        Vector<Integer> f = new Vector<>();
        Vector<Integer> g = new Vector<>();
        for (int i = 0; i < 12; i++) {
            f.add(in.nextInt());
        }
        for (int i = 0; i < 5; i++) {
            g.add(in.nextInt());
        }
        System.out.println(ob.mod(f, g, 2));
    }

    public Vector<Integer> GCD(Vector<Integer> a, Vector<Integer> b, int Zp) {
        if (a.isEmpty()) {
            return b;
        }
        return GCD(mod(b, a, Zp), a, Zp);
    }

    public Vector<Integer> mod(Vector<Integer> f, Vector<Integer> g, int Zp) {
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
    
    public Vector<Integer> invfun(Vector<Integer> f){
        Vector<Integer> res = (Vector<Integer>) f.clone();
        Vector<Vector<Integer>> w = new Vector<>();
        for(int i = 0; i < 2; i++){
            Vector<Integer> tmp = new Vector<>();
            for(int j = 0; j < prime.size() - 1; j++){
                tmp.add(0);
            }
            w.add(tmp);
        }
        w.get(1).set(0, 1);
        int idx = 1;
        Vector<Integer> p = (Vector<Integer>)prime.clone();
        while(!out((Vector<Integer>) res.clone())){
            idx++;
            Vector<Integer> h = (Vector<Integer>) res.clone();
            Vector<Integer> q = function.div((Vector<Integer>) p.clone(), (Vector<Integer>) res.clone(), 2);
            res = function.mod((Vector<Integer>) p.clone(), (Vector<Integer>) res.clone(), 2);
            Vector<Integer> tmp = function.addfun((Vector<Integer>) w.get(idx-2).clone(), function.mulfun((Vector<Integer>) w.get(idx - 1).clone(), q, 2), 2);
            tmp = function.mod(tmp, prime, 2);
            w.add(tmp);
            p = h;
        }
        return w.get(w.size() - 1);
    }
    
    private boolean out(Vector<Integer> f){
        for(int i = 1; i < f.size(); i++)
            if(f.get(i) == 1) return false;
        return true;
    }

    private int pow(int n, int p, int Zp) {
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

    private int inv(int n, int Zp) {
        return pow(n, Zp - 2, Zp);
    }
}
/*
 1 1 0 1
 0 1 1
 1 0 0

 */
