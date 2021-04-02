package pro.tree;

import java.util.Scanner;

public class AligaliTree {
    static int N = 0;
    static int R = 0;
    static Tree3[] tree = new Tree3[10001];
    static int EDGE = -1;
    static boolean SAME_EDGE = true;
    public static void main(String[] args) {

       // Please Enter Your Code Here
       Scanner scan = new Scanner(System.in);
       N = scan.nextInt();
       R = scan.nextInt();
       
       for(int inx=1; inx <= N-1; inx++) {
           int i = scan.nextInt();
           int v = scan.nextInt();

           if (tree[i] == null) {
        	   tree[i] = new Tree3(i);
           }
           if (tree[v] == null) {
        	   tree[v] = new Tree3(v);
           }
           if (tree[i].left == -1) {
        	   tree[i].left = v;
           } else {
        	   tree[i].right = v;
           }
       }

       tree[R].edge = 0;
       if (tree[R].left != -1 || tree[R].right != -1) {
    	   inorder(tree[R], -1);
       } else {
           tree[R].edge = 0;
           EDGE = 0;
       }

       if (SAME_EDGE) {
    	   System.out.println(EDGE);
       } else {
    	   System.out.println(-1);
       }
       
       scan.close();
    }

    // leftRight : true left 가 out, false right이 out임.
    // curr : true out, false in
    public static void inorder(Tree3 node, int parentEdge) {
    	if (node.left == -1 && node.right == -1) {
    		node.isLeaf = true;
    	}
    	
    	node.edge = parentEdge + 1;
    	if (node.isLeaf) {
    		if (EDGE == -1) {
    			// 최초값이면 세팅
    			EDGE = node.edge;
    			SAME_EDGE = true;
    		}
    		else {
    			if (SAME_EDGE && EDGE != node.edge) {
    				SAME_EDGE = false;
    			}
    		}
    	}
    	
    	if (SAME_EDGE) {
	    	if (node.left != -1) {
	    		inorder(tree[node.left], node.edge);
	    	}
	    	if (node.right != -1) {
	    		inorder(tree[node.right], node.edge);
	    	}
    	}
    }

}

class Tree3 {
    public int node;
    public int left;
    public int right;
    public int edge; // root로부터의 간선 수
    public boolean isLeaf = false;
    public Tree3(int n) {
        this.node = n;
        this.left = -1;
        this.right = -1;
    }
    @Override
	public String toString() {
		return "Tree3 [node=" + node + ", left=" + left + ", right=" + right + ", edge=" + edge + ", isLeaf=" + isLeaf
				+ "]";
	}
}
