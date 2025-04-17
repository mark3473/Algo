class Solution {
    
    public int solution(String[] board) {
        int answer = -1;
        // 선공 o, 후공 x
        // 1. O 갯수가 X 갯수와 같을때, O 빙고가 아니라면 성공
        // 2. O 갯수가 X 갯수보다 1개 많을때, X 빙고가 아니라면 성공
        // 3. 이외에는 모두 실패
        int[] di = {0,1,1,1}; // 오른쪽, 아래, 대각선(우측하단), 대각선(좌측하단)
        int[] dj = {1,0,1,-1};
        
        int first = 0;
        int last = 0;
        int Oflag = 0;
        int Xflag = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                char c = board[i].charAt(j);
                if(c=='.') continue;
                else if(c=='O') first+=1;
                else if(c=='X') last+=1;
                
                // 제일 윗줄이면
                if(i==0){
                    // 아래 확인
                    for(int d=1; d<=2; d++){
                        if(board[di[1]*d].charAt(j)!=c) break;
                        
                        if(d==2) {
                            if(c=='X') Xflag++;
                            else Oflag++;
                        }
                    }
                    if(j==0){
                        // 오른쪽 확인
                        for(int d=1; d<=2; d++){
                            if(board[0].charAt(dj[0]*d)!=c) break;

                            if(d==2) {
                                if(c=='X') Xflag++;
                                else Oflag++;
                            }
                        }
                        // 우대각 확인
                        for(int d=1; d<=2; d++){
                            if(board[di[2]*d].charAt(j+dj[2]*d)!=c) break;
                        
                            if(d==2) {
                                if(c=='X') Xflag++;
                                else Oflag++;
                            }
                        }
                    }
                    // 좌대각 확인
                    else if(j==2){
                        for(int d=1; d<=2; d++){
                            if(board[di[3]*d].charAt(j+dj[3]*d)!=c) break;
                        
                            if(d==2) {
                                if(c=='X') Xflag++;
                                else Oflag++;
                            }
                        }
                    }
                }
                else { //제일 윗줄 아닐 때
                    if(j==0){
                        // 오른쪽 확인
                        for(int d=1; d<=2; d++){
                            if(board[i].charAt(dj[0]*d)!=c) break;

                            if(d==2) {
                                if(c=='X') Xflag++;
                                else Oflag++;
                            };
                        }
                    }
                }
            }
        }
        // if(flag>=1){
        //     if((first==last && stone=='X') || (first==last+1 && stone=='O')) return 1;
        // }
        // else if(flag==0){
        //     if(first==last || first==last+1) return 1;
        // }
        
        if(first==last && Oflag==0) return 1;
        else if(first==last+1 && Xflag==0) return 1;
        else return 0;
    }
}