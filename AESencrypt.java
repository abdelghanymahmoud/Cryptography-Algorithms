/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AES;

import java.io.*;
import java.util.*;

/**
 *
 * @author Abdelgany
 */
public class AESencrypt {

    private final AESkey key;
    private final String Sbox[][] = new String[16][16];
    private final String mix[][] = new String[4][4];
    private final Vector<Integer> poly = new Vector<>();

    public AESencrypt(AESkey k) {
        key = k;
        init();
    }

    public String encrypt(File f) throws IOException {
        String res = "";
        Scanner in = new Scanner(f);
        while (in.hasNext()) {
            res += encrypt(in.nextLine());
        }
        return res;
    }

    public String encrypt(String text) {
        text = Hexa.toHexa(text);
        String res = "";
        while (text.length() % 32 != 0) {
            text += "0";
        }
        for (int i = 0; i < text.length(); i += 32) {
            res += encrypt128(text.substring(i, i + 32));
        }
        return res;
    }

    private String encrypt128(String text) {
        Vector<Integer> bin = Binary.toBin(text);
        String str = "";
        for (int i = 0; i < bin.size(); i++) {
            str += (char) (bin.get(i) + '0');
        }
        String cipher[][] = convertToMatrix(str);
        for (int r = 0; r < 11; r++) {

            if (r != 0) {
                cipher = Substitution(cipher);
                cipher = Shift(cipher);

                if (r != 10) {
                    cipher = Mixcolumns(cipher);
                }
            }
            String ke = "";
            for (int i = r * 4; i < r * 4 + 4; i++) {
                ke += key.getkey(i);
            }
            cipher = addRoundKey(cipher, convertToMatrix(ke));
        }

        String res = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res += Hexa.BintoHex(cipher[j][i]);
            }
        }
        return res;
    }

    private String[][] addRoundKey(String mat1[][], String mat2[][]) {
        String res[][] = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res[i][j] = Xor(mat1[i][j], mat2[i][j]);
            }
        }
        return res;
    }

    private String Xor(String s1, String s2) {
        String res = "";
        for (int i = 0; i < s1.length(); i++) {
            res += (char) (((s1.charAt(i) + s2.charAt(i)) % 2) + '0');
        }
        return res;
    }

    private String[][] Mixcolumns(String mat[][]) {
        functionOperations obj = new functionOperations(poly);
        String res[][] = new String[4][4];
        for (int I = 0; I < 4; I++) {
            for (int J = 0; J < 4; J++) {
                Vector<Integer> tmp = new Vector<>();
                for (int i = 0; i < 8; i++) {
                    tmp.add(0);
                }
                for (int k = 0; k < 4; k++) {
                    Vector<Integer> v1 = new Vector<>();
                    Vector<Integer> v2 = new Vector<>();
                    for (int i = 7; i > -1; i--) {
                        v1.add(mix[I][k].charAt(i) - '0');
                        v2.add(mat[k][J].charAt(i) - '0');
                    }
                    v1 = obj.mult(v1, v2);
                    tmp = obj.add(tmp, v1);
                }
                res[I][J] = "";
                for (int i = 7; i > -1; i--) {
                    res[I][J] += (char) (tmp.get(i) + '0');
                }
            }
        }
        return res;
    }

    private String[][] Mixcolumns1(String mat[][]) {
        String res[][] = new String[4][4];
        for (int I = 0; I < 4; I++) {
            for (int J = 0; J < 4; J++) {
                Vector<Integer> tmp = new Vector<>();
                for (int k = 0; k < 4; k++) {
                    Vector<Integer> v1 = new Vector<>();
                    Vector<Integer> v2 = new Vector<>();
                    for (int i = 7; i > -1; i--) {
                        v1.add(mix[I][k].charAt(i) - '0');
                        v2.add(mat[k][J].charAt(i) - '0');
                    }
                    v1 = function.mulfun(v1, v2, 2);
                    tmp = function.addfun(tmp, v1, 2);
                }
                tmp = function.mod(tmp, poly, 2);
                res[I][J] = "";
                while (tmp.size() < 8) {
                    tmp.add(0);
                }
                for (int i = 7; i > -1; i--) {
                    res[I][J] += (char) (tmp.get(i) + '0');
                }
            }
        }
        return res;
    }

    private String[][] Shift(String mat[][]) {
        for (int i = 1; i < 4; i++) {
            String tmp = "";
            for (int j = 0; j < 4; j++) {
                tmp += mat[i][j];
            }
            int t = i;
            while (t-- > 0) {
                tmp = tmp.substring(8) + tmp.substring(0, 8);
            }
            for (int j = 0; j < 32; j += 8) {
                mat[i][j / 8] = tmp.substring(j, j + 8);
            }
        }
        return mat;
    }

    private String[][] Substitution(String mat[][]) {
        String res[][] = new String[4][4];
        for (int I = 0; I < 4; I++) {
            for (int J = 0; J < 4; J++) {
                int r = 0, c = 0, pos = 0;
                for (int i = 3; i > -1; i--) {
                    r += (mat[I][J].charAt(i) - '0') * (1 << pos++);
                }
                pos = 0;
                for (int i = 7; i > 3; i--) {
                    c += (mat[I][J].charAt(i) - '0') * (1 << pos++);
                }
                res[I][J] = Sbox[r][c];
            }
        }
        return res;
    }

    private String[][] convertToMatrix(String str) {
        String res[][] = new String[4][4];
        int idx = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res[j][i] = "";
                for (int k = 0; k < 8; k++) {
                    res[j][i] += str.charAt(idx++);
                }
            }
        }
        return res;
    }

    private void init() {
        String str = "63 7C 77 7B F2 6B 6F C5 30 01 67 2B FE D7 AB 76\n"
                + " CA 82 C9 7D FA 59 47 F0 AD D4 A2 AF 9C A4 72 C0\n"
                + " B7 FD 93 26 36 3F F7 CC 34 A5 E5 F1 71 D8 31 15\n"
                + " 04 C7 23 C3 18 96 05 9A 07 12 80 E2 EB 27 B2 75\n"
                + " 09 83 2C 1A 1B 6E 5A A0 52 3B D6 B3 29 E3 2F 84\n"
                + " 53 D1 00 ED 20 FC B1 5B 6A CB BE 39 4A 4C 58 CF\n"
                + " D0 EF AA FB 43 4D 33 85 45 F9 02 7F 50 3C 9F A8\n"
                + " 51 A3 40 8F 92 9D 38 F5 BC B6 DA 21 10 FF F3 D2\n"
                + " CD 0C 13 EC 5F 97 44 17 C4 A7 7E 3D 64 5D 19 73\n"
                + " 60 81 4F DC 22 2A 90 88 46 EE B8 14 DE 5E 0B DB\n"
                + " E0 32 3A 0A 49 06 24 5C C2 D3 AC 62 91 95 E4 79\n"
                + " E7 C8 37 6D 8D D5 4E A9 6C 56 F4 EA 65 7A AE 08\n"
                + " BA 78 25 2E 1C A6 B4 C6 E8 DD 74 1F 4B BD 8B 8A\n"
                + " 70 3E B5 66 48 03 F6 0E 61 35 57 B9 86 C1 1D 9E\n"
                + " E1 F8 98 11 69 D9 8E 94 9B 1E 87 E9 CE 55 28 DF\n"
                + " 8C A1 89 0D BF E6 42 68 41 99 2D 0F B0 54 BB 16";
        Scanner in = new Scanner(str);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Vector<Integer> vec = Binary.toBin(in.next());
                Sbox[i][j] = "";
                for (int k = 0; k < vec.size(); k++) {
                    Sbox[i][j] += (char) (vec.get(k) + '0');
                }
            }
        }
        str = "02 03 01 01\n"
                + "01 02 03 01\n"
                + "01 01 02 03\n"
                + "03 01 01 02";
        in = new Scanner(str);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Vector<Integer> vec = Binary.toBin(in.next());
                mix[i][j] = "";
                for (int k = 0; k < vec.size(); k++) {
                    mix[i][j] += (char) (vec.get(k) + '0');
                }
            }
        }
        str = "1 1 0 1 1 0 0 0 1";
        in = new Scanner(str);
        while (in.hasNext()) {
            poly.add(in.nextInt());
        }
    }
}
