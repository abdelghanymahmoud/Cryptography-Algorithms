/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hill;

import java.util.Scanner;

/**
 *
 * @author Abdelgany
 */
public class hillkey {
    
    int N;
    int p[][];
    int invp[][];
    
    hillkey(int a[][]){// the matrix must be square
        p = a;
        N = a.length;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                while(p[i][j] < 0) p[i][j] += 26;
            }
        }
        build();
    }
    private int pw(int n, int P, int mod){
        int res = 1;
        while(P > 0){
            if(P % 2 == 1) res = (res * n) % mod;
            n = (n * n) % mod;
            P /= 2;
        }
        return res;
    }
    private void build(){
        invp = new int[N][N];
        int d = det(p);
        while(d < 0) d += 26;
        d %= 26;System.out.println(d);
        d = pw(d, 11, 26);
        int c[][] = new int[N][N];
        int b[][] = new int[N-1][N-1];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int II = 0;
                for(int I = 0; I < N-1; I++){
                    int JJ = 0;
                    if(I == i) II++;
                    for(int J = 0; J < N-1; J++){
                        if(J == j) JJ++;
                        b[I][J] = p[II][JJ++];
                    }
                    II++;
                }
                c[i][j] = det(b);
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                invp[i][j] = d * c[j][i];
                if((i+j)%2 == 1) invp[i][j] *= -1;
                while(invp[i][j] < 0) invp[i][j] += 26;
            }
        }
    }
    private int det(int a[][]){
        int n = a.length;
        int m = a[0].length;
        if(n == m && n == 1) return a[0][0];
        int res = 0;
        int b[][] = new int[n-1][m-1];
        for(int i = 0; i < n; i++){
            for(int j = 1; j < n; j++){
                int col = 0;
                for(int k = 0; k < m-1; k++){
                    if(i == k) col++;
                    b[j-1][k] = a[j][col++];
                }
            }
            if(i % 2 == 0) res += a[0][i] * det(b);
            else res -= a[0][i] * det(b);
        }
        return res;
    }
    public static void main(String[] args) {
        java.util.Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[][] = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)
                a[i][j] = in.nextInt();
        }
        hillkey ob = new hillkey(a);
    }
}
