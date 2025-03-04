import java.io.*;
import java.util.*;

public class Main {
    static String ans = "NP";
    public static void main(String[] args) throws Exception {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        String string = br.readLine();

        Stack<Character> stack = new Stack<>();
        int length = string.length();
        for(int i=0; i<length; i++){
            // 글자 하나 넣고
            stack.add(string.charAt(i));
            // 스택 내에 ppap가 있는지 확인
            checkPPAP(stack);
        }

        if(stack.size()==1 && stack.get(0)=='P'){
            System.out.println("PPAP");
        } else System.out.println("NP");
    }
    static void checkPPAP(Stack<Character> stack){
        int size = stack.size();
        if(size>=4 && stack.get(size-4)=='P'
                && stack.get(size-3)=='P'
                && stack.get(size-2)=='A'
                && stack.get(size-1)=='P'){
            for(int j=0; j<4; j++) stack.pop();
            stack.add('P');
        }
    }
}
