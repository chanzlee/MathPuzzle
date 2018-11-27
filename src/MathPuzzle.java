import java.util.Scanner;

public class MathPuzzle {
    public static void main(String[] args) {
//        BaseConversion();
//        System.out.println(BaseConversionRecursive());
//        ReverseBaseConversion();
//        System.out.println(PrimeNumber());
        SieveOfEratosthenes(100);
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

    //11005
    //N is 10 base number. Convert it to B base number.(2<=B<=36)
    // If 1 digit gets bigger than 9, use alphabet like
    // A: 10, B:11, ... ,Z: 35
    public static void BaseConversion() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int b = sc.nextInt();

        String answer="";

        while(n > 0) {
            if(n%b < 10) {
                answer = (n%b) + answer;
                n = n/b;
            } else {
                int temp = (n%b) + 55;
                answer =  (char) temp + answer;
                n = n/b;
            }
        }
        System.out.println(answer);
    }

    public static String BaseConversionRecursive() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int b = sc.nextInt();
        String answer ="";
        return BaseConversionRecursive(n,b,answer);
    }

    public static String BaseConversionRecursive(int n, int b, String answer) {
        if (n == 0 ) return answer;
        if (n%b <10) {
            answer = (n%b) + answer;
        } else {
            int temp = n%b + 55;
            answer = (char) temp + answer;
        }
        return BaseConversionRecursive(n/b,b,answer);
    }

    //2745
    //Convert B based number N into 10 base number. (returned number doesn't exceed 100,000,000)
    public static void ReverseBaseConversion () {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        int b = sc.nextInt();

        char[] arr = n.toCharArray();
        int size = arr.length;
        int answer =0;

        for (int i=0; i<size; i++) {

            answer*=b;
            if((int) arr[i]-48 >= 0 && (int) arr[i]-48 <= 9) {
                //'1' is 49
                answer += (arr[i] -48);
            } else {
                //'A" is 65; ( offset 65 - 10)
                answer += ((int) arr[i] - 55);
            }
        }

        System.out.println(answer);
    }

    // Note that conversion between bases other than 10 can be mitigated by 10base conversion.
    public static boolean PrimeNumber() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n < 2) return false;

        // N = a*b, (if not prime number)
        // Smallest possible a is 2,
        // thus biggest possible b is n/2


        // but, this is inefficient compare to root(n).
        // If a> root(n) && b> root(n), a*b > n, so a , b are not the right combination.
        // There can be a error term if real number is used for root(n).
        // So, powering by 2 on both LHS and RHS will result below limit condition.

        for (int i=2; i*i<=n; i++) {
            if (n%i==0 ) {
                return false;
            }
        }
        return true;
    }

    public static void SieveOfEratosthenes(int n) {
        int[] numbers = new int[n];
        boolean[] crossed = new boolean[n+1];

        int primeNumberCnt = 0;

        for (int i= 2; i<= n; i++){
            if(crossed[i]==false) {
                numbers[primeNumberCnt++] = i;

                // j less than i*i was already crossed out from previous i loop.
                for ( int j = i*i; j<=n; j+=i) {
                    crossed[j] = true;
                }
            }
        }

        for(int i=0;i<n;i++) {
            if (numbers[i]<=0) break;
            System.out.println(numbers[i]);
        }

        // O(n) *  (n/2 + n/3 + ... + n/n  <= loglogN)
        // = O(n loglogN), very close to O(n), lot faster than previous O(n root(n)) solution.
        // real implementation involves setting i*i as i+i to prevent overflow.
    }
}
