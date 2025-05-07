import java.util.*;
import java.io.*;

public class Main {
    static int r, c;
    static int page;
    static int N;
    static int Min;
    static ArrayList<Integer> columnList = new ArrayList<>(); // 구멍의 열을 담는 리스트
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        page = Integer.parseInt(br.readLine().trim());
        N = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            Min = Math.max(Min, y); // 이분탐색을 위한 최소값 (높이의 최대값)
            columnList.add(x);
        }
        Collections.sort(columnList); // 이분탐색을 위한 정렬
        binarySearch();
    }

    private static void binarySearch() {
        int low = Min;
        int high = 1000000; // 최대 높이 백만
        int mid;

        while (low < high) {
            mid = (low + high) / 2;

            int pageCnt = matching(mid); // mid값이 색종이의 넓이, 이 값으로 구멍을 가려본다

            if (pageCnt > page) // 만약 사용된 종이 개수가 원하는 개수보다 많은 경우 길이를 늘린다
                low = mid + 1;
            else // 사용된 종이 개수가 적거나 같다면 색종이 길이를 더 줄여 본다 (upper bound)
                high = mid; // 해당 값을 상한값으로 두고 더 낮은 값으로 더 진행
        }
        System.out.println(low); // 최종 색종이의 길이
    }

    private static int matching(int size) {
        // 선택된 넓이의 색종이로 구멍들을 전부 채워본다
        int start = columnList.get(0);
        int pageCount = 0;

        int i = 0;
        loop:
        while (true) {
            while (columnList.get(i) < start + size) { //색종이 범위 내에 있는 구멍은 다 제거
                i++;
                if (i >= columnList.size()) { // 마지막 구멍까지 다 넣었으면 색종이 개수 더해주고 종료
                    pageCount++;
                    break loop;
                }
            }
            start = columnList.get(i); // 다음 구멍의 위치를 시작위치로 갱신
            pageCount++;
        }

        return pageCount;
    }
}