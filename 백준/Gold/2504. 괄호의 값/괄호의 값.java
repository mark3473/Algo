import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String string = br.readLine();
        Stack<Character> stack = new Stack<>();
        int ans = 0;
        int tmp = 1;
        for(int i=0; i<string.length(); i++){
            char c = string.charAt(i);

            if(c=='('){
                stack.add(c);
                tmp*=2;
            }
            else if(c=='['){
                stack.add(c);
                tmp*=3;
            }
            else if(c==')'){
                if(stack.isEmpty() || stack.peek()!='(') {
                    System.out.println(0);
                    return;
                }

                if(string.charAt(i-1)=='('){ // 이번 괄호로 인해 바로 인접한 부분이 닫혔으면
                    ans+=tmp;
                }

                stack.pop();
                tmp/=2;
            }
            else if(c==']'){
                if(stack.isEmpty() || stack.peek()!='['){
                    System.out.println(0);
                    return;
                }

                if(string.charAt(i-1)=='['){
                    ans+=tmp;
                }

                stack.pop();
                tmp/=3;
            }
        }

        if(stack.isEmpty()) System.out.println(ans);
        else System.out.println(0);
    }
}
