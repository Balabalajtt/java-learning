package yeeku;

import lee.Output;
import lee.Printer;

/**
 * Created by 江婷婷 on 2017/7/15.
 */
public class Test {
    public static void main(String args[])
    {
        System.out.println(lee.Output.MAX_CACHE_LINE);//不同包访问说明是public修饰的 接口访问说明static修饰
        //Output.MAX_CACHE_LINE = 4;//final修饰不能重新赋值

        System.out.println(lee.Output.a);
        System.out.println(lee.Output.b);//多继承

        Printer.aa();

    }
}


