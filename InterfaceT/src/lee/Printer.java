package lee;

/**
 * Created by 江婷婷 on 2017/7/15.
 */
public class Printer implements Output, Product
{
    //实现Output接口 写全抽象方法 要把public补全
    public void out(){}
    public void getData(String msg) {}
    //继承接口的抽象方法
    public void af(){}
    public void bf(){}

    //实现Product接口
    public int getProduceTime()
    {
        return 45;
    }

    public  static void aa()
    {
        Output o = new Printer();
        o.getData("11");
        o.out();
        o.print();
        o.af();
        o.bf();
        Product p = new Printer();
        Object obj = p;//所有接口类型都可以给Object赋值
    }
}

