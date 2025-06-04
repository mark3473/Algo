import java.io.*;
import java.util.*;

public class Main {
    static int C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 물건 수
        C = Integer.parseInt(st.nextToken()); // 무게 한도

        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N/2; i++) A.add(Integer.parseInt(st.nextToken()));
        while(st.hasMoreTokens()) B.add(Integer.parseInt(st.nextToken()));

        ArrayList<Integer> Asum = new ArrayList<>();
        ArrayList<Integer> Bsum = new ArrayList<>();

        dfs(0, 0, A, Asum);
        dfs(0, 0, B, Bsum);

        Collections.sort(Bsum);
        int ans = 0;

        for(int i=0; i<Asum.size(); i++){
            int a = Asum.get(i);

            int left = 0;
            int right = Bsum.size();

            while(left < right){
                int mid = (left+right)/2;
                int b = Bsum.get(mid);

                if(a+b <= C){
                    left = mid+1;
                }
                else{
                    right = mid;
                }
            }

            ans += left;
        }

        System.out.println(ans);
    }

    static void dfs(int cnt, int sum, ArrayList<Integer> Bag, ArrayList<Integer> result){
        if(sum>C) return;

        if(cnt==Bag.size()){
            result.add(sum);
            return;
        }

        dfs(cnt+1, sum+Bag.get(cnt), Bag, result);
        dfs(cnt+1, sum, Bag, result);
    }
}
