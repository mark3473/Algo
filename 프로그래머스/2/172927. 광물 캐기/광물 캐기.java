import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int totalPicks = picks[0] + picks[1] + picks[2];
        int len = Math.min(totalPicks * 5, minerals.length);
        
        List<int[]> mineralChunks = new ArrayList<>();
        for (int i = 0; i < len; i += 5) {
            int dia = 0, iron = 0, stone = 0;
            for (int j = i; j < i + 5 && j < len; j++) {
                if (minerals[j].equals("diamond")) dia++;
                else if (minerals[j].equals("iron")) iron++;
                else stone++;
            }
            // [다이아 수, 철 수, 돌 수]
            mineralChunks.add(new int[]{dia, iron, stone});
        }

        // 피로도 많은 순으로 정렬 (비싼 광물 우선)
        mineralChunks.sort((a, b) -> {
            int aScore = a[0]*25 + a[1]*5 + a[2];
            int bScore = b[0]*25 + b[1]*5 + b[2];
            return bScore - aScore;
        });

        int fatigue = 0;
        int idx = 0;

        for (int pick = 0; pick < 3; pick++) {
            for (int i = 0; i < picks[pick]; i++) {
                if (idx >= mineralChunks.size()) return fatigue;

                int[] chunk = mineralChunks.get(idx++);
                if (pick == 0) { // 다이아 곡괭이
                    fatigue += chunk[0] + chunk[1] + chunk[2];
                } else if (pick == 1) { // 철 곡괭이
                    fatigue += chunk[0]*5 + chunk[1] + chunk[2];
                } else { // 돌 곡괭이
                    fatigue += chunk[0]*25 + chunk[1]*5 + chunk[2];
                }
            }
        }

        return fatigue;
    }
}
