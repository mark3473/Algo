import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		if(N==1) System.out.println(1);
		else {
			int gap = 1;
			int min = 2;
			
			while(min+gap*6<=N) {
				min += gap*6;
				gap++;
			}
			
			System.out.println(gap+1);
		}
	}

}
