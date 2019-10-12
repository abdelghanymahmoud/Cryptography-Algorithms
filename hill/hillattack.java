/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hill;

/**
 *
 * @author Abdelgany
 */
public class hillattack {
    
     private int[][] mul(int a[][], int b[][]){
        int n = b.length;
        int res[][] = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }
    
    public String attack(int en[][], int de[][]) {
        String res = "Key\r\n";
        hillkey ob = new hillkey(de);
        int mat[][] = mul(en, ob.invp);
        for(int i = 0; i < en.length; i++){
            for(int j = 0; j < en.length; j++){
                res += (mat[i][j]%26) + " ";
            }
            res += "\r\n";
        }
        return res;
    }
}
