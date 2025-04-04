import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for(int n=0; n<N; n++){
            Set<String> set = new HashSet<>();
            String str = br.readLine();
            int len = str.length();
            int[] check = new int[26];
            for(int i=0; i<len; i++){
                check[str.charAt(i)-'a']++;
            }

            fact(set, str,new StringBuilder(), check);
            set.stream().sorted().forEach(s->sb.append(s).append("\n"));
        }

        System.out.println(sb.toString());
    }

    static void fact(Set<String> set, String str, StringBuilder sb, int[] v){
        if(sb.length()==str.length()){
            set.add(sb.toString());
            return;
        }

        for(int i=0; i<26; i++){
            if(v[i]>0){
                v[i]-=1;
                sb.append((char)(i+'a'));
                fact(set, str, sb, v);
                sb.deleteCharAt(sb.length()-1);
                v[i]+=1;
            }
        }
    }
}
