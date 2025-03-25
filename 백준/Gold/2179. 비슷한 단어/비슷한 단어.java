import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        for(int n=0; n<N; n++){
            words[n] = br.readLine();
        }

        String[] ans = new String[2];
        int Max = 0;

        for(int i=0; i<N-1; i++){
            for(int j=i+1; j<N; j++){
                String w1 = words[i];
                String w2 = words[j];
                int size1 = w1.length();
                int size2 = w2.length();

                int cnt = 0;
                if(size1>=size2){
                    for(int k=0; k<size2; k++){
                        if(w1.charAt(k) == w2.charAt(k)) cnt++;
                        else break;
                    }
                }
                else {
                    for(int k=0; k<size1; k++){
                        if(w1.charAt(k) == w2.charAt(k)) cnt++;
                        else break;
                    }
                }

                if(cnt>Max) {
                    ans[0] = words[i];
                    ans[1] = words[j];
                    Max = cnt;
                }
            }
        }
        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }
}
