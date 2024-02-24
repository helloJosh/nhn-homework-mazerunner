import java.util.Stack;

class Pair {
    private int x;
    private int y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        String s = "( " + y + " , " + x + " )";
        return s;
    }
}

public class Mazerunner {
    private final static int[] dx = { 0, 1, 0, -1 };
    private final static int[] dy = { 1, 0, -1, 0 };
    private final static Stack<Pair> stack = new Stack<Pair>();
    private final static int[][] maze = {
            { 0, 1, 0, 0, 0 },
            { 0, 1, 0, 1, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 1, 0, 1, 0 },
            { 0, 0, 0, 0, 0 }
    };
    private final static boolean[][] visited = new boolean[maze.length][maze.length];

    /**
     * 다음 길이 있는지 재귀함수로 확인 메서드
     * 길이 없으면 False 반환
     * 목표 지점 까지 길이 있으면 True 반환
     * 
     * @param a
     * @param p
     * @return
     */
    public static boolean findNextPath(int[][] a, Pair p) {
        int n = a.length;
        int x = p.getX();
        int y = p.getY();
        // 현재 위치가 미로의 범위를 벗어나는지 확인
        if (0 > x || x >= n || 0 > y || y >= n) {
            return false;
        } else {
            // 현재 위치가 벽이거나 이미 방문한 곳인지 학인
            if (a[x][y] == 1 || visited[x][y]) {
                return false;
            }
            // 도착 지점에 도달한 경우
            else if (x == n - 1 && y == n - 1) {
                stack.push(p);
                return true;
            }
            // 백트래킹을 통한 탐색 반복
            else {
                stack.push(p);
                visited[x][y] = true;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (findNextPath(a, new Pair(nx, ny))) {
                        return true;
                    }
                }
                stack.pop();
                return false;
            }
        }
    }

    /**
     * stack으로 들어간 자료 출력 메서드
     */
    public static void printPath() {
        int i = 1;
        System.out.println();
        System.out.println("====Road Map====");
        for (Pair p : stack) {
            System.out.print(i + ".   ");
            System.out.println(p);
            i++;
        }
    }

    public static void main(String[] args) {
        if (findNextPath(maze, new Pair(0, 0))) {
            System.out.println("has way out");
        } else {
            System.out.println("no way out");
        }
        printPath();
    }

}