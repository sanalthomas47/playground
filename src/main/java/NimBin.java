/**
 * Determines the winner of a Nim game using XOR (nimber) theory.
 * Alice wins when the XOR of all pile sizes is 0 or the number of piles is even;
 * otherwise Bob wins.
 */
public class NimBin {

    /**
     * Determines the winner of a Nim game.
     *
     * @param A array of pile sizes
     * @param n number of piles
     * @return {@code "Alice"} or {@code "Bob"}
     */
    static String findWinner(int A[], int n)
    {
        int res = 0;

        for (int i = 0; i < n; i++)
            res ^= A[i];

        // case when Alice is winner
        if (res == 0 || n % 2 == 0)
            return "Alice";

            // when Bob is winner
        else
            return "Bob";
    }

    //Driver code
    public static void main (String[] args)
    {
        int A[] = { 1, 4, 3, 5 };
        int n =A.length;

        System.out.print("Winner = "
                + findWinner(A, n));
    }
}
