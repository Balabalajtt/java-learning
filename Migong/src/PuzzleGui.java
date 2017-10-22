import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 江婷婷 on 2017/10/22.
 */
public class PuzzleGui extends JFrame {
    int m;
    int n;
    int[][] puzzleMap;
    JButton[][] map;
    List<Path> pathList = new ArrayList<>();
    List<Record> recordList = new ArrayList<>();

    public PuzzleGui(int m, int n) throws HeadlessException {
        this.m = m;
        this.n = n;
        puzzleMap = new int[m + 2][n + 2];
        map = new JButton[m + 2][n + 2];
        for (int i = 0; i < m + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (i == 0 || i == m + 1 || j == 0 || j == n + 1) {
                    puzzleMap[i][j] = 1;
                } else {
                    puzzleMap[i][j] = 0;
                }
            }
        }
        pathList.add(new Path(1, 1));
        recordList.add(new Record(1, 1, 1));
        puzzleMap[1][1] = 2;
//        puzzleMap[m][n] = 8;
        initMap();
    }

    public void initMap() {
        setLayout(new GridLayout(m + 2, n + 2));
        int count = 0;
        for (int i = 0; i < m + 2; i++, count++) {
            for (int j = 0; j < n + 2; j++) {
                map[i][j] = new JButton("");
                int finalJ = j;
                int finalI = i;
                if (finalI != 1 || finalJ != 1) {
                    map[i][j].addActionListener(e1 -> {
                        if (map[finalI][finalJ].getBackground() == Color.WHITE) {
                            map[finalI][finalJ].setBackground(Color.PINK);
                            puzzleMap[finalI][finalJ] = 1;
                        } else {
                            map[finalI][finalJ].setBackground(Color.WHITE);
                            puzzleMap[finalI][finalJ] = 0;
                        }
                    });
                }
                map[i][j].setBackground(Color.WHITE);
                if (i == 0 || i == m + 1 || j == 0 || j == n + 1) {
                    map[i][j].setBackground(Color.PINK);
                }
                add(map[i][j]);
                count++;
            }
        }
        map[1][1].setText("起点");
        map[1][1].addActionListener(e -> {
            walk();
            new Thread(() -> {

                //跑结果
//                for (int i = 0; i < pathList.size(); i++) {
//                    map[pathList.get(i).getX()][pathList.get(i).getY()].setBackground(Color.CYAN);
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e1) {
//                        e1.printStackTrace();
//                    }
//                    System.out.println("cs");
//                }

                //跑记录
                for (int i = 0; i < recordList.size(); i++) {
                    Record record = recordList.get(i);
                    if (record.type == 1) {
                        map[record.x][record.y].setBackground(Color.CYAN);
                    } else {
                        map[record.x][record.y].setBackground(Color.WHITE);
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }

            }).start();
        });
        map[1][1].setFont(new Font("黑体", Font.BOLD, 30));
        map[m][n].setText("终点");
        map[m][n].setFont(new Font("黑体", Font.BOLD, 30));
    }

    public int[] xType = {1, 0, -1, 0};
    public int[] yType = {0, -1, 0, 1};

    public void walk() {
        if (pathList.isEmpty()) {
            return;
        }
        Path lastLocation = pathList.get(pathList.size() - 1);
        if (isDown()) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            Path nextLocation = new Path(lastLocation.getX() + xType[i], lastLocation.getY() + yType[i]);
            if (puzzleMap[lastLocation.getX() + xType[i]][lastLocation.getY() + yType[i]] == 0) {
                pathList.add(nextLocation);
                System.out.println(nextLocation.getX() + "-" + nextLocation.getY() + "入" );
                recordList.add(new Record(nextLocation.getX(), nextLocation.getY(), 1));
//                map[nextLocation.getX()][nextLocation.getY()].setBackground(Color.BLUE);
                puzzleMap[nextLocation.getX()][nextLocation.getY()] = 2;//走过
                if (isDown()) {
                    return;
                }
                walk();
                return;
            }
        }
        Path path = pathList.get(pathList.size() - 1);
        pathList.remove(pathList.size() - 1);
        System.out.println(path.getX() + "-" + path.getY() + "出" );
        recordList.add(new Record(path.getX(), path.getY(), 0));
//        map[path.getX()][path.getY()].setBackground(Color.WHITE);
        puzzleMap[path.getX()][path.getY()] = 2;
        walk();
    }

    private boolean isDown() {
        int x = pathList.get(pathList.size() - 1).getX();
        int y = pathList.get(pathList.size() - 1).getY();
        if (x == m && y == n) {
            return true;
        } else {
            return false;
        }

    }


}

class Record {
    int x;
    int y;
    int type;//入1 出0

    public Record(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
