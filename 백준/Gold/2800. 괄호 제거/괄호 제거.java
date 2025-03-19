import java.io.*;
import java.util.*;

public class Main {
    static class Pair{
        int left;
        int right;

        Pair(int l, int r){
            this.left = l;
            this.right = r;
        }
    }
    static ArrayList<Pair> arr = new ArrayList<>();
    static Set<String> set = new HashSet<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        int len = str.length();
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<len; i++){
            if(str.charAt(i)=='(') stack.add(i);
            else if(str.charAt(i)==')') arr.add(new Pair(stack.pop(), i));
        }

        comb(0, str);
        set.remove(str);
        set.stream().sorted().forEach(s -> System.out.println(s));
    }

    static void comb(int cnt, String str){
        if(cnt==arr.size()){
            set.add(str.replaceAll(" ", ""));
            return;
        }

        Pair p = arr.get(cnt);
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(p.left, ' ');
        sb.setCharAt(p.right, ' ');
        comb(cnt+1, sb.toString());

        comb(cnt+1, str);
    }
}
