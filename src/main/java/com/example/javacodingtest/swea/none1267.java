package swea;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.27
 @link
 @timecomplex
 @performance 40256KB, 188MS
 @category 위상정렬
 @note
 */
public class none1267 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int v;
    static int e;
    static int[][] edges;
    static int[] degree;
    static boolean[] visited;
    static Deque<Integer> queue;

    public void solution() throws IOException {
        for (int test = 1; test <= 10; test++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            edges = new int[v][v];
            degree = new int[v];
            visited = new boolean[v];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < e; i++) {
                int startNode = Integer.parseInt(st.nextToken()) - 1;
                int endNode = Integer.parseInt(st.nextToken()) - 1;
                edges[startNode][endNode] = 1;
                degree[endNode]++;
            }

            sb.append("#" + test + " ");
            queue = new ArrayDeque<>();
            for (int i = 0; i < v; i++) {
                if (degree[i] == 0) {
                    queue.push(i);
                }
            }

            for (int i = 0; i < v; i++) {
                int node = queue.pop();
                visited[node] = true;
                sb.append(node + 1).append(" ");
                for (int j = 0; j < v; j++) {
                    if (edges[node][j] == 1) {
                        degree[j]--;
                    }
                }
                for (int j = 0; j < v; j++) {
                    if (degree[j] == 0 && !visited[j]) {
                        queue.push(j);
                    }
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        new none1267().solution();
    }
}
