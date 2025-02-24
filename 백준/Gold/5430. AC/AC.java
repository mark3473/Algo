import java.io.*;
import java.util.*;

// R : 뒤집기 -
// D : 버리기 - 첫번째 수 버리기 - 비어있을때는 error
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            String command = br.readLine();
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[,]");
            int[] arr = new int[n];
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int p1=0;
            int p2=n-1;
            int flag = 1; // 1이면 정순, -1이면 역순

            for(int i=0; i<command.length(); i++){
                char c = command.charAt(i);

                if(c=='R'){
                    flag *= -1;
                }
                else{ // c=='D'
                    if(flag==1){ // 정순이면
                        p1++;
                    }
                    else if(flag==-1){
                        p2--;
                    }

                    if(p1-p2>1) break;
                }
            }

            if(p1-p2>1) sb.append("error").append("\n");
            else if(p1-p2==1) sb.append("[]").append("\n");
            else if(flag==1){
                sb.append("[");
                for(int p=p1; p<p2; p++){
                    sb.append(arr[p]).append(",");
                }
                sb.append(arr[p2]).append("]").append("\n"); // p1 p2가 같을 때도 잘되는지 확인
            }
            else if(flag==-1){
                sb.append("[");
                for(int p=p2; p>p1; p--){
                    sb.append(arr[p]).append(",");
                }
                sb.append(arr[p1]).append("]").append("\n"); // p1 p2가 같을 때도 잘되는지 확인
            }
        }
        System.out.println(sb);
    }
}
// 1개일 때 D 하면 빈 배열이여야함
