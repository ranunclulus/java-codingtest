import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class two15664 {
    static StringBuilder builder;
    static int n;
    static int m;
    static int[] arr;
    static int[] result;
    static List<String> ans = new LinkedList<>();
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        result = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        recursive(0, 0);
        for (String temp : ans) {
            System.out.println(temp);
        }
    }

    private void recursive(int index, int depth) {
        if (depth == m) {
            builder = new StringBuilder();
            for (int i = 0; i < m; i++) {
                builder.append(result[i]).append(" ");
            }
            if (!ans.contains(builder.toString())) {
                ans.add(builder.toString());
            }
            return;
        }
        for (int i = index; i < n; i++) {
            result[depth] = arr[i];
            recursive(i + 1, depth + 1);
        }

    }


    public static void main(String[] args) throws IOException {
        new two15664().solution();
    }
}
