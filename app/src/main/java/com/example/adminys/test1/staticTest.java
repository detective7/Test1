package com.example.adminys.test1;

/**
 * Created by Administrator on 2015/12/17 0017.
 */
public class staticTest {

    private static int a = 5;
    public static void main(String[] args){
        modify();
        System.out.println(a);
    }
    public static void modify(){
        a++;
    }
}
