import java.util.Arrays;
import java.util.Objects;

/**
 * Created by 江婷婷 on 2017/7/5.
 */
public class details {

    public static void main(String args[]) {
        byte by = 56;//系统自动把56当成byte类型处理
        //long bigValue=99999999999999;//不会自动转换

        int aa;
        aa = 5;
//      aa=aa+5;
        aa += 5;

        System.out.println(5.0 > 4);
        String word1 = "aaa";
        String word2 = "bba";
        System.out.println(Objects.equals(word1, word2));

        long bigValue = 99999999999999L;//强制转换
        int bb = 5;
        double dodo = 5.6;
        //dodo<<5;
        bb = bb << 2;

        outer:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 2) break outer;
                System.out.println("i=" + i + "j=" + j);
            }
        }
        while (true) {
            aa++;
            System.out.println("***");
            if (aa > 20) break;
        }


        int[] nums;
        nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] num;
        num = new int[10];

        int[] fff={1,2,3,4,5};//定义同时初始化简化写法
        int[] ffff=new int[]{1,2,3,4,5};//完整写法
//        System.out.println(fff[5]);//越界不报错，抛异常

        int[] prices =new int[5];
        for(int j=0;j<prices.length;j++)//length属性求数组长度
            System.out.println(prices[j]);

        Object[] books=new String[4];//初始化数组元素是定义数组元素的子类
        books[0]="疯狂";
        books[1]="我啊";

        for(int i=0;i<books.length;i++)
            System.out.println(books[i]);//还会输出null

        for(Object book :books)//注意类型是定义数组元素的类型 不能是初始化数组元素的类型
        {
            System.out.println(book);
        }

        int[] ya= new int[]{1,2,3,4};
        int[] yb = new int[3];
        System.out.println("arraya:"+ya.length);
        System.out.println("arrayb:"+yb.length);
        yb=ya;//同类型数组变量可以赋值
        System.out.println("arrayb:"+yb.length);


        Person[] students;//引用类型数组
        students = new Person[2];

        Person zhang = new Person();//创建实例
        zhang.age=15;//给对象赋值
        zhang.height=158;
        Person lee = new Person();
        lee.age=16;
        lee.height=161;

        students[0]=zhang;
        students[1]=lee;

        lee.info();
        students[1].info();

//Java没有多维数组

        System.out.println("******************");
        int[][] erwei;
        erwei = new int[4][];
        for(int i=0;i<erwei.length;i++)
            System.out.println(erwei[i]);

//        int nu;
        erwei[0]=new int[2];//初始化erwei第一个元素
        erwei[0][1]=6;//赋值
        for(int nu:erwei[0])
        {
            System.out.println(nu);
        }

        //静态初始二维数组
        String[][] str1 = new String[][]{new String[3],new String[]{"hello"}};
        //简化静态初始二维数组
        String[][] str2 = {new String[3],new String[]{"hello","world"}};


        /*Array类的static修饰的方法*/
        int[] a1 = new int[]{3,4,5,6};
        int[] a2 = new int[]{3,4,5,6};
        System.out.println("a1a2数组是否相等："+ Arrays.equals(a1,a2));
        int[] a3 = Arrays.copyOf(a1,6);
        System.out.println("a1a3数组是否相等："+ Arrays.equals(a1,a3));
        System.out.println("a3数组："+Arrays.toString(a3));
        Arrays.fill(a3,2,4,9);
        System.out.println("a3数组"+Arrays.toString(a3));
        Arrays.sort(a3);
        System.out.println("排序之后a3："+Arrays.toString(a3));
        System.out.println("4在a3的索引"+Arrays.binarySearch(a3,4));
        System.out.println("9在a3的索引"+Arrays.binarySearch(a3,9));//只返回第一个相同值元素的索引
        System.out.println("范围9在a3的索引"+Arrays.binarySearch(a3,0,2,9));
        System.out.println("范围9在a3的索引"+Arrays.binarySearch(a3,0,5,9));
        System.arraycopy(a2,1,a3,3,2);
        System.out.println("赋值后的b3"+Arrays.toString(a3));




    }

}


class Person
{
    public int age;
    public double height;
    public void info()
    {
        System.out.println("我的年龄："+age+"，身高："+height);
    }
}
