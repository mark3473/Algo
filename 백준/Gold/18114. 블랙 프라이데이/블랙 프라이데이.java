import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물건의 개수
        int C = Integer.parseInt(st.nextToken()); // 무게

        int[] items = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            items[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(items);

        for(int A=N-1; A>=0; A--){
            if(items[A]>C) continue;
            if(items[A]==C) {
                System.out.println(1);
                return;
            }

            int left = 0;
            int right = A-1;
            while(left<=right){
                int sum = left==right? items[left]+items[A] : items[left]+items[right]+items[A];

                if(sum==C) {
                    System.out.println(1);
                    return;
                }
                else if(sum<C) left+=1;
                else right-=1;
            }
        }

        System.out.println(0);
        return;
    }
}
