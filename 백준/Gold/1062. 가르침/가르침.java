import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 단어 수
        int K = Integer.parseInt(st.nextToken()); // 가르칠 글자 수

        // N개의 단어가 주어짐
        // K개의 글자를 가르칠 시간밖에 없음
        // 가장 잘 가르쳤을 때 읽을 수 있는 최대 단어 수 구하기

        int[] words = new int[N];
        int init = 0;
        init = init | (1<<('a'-'a'));
        init = init | (1<<('n'-'a'));
        init = init | (1<<('t'-'a'));
        init = init | (1<<('i'-'a'));
        init = init | (1<<('c'-'a'));
        int set = init;
        for(int i=0; i<N; i++){
            String word = br.readLine();
            int size = word.length();

            words[i] = init;

            for(int j=4; j<size-4; j++){
                int c = word.charAt(j)-'a';
//                System.out.println("c : "+c);
                words[i] = words[i] | (1<<c); // 글자 c가 단어에 포함되었음을 기록
                set = set | (1<<c); // 이건 전체 단어에 사용된 글자 기록
//                System.out.println("set : "+ Integer.toBinaryString(set));
            }

//            System.out.println(words[i]+" : "+Integer.toBinaryString(words[i]));
        }

//        System.out.println("set : "+ Integer.toBinaryString(set));
        int ans = 0;
        // set 안의 단어들 중 K개를 조합해 읽을 수 있는 단어 카운트
        if(Integer.bitCount(set)<=K) System.out.println(N);
        else{
            for (int subset = set ; subset>0; subset = ((subset - 1) & set)){
                if(Integer.bitCount(subset)!=K) continue;

                int count = 0;
//                System.out.println("subset : "+ Integer.toBinaryString(subset));

                for(long bit : words){
                    if((bit & subset) == bit) count++;
                }

                ans = Math.max(ans, count);
            }

            System.out.println(ans);
        }
    }
}
