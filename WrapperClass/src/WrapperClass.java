/**
 * Created by 江婷婷 on 2017/7/14.
 */
public class WrapperClass {
    public static void main(String args[])
    {

        /*自动装箱 自动拆箱*/
        Integer inObj = 5;//自动装箱 把一个基本类型直接赋给对应包装类变量或Object变量
        Object boolObj = true;//true自动装箱成Boolean包装类，向上转型赋给父类（Object是所有类的父类）
        int it = inObj;//自动拆箱 包装类对象直接赋值给对应的基本类型变量
        if(boolObj instanceof Boolean)//判断前面对象是否是后面类的实例
        {
            boolean b = (Boolean)boolObj;//Object强制转换成Boolean 再自动拆箱
            System.out.println(b);
        }


        /*包装类提供的静态方法和构造器 实现 基本类型变量和字符串之间的转换*/
        String intStr = "123";
        int it1 = Integer.parseInt(intStr);//Xxx包装类的parseXxx(String s)静态方法
        int it2 = new Integer(intStr);//构造器
        System.out.println(it2);
        String ftStr = String.valueOf(2.345f);//多个重载的valueOf方法 将基本类型转换为字符串
        System.out.println(ftStr);

        /*包装类实例与数值类型比较*/
        Integer a = new Integer(6);
        System.out.println(a > 5.0);//可以进行比较 true
        System.out.println(new Integer(2) == new Integer(2));//false 比较两个包装类引用指向同一对象时才相等
        Integer ina = 2;
        Integer inb = 2;
        System.out.println(ina == inb);//true 自动装箱 类初始化 创建cache数组 装箱时直接引用cache数组的同一元素
        Integer biga = 128;
        Integer bigb = 128;
        System.out.println(biga == bigb);//false 128超出[-128,127]系统会重新创建Integer实例 不是引用同一对象

        System.out.println(Boolean.compare(true,false));//为包装类提供的方法比较基本类型值的大小
        
    }

}
