import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class Tabell {
    // Private constructor to prevent instantiation
    private Tabell() {}

    // Main method - currently empty
    public static void main(String[] args) {

        //Test av å finne største

//        int[] a = Tabell.randPerm(20);              // en tilfeldig tabell
//        for (int k : a) System.out.print(k + " ");  // skriver ut a
//
//        int m = Tabell.maks(a);   // finner posisjonen til største verdi
//
//        System.out.println("\nStørste verdi ligger på plass " + m);


        //Test av nest største metoden:
//        int[] a = Tabell.randPerm(20); // tilfeldig permutasjon av 1 . . 20
//        int[] b = Tabell.nestMaks(a);  // metoden returnerer en tabell
//
//        int m = b[0], nm = b[1];       // m for maks, nm for nestmaks
//
//        for (int k : a) System.out.print(k + " ");
//        System.out.print("\nStørst(" + a[m] + ") har posisjon " + m);
//        System.out.println(", nest størst(" + a[nm] + ") har posisjon " + nm);

        //Test av å finne minst og nest minst
        int[] a = {4, 5, 1, 2, 9, 7, 3};  // Eksempel på en tabell
        int[] resultat = nestMinst(a);  // Kall på metoden

        for (int k : a) System.out.print (k + " ");
        System.out.println("\nMinste verdi er på indeks: " + resultat[0]);
        System.out.println("Nest minste verdi er på indeks: " + resultat[1]);

    }

    public static void bytt(char[] a, int i, int j)
    {
        char temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    // Method to swap elements at indices i and j in the array a
    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Method to generate a random permutation of numbers from 1 to n
    public static int[] randPerm(int n) {
        Random r = new Random(); // Random number generator
        int[] a = new int[n];    // Array to hold the permutation

        // Initialize array with values 1 to n
        Arrays.setAll(a, i -> i + 1);

        // Shuffle the array
        for (int k = n - 1; k > 0; k--) {
            int i = r.nextInt(k + 1); // Random index from 0 to k
            bytt(a, k, i);           // Swap elements
        }

        return a; // Return the shuffled array
    }

    // Method to shuffle the given array in place
    public static void randPerm(int[] a) {
        Random r = new Random(); // Random number generator

        // Shuffle the array
        for (int k = a.length - 1; k > 0; k--) {
            int i = r.nextInt(k + 1); // Random index from 0 to k
            bytt(a, k, i);           // Swap elements
        }
    }

    // Method to find the index of the maximum value in the entire array
    public static int maks(int[] a) {
        return maks(a, 0, a.length); // Delegate to the more general method
    }

    // Method to find the index of the maximum value in a specified subarray
    public static int maks(int[] a, int fra, int til) {
        // Validate indices
        if (fra < 0 || til > a.length || fra >= til) {
            throw new IllegalArgumentException("Illegalt intervall!");
        }

        // Initialize variables
        int m = fra;                // Index of the maximum value
        int maksverdi = a[fra];     // Maximum value

        // Iterate over the specified range
        for (int i = fra + 1; i < til; i++) {
            if (a[i] > maksverdi) {
                m = i;               // Update index of the maximum value
                maksverdi = a[m];    // Update the maximum value
            }
        }

        return m; // Return the index of the maximum value
    }

    // Method to find the index of the minimum value in the entire array
    public static int min(int[] a) {
        return min(a, 0, a.length); // Delegate to the more general method
    }

    // Method to find the index of the minimum value in a specified subarray
    public static int min(int[] a, int fra, int til) {
        // Validate indices
        if (fra < 0 || til > a.length || fra >= til) {
            throw new IllegalArgumentException("Illegalt intervall!");
        }

        // Initialize variables
        int m = fra;                // Index of the minimum value
        int minverdi = a[fra];     // Minimum value

        // Iterate over the specified range
        for (int i = fra + 1; i < til; i++) {
            if (a[i] < minverdi) {
                m = i;               // Update index of the minimum value
                minverdi = a[m];     // Update the minimum value
            }
        }

        return m; // Return the index of the minimum value
    }

    //Metode for å finne nest største:
    public static int[] nestMaks(int[] a) // ny versjon
    {
        int n = a.length;     // tabellens lengde
        if (n < 2) throw      // må ha minst to verdier
                new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int m = 0;      // m er posisjonen til største verdi
        int nm = 1;     // nm er posisjonen til nest største verdi

        // bytter om m og nm hvis a[1] er større enn a[0]
        if (a[1] > a[0]) { m = 1; nm = 0; }

        int maksverdi = a[m];                // største verdi
        int nestmaksverdi = a[nm];           // nest største verdi

        for (int i = 2; i < n; i++)
        {
            if (a[i] > nestmaksverdi)
            {
                if (a[i] > maksverdi)
                {
                    nm = m;
                    nestmaksverdi = maksverdi;     // ny nest størst

                    m = i;
                    maksverdi = a[m];              // ny størst
                }
                else
                {
                    nm = i;
                    nestmaksverdi = a[nm];         // ny nest størst
                }
            }
        } // for

        return new int[] {m,nm};    // n i posisjon 0, nm i posisjon 1

    } // nestMaks

    // Metode for å finne både minste og nest minste verdi i en tabell
    public static int[] nestMinst(int[] a) {
        int n = a.length;  // henter lengden på tabellen

        // Sjekk at tabellen har minst to elementer
        if (n < 2) {
            throw new NoSuchElementException("Tabellen må ha minst to elementer, men har bare " + n);
        }

        // Initialiserer m som posisjonen til minste verdi og nm som posisjonen til nest minste verdi
        int m = 0;
        int nm = 1;

        // Sjekk om det andre elementet er mindre enn det første, og bytt om m og nm hvis det er tilfelle
        if (a[1] < a[0]) {
            m = 1;
            nm = 0;
        }

        // Sett minverdi og nestminverdi til henholdsvis minste og nest minste verdi
        int minverdi = a[m];
        int nestminverdi = a[nm];

        // Gå gjennom resten av tabellen for å finne de eksakte posisjonene til de to minste verdiene
        for (int i = 2; i < n; i++) {
            if (a[i] < nestminverdi) {
                if (a[i] < minverdi) {
                    // Hvis a[i] er mindre enn minverdi, oppdater både minst og nest minst
                    nm = m;
                    nestminverdi = minverdi;  // nåværende minst blir den nye nest minst

                    m = i;
                    minverdi = a[m];  // a[i] blir den nye minst verdi
                } else {
                    // Hvis a[i] bare er mindre enn nestminverdi, oppdater kun nest minst
                    nm = i;
                    nestminverdi = a[nm];
                }
            }
        }

        // Returner posisjonene til minste (m) og nest minste (nm) verdier
        return new int[]{m, nm};
    }
}