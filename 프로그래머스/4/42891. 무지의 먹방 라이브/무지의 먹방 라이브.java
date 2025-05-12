import java.util.*;

class Solution {
    class Food{
        int num, time;
        
        Food(int n, int t){
            this.num = n;
            this.time = t;
        }
    }
    public int solution(int[] food_times, long k) {
        int answer = -1;
        
        int len = food_times.length;
        long total = 0;
        PriorityQueue<Food> pq = new PriorityQueue<>((o1,o2)->o1.time-o2.time);
        for(int i=0; i<len; i++){
            pq.add(new Food(i+1, food_times[i]));
            total += food_times[i]; // 모두 먹는데 걸리는 시간
        }
        
        if(total<=k) return -1; // k시간동안 다 먹지도 못하면 그냥 끝
        
        long time = k;
        long prev = 0;
        long cnt = len;
        while(time >= (pq.peek().time-prev)*cnt){
            int now = pq.poll().time;
            time -= (now-prev)*cnt;
            prev = now;
            cnt--;
        }
        // long time = 0;
        // long prev = 0;
        // int cnt = len;
        // while(time+(pq.peek().time-prev)*cnt <= k){
        //     int now = pq.poll().time;
        //     time += (now-prev)*cnt;
        //     prev = now;
        //     cnt--;
        // }
        
        ArrayList<Food> arr = new ArrayList<>();
        while(!pq.isEmpty()){
            arr.add(pq.poll());
        }
        
        arr.sort(Comparator.comparing(f->f.num));
        
        return arr.get((int)(time%cnt)).num;
    }
}