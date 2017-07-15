package lee;

/**
 * Created by 江婷婷 on 2017/7/15.
 */
public interface Output extends InterfaceA,InterfaceB //接口多继承
{
    int MAX_CACHE_LINE = 50;//接口定义的成员变量只能是常量 自动public static final修饰

    void out();//普通方法只能是public abstract修饰的抽象方法
    void getData(String msg);

    default void print(String... msgs)//默认方法必须用default修饰 自动public修饰 不能使用static修饰 只能使用实现类的实例来调用默认方法
    {
        for (String msg : msgs)
        {
            System.out.println(msg);
        }
    }

    static String staticTest()//类方法必须用static修饰 不能使用default 自动public修饰 可以直接用接口来调用
    {
        return "接口的类方法";
    }
}

interface InterfaceA
{
    int a = 5;
    void af();
}

interface InterfaceB
{
    int b = 2;
    void bf();
}

interface Product
{
    int getProduceTime();
}

