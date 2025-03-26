import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int order = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int Asize = Integer.parseInt(st.nextToken());
        int[] A = new int[Asize*2+1];
        int Bsize = Integer.parseInt(st.nextToken());
        int[] B = new int[Bsize*2+1];

        for(int i=1; i<=Asize; i++){
            A[i] = Integer.parseInt(br.readLine())+A[i-1];
        }
        for(int i=1; i<Asize; i++){
            A[i+Asize] = A[i+Asize-1] + A[i]-A[i-1];
        }

        Map<Integer, Integer> Ap = new HashMap<>();
        Ap.put(A[Asize]-A[0], 1);
        Ap.put(0, 1);
        for(int d=1; d<Asize; d++){ // d 사이즈만큼의 윈도우 슬라이드
            for(int i=0; i<Asize; i++){ // 윈도우 슬라이딩 시작 지점
                Ap.put(A[i+d]-A[i], Ap.getOrDefault(A[i+d]-A[i], 0)+1);
            }
        }

        for(int i=1; i<=Bsize; i++){
            B[i] = Integer.parseInt(br.readLine())+B[i-1];
        }
        for(int i=1; i<Bsize; i++){
            B[i+Bsize] = B[i+Bsize-1] + B[i]-B[i-1];
        }

        Map<Integer, Integer> Bp = new HashMap<>();
        Bp.put(B[Bsize]-B[0], 1);
        Bp.put(0, 1);
        for(int d=1; d<Bsize; d++){ // d 사이즈만큼의 윈도우 슬라이드
            for(int i=0; i<Bsize; i++){ // 윈도우 슬라이딩 시작 지점
                Bp.put(B[i+d]-B[i], Bp.getOrDefault(B[i+d]-B[i], 0)+1);
            }
        }


        int[] Aarr = Ap.keySet().stream().mapToInt(i->i).sorted().toArray();
        int[] Barr = Bp.keySet().stream().mapToInt(i->i).sorted().toArray();

        int pA = 0;
        int pB = Barr.length-1;
        int ans = 0;
        while(pA<Aarr.length && pB>=0){
            if(Aarr[pA]+Barr[pB]==order){
                ans += (Ap.get(Aarr[pA]) * Bp.get(Barr[pB]));
                pA++;
            } else if (Aarr[pA]+Barr[pB] < order) pA++;
            else pB--;
        }

        System.out.println(ans);
    }
}
