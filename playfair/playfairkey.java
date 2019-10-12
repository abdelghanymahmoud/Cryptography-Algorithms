/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playfair;

/**
 *
 * @author Abdelgany
 */
public class playfairkey {

    char[][] mat = new char[5][5];

    playfairkey() {
        build("");
    }

    playfairkey(String str) {
        build(str);
    }
    
    playfairposition getpos(char ch){
        if(ch == 'J') ch = 'I';
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(mat[i][j] == ch) return new playfairposition(i, j);
            }
        }
        return new playfairposition(-1, -1);
    }

    private void build(String str) {
        str = str.toUpperCase();
        int idx[] = new int[26];
        for (int i = 0; i < 26; i++) {
            idx[i] = -1;
        }
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch < 'A' || ch > 'Z') continue;
            if (idx[ch - 'A'] == -1) {
                if (ch == 'I' || ch == 'J') {
                    idx['I' - 'A'] = cnt;
                    idx['J' - 'A'] = cnt++;
                    continue;
                }
                idx[ch - 'A'] = cnt++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (idx[i] == -1) {
                if (i == 8) {// char I
                    idx[i] = cnt;
                    idx[i + 1] = cnt++;
                    continue;
                }
                idx[i] = cnt++;
            }
        }
        for(int i = 0; i < 26; i++){
            if(i == 9) continue;
            int I = idx[i] / 5;
            int J = idx[i] % 5;
            mat[I][J] = (char)('A'+i);
        }
    }
    
//    public static void main(String[] args) {
//        playfairkey k = new playfairkey("occurrence......");
//        for(int i = 0 ; i < 5; i++){
//            for(int j = 0; j < 5; j++){
//                System.out.print(k.mat[i][j] + " ");
//            }
//            System.out.println("");
//        }
//    }
}
