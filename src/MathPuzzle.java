public class MathPuzzle {
    public static void main(String[] args) {
        System.out.println(LeastCommonMultiple(10, 15));
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

    public static int EuclideanAlgorithmRecursive (int a, int b) {
        if (b==0) {
            return a;
        } else {
            return EuclideanAlgorithmRecursive(b, a%b);
        }
    }

    public static int EuclideanAlgorithm (int a, int b) {
        while(b!=0) {
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }
    //2609
    // Cautious about the data size, as lcm is bigger than two numbers
    public static int LeastCommonMultiple(int a, int b) {
        // Using LG=AB equation
        return (a*b)/EuclideanAlgorithm(a,b);
    }
}
