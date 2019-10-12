/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affine;

/**
 *
 * @author Abdelgany
 */
public class affinekey {

    int a, b, inv;// take care that gcd between (a, 26) = 1

    affinekey() {
        a = 1;
        b = 0;
        inverse();
    }

    affinekey(int x, int y) {
        a = x;
        b = y;
        inverse();
    }

    private int pw(int n, int p, int mod) {
        int res = 1;
        while (p > 0) {
            if (p % 2 == 1) {
                res = (res * n) % mod;
            }
            n = (n * n) % 26;
            p /= 2;
        }
        return res;
    }

    private void inverse() {
        inv = pw(a, 11, 26);
    }
}
