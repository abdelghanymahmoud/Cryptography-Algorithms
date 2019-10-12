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
public class AESdecrypt {

    private final AESkey key;
    private final String invSbox[][] = new String[16][16];
    private final String invmix[][] = new String[4][4];
    private final Vector<Integer> poly = new Vector<>();

    public AESdecrypt(AESkey k) {
        key = k;
        init();
    }

    public String decrypt(File f) throws IOException {
        String res = "";
        Scanner in = new Scanner(f);
        while (in.hasNext()) {
            res += decrypt(in.nextLine());
        }
        return res;
    }

    public String decrypt(String text) {
        String res = "";
        while (text.length() % 32 != 0) {
            text += "0";
        }
        for (int i = 0; i < text.length(); i += 32) {
            res += decrypt128(text.substring(i, i + 32));
        }
        return res;
    }

    private String decrypt128(String cipher) {
        Vector<Integer> bin = Binary.toBin(cipher);
        String str = "";
        for (int i = 0; i < bin.size(); i++) {
            str += (char) (bin.get(i) + '0');
        }
        String plainText[][] = convertToMatrix(str);
        for (int r = 10; r > -1; r--) {
            if (r != 10) {
                plainText = invShift(plainText);
            }
            if (r != 10) {
                plainText = invSubstitution(plainText);
            }
            String ke = "";
            for (int i = r * 4; i < r * 4 + 4; i++) {
                ke += key.getkey(i);
            }
            plainText = addRoundKey(plainText, convertToMatrix(ke));
            if (r != 10 && r != 0) {
                plainText = invMixcolumns(plainText);
            }
        }
        String res = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res += Hexa.BintoHex(plainText[j][i]);
            }
        }
        return Hexa.toStr(res);
//        return res;
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

    private String[][] invMixcolumns(String mat[][]) {
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
                        v1.add(invmix[I][k].charAt(i) - '0');
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

    private String[][] invMixcolumns1(String mat[][]) {
        String res[][] = new String[4][4];
        for (int I = 0; I < 4; I++) {
            for (int J = 0; J < 4; J++) {
                Vector<Integer> tmp = new Vector<>();
                for (int k = 0; k < 4; k++) {
                    Vector<Integer> v1 = new Vector<>();
                    Vector<Integer> v2 = new Vector<>();
                    for (int i = 7; i > -1; i--) {
                        v1.add(invmix[I][k].charAt(i) - '0');
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

    private String[][] invShift(String mat[][]) {
        for (int i = 1; i < 4; i++) {
            String tmp = "";
            for (int j = 0; j < 4; j++) {
                tmp += mat[i][j];
            }
            int t = 4 - i;
            while (t-- > 0) {
                tmp = tmp.substring(8) + tmp.substring(0, 8);
            }
            for (int j = 0; j < 32; j += 8) {
                mat[i][j / 8] = tmp.substring(j, j + 8);
            }
        }
        return mat;
    }

    private String[][] invSubstitution(String mat[][]) {
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
                res[I][J] = invSbox[r][c];
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
        String str = "52 09 6A D5 30 36 A5 38 BF 40 A3 9E 81 F3 D7 FB\n"
                + " 7C E3 39 82 9B 2F FF 87 34 8E 43 44 C4 DE E9 CB\n"
                + " 54 7B 94 32 A6 C2 23 3D EE 4C 95 0B 42 FA C3 4E\n"
                + " 08 2E A1 66 28 D9 24 B2 76 5B A2 49 6D 8B D1 25\n"
                + " 72 F8 F6 64 86 68 98 16 D4 A4 5C CC 5D 65 B6 92\n"
                + " 6C 70 48 50 FD ED B9 DA 5E 15 46 57 A7 8D 9D 84\n"
                + " 90 D8 AB 00 8C BC D3 0A F7 E4 58 05 B8 B3 45 06\n"
                + " D0 2C 1E 8F CA 3F 0F 02 C1 AF BD 03 01 13 8A 6B\n"
                + " 3A 91 11 41 4F 67 DC EA 97 F2 CF CE F0 B4 E6 73\n"
                + " 96 AC 74 22 E7 AD 35 85 E2 F9 37 E8 1C 75 DF 6E\n"
                + " 47 F1 1A 71 1D 29 C5 89 6F B7 62 0E AA 18 BE 1B\n"
                + " FC 56 3E 4B C6 D2 79 20 9A DB C0 FE 78 CD 5A F4\n"
                + " 1F DD A8 33 88 07 C7 31 B1 12 10 59 27 80 EC 5F\n"
                + " 60 51 7F A9 19 B5 4A 0D 2D E5 7A 9F 93 C9 9C EF\n"
                + " A0 E0 3B 4D AE 2A F5 B0 C8 EB BB 3C 83 53 99 61\n"
                + " 17 2B 04 7E BA 77 D6 26 E1 69 14 63 55 21 0C 7D";
        Scanner in = new Scanner(str);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Vector<Integer> vec = Binary.toBin(in.next());
                invSbox[i][j] = "";
                for (int k = 0; k < vec.size(); k++) {
                    invSbox[i][j] += (char) (vec.get(k) + '0');
                }
            }
        }
        str = "0E 0B 0D 09\n"
                + "09 0E 0B 0D\n"
                + "0D 09 0E 0B\n"
                + "0B 0D 09 0E";
        in = new Scanner(str);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Vector<Integer> vec = Binary.toBin(in.next());
                invmix[i][j] = "";
                for (int k = 0; k < vec.size(); k++) {
                    invmix[i][j] += (char) (vec.get(k) + '0');
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
