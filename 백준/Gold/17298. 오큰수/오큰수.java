import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // 처음에는 0번 idx 넣기
        for(int i=1; i<N; i++){
            while(!stack.isEmpty() && nums[stack.peek()]<nums[i]){
                nums[stack.pop()] = nums[i];
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            nums[stack.pop()] = -1;
        }

        for(int i : nums){
            sb.append(i).append(" ");
        }

        System.out.println(sb.toString());
    }
}
