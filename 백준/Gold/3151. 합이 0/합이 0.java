import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 학생 수
        int[] val = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            val[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(val);
        long ans = 0;

        for(int i=0; i<N-2; i++){
            // i번째를 고르고 그 뒤를 조합
            int left = i+1;
            int right = N-1;
            while(left<right){
                int sum = val[left]+val[right]+val[i];
                if(sum<0) left+=1;
                else if(sum>0) right-=1;
                else{ // 합이 0일 때
                    if(val[left]==val[right]){ // left~right까지 모두 같은 수이면
                        ans+=((right-left+1)*(right-left)/2);
                        break;
                    }

                    // 같지 않다면? left의 값과 같은 개수 * right의 값과 같은 개수
                    int cntL = 1;
                    while(val[left]==val[left+1]){
                        cntL++;
                        left++;
                    }
                    int cntR = 1;
                    while(val[right]==val[right-1]){
                        cntR++;
                        right--;
                    }
                    ans += (cntL*cntR);
                    left++;
                    right--;
                }
            }
        }

        System.out.println(ans);
    }
}
