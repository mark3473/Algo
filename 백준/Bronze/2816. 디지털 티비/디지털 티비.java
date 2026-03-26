import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int kbs1 = -1;
		int kbs2 = -1;
		for(int i=0; i<N; i++) {
			String channel = br.readLine();
			if(channel.equals("KBS1")) kbs1 = i;
			else if(channel.equals("KBS2")) kbs2 = i;
			
			if(kbs1 != -1 && kbs2 != -1) break;
		}
		
		for(int i=0; i<kbs2; i++) sb.append(1); // kbs2를 찾음
		for(int i=0; i<kbs2; i++) sb.append(4); // kbs2를 첫번째 채널로 이동
		
		if(kbs1 < kbs2) kbs1++; // kbs2를 옮기는 과정에서 kbs1의 위치가 한칸 내려가는 경우
		
		for(int i=0; i<kbs1; i++) sb.append(1); // kbs1을 찾음
		for(int i=0; i<kbs1; i++) sb.append(4); // kbs1을 첫번째 채널로 이동 (마지막에는 kbs2와 자리를 바꾸게 됨)
		
		System.out.println(sb);
	}

}
