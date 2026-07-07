import java.io.*;
import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        
        String[] words = message.split(" ");
        
        HashSet<String> visibleWords = new HashSet<>();
        Queue<String> spoilerWords = new LinkedList<>();
        
        int p1 = 0; // 현재 단어의 첫 idx
        int p2 = 0; // 현재 단어의 마지막 idx
        int p3 = 0; // 현재 확인 중인 범위의 idx
        for(int i=0; i<words.length; i++){
            p2 = message.indexOf(words[i], p1) + words[i].length()-1; // 이번 단어를 읽은 후의 idx
            
            while(((p3+1)<spoiler_ranges.length) && (p2>=spoiler_ranges[p3+1][0])){
                    p3++;
            }
            
            int[] range = spoiler_ranges[p3];
            // 단어가 범위에 포함되지 않을 때는 단어를 저장하고 다음 단어 탐색 
            if(p2<range[0] || p1>range[1]){
                visibleWords.add(words[i]);
            }
            else{
                // 이번 단어가 범위에 포함되어 있다면
                // 다음 범위에도 이번 단어가 포함되어 있는지 확인
                while(((p3+1)<spoiler_ranges.length) && (p2>=spoiler_ranges[p3+1][0])){
                    p3++; // p3 : 단어가 포함된 가장 마지막 스포 방지 구간
                }
                
                spoilerWords.add(words[i]);
                
                if(p2>=range[1] && ((p3+1)<spoiler_ranges.length)) p3++;
            }
            p1 = p2+2;
        }
        
        
        // 이제 spoiler 단어를 꺼내면서 기존 word에 없으면 count
        while(!spoilerWords.isEmpty()){
            String word = spoilerWords.poll();
            if(!visibleWords.contains(word)){
                answer++;
                visibleWords.add(word);
            }
        }
        
        return answer;
    }
}