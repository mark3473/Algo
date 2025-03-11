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

            comb(2,"1");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    //
    static void comb(int num, String str){
        if(num>N){
            if(cal(str)) sb.append(str).append("\n");
            return;
        }
        // 공백
        comb(num+1, str+' '+num);
        // 더하기
        comb(num+1,str+'+'+num);
        // 빼기
        comb(num+1, str+'-'+num);
  
    }

    static boolean cal(String str){
        // str 계산하기

        // 1. str에 공백 없애기
        str  = str.replace(" ", "");

        // 2. str을 +, -를 기준으로 나눠 배열로 만들고
        //      그것을 stream.map을 통해 Integer로 만들고
        //      List로 만든 후 원소를 순차적으로 처리할 수 있는 Iterator로 변환.
        Iterator<Integer> it = Arrays.stream(str.split("[+,-]"))
                .map(Integer::parseInt)
                .collect(Collectors.toList()).iterator();

        int result = it.next();

        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)=='+'){
                result += it.next();
            }
            else if(str.charAt(i)=='-'){
                result -= it.next();
            }
        }

        if(result==0) return true;
        else return false;
    }
}
