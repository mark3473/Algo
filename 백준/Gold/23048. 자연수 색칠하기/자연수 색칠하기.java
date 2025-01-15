import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] color = new int[N+1];
        color[1] = 1;
        boolean[] check = new boolean[N+1];;
        int count=1;
        for(long i=2; i<=N; i++){
            if(check[(int)i]) continue; // 색칠됐으면 패스
            check[(int)i] = true; // 안되어 있으면 색칠
            count++; // 카운트 증가 (사용한 색의 개수)
            color[(int)i] = count; // 즉 count번째의 색을 새롭게 색칠했다
            for(long j= i*i; j<=N; j++){ // 그 제곱수부터 쭉 살펴봤을 때
                if(j%i==0){ // 나눠 떨어지면 == 서로소가 아니면
                    check[(int)j] = true; // 색칠하기
                    color[(int)j] = count; // 같은 색으로
                }
            }
        }
        System.out.println(count++);
        for(int i=1; i<=N; i++){
            System.out.print(color[i]+" ");
        }
    }
}
