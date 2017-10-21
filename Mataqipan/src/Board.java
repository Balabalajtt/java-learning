import java.awt.*;
import javax.swing.*;

/**
 * Created by 江婷婷 on 2017/10/21.
 */

public class Board extends JFrame {

    final int BOARD_SIZE = 8;
    final int BOARD_SUM_SIZE = BOARD_SIZE + 4;

    //下一步路线
    final int NEXT_TYPE_X[] = {1, 2, 2, 1, -1, -2, -2, -1};
    final int NEXT_TYPE_Y[] = {2, 1, -1, -2, -2, -1, 1, 2};

    //棋盘
    private JButton board[][] = new JButton[BOARD_SIZE][BOARD_SIZE];

    //棋盘 周围有宽度2的边框
    final int frameBoard[][] = new int[BOARD_SUM_SIZE][BOARD_SUM_SIZE];

    //每一步坐标
    private int xLocate[] = new int[64];
    private int yLocate[] = new int[64];



    public Board() {
        initBoardGUI();
        initBoardData();
    }

    /**
     * 设置边界 边界为 1 不能触碰
     */
    public void initBoardData() {
        int i, j;
        for (i = 0; i < 2; i++) {
            for (j = 0; j < BOARD_SUM_SIZE; j++) {
                frameBoard[i][j] = 1;
                frameBoard[BOARD_SUM_SIZE - i - 1][j] = 1;
                frameBoard[j][i] = 1;
                frameBoard[j][BOARD_SUM_SIZE - i - 1] = 1;
            }
        }
    }


    /**
     * 走步
     * @param x 初始x
     * @param y 初始y
     */
    private void goNext(int x, int y) {
        //初始步
        xLocate[0] = x;
        yLocate[0] = y;
        x = x + 1;
        y = y + 1;

        int count = 1;//已走步数
        int nextChooseCount[] = new int[BOARD_SIZE];//八个方向数组 数字表示八个方向的下一步的种数
        int minType;
        frameBoard[x][y] = count;
        for (count = 2; count < 65; count++) {
            minType = 8;
            int thisX = 0, thisY = 0;
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (frameBoard[x + NEXT_TYPE_X[i]][y + NEXT_TYPE_Y[i]] == 0) {  //可走
                    nextChooseCount[i] = computeCount(x + NEXT_TYPE_X[i], y + NEXT_TYPE_Y[i]); //计算方向的下一步种数
                    if (nextChooseCount[i] < minType) { //求最少解
                        minType = nextChooseCount[i];
                        thisX = x + NEXT_TYPE_X[i];
                        thisY = y + NEXT_TYPE_Y[i];
                    }
                }
            }

            x = thisX;
            y = thisY;
            frameBoard[x][y] = count;
            xLocate[count - 1] = x - 1;
            yLocate[count - 1] = y - 1;
        }
    }

    /**
     * 计算下一步种数
     * @param x
     * @param y
     * @return 种数
     */
    private int computeCount(int x, int y) {
        int count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (frameBoard[x + NEXT_TYPE_X[i]][y + NEXT_TYPE_Y[i]] == 0) {
                count++;
            }
        }
        return count;
    }


    /**
     * 设置按钮界面 添加点击事件
     */
    public void initBoardGUI() {
        setLayout(new GridLayout(8, 8));
        int count = 0;
        for(int i = 0; i < board.length; i++, count++) {
            for(int j = 0; j < board.length; j++) {
                board[i][j] = new JButton("");
                int finalI = i;
                int finalJ = j;
                board[i][j].addActionListener(e -> {
                    goNext(finalI + 1, finalJ + 1);//一个线程开始计算
                    new Thread(() -> {//一个线程开始走步
                        try {
                            for (int i1 = 1; i1 < 65; i1++) {
                                ImageIcon icon = new ImageIcon("E:\\work-Java\\Mataqipan\\src\\banma.png");
                                board[xLocate[i1 - 1] - 1][yLocate[i1 - 1] - 1].setIcon(icon);
                                Thread.sleep(500);
                                board[xLocate[i1 - 1] - 1][yLocate[i1 - 1] - 1].setIcon(null);
                                board[xLocate[i1 - 1] - 1][yLocate[i1 - 1] - 1].setText(i1 + "(" + (yLocate[i1 - 1] - 1) + "," + (xLocate[i1 - 1] - 1) + ")");
                                board[xLocate[i1 - 1] - 1][yLocate[i1 - 1] - 1].setFont(new Font("黑体", Font.BOLD, 20));
                                System.out.println((yLocate[i1 - 1] - 1) + "-" + (xLocate[i1 - 1] - 1));
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }).start();
                });
                if(count % 2 == 0) {
                    board[i][j].setBackground(Color.WHITE);
                } else {
                    board[i][j].setBackground(Color.PINK);
                }
                add(board[i][j]);
                count++;
            }
        }
    }

}
