import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String string = br.readLine();
        String bomb = br.readLine();

        int stringSize = string.length();
        int bombSize = bomb.length();

        Stack<Character> stack = new Stack<>();
        for(int idx=0; idx<stringSize; idx++){
            stack.push(string.charAt(idx));

            int cnt=0;
            if(stack.size()>=bombSize){
                for(int i=0; i<bombSize; i++){
                    if(stack.get(stack.size()-bombSize+i) == bomb.charAt(i)) cnt++;
                    else break;
                }

                if(cnt==bombSize){
                    for(int i=0; i<bombSize; i++) stack.pop();
                }
            }
        }

        if(stack.isEmpty()) System.out.println("FRULA");
        else{
            for(char c:stack){
                sb.append(c);
            }
            System.out.println(sb.toString());
        }
    }
}
