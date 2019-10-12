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
public class AESkey {

    public static void main(String[] args) {
//        AESkey key = new AESkey("0f1571c947d9e8590cb7add6af7f6798");
//        AESencrypt en = new AESencrypt(key);
//        AESdecrypt de = new AESdecrypt(key);
//        String cipher = en.encrypt("0123456789abcdeffedcba9876543210");
//        System.out.println(cipher);
//        String plaintext = de.decrypt(cipher);
//        System.out.println(plaintext);
        AESkey key = new AESkey("kjuhygtfrbvghfde");
        AESencrypt en = new AESencrypt(key);
        AESdecrypt de = new AESdecrypt(key);
        String cipher = en.encrypt("abdelhamid ahmed hassan noor abdelgany mahmoud netbeans )()_+-?76846489430&%%&$%@#$%^@#$%^<><M<:");
        System.out.println(cipher);
        String plaintext = de.decrypt(cipher);
        System.out.println(plaintext);
    }

    private final String key[] = new String[44];
    private final String Sbox[][] = new String[16][16];
    private final String Rcon[] = new String[10];

    public AESkey(String ke) {
        ke = Hexa.toHexa(ke.substring(0, 16));
        init();
        initKey0(ke);
        generateKeys();
    }

    public String getkey(int idx) {
        return key[idx];
    }

    private void generateKeys() {
        for (int i = 4; i < 44; i++) {
            String tmp = key[i - 1];
            if (i % 4 == 0) {
                tmp = Xor(SubWord(RotWord(tmp)), Rcon[i / 4 - 1]);
            }
            key[i] = Xor(key[i - 4], tmp);
        }
    }

    private String SubWord(String ke) {
        String res = "";
        for (int i = 7; i < ke.length(); i += 8) {
            int r = 0, c = 0, pos = 0;
            for (int j = i - 4; j > i - 8; j--) {
                r += (ke.charAt(j) - '0') * (1 << pos++);
            }
            pos = 0;
            for (int j = i; j > i - 4; j--) {
                c += (ke.charAt(j) - '0') * (1 << pos++);
            }
            res += Sbox[r][c];
        }
        return res;
    }

    private String RotWord(String ke) {
        return ke.substring(8) + ke.substring(0, 8);
    }

    private void initKey0(String ke) {
        Vector<Integer> bin = Binary.toBin(ke);
        int idx = 0;
        for (int i = 0; i < 4; i++) {
            key[i] = "";
            for (int j = 0; j < 32; j++) {
                key[i] += (char) (bin.get(idx++) + '0');
            }
        }
    }

    private String Xor(String s1, String s2) {
        String res = "";
        for (int i = 0; i < s1.length(); i++) {
            res += (char) (((s1.charAt(i) + s2.charAt(i)) % 2) + '0');
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
        str = "01 02 04 08 10 20 40 80 1B 36";
        in = new Scanner(str);
        for (int i = 0; i < 10; i++) {
            String tmp = in.next();
            while (tmp.length() < 8) {
                tmp += "0";
            }
            Vector<Integer> vec = Binary.toBin(tmp);
            Rcon[i] = "";
            for (int k = 0; k < vec.size(); k++) {
                Rcon[i] += (char) (vec.get(k) + '0');
            }
        }
    }
}
