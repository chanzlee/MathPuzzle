public class MathPuzzle {
    public static void main(String[] args) {
        System.out.println(GreatestCommonDivisor(10, 15));
    }

    public static int GreatestCommonDivisor(int a, int b) {

        int gcd = 1;

        for (int i=2; i<=Math.min(a,b); i++) {
            if (a % i ==0 && b % i ==0) {
                gcd = i;
            }
        }
        return gcd;
    }
}
