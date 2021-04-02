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
 
        // 2��Ʈ���� ǥ���ؾ��ϱ� ������ �迭������� �׻� 2^n���� ������ �մϴ�.
        // �������̱� ������ ���� �迭���� ū �κ��� 0���� ä�� ��� ��꿡 ������ ��ġ�� �ʽ��ϴ�.
        int S = 1;
        while (S < N)
            S <<= 1;
 
        // ������� �� �� ũ�� ����� ���� �迭�� �� �θ������ ��� �� �� �ִ� ũ�Ⱑ �˴ϴ�.
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
            // �����ϴ� �κ��� Ȧ������ �Ǵ��մϴ�.
            if ((b & 1) == 1) {
                sum += tree[b];
                b++;
            }
            // ������ �κ��� ¦������ �Ǵ��մϴ�.
            if ((c & 1) == 0) {
                sum += tree[c];
                c--;
            }
            b /= 2;
            c /= 2;
        }
        // �ؿ������� ���۰� ���� �ö�ͼ� ������ �ȴٸ� �� ������ ���� ��尡 ���� �θ��� ���̰�,
        // ���� ���� ���� ��� ������ ���� ��Ÿ���� ���Դϴ�.
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
