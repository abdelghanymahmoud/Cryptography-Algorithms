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
public class GF {

    public static void main(String[] args) {
        Vector<Integer> vec = new Vector<>();
        vec.add(1);
        vec.add(1);
        vec.add(0);
        vec.add(0);
        vec.add(1);
        GF ob = new GF(4, vec);
        System.out.println(ob.print());
    }

    private final functionOperations ob;
    int N;
    private final Vector<Vector<Integer>> vec = new Vector<>();

    public GF(int n, Vector<Integer> p) {
        N = n;
        ob = new functionOperations(p);
        build();
    }
    
    private void build(){
        for(int i = 0; i < (1<<N); i++){
            Vector<Integer> tmp = new Vector<>();
            for(int j = 0; j < N; j++){
                if((i & (1<<j)) != 0){
                    tmp.add(1);
                }else{
                    tmp.add(0);
                }
            }
            vec.add(tmp);
        }
    }
    
    private String print(){
        String res = "";
        res += "addition\n";
        res += printadd() + "\n";
        res += "multiplication\n";
        res += printmult() + "\n";
        res += "inverse\n";
        res += printinv() + "\n";
        res += "GCD\n";
        res += printGCD() + "\n";
        return res;
    }
    private String printvec(Vector<Integer> v){
        String res = "";
        for(int i = v.size()-1; i > -1; i--){
            res += v.get(i) + "";
        }
        return res;
    }
    private String printinv(){
        String res = "";
        for(int i = 1; i < vec.size(); i++){
            Vector<Integer> v = ob.invfun(vec.get(i));
            while(v.size() < N) v.add(0);
            res += printvec(vec.get(i)) + " --> " + printvec(v);
//            System.out.println(function.mod(function.mulfun(v, vec.get(i), 2), ob.prime, 2));
            res += "\n";
        }
        return res;
    }
    
    private String printGCD(){
        String res = "";
        for(int i = 0; i < vec.size(); i++){
            for(int j = 0; j < vec.size(); j++){
                Vector<Integer> tmp = ob.GCD((Vector<Integer>) vec.get(i).clone(), (Vector<Integer>) vec.get(j).clone(), 2);
                while(tmp.size() < N) tmp.add(0);
                res += printvec(tmp) + " ";
            }
            res += "\n";
        }
        return res;
    }
    
    private String printadd(){
        String res = "";
        for(int i = 0; i < vec.size(); i++){
            for(int j = 0; j < vec.size(); j++){
                Vector<Integer> tmp = ob.add(vec.get(i), vec.get(j));
                res += printvec(tmp) + " ";
            }
            res += "\n";
        }
        return res;
    }
    private String printmult(){
        String res = "";
        for(int i = 0; i < vec.size(); i++){
            for(int j = 0; j < vec.size(); j++){
                Vector<Integer> tmp = ob.mult(vec.get(i), vec.get(j));
                res += printvec(tmp) + " ";
            }
            res += "\n";
        }
        return res;
    }
}
