import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 구슬 수
        int M = Integer.parseInt(st.nextToken()); // 그룹 수
        int[] vals = new int[N];
        int[] ans = new int[M];
        int Min = 0;
        st = new StringTokenizer(br.readLine());
        int left= 0;
        int right = 0;
        for(int i=0; i<N; i++){
            vals[i] = Integer.parseInt(st.nextToken());
            right += vals[i];
        }
        Min = right;

        l: while(left<right){
            int mid = (left+right)/2;
            int lastIdx=0;
            int cnt = 1; // 그룹 수
            int sum = 0; // 현재 그룹 내 숫자의 합
            int[] tmp = new int[M];
            int tmpM = 0;
            for(int i=0; i<N; i++){
                if(cnt>M || vals[i]>mid) { // 그룹을 더 많이 만들어버렸거나 구슬 하나의 값이 mid보다 클 때
                    left = mid+1;
                    continue l;
                }
                if(sum+vals[i]>mid || N-i<=M-cnt){ // 그룹 합의 한도를 초과할 경우
                    tmp[cnt-1] = i-lastIdx; // 그룹 내 구슬 수 저장
                    tmpM = Math.max(tmpM, sum); // 지금까지 생성한 그룹 내 합 최대값 갱신
                    cnt++; // 새 그룹 생성
                    sum = vals[i]; // 새 그룹 내 합 초기화
                    lastIdx = i;
                }
                else { // 그룹에 구슬 추가
                    sum+=vals[i];
                }
            }
            // M개의 그룹을 만들 수 없으면 다시
            if(cnt>M) {
                left = mid+1;
            }
            else { // M개의 그룹을 만들 수 있다면
                tmp[cnt-1] = N-lastIdx;
                // sum이 mid를 넘는지 확인
                if(sum>mid) left = mid+1;
                else {
                    tmpM = Math.max(tmpM, sum);
                    Min = Math.min(Min, tmpM);
                    ans = tmp;
                    right = mid;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(Min).append("\n");
        for(int i=0; i<M; i++){
            sb.append(ans[i]).append(" ");
        }

        System.out.println(sb);
    }
}
