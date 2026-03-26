import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String word = br.readLine().toUpperCase();
		
		int[] cnt = new int[26];
		int max = 0;
		for(char c : word.toCharArray()) {
			cnt[c-'A']++;
			max = Math.max(max, cnt[c-'A']);
		}
		
		char ans = '?';
		for(int i=0; i<26; i++) {
			if(cnt[i]==max) {
				if(ans=='?') {
					ans = (char)('A'+i);
				}
				else {
					System.out.println("?");
					return;
				}
			}
		}
		
		System.out.println(ans);
	}

}
