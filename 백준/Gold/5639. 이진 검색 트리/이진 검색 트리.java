import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        Node left;
        int val;
        Node right;

        Node(int v){
            this.val = v;
            left = null;
            right = null;
        }

        void insert(int v){
            if(this.val < v){ // 현재 val보다 크면
                if(this.right == null) this.right = new Node(v); // right이 비어있으면 넣기
                else this.right.insert(v); // right에 이미 자식이 있으면 자식에게 insert 함수 시키기
            }
            else{
                if(this.left == null) this.left = new Node(v);
                else this.left.insert(v);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));

        while(true){
            String line = br.readLine();
            if(line==null || line.isEmpty()) break;

            root.insert(Integer.parseInt(line));
        }

        subfix(root);
    }

    static void subfix(Node node){
        if(node.left!=null) subfix(node.left);
        if(node.right!=null) subfix(node.right);
        System.out.println(node.val);
    }
}
