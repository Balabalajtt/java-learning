import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Math;

/**
 * Created by 江婷婷 on 2017/7/12.
 *
 * */

public class wuziqi {
    private static int BOARD_SIZE = 15;
    private String[][] board;

    //生成空棋盘
    public void initBoard() {
        board = new String[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = "+";
            }
        }
    }

    //输出棋盘
    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
    }

    public int judge(int xPos, int yPos) {
        int count, i, j;

        String nowqi = board[xPos - 1][yPos - 1];

        for (i = xPos - 2, count = 1; i >= 0 && board[i][yPos - 1] == nowqi; i--) count++;
        for (i = xPos; i < BOARD_SIZE && board[i][yPos - 1] == nowqi; i++) count++;
        if (count > 4) return 1;

        for (i = yPos - 2, count = 1; i >= 0 && board[xPos - 1][i] == nowqi; i--) count++;
        for (i = yPos; i < BOARD_SIZE && board[xPos - 1][i] == nowqi; i++) count++;
        if (count > 4) return 1;

        for (i = xPos - 2, j = yPos - 2, count = 1; i >= 0 && j >= 0 && board[i][j] == nowqi; i--, j--) count++;
        for (i = xPos, j = yPos; i < BOARD_SIZE && j < BOARD_SIZE && board[i][j] == nowqi; i++, j++) count++;
        if (count > 4) return 1;

        for (i = xPos, j = yPos - 2, count = 1; i < BOARD_SIZE && j >= 0 && board[i][j] == nowqi; i++, j--) count++;
        for (i = xPos - 2, j = yPos; i >= 0 && j < BOARD_SIZE && board[i][j] == nowqi; i--, j++) count++;
        if (count > 4) return 1;

        return 0;
    }

    

    public static void main(String[] args) throws Exception//表明main方法不处理任何异常
    {
        wuziqi qi = new wuziqi();
        qi.initBoard();
        qi.printBoard();
        //获取键盘输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = null;
        do {
            System.out.println("请输入您下棋的坐标，应以x,y的格式");
            inputStr = br.readLine();
            String[] posStrArr = inputStr.split(",");//以逗号分离字符串
            int xPos = Integer.parseInt(posStrArr[0]);
            int yPos = Integer.parseInt(posStrArr[1]);
            if (qi.board[xPos - 1][yPos - 1] != "+") {
                System.out.println("此处已有落子，请重新输入");
                continue;
            }
            qi.board[xPos - 1][yPos - 1] = "o";


                                    /*判断输赢*/
            if (qi.judge(xPos, yPos) == 1) {
                qi.printBoard();
                System.out.println("You win!");
                break;
            }

                            /*电脑随机数落子*/
            int x, y;
            do {
                x = (int) (Math.random() * 15);
                y = (int) (Math.random() * 15);
            } while (qi.board[x][y] != "+");
            qi.board[x][y] = "X";
            if (qi.judge(x + 1, y + 1) == 1) {
                qi.printBoard();
                System.out.println("You lose!");
                break;
            }
            qi.printBoard();

        } while (inputStr != null);

    }


}
