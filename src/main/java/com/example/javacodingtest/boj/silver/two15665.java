import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class two15665 {
    static StringBuilder builder;
    static int n;
    static int m;
    static Set<Integer> set = new HashSet<>();
    static int[] result;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        result = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }


        recursive( 0);
    }

    private void recursive(int depth) {
        if (depth == m) {
            builder = new StringBuilder();
            for (int i = 0; i < m; i++) {
                builder.append(result[i]).append(" ");
            }
            System.out.println(builder.toString());
            return;
        }
        for (int i : set) {
            result[depth] = i;
            recursive(depth + 1);
        }

    }


    public static void main(String[] args) throws IOException {
        new two15665().solution();
    }
}
