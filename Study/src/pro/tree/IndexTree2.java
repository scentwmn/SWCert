package pro.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class IndexTree2 {
    private static long[] tree;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
 
        // 2진트리로 표현해야하기 때문에 배열사이즈는 항상 2^n으로 나오게 합니다.
        // 구간합이기 때문에 실제 배열보다 큰 부분은 0으로 채울 경우 계산에 영향을 미치지 않습니다.
        int S = 1;
        while (S < N)
            S <<= 1;
 
        // 사이즈보다 두 배 크게 만들면 기존 배열과 그 부모노드들이 모두 들어갈 수 있는 크기가 됩니다.
        tree = new long[2 * S];
        for (int i = S; i <= S + N - 1; i++)
            tree[i] = Long.parseLong(br.readLine().trim());
        for (int i = S; i <= S + N - 1; i++) {
            int P = i / 2;
            while (P != 0) {
                tree[P] = tree[P] + tree[i];
                P /= 2;
            }
        }
        for (int i = 1; i <= M + K; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                update(S + b - 1, c);
            } else
                System.out.println(sum(S + b - 1, S + c - 1));
        }
    }
 
    private static long sum(int b, int c) {
        long sum = 0L;
        while (b < c) {
            // 시작하는 부분이 홀수인지 판단합니다.
            if ((b & 1) == 1) {
                sum += tree[b];
                b++;
            }
            // 끝나는 부분이 짝수인지 판단합니다.
            if ((c & 1) == 0) {
                sum += tree[c];
                c--;
            }
            b /= 2;
            c /= 2;
        }
        // 밑에서부터 시작과 끝이 올라와서 만나게 된다면 그 구간은 현재 노드가 같은 부모인 것이고,
        // 현재 노드는 하위 모든 노드들의 합을 나타내는 것입니다.
        if (b == c)
            sum += tree[b];
        return sum;
    }

    static void update(int idx, long val) {
        long minus = tree[idx];
        int P = idx;
        while (P != 0) {
            tree[P] = tree[P] - minus + val;
            P /= 2;
        }
    }
}
