import java.io.*;
import java.util.*;

public class Main {
    static int K, Min, Max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 게임 수
        for(int t=0; t<T; t++){
            String str = br.readLine();
            K = Integer.parseInt(br.readLine()); // 목표 수
            int[] indexs = new int[K];
            int length = str.length();
            Map<Character, ArrayList<Integer>> map = new HashMap<>();
            for(int i=0; i<length; i++){
                char c = str.charAt(i);
                ArrayList<Integer> array = map.getOrDefault(c, new ArrayList<>());
                array.add(i);
                map.putIfAbsent(c, array);
            }

            // 확인하기
            Min = length+1;
            Max = -1;
            for(ArrayList<Integer> arr : map.values()){
                if(arr.size()<K) continue;

                for(int p=0; p<=arr.size()-K; p++){
                    int val = arr.get(p+K-1)-arr.get(p)+1;
                    Min = Math.min(Min, val);
                    Max = Math.max(Max, val);
                }
            }
            if(Max==-1) sb.append(-1).append("\n");
            else sb.append(Min).append(" ").append(Max).append("\n");
        }

        System.out.println(sb);
    }
}
