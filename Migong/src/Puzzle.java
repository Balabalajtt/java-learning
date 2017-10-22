import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by 江婷婷 on 2017/10/22.
 */
class Puzzle {
    int m;

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    int n;
    public int[][] pazzle;
    public int[] xType = {1, 0, -1, 0};
    public int[] yType = {0, 1, 0, -1};

    public List<Path> pathList = new ArrayList<>();

    /**
     * 递归求解
     */
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
            if (isValid(nextLocation)) {
                pathList.add(nextLocation);
                pazzle[nextLocation.getX()][nextLocation.getY()] = 2;//走过
                if (isDown()) {
                    return;
                }
                walk();
                return;
            }
        }
        Path path = pathList.get(pathList.size() - 1);
        pathList.remove(pathList.size() - 1);
        pazzle[path.getX()][path.getY()] = 2;
        walk();
    }

    /**
     * 初始化迷宫
     */
    public void initPuzzle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入迷宫规格");
        m = scanner.nextInt();
        n = scanner.nextInt();
        scanner.nextLine();
        System.out.println("请输入" + m + "行" + n + "列迷宫");
        pazzle = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] strings = scanner.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                pazzle[i][j] = Integer.parseInt(strings[j]);
            }
        }
        scanner.close();
        pathList.add(new Path(0, 0));
    }

    public boolean isValid(Path path) {
        int x = path.getX();
        int y = path.getY();
        if (x >= 0 && x < m && y >= 0 && y < n) {
            if (pazzle[path.getX()][path.getY()] == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isDown() {
        Path path = pathList.get(pathList.size() - 1);
        if (path.getX() == m - 1 && path.getY() == n - 1) {
            return true;
        } else {
            return false;
        }
    }

    public void printStack() {
        for (Path path : pathList) {
            System.out.println(path.getX() + "-" + path.getY());
        }
    }

}


