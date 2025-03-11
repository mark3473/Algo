import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine());

            comb(1,"");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    //
    static void comb(int num, String str){
        if(num==N){
            cal(str);
            return;
        }
        // 공백
        comb(num+1, str+' ');
        // 더하기
        comb(num+1,str+'+');
        // 빼기
        comb(num+1, str+'-');
    }

    static void cal(String str){
        int sum = 0;
        int prev = 1;
        for(int n=2; n<=N; n++){ // 2부터 N까지의 숫자 다루기
            if(str.charAt(n-2)=='+'){ // +이면 prev가 가리키는 숫자를 더하기
                sum+=prev;
                prev = n; // prev 갱신
            }
            else if(str.charAt(n-2)=='-'){
                sum+=prev;
                prev = -n;
            }
            else{
                prev *= 10;
                prev += prev>0? n:-n;
            }
        }
        sum+=prev;
        if(sum==0){
            sb.append(1);
            for(int n=2; n<=N; n++){
                sb.append(str.charAt(n-2)).append(n);
            }
            sb.append("\n");
        }
    }
}
