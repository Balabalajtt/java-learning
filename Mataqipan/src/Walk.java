import java.util.ArrayList;
import java.util.List;

/**
 * Created by 江婷婷 on 2017/10/21.
 */
public class Walk {
    public static int MaxX;

    public static int MaxY;

    public static boolean isValid(Chess chess) {
        if (chess.getX() < MaxX && chess.getX() >= 0 && chess.getY() < MaxY && chess.getY() >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void computeNowChess(Chess lastChess) {
        //初始
        if (Stack.chessStack.size() == MaxX * MaxY) {
            return;
        }
        int nextX = 0;
        int nextY = 0;

        while (lastChess.getWalkType() != 8) {
            switch (lastChess.getWalkType()) {
                case 0:
                    nextX = lastChess.getX() + 1;
                    nextY = lastChess.getY() - 2;
                    break;
                case 1:
                    nextX = lastChess.getX() + 2;
                    nextY = lastChess.getY() - 1;
                    break;
                case 2:
                    nextX = lastChess.getX() + 2;
                    nextY = lastChess.getY() + 1;
                    break;
                case 3:
                    nextX = lastChess.getX() + 1;
                    nextY = lastChess.getY() + 2;
                    break;
                case 4:
                    nextX = lastChess.getX() - 1;
                    nextY = lastChess.getY() + 2;
                    break;
                case 5:
                    nextX = lastChess.getX() - 2;
                    nextY = lastChess.getY() + 1;
                    break;
                case 6:
                    nextX = lastChess.getX() - 2;
                    nextY = lastChess.getY() - 1;
                    break;
                case 7:
                    nextX = lastChess.getX() - 1;
                    nextY = lastChess.getY() - 2;
                    break;
            }

            Chess nextChess = new Chess(nextX, nextY);
            if (isValid(nextChess) && !Stack.isContain(nextChess)) {
                Stack.inStack(nextChess);
                computeNowChess(nextChess);
                if (Stack.chessStack.size() == MaxX * MaxY) {
                    return;
                }
            } else {
                lastChess.setWalkType(lastChess.getWalkType() + 1);
                if (Stack.chessStack.size() == MaxX * MaxY) {
                    return;
                }
            }

        }

        int index = Stack.chessStack.indexOf(lastChess);
        Stack.outStack();
        Stack.topStack().setWalkType(Stack.chessStack.get(index - 1).getWalkType() + 1);
        return;
    }

}

class Chess {

    private int x;//本棋子的横坐标

    private int y;//本棋子的纵坐标

    private int walkType;//下一步棋的方向

    public int getWalkType() {
        return walkType;
    }

    public void setWalkType(int walkType) {
        this.walkType = walkType;
    }

    public Chess(int x, int y) {
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

class Stack {

    public static List<Chess> chessStack = new ArrayList<>();

    /**
     * 棋子入栈
     * @param chess
     * @return 入栈成不成功
     */
    public static boolean inStack(Chess chess) {
        return chessStack.add(chess);
    }

    /**
     * 棋子出栈
     * @return 出栈的那个棋子
     */
    public static Chess outStack() {
        if (chessStack.isEmpty()) {
            return null;
        } else {
            Chess chess = chessStack.get(chessStack.size() - 1);
            chessStack.remove(chessStack.size() - 1);
            return chess;
        }
    }

    /**
     * 访问栈顶元素
     * @return 返回栈顶元素
     */
    public static Chess topStack() {
        if (chessStack.isEmpty()) {
            return null;
        } else {
            return chessStack.get(chessStack.size() - 1);
        }
    }

    /**
     * 判断栈中是否有棋子坐标与此棋子的相同
     * @param chess
     * @return 有无相同坐标的棋子
     */
    public static boolean isContain(Chess chess) {
        for (Chess c : chessStack) {
            if (c.getX() == chess.getX() && c.getY() == chess.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 打印栈
     */
    public static void printStack() {
        for (Chess chess : chessStack) {
            System.out.println(chess.getX() + "-" + chess.getY());
        }
    }

    /**
     * 单步打印棋盘
     */
    public static void print() {
        int max = Walk.MaxX;

        ArrayList<Chess> p = new ArrayList<>();
        for (Chess chess : chessStack) {
            p.add(chess);
            printQipan(p, max);
        }
    }


    public static void printQipan(ArrayList<Chess> p, int max) {
        char[][] qipan = new char[max][max];
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                qipan[i][j] = '*';
            }
        }
        for (Chess chess : p) {
            qipan[chess.getX()][chess.getY()] = 'o';
        }
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                System.out.print(qipan[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

}