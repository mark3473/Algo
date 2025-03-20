import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken()); // 사로 수
        int N = Integer.parseInt(st.nextToken()); // 동물 수
        int L = Integer.parseInt(st.nextToken()); // 사정거리

        int[] guns = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            guns[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(guns);
        int ans=0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(L<y){
                continue;
            }

            if(binarySearch1(guns, x+y-L)<=binarySearch2(guns, L+x-y)){
                ans++;
            }
        }

        System.out.println(ans);
    }

    static int binarySearch1(int[] arr, int val){
        int left = 0;
        int right = arr.length;
        while(left<right){
            int mid = (left+right)/2;

            if(arr[mid]<val){
                left = mid+1;
            } else{
                right = mid;
            }
        }
        return right;
    }

    static int binarySearch2(int[] arr, int val){
        int left = 0;
        int right = arr.length;
        while(left<right){
            int mid = (left+right)/2;

            if(arr[mid]<=val){
                left = mid+1;
            } else{
                right = mid;
            }
        }
        return left-1;
    }
}
