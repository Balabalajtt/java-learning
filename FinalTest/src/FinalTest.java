import java.util.Arrays;

/**
 * Created by 江婷婷 on 2017/7/14.
 */
public class FinalTest
{

    final int a = 6;//实例变量声明时赋初始值
    final String str;//非静态初始化块赋初始值 系统不会对final修饰的实例变量进行初始化
    final static double b;//静态初始化块赋初始值
    final char ch;//构造器赋初始值
    //final int c;//final修饰的成员变量必须成功显示赋初始值

    {
        //System.out.println(str);//初始化之前访问成员会出错
        str = "Hello";//非静态初始化块赋初始值
        //a=9;//已经初始过再赋值会出错
    }

    static {
        b = 2.0;//静态初始化块赋初始值
    }

    public FinalTest()
    {
        ch = 'a';//构造器赋初始值
    }

    public void change(final int xingcan)
    {
        //c = 2;//不能在方法里赋初始值
        //xingcan = 5;//不能对final修饰的形参赋值
    }

    public static void main(String args[])
    {
        FinalTest test = new FinalTest();

        final int x = 2;
        //x = 3;//非法
        test.change(x);

        final int y;
        y = 2;
        //y = 3;//非法

        final int[] intArr = {1,2,4,3};//intArr只是一个引用 只要指向不变 对象可以变
        System.out.println(Arrays.toString(intArr));
        Arrays.sort(intArr);//对数组排序 合法
        intArr[2] = 8;//合法
        //intArr = null;//不合法 改变了intArr引用

        final Person p = new Person(18);
        p.setAge(19);//合法 改变引用实例的数据
        System.out.println(p.getAge());
        //p = null;//不合法 不能改变p的指向

        final int w = 10;//w被final修饰 定义变量时确定初始值 初始值在编译时就被确定下来   w相当于直接量 宏变量
        System.out.println(w);//编译转换成System.out.println(10); w变量对于程序来说不存在
        final String book = "zzz" + String.valueOf(11);//调用了方法 无法在编译时确定 不会当作宏变量处理

        String st4 = "1122";
        final String st1 = "11";
        final String st2 = "22";
        String st3 = st1 + st2;//宏替换 编译时确定
        System.out.println(st3 == st4);//true 同时引用常量池的"1122"

    }
}

class Person
{
    private int age;
    public Person(int age)
    {
        this.age = age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public int getAge()
    {
        return this.age;
    }
}

class Fu
{
    public final void test(){}
    private final void test2(){}
}
class Zi extends Fu
{
    //public void test(){};//无法重写final修饰的方法

    public final void t(){}
    public final void p(){}//final修饰的方法可以重载

    public void test2(){}//因为父类有private修饰 所以此处只是定义了一个新方法
}

final class f{}//final修饰的类不可以有子类
//class z extends f{}//错误

