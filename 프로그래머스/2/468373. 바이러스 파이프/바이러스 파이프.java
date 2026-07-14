// 닫을 파이프를 k번 고르는 경우의 수를 시뮬레이션해 최대값 찾기
// O(3^k) 정도라 괜찮을 것 같음
import java.io.*;
import java.util.*;

class Solution {
    ArrayList<Integer>[] A, B, C;
    int max=0;
    public int solution(int n, int infection, int[][] edges, int k) {
        int answer = 0;
        
        A = new ArrayList[n+1];
        B = new ArrayList[n+1];
        C = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            A[i] = new ArrayList<>();
            B[i] = new ArrayList<>();
            C[i] = new ArrayList<>();
        }
        
        for(int[] e : edges){
            if(e[2]==1) {
                A[e[0]].add(e[1]);
                A[e[1]].add(e[0]);
            }
            else if(e[2]==2) {
                B[e[1]].add(e[0]);
                B[e[0]].add(e[1]);
            }
            else {
                C[e[1]].add(e[0]);
                C[e[0]].add(e[1]);
            }
        }
        
        Deque<Integer> seq = new LinkedList<>();
        simulate(seq, infection, k);
        
        return max;
    }
    
    
    public void simulate(Deque<Integer> seq, int inf, int k){
        if(seq.size()==k){
            
            // seq에 따라 시뮬레이션해보기
            ArrayList<Integer> infected = new ArrayList<>();
            infected.add(inf);
            
            Deque<Integer> cop = new LinkedList<>(seq);
            while(!cop.isEmpty()){
                int s = cop.poll();
                if(s==1){
                    for(int idx=0; idx<infected.size(); idx++){ // 감염된 노드들 하나씩 확인
                        // infected.get(idx) 노드와 연결된 노드들 확인
                        int cur = infected.get(idx);
                        for(int next : A[cur]){
                            // next가 이미 감염된 노드면 패스
                            if(infected.contains(next)) continue;
                            // System.out.println("A 통로로 연결된 노드 : "+next);
                            infected.add(next);
                        }
                    }
                }
                else if(s==2){
                    for(int idx=0; idx<infected.size(); idx++){ // 감염된 노드들 하나씩 확인
                        // infected.get(idx) 노드와 연결된 노드들 확인
                        int cur = infected.get(idx);
                        for(int next : B[cur]){
                            // next가 이미 감염된 노드면 패스
                            if(infected.contains(next)) continue;
                            // System.out.println("B 통로로 연결된 노드 : "+next);
                            infected.add(next);
                        }
                    }
                }
                else {
                    for(int idx=0; idx<infected.size(); idx++){ // 감염된 노드들 하나씩 확인
                        // infected.get(idx) 노드와 연결된 노드들 확인
                        int cur = infected.get(idx);
                        for(int next : C[cur]){
                            // next가 이미 감염된 노드면 패스
                            if(infected.contains(next)) continue;
                            // System.out.println("C 통로로 연결된 노드 : "+next);
                            infected.add(next);
                        }
                    }
                }
            }
            System.out.println(infected.size());
            max = Math.max(max, infected.size());            
            return;
        }
        
        for(int t=1; t<=3; t++){
            seq.add(t);
            simulate(seq, inf, k);
            seq.pollLast();
        }
    }
}