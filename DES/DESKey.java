/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DES;

import java.util.*;

/**
 *
 * @author Abdelgany
 */
public class DESKey {

    public static void main(String[] args) {
//        DESKey key = new DESKey("47d9e859");// 0f1571c947d9e859
//        DESEncrypt en = new DESEncrypt(key);
//        DESDecrypt de = new DESDecrypt(key);
//        String e = en.encrpty("02468aceeca86420");
//        String d = de.decrpty(e);// da02ce3a89ecac3b
//        System.out.println(e + "\n" + d);
        DESKey key = new DESKey("posuHfyF");
        DESEncrypt en = new DESEncrypt(key);
        DESDecrypt de = new DESDecrypt(key);
        String e = en.encrpty("QWWR mahmoud ABDELGAANY ABDELHAMID netbeans NOOR ahmed ABdo. ..  o.O p.PP \\// KNB.017/*-%$#@!");
        String d = de.decrpty(e);
        System.out.println(e + "\n" + d);
    }

    private final int PC1[][] = new int[8][7];
    private final int PC2[][] = new int[8][6];
    private final int RD[] = new int[16];

    private final Vector<Vector<Integer>> subkey56 = new Vector<>();
    private final Vector<Vector<Integer>> subkey48 = new Vector<>();
    private Vector<Integer> K64 = new Vector<>();
    private final Vector<Integer> K56 = new Vector<>();

    DESKey(String k) {
        k = Hexa.toHexa(k.substring(0, 8));
        init();
        K64 = Binary.toBin(k);
        initK56();
        initsubkey56();
        initsubkey48();
    }

    Vector<Integer> getSubKey(int idx) {
        return subkey48.get(idx);
    }

    private void initsubkey48() {
        for (int k = 0; k < subkey56.size(); k++) {
            Vector<Integer> tp = new Vector<>();
            for (int i = 0; i < PC2.length; i++) {
                for (int j = 0; j < PC2[i].length; j++) {
                    tp.add(subkey56.get(k).get(PC2[i][j] - 1));
                }
            }
            subkey48.add(tp);
        }
    }

    private void initsubkey56() {
        for (int i = 0; i < RD.length; i++) {
            for (int j = 0; j < RD[i]; j++) {
                K56.add(28, K56.get(0));
                K56.remove(0);
                K56.add(56, K56.get(28));
                K56.remove(28);
            }
            Vector<Integer> tp = new Vector<>();
            for (int j = 0; j < K56.size(); j++) {
                tp.add(K56.get(j));
            }
            subkey56.add(tp);
        }
    }

    private void initK56() {
        for (int i = 0; i < PC1.length; i++) {
            for (int j = 0; j < PC1[i].length; j++) {
                K56.add(K64.get(PC1[i][j] - 1));
            }
        }
    }

    private void init() {
        Scanner in;
        // initializing the matrix PC1 that transform key of 64bit to 56bit
        String pc1 = "57   49    41   33    25    17    9\n"
                + "               1   58    50   42    34    26   18\n"
                + "              10    2    59   51    43    35   27\n"
                + "              19   11     3   60    52    44   36\n"
                + "              63   55    47   39    31    23   15\n"
                + "               7   62    54   46    38    30   22\n"
                + "              14    6    61   53    45    37   29\n"
                + "              21   13     5   28    20    12    4";
        in = new Scanner(pc1);
        for (int i = 0; i < PC1.length; i++) {
            for (int j = 0; j < PC1[i].length; j++) {
                PC1[i][j] = in.nextInt();
            }
        }
        // initializing the matrix PC2 that transform key of 56bit to 48bit
        String pc2 = "14    17   11    24     1    5\n"
                + "                  3    28   15     6    21   10\n"
                + "                 23    19   12     4    26    8\n"
                + "                 16     7   27    20    13    2\n"
                + "                 41    52   31    37    47   55\n"
                + "                 30    40   51    45    33   48\n"
                + "                 44    49   39    56    34   53\n"
                + "                 46    42   50    36    29   32";
        in = new Scanner(pc2);
        for (int i = 0; i < PC2.length; i++) {
            for (int j = 0; j < PC2[i].length; j++) {
                PC2[i][j] = in.nextInt();
            }
        }
        // initializing the array RD that shifting key n times according to its round
        String round = "1 1 2 2 2 2 2 2 1 2 2 2 2 2 2 1";
        in = new Scanner(round);
        for (int i = 0; i < RD.length; i++) {
            RD[i] = in.nextInt();
        }
    }
}
