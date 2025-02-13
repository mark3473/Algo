import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int num = Integer.parseInt(st.nextToken());
            sb.append(upperBinarySearch(num, arr) - lowerBinarySearch(num, arr)).append(" ");
        }

        System.out.println(sb);
    }

    static int lowerBinarySearch(int num, int[] arr){
        int left = 0;
        int right = arr.length;
        while(left < right){
            int mid = (left + right) / 2;
            if(num <= arr[mid]) right = mid;
            else left = mid + 1;
        }

        return left;
    }

    static int upperBinarySearch(int num, int[] arr){
        int left = 0;
        int right = arr.length;
        while(left < right){
            int mid = (left + right) / 2;
            if(num < arr[mid]) right = mid;
            else left = mid + 1;
        }

        return left;
    }
}
