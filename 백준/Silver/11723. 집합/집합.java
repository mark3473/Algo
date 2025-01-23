import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        int S = 0; // 비트마스킹할 값
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            if(command.equals("add")){
                S |= 1<< (Integer.parseInt(st.nextToken())); // or 연산을 통해 집합에 값 저장
            } else if(command.equals("remove")){
                S &= ~(1 << (Integer.parseInt(st.nextToken()))); // 값의 NOT과 and 연산을 통해 값 제거
            }
            else if(command.equals("check")){
                int num = Integer.parseInt(st.nextToken());
                if( (S & (1<<num)) == (1<<num) ) sb.append(1);
                else sb.append(0);

                sb.append("\n");
            }
            else if(command.equals("toggle")){ // 토글 : 있으면 제거, 없으면 저장
                S ^= 1<<(Integer.parseInt(st.nextToken())); // xor 연산을 통해 같으면 0, 다르면 1로 반전시킴
            }
            else if(command.equals("all")){
                S = (1<<21) - 1; // 꽉찬 집합 만들기 (1부터 20까지 확인하기 위해 21로. 0은 사용 안함)
            }
            else if(command.equals("empty")){
                S = 0; // 빈 집합으로 만들기
            }
        }

        System.out.println(sb.toString());
    }
}
