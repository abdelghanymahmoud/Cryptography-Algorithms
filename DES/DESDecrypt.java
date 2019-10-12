/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DES;

import java.io.*;
import java.util.*;

/**
 *
 * @author Abdelgany
 */
public class DESDecrypt {

    private final DESKey key;

    private final int IP[][] = new int[8][8];
    private final int E[][] = new int[8][6];
    private final int Sbox[][][] = new int[8][4][16];
    private final int P[][] = new int[8][4];
    private final int IPinv[][] = new int[8][8];

    DESDecrypt(DESKey ob) {
        init();
        key = ob;
    }

    public String decrypt(File f) throws IOException {
        String res = "";
        Scanner in = new Scanner(f);
        while (in.hasNext()) {
            res += decrpty(in.nextLine());
        }
        return res;
    }

    public String decrpty(String text) {
        String res = "";
        while (text.length() % 16 != 0) {
            text += " ";
        }
        for (int i = 0; i < text.length(); i += 16) {
            res += decrypt64(text.substring(i, i + 16));
        }
        return Hexa.toStr(res);
    }

    private String decrypt64(String text) {
        Vector<Integer> m = mutateIP(Binary.toBin(text));
        Vector<Integer> R = new Vector<>();
        Vector<Integer> L = new Vector<>();
        for (int i = 0; i < 32; i++) {
            R.add(m.get(i));
        }
        for (int i = 32; i < 64; i++) {
            L.add(m.get(i));
        }
        for (int r = 15; r >= 0; r--) {
            Vector<Integer> subkey = key.getSubKey(r);
            Vector<Integer> newR = new Vector<>();
            Vector<Integer> newL = new Vector<>();
            for (int i = 0; i < R.size(); i++) {
                newR.add(L.get(i));
            }
            Vector<Integer> f = function(newR, subkey);
            for (int i = 0; i < L.size(); i++) {
                newL.add(R.get(i) ^ f.get(i));
            }
            L = newL;
            R = newR;
        }
        for (int i = 0; i < 32; i++) {
            m.insertElementAt(L.get(i), i);
        }
        for (int i = 32; i < 64; i++) {
            m.insertElementAt(R.get(i - 32), i);
        }
        m = mutateIPinv(m);
        return convertTstring(m);
    }

    private String convertTstring(Vector<Integer> m) {
        String res = "";
        for (int i = 3; i < 64; i += 4) {
            int ch = 0;
            int pos = 0;
            for (int j = i; j > i - 4; j--) {
                if (m.get(j) == 1) {
                    ch += (1 << pos);
                }
                pos++;
            }
            res += hexach(ch);
        }
        return res;
    }

    private char hexach(int x) {
        if (x >= 0 && x <= 9) {
            return (char) (x + (int) '0');
        }
        return (char) (x - 10 + 'A');
    }

    private Vector<Integer> function(Vector<Integer> R, Vector<Integer> K) {
        Vector<Integer> m48 = new Vector<>();
        Vector<Integer> newR = expandE(R);
        for (int i = 0; i < K.size(); i++) {
            m48.add(newR.get(i) ^ K.get(i));
        }
        Vector<Integer> res = to32(m48);
        return Pmutate(res);
    }

    private Vector<Integer> to32(Vector<Integer> vec) {
        Vector<Integer> res = new Vector<>();
        for (int i = 0; i < 48; i += 6) {
            int row = vec.get(i) * 2 + vec.get(i + 5);
            int col = 0;
            int pos = 3;
            for (int j = i + 1; j < i + 5; j++) {
                col += vec.get(j) * (1 << pos);
                pos--;
            }
            int val = Sbox[i / 6][row][col];
            Vector<Integer> tp = new Vector<>();
            while (val > 0) {
                tp.add(0, val % 2);
                val /= 2;
            }
            while (tp.size() < 4) {
                tp.add(0, 0);
            }
            for (int k = 0; k < tp.size(); k++) {
                res.add(tp.get(k));
            }
        }
        return res;
    }

    private Vector<Integer> Pmutate(Vector<Integer> vec) {
        Vector<Integer> res = new Vector<>();
        for (int i = 0; i < P.length; i++) {
            for (int j = 0; j < P[i].length; j++) {
                res.add(vec.get(P[i][j] - 1));
            }
        }
        return res;
    }

    private Vector<Integer> expandE(Vector<Integer> vec) {
        Vector<Integer> res = new Vector<>();
        for (int i = 0; i < E.length; i++) {
            for (int j = 0; j < E[i].length; j++) {
                res.add(vec.get(E[i][j] - 1));
            }
        }
        return res;
    }

    private Vector<Integer> mutateIPinv(Vector<Integer> vec) {
        Vector<Integer> res = new Vector<>();
        for (int i = 0; i < IPinv.length; i++) {
            for (int j = 0; j < IPinv[i].length; j++) {
                res.add(vec.get(IPinv[i][j] - 1));
            }
        }
        return res;
    }

    private Vector<Integer> mutateIP(Vector<Integer> vec) {
        Vector<Integer> res = new Vector<>();
        for (int i = 0; i < IP.length; i++) {
            for (int j = 0; j < IP[i].length; j++) {
                res.add(vec.get(IP[i][j] - 1));
            }
        }
        return res;
    }

    private void init() {
        Scanner in;
        String ip = "58    50   42    34    26   18    10    2\n"
                + "            60    52   44    36    28   20    12    4\n"
                + "            62    54   46    38    30   22    14    6\n"
                + "            64    56   48    40    32   24    16    8\n"
                + "            57    49   41    33    25   17     9    1\n"
                + "            59    51   43    35    27   19    11    3\n"
                + "            61    53   45    37    29   21    13    5\n"
                + "            63    55   47    39    31   23    15    7";
        in = new Scanner(ip);
        for (int i = 0; i < IP.length; i++) {
            for (int j = 0; j < IP[i].length; j++) {
                IP[i][j] = in.nextInt();
            }
        }
        String expand = "32     1    2     3     4    5\n"
                + "                  4     5    6     7     8    9\n"
                + "                  8     9   10    11    12   13\n"
                + "                 12    13   14    15    16   17\n"
                + "                 16    17   18    19    20   21\n"
                + "                 20    21   22    23    24   25\n"
                + "                 24    25   26    27    28   29\n"
                + "                 28    29   30    31    32    1";
        in = new Scanner(expand);
        for (int i = 0; i < E.length; i++) {
            for (int j = 0; j < E[i].length; j++) {
                E[i][j] = in.nextInt();
            }
        }
        String sbox = "     14  4  13  1   2 15  11  8   3 10   6 12   5  9   0  7\n"
                + "      0 15   7  4  14  2  13  1  10  6  12 11   9  5   3  8\n"
                + "      4  1  14  8  13  6   2 11  15 12   9  7   3 10   5  0\n"
                + "     15 12   8  2   4  9   1  7   5 11   3 14  10  0   6 13\n"
                + "\n"
                + "\n"
                + "     15  1   8 14   6 11   3  4   9  7   2 13  12  0   5 10\n"
                + "      3 13   4  7  15  2   8 14  12  0   1 10   6  9  11  5\n"
                + "      0 14   7 11  10  4  13  1   5  8  12  6   9  3   2 15\n"
                + "     13  8  10  1   3 15   4  2  11  6   7 12   0  5  14  9\n"
                + "\n"
                + "\n"
                + "     10  0   9 14   6  3  15  5   1 13  12  7  11  4   2  8\n"
                + "     13  7   0  9   3  4   6 10   2  8   5 14  12 11  15  1\n"
                + "     13  6   4  9   8 15   3  0  11  1   2 12   5 10  14  7\n"
                + "      1 10  13  0   6  9   8  7   4 15  14  3  11  5   2 12\n"
                + "\n"
                + "\n"
                + "      7 13  14  3   0  6   9 10   1  2   8  5  11 12   4 15\n"
                + "     13  8  11  5   6 15   0  3   4  7   2 12   1 10  14  9\n"
                + "     10  6   9  0  12 11   7 13  15  1   3 14   5  2   8  4\n"
                + "      3 15   0  6  10  1  13  8   9  4   5 11  12  7   2 14\n"
                + "\n"
                + "\n"
                + "      2 12   4  1   7 10  11  6   8  5   3 15  13  0  14  9\n"
                + "     14 11   2 12   4  7  13  1   5  0  15 10   3  9   8  6\n"
                + "      4  2   1 11  10 13   7  8  15  9  12  5   6  3   0 14\n"
                + "     11  8  12  7   1 14   2 13   6 15   0  9  10  4   5  3\n"
                + "\n"
                + "\n"
                + "     12  1  10 15   9  2   6  8   0 13   3  4  14  7   5 11\n"
                + "     10 15   4  2   7 12   9  5   6  1  13 14   0 11   3  8\n"
                + "      9 14  15  5   2  8  12  3   7  0   4 10   1 13  11  6\n"
                + "      4  3   2 12   9  5  15 10  11 14   1  7   6  0   8 13\n"
                + "\n"
                + "\n"
                + "      4 11   2 14  15  0   8 13   3 12   9  7   5 10   6  1\n"
                + "     13  0  11  7   4  9   1 10  14  3   5 12   2 15   8  6\n"
                + "      1  4  11 13  12  3   7 14  10 15   6  8   0  5   9  2\n"
                + "      6 11  13  8   1  4  10  7   9  5   0 15  14  2   3 12\n"
                + "\n"
                + "\n"
                + "     13  2   8  4   6 15  11  1  10  9   3 14   5  0  12  7\n"
                + "      1 15  13  8  10  3   7  4  12  5   6 11   0 14   9  2\n"
                + "      7 11   4  1   9 12  14  2   0  6  10 13  15  3   5  8\n"
                + "      2  1  14  7   4 10   8 13  15 12   9  0   3  5   6 11";
        in = new Scanner(sbox);
        for (int k = 0; k < 8; k++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 16; j++) {
                    Sbox[k][i][j] = in.nextInt();
                }
            }
        }
        String p = "16   7  20  21\n"
                + "                         29  12  28  17\n"
                + "                          1  15  23  26\n"
                + "                          5  18  31  10\n"
                + "                          2   8  24  14\n"
                + "                         32  27   3   9\n"
                + "                         19  13  30   6\n"
                + "                         22  11   4  25";
        in = new Scanner(p);
        for (int i = 0; i < P.length; i++) {
            for (int j = 0; j < P[i].length; j++) {
                P[i][j] = in.nextInt();
            }
        }
        String ipinv = "40     8   48    16    56   24    64   32\n"
                + "            39     7   47    15    55   23    63   31\n"
                + "            38     6   46    14    54   22    62   30\n"
                + "            37     5   45    13    53   21    61   29\n"
                + "            36     4   44    12    52   20    60   28\n"
                + "            35     3   43    11    51   19    59   27\n"
                + "            34     2   42    10    50   18    58   26\n"
                + "            33     1   41     9    49   17    57   25";
        in = new Scanner(ipinv);
        for (int i = 0; i < IPinv.length; i++) {
            for (int j = 0; j < IPinv[i].length; j++) {
                IPinv[i][j] = in.nextInt();
            }
        }
    }
}
