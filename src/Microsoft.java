import java.util.Arrays;

public class Microsoft {

    public static void main(String[] args) {
//        int[] start = {1, 0, 2, 4};
//        int[] dest = {2, 2, 0, 5};
//        int[] limit = {3, 17, 7, 4, 5, 17};
//        int result = solution(start, dest, limit);
//        System.out.println(result);

        int[] A = {2, 3, 3, 4, 1, 5};
        int result = solution(A);
        System.out.println(result);
    }
    static int solution(int[] A) {
        // Implement your solution here

        int removeDomino = 0;

        for (int i = 1 ; i < A.length-1; i +=2)
            if (A[i] != A[i+1]){
            removeDomino ++;
        }
        return removeDomino;
    }
//    static int solution(int[] start, int[] dest, int[] limit) {
//        // Implement your solution here
//
//        int boardingPrice = 1;
//        // Destination
//        int traveledStops = 0;
//
//        int largestStationNumber = dest[0];
//        for (int i = 0; i < start.length; i++){
//            traveledStops += (dest[i] - start[i]);
//            if (dest[i] > largestStationNumber){
//                largestStationNumber = dest[i];
//            }
//        }
//        traveledStops *= 2;
//        int totalLimit = limit[largestStationNumber];
//        System.out.println(totalLimit);
//        int total = boardingPrice + traveledStops;
//        if (total <= totalLimit){
//            return total;
//        }
//        return totalLimit;
//    }

}
