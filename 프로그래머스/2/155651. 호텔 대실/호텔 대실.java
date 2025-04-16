class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[] howManyBooksInTime = new int[1440];
        for(String[] book : book_time){
            int start = toInt(book[0]);
            int end = Math.min(1439,toInt(book[1])+10);
            for(int i=start; i<end; i++){
                howManyBooksInTime[i]++;
            }
        }
        for(int i=0; i<1440; i++){
            answer = Math.max(answer,howManyBooksInTime[i]);
        }
        return answer;
    }

    public int toInt(String s){
        String[] ss = s.split(":");
        return Integer.parseInt(ss[0])*60 + Integer.parseInt(ss[1]);
    }
}