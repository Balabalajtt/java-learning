import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by 江婷婷 on 2017/10/21.
 */
public class Translate {

    public static String mWords = "";

    public static List<String> request = new ArrayList<>();

    public static List<String[]> dividedRequest = new ArrayList<>();

    public static List<String[]> handledRequest = new ArrayList<>();

    /**
     * 初始输入条件式子
     * #结束
     */
    public static void initInput() {
        Scanner sc = new Scanner(System.in);
        request.clear();
        System.out.println("请输入条件，每条用回车隔开，# 表达式作为结束");
        while (true) {
            String string = sc.nextLine();
            if (string.equals("#")) {
                break;
            } else {
                request.add(string);
            }
        }

        System.out.println("请输入魔王语言");
        mWords = sc.nextLine();

    }

    /**
     * 处理条件式
     */
    public static void handleRequest() {
        divideRequest();
        removeRequestBrackets();
        downRequest();
    }

    /**
     * 使用已处理的条件式转换魔王语言
     */
    public static void replaceWords() {
        for (String[] strings : handledRequest) {
            mWords = mWords.replaceAll(strings[0], strings[1]);
        }
    }

    /**
     * 去括号
     */
    public static void removeBrackets(String s) {

        Stack.stack1.clear();
        Stack.stack2.clear();
        Queue.queue.clear();

        //将魔王语言从尾到头入栈
        Stack.inStack(reserve(s), Stack.stack1);

        //处理内嵌括号的循环
        while (Stack.containChar(')', Stack.stack1)) {

            //s1出栈入s2 直到遇见 )
            while (!Stack.stack1.isEmpty()) {
                char c = Stack.outStack(Stack.stack1);
                if (c == ')') {
                    break;
                } else {
                    Stack.inStack(c, Stack.stack2);
                }
            }

            //s2出栈入队列q 直到遇见 (
            while (true) {
                char c = Stack.outStack(Stack.stack2);
                if (c == '(') {
                    break;
                } else {
                    Queue.inQueue(c);
                }
            }

            //()运算规则保存队尾元素 入s2
            char ele = Queue.getTail();
            while (!Queue.queue.isEmpty()) {
                Stack.inStack(ele, Stack.stack2);
                char c = Queue.outQueue();
                Stack.inStack(c, Stack.stack2);
            }
            Stack.stack2.remove(Stack.stack2.size() - 1);

        }

        //stack1出栈入stack2
        while(!Stack.stack1.isEmpty()) {
            Stack.inStack(Stack.outStack(Stack.stack1), Stack.stack2);
        }

    }

    /**
     * 对应汉字
     */
    public static String toChinese() {

        String s = "";
        for (Character c : Stack.stack2) {
            s = s + c;
        }

        //规则
        List<String[]> rule = new ArrayList<>();
        rule.add(new String[]{"t", "天"});
        rule.add(new String[]{"d", "地"});
        rule.add(new String[]{"s", "上"});
        rule.add(new String[]{"a", "一只"});
        rule.add(new String[]{"e", "鹅"});
        rule.add(new String[]{"z", "追"});
        rule.add(new String[]{"g", "赶"});
        rule.add(new String[]{"x", "下"});
        rule.add(new String[]{"n", "蛋"});
        rule.add(new String[]{"h", "恨"});

        String rtn = "";
        for (int i = 0; i < s.length(); i++) {
            for (String[] strings : rule) {
                if (strings[0].equals(s.charAt(i) + "")) {
                    rtn = rtn + strings[1];
                    break;
                }
            }
        }
        return rtn;
    }



    /**
     * 逆序字符串
     * @param origin
     * @return
     */
    private static String reserve(String origin) {
        String rtn = "";
        for (int i = origin.length() - 1; i >= 0; i--) {
            rtn = rtn + origin.charAt(i);
        }
        return rtn;
    }

        /**
     * 判断是否为终极式
     * @param string
     * @return
     */
    private static boolean isDone(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (Character.isUpperCase(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 以->分割
     */
    private static void divideRequest() {
        for (String s : request) {
            dividedRequest.add(s.split("->"));
        }
    }

    /**
     * 处理条件式中的括号问题
     */
    private static void removeRequestBrackets() {
        for (String[] strings : dividedRequest) {
            if (strings[1].contains("(")) {
                removeBrackets(strings[1]);
            }
        }
    }

    /**
     *  使每一条条件式成为终极式
     */
    private static void downRequest() {
        for (String[] ss : dividedRequest) {
            String[] downS = new String[2];
            downS[0] = ss[0];
            downS[1] = ss[1];
            while (!isDone(downS[1])) {
                for (String[] strings : dividedRequest) {
                    downS[1] = downS[1].replaceAll(strings[0], strings[1]);
                }
            }
            handledRequest.add(downS);
        }
    }


}
