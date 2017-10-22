import javax.swing.*;
import java.util.Scanner;

/**
 * Created by 江婷婷 on 2017/10/22.
 */

public class Main {
    public static void main(String[] args) {
//        test1();
//        Puzzle pazzle = new Puzzle();
//        pazzle.initPuzzle();

        System.out.println("输入迷宫规格");
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.close();
        JFrame puzzleGui = new PuzzleGui(m, n);
        puzzleGui.setTitle("迷宫");
        puzzleGui.setLocation(300, 20);
        puzzleGui.setSize(1000, 1000);
        puzzleGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        puzzleGui.setVisible(true);

    }

    /**
     * 递归求解 顺序解
     */
    private static void test1() {
        Puzzle pazzle = new Puzzle();
        pazzle.initPuzzle();
//        pazzle.printPuzzle();
        pazzle.walk();
        if (pazzle.pathList.isEmpty()) {
            System.out.println("无解");
        } else {
            pazzle.printStack();
        }

    }

}



class Path {
    private int x;
    private int y;

    public Path(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

