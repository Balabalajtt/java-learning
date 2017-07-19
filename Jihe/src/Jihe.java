import sun.rmi.runtime.Log;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Created by 江婷婷 on 2017/7/19.
 */
public class Jihe {

    public static void main(String args[])
    {
        Collection co = new ArrayList();
        co.add("11");
        co.add("22");
        co.add("33");
        Iterator iterator = co.iterator();
        while(iterator.hasNext())
        {
            String str = (String) iterator.next();
            System.out.println(str);
            if(str.equals("11"))
            {
                //co.remove(str);//不能在遍历时删除
                //iterator.remove();
            }
        }

        Iterator iterator1 = co.iterator();
        iterator1.forEachRemaining(obj->System.out.println(obj));

        co.forEach(obj->System.out.println(obj));

        for(Object obj:co)
        {
            System.out.println(obj);
        }


        //builder()返回 new 构造器
        IntStream intStream = IntStream.builder().add(1).add(3).add(5).add(7).add(9).build();
//        System.out.println(intStream.max().getAsInt());//末端方法只能执行一行
//        System.out.println(intStream.sum());//末端
//        System.out.println(intStream.allMatch(ele->ele>5));//末端
//        System.out.println(intStream.anyMatch(ele->ele==1));//末端
//        IntStream newIs = intStream.map(ele->ele+1);//中间方法
//        newIs.forEach(System.out::println);//末端方法
        DoubleStream ds = intStream.mapToDouble(ele->ele*1.1);
        ds.forEach(System.out::println);
        

    }

}
