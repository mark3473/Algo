import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            String string = br.readLine();
            int ans = check(string, 0, string.length()-1, false, 1<<3);
            if((ans & (1<<0)) == (1<<0)) sb.append(0).append("\n");
            else if((ans & (1<<1)) == (1<<1)) sb.append(1).append("\n");
            else sb.append(2).append("\n");
        }

        System.out.println(sb.toString());
    }

    static int check(String string, int left, int right, boolean done, int result){
        if(left>=right){
            if(!done) return 1<<0;
            else return 1<<1;
        }

        if(string.charAt(left) == string.charAt(right)){
            result |= check(string, left+1, right-1, done, result);
        }
        else { // 다를 때
            if (done) {
                return result|(1<<2);
            }
            if (string.charAt(left + 1) == string.charAt(right)) {
                result |= check(string, left + 2, right - 1, true, result);
            }
            if (string.charAt(left) == string.charAt(right - 1)) {
                result |= check(string, left + 1, right - 2, true, result);
            }
            if (string.charAt(left) != string.charAt(right - 1) && string.charAt(left + 1) != string.charAt(right)) {
                return result|(1<<2);
            }
        }
        return result;
    }
}
