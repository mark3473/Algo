class Solution {
    public long solution(int price, int money, int count) {
        // count번 타는데
        // 원래 이용료는 price원이고
        // 갖고 있는 금액은 money
        long sum = 0;
        for(long i=1; i<=count; i++){
            sum += i*price;
        }
        
        if(money>=sum) return 0;
        else return sum-money;
    }
}