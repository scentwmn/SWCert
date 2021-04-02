package pro.tree;

import java.util.Scanner;

// 표리일체 이진트리
public class InOutSameTree {
    static int N = 0;
    static int R = 0;
    static Tree2[] tree = new Tree2[1001];
    static int IN_SUM = 0;
    static int OUT_SUM = 0;
    public static void main(String[] args) {

       // Please Enter Your Code Here
       Scanner scan = new Scanner(System.in);
       String nr = scan.nextLine();
       String[] nra = nr.split(" ");
       if (nra.length == 1) {
    	   N = Integer.parseInt(nra[0]);
    	   R = N;
       } else {
    	   N = Integer.parseInt(nra[0]);
    	   R = Integer.parseInt(nra[1]);
       }
       
       IN_SUM = 0;
       OUT_SUM = 0;
       for(int inx=0; inx < N; inx++) {
         int i = scan.nextInt();
         int l = scan.nextInt();
         int r = scan.nextInt();
         
         tree[i] = new Tree2(i, l, r);
       }
       
       OUT_SUM += tree[R].node;
       
       inorder(true, tree[tree[R].left], true);
       inorder(false, tree[tree[R].right], true);
       if (IN_SUM == OUT_SUM) {
    	   System.out.println(IN_SUM);
       }
       else {
    	   System.out.println(-1);
       }
       
       scan.close();
    }

    // leftRight : true left 가 out, false right이 out임.
    // curr : true out, false in
    public static void inorder(final boolean leftRight, Tree2 node, final boolean curr) {
    	
    	if (curr) {
    		OUT_SUM += node.node;
    	} else {
    		IN_SUM += node.node;
    	}
    	if (leftRight) {
    		// 왼쪽이 있으면, 왼쪽이 OUT
    		if (node.left != -1) {
    			inorder(leftRight, tree[node.left], curr);
    		}
    		// 오른쪽만 있으면, 오른쪽이 OUT
    		if (node.left == -1 && node.right != -1 ) {
				// 현재 node가 OUT이면, OUT으로 감
				inorder(leftRight, tree[node.right], curr);
    		}
    		else if (node.right != -1) {
    			inorder(leftRight, tree[node.right], false);
    		}
    	}
    	else {
    		// 오른쪽이 있으면, 오른쪽이 OUT
    		if (node.right != -1) {
    			inorder(leftRight, tree[node.right], curr);
    		}
    		// 왼쪽만 있으면, 왼쪽이 OUT
    		if (node.right == -1 && node.left != -1 ) {
				// 현재 node가 OUT이면, OUT으로 감
				inorder(leftRight, tree[node.left], curr);
    		}
    		else if (node.left != -1) {
    			inorder(leftRight, tree[node.left], false);
    		}
    	}
    }

}

class Tree2 {
	public int node;
	public int left;
	public int right;
	public Tree2(int n, int l, int r) {
		this.node = n;
		this.left = l;
		this.right = r;
	}
	@Override
	public String toString() {
		return "Tree2 [node=" + node + ", left=" + left + ", right=" + right + "]";
	}
}
