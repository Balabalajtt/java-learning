import com.sun.org.apache.xalan.internal.utils.XMLSecurityPropertyManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Created by 江婷婷 on 2017/7/19.
 */
public class Jihe {

    public static void main(String args[]) throws FileNotFoundException {
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


        List<String> list = Arrays.asList("55","6","777","52");
        System.out.println(list.getClass());//Arrays的内部类

        PriorityQueue<Number> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(3);
        priorityQueue.offer(9);
        priorityQueue.offer(8);
        priorityQueue.offer(2);
        priorityQueue.offer(1);
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());

        Map map = new LinkedHashMap();
        map.put(1,"sss");
        System.out.println();

        Properties properties = new Properties();
        properties.put("name","JiangTingTing");
        properties.put("password","123456");
        try {
            properties.store(new FileOutputStream("test.ini"),"comment line");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Properties properties1 = new Properties();
        try {
            properties1.load(new FileInputStream("test.ini"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties1);

        Collection arrayList = Collections.synchronizedCollection(new ArrayList<>());//包装成线程同步集合
        List ulist = Collections.unmodifiableList(list);//返回list的不可变集合版本
        Set set = Collections.singleton("sss");//返回一项元素的不可变集合

    }



}
