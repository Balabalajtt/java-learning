/**
 * Created by 江婷婷 on 2017/10/21.
 */
public class Main {

    public static void main(String[] args) {

        //输入条件转换式和魔王语言
        Translate.initInput();

        //处理条件式
        Translate.handleRequest();

        //使用处理好的表达式转换魔王语言
        Translate.replaceWords();

        //处理括号
        Translate.removeBrackets(Translate.mWords);

        System.out.println("翻译结果：");
        Stack.printStack2();

        System.out.println(Translate.toChinese());

    }

}

