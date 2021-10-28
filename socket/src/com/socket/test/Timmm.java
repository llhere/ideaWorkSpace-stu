package com.socket.test;

public class Timmm {

    public void change(String str, char ch[])
    {
        System.out.println("3    "+str.hashCode());
        str = new String("goods");
        ch[0] = 'g';
        System.out.println("4    "+str.hashCode() + str);
    }
    public static void main(String args[])
    {
        String str = new String("good");
        System.out.println("1    "+str.hashCode());
        char[] ch = { 'a', 'b', 'c' };
        Timmm ex = new Timmm();
        ex.change(str, ch);
        //System.out.print(str + "and ");
        System.out.println("2    "+str.hashCode() + str + ch[0]);
    }
}

