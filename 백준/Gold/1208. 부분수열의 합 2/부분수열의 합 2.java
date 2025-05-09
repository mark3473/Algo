import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수열의 길이
        int S = Integer.parseInt(st.nextToken()); // 목표값

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int size = N/2;
        int[] front = new int[1<<size];
        int[] back = new int[1<<(N-size)];

        for(int i=0; i<1<<size; i++){
            for(int j=0; j<size; j++){
                if((i&(1<<j)) == (1<<j)){
                    front[i] += arr[j];
                }
            }
        }
        Arrays.sort(front);

        for(int i=0; i<1<<(N-size); i++){
            for(int j=0; j<N-size; j++){
                if((i&1<<j) == (1<<j)){
                    back[i] += arr[size+j];
                }
            }
        }
        Arrays.sort(back);

        long ans = 0;
        int left = 0;
        int right = (1<<(N-size))-1;
        while( left<front.length && right>-1 ){
            int sum = front[left] + back[right];
            if(sum>S) right-=1;
            else if(sum<S) left+=1;
            else {
                // front에서 같은 값 개수 찾기
                int v1 = front[left];
                long cnt1 = 0;
                while(left<front.length && v1==front[left]){
                    cnt1++;
                    left++;
                }

                // back에서 같은 값 개수 찾기
                int v2 = back[right];
                long cnt2 = 0;
                while(right>-1 && v2==back[right]){
                    cnt2++;
                    right--;
                }

                ans += (cnt1*cnt2);
            }
        }

        if(S==0) ans-=1;
        System.out.println(ans);
    }
}
