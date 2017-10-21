import javax.swing.*;
import java.util.Scanner;

/**
 * Created by 江婷婷 on 2017/10/20.
 */

public class Main {
    public static void main (String[] args) {
//        test1();
        test2();
    }

    /**
     * GUI 贪心
     */
    private static void test2() {
        JFrame chessBorder = new Board();
        chessBorder.setTitle("斑马踏按钮");
        chessBorder.setLocation(300, 20);
        chessBorder.setSize(1000, 1000);
        chessBorder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chessBorder.setVisible(true);
    }


    /**
     * Dos 递归
     */
    private static void test1() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入棋盘大小，如8x8棋盘应输入：8");
        int size = scanner.nextInt();
        scanner.nextLine();
        Walk.MaxX = size;
        Walk.MaxY = size;

        System.out.println("请输入棋子起始坐标，坐标起点从1开始，如（1，2）棋盘应输入：1 2");
        String s = scanner.nextLine();
        int x = Integer.parseInt(s.split(" ")[0]);
        int y = Integer.parseInt(s.split(" ")[1]);
        scanner.close();

        Chess chess = new Chess(x - 1, y - 1);
        Stack.inStack(chess);

        Walk.computeNowChess(chess);

        Stack.print();
    }
}
