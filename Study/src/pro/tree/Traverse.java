package pro.tree;

import java.util.Scanner;

public class Traverse {
    static int printIdx = 0;
    static int K = 0;
    static Tree[] tree = null;
    public static void main(String[] args){

       // Please Enter Your Code Here
       Scanner scan = new Scanner(System.in);
       int N = scan.nextInt();
       K = scan.nextInt();

       tree = new Tree[N];
       for(int inx=0; inx < N; inx++) {
         int I = scan.nextInt();
         int L = scan.nextInt();
         int R = scan.nextInt();
         
         tree[I] = new Tree();
         tree[I].left = L;
         tree[I].right = R;
       }
       
       printIdx = 0;
       inorder(0);
       System.out.println();
    }

    public static void inorder(int i) {
      if (tree[i].left == -1 && tree[i].right == -1) {
    	  if (printIdx+1 == K) {
    		  System.out.println(i);
    	  }
    	  printIdx++;
      }
      else {
        if (tree[i].left != -1) {
        	inorder(tree[i].left);
        }
	  	  if (printIdx+1 == K) {
			  System.out.println(i);
		  }
		  printIdx++;
        if (tree[i].right != -1) {
        	inorder(tree[i].right);
        }
      }
    }
  
}

class Tree {
  public int left;
  public int right;
}
