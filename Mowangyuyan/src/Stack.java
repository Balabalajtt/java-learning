import java.util.ArrayList;
import java.util.List;

/**
 * Created by 江婷婷 on 2017/10/21.
 */
public class Stack {

    public static List<Character> stack1 = new ArrayList<>();

    public static List<Character> stack2 = new ArrayList<>();

    public static boolean inStack(char c, List<Character> stack) {
        return stack.add(c);
    }

    public static boolean inStack(String s, List<Character> stack) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!stack.add(c)) {
                return false;
            }
        }
        return true;
    }

    public static Character outStack(List<Character> stack) {
        if (stack.isEmpty()) {
            return null;
        } else {
            Character ch = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            return ch;
        }
    }

    public static boolean containChar(char c, List<Character> stack) {
        for (Character character : stack) {
            if (c == character) {
                return true;
            }
        }
        return false;
    }

    public static void printStack2() {
        for (Character c : stack2) {
            System.out.print(c);
        }
        System.out.println();
    }
}
