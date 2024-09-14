import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class Tabell {

    // ------------------------------
    // Constructor (private to prevent instantiation)
    // ------------------------------
    private Tabell() {}

    // ------------------------------
    // Main Method (Test Cases)
    // ------------------------------
    public static void main(String[] args) {
//        int[] a = Tabell.randPerm(15);
//        Tabell.flettesortering(a);
//        System.out.println(Arrays.toString(a));
        testTidsforbruk();
    }

    // ------------------------------
    // Hovedmetode for å teste tidsforbruk
    // ------------------------------
    public static void testTidsforbruk() {
        int[] array1 = genererTilfeldigArray(1000_000); // Generer et array med 10 000 elementer
        int[] array2 = array1.clone();
        int[] array3 = array1.clone();

        // Mål tidsforbruk for kvikksortering
        long startTime = System.nanoTime();
        kvikksortering(array1);
        long endTime = System.nanoTime();
        System.out.println("Kvikksortering: " + (endTime - startTime) + " nanosekunder");

        // Mål tidsforbruk for innsettingssortering
        startTime = System.nanoTime();
        innsettingssortering(array2);
        endTime = System.nanoTime();
        System.out.println("Innsettingssortering: " + (endTime - startTime) + " nanosekunder");

        // Mål tidsforbruk for Arrays.sort()
        startTime = System.nanoTime();
        Arrays.sort(array3);
        endTime = System.nanoTime();
        System.out.println("Arrays.sort(): " + (endTime - startTime) + " nanosekunder");
    }

    // Generer et tilfeldig array med n elementer
    private static int[] genererTilfeldigArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * 10000); // Tilfeldige tall mellom 0 og 9999
        }
        return array;
    }


    // ------------------------------
    // Partisjonering og kvikksortering
    // ------------------------------
    private static void kvikksortering0(int[] a, int v, int h)  // en privat metode
    {
        if (v >= h) return;  // a[v:h] er tomt eller har maks ett element
        int k = sParter0(a, v, h, (v + h)/2);  // bruker midtverdien
        kvikksortering0(a, v, k - 1);     // sorterer intervallet a[v:k-1]
        kvikksortering0(a, k + 1, h);     // sorterer intervallet a[k+1:h]
    }

    public static void kvikksortering(int[] a, int fra, int til) // a[fra:til>
    {
        fratilKontroll(a.length, fra, til);  // sjekker når metoden er offentlig
        kvikksortering0(a, fra, til - 1);  // v = fra, h = til - 1
    }

    public static void kvikksortering(int[] a)   // sorterer hele tabellen
    {
        kvikksortering0(a, 0, a.length - 1);
    }

    //Kode som får skilleverdien inn på riktig plass
    private static int sParter0(int[] a, int v, int h, int indeks)
    {
        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter0(a, v, h - 1, a[h]);  // partisjonerer a[v:h - 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien
    }

    // intervallet a[fra:til>
    public static int parter(int[] a, int fra, int til, int skilleverdi)
    {
        fratilKontroll(a.length, fra, til);
        return parter0(a, fra, til - 1, skilleverdi);
    }

    public static int parter(int[] a, int skilleverdi)  // hele tabellen
    {
        return parter0(a, 0, a.length - 1, skilleverdi);
    }


    //a[fra:til]
    private static int parter0(int[] a, int v, int h, int skilleverdi)
    {
        while (true)                                  // stopper når v > h
        {
            while (v <= h && a[v] < skilleverdi) v++;   // h er stoppverdi for v
            while (v <= h && a[h] >= skilleverdi) h--;  // v er stoppverdi for h

            if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }

    // ------------------------------
    // Flettemetoder
    // ------------------------------

    // fletter sammen to heltallstabeller
    public static int[] enkelFletting(int[] a, int[] b)
    {
        int[] c = new int[a.length + b.length];  // en tabell av rett størrelse
        int i = 0, j = 0, k = 0;                 // løkkevariabler

        while (i < a.length && j < b.length)
        {
            c[k++] = a[i++];      // først en verdi fra a
            c[k++] = b[j++];      // så en verdi fra b
        }
        // vi må ta med resten
        while (i < a.length) c[k++] = a[i++];
        while (j < b.length) c[k++] = b[j++];

        return c;
    } // Programkode 1.3.11 a)


    // fletting av to halvåpne intervaller
    public static int flett(int[] a, int m, int[] b, int n, int[] c) {
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) c[k++] = a[i] <= b[j] ? a[i++] : b[j++];
        while (i < m) c[k++] = a[i++];  // tar med resten av a
        while (j < n) c[k++] = b[j++];  // tar med resten av b
        return k;  // antall verdier lagt inn i c
    } // Programkode 1.3.11 c)

    // Flg. metode fletter sammen to hele tabeller ved hjelp av flett() (koden ovenfor):
    public static int flett(int[] a, int[] b, int[] c) {
        return flett(a, a.length, b, b.length, c);
    } // Programkode 1.3.11 d)


    // Flg. private hjelpemetode fletter b[0:n> og a[m:til> over i a[fra:til>:
    // Det vil si: vi har et array, og vi kopierer halve inn i et tomt array.
    // Det tomme arrayet må minst ha plass til n verdier der n = m – fra:
    private static void flett(int[] a, int[] b, int fra, int m, int til)
    {
        int n = m - fra;                // antall elementer i a[fra:m>
        System.arraycopy(a,fra,b,0,n);  // kopierer a[fra:m> over i b[0:n>

        int i = 0, j = m, k = fra;      // løkkevariabler og indekser

        while (i < n && j < til)        // fletter b[0:n> og a[m:til> og
        {                               // legger resultatet i a[fra:til>
            a[k++] = b[i] <= a[j] ? b[i++] : a[j++];
        }

        while (i < n) a[k++] = b[i++];  // tar med resten av b[0:n>
    } // Programkode 1.3.11 f)


    // Flg. rekursive (og private) metode benytter flett-metoden i Programkode 1.3.11 f):
    private static void flettesortering(int[] a, int[] b, int fra, int til)
    {
        if (til - fra <= 1) return;   // a[fra:til> har maks ett element
        int m = (fra + til)/2;        // midt mellom fra og til

        flettesortering(a,b,fra,m);   // sorterer a[fra:m>
        flettesortering(a,b,m,til);   // sorterer a[m:til>

        if (a[m-1] > a[m]) flett(a,b,fra,m,til);  // fletter a[fra:m> og a[m:til>
    } // Programkode 1.3.11 g)

    //Flg. offentlige metode sorterer en hel tabell:
    public static void flettesortering(int[] a)
    {
        int[] b = Arrays.copyOf(a, a.length/2);   // en hjelpetabell for flettingen
        flettesortering(a,b,0,a.length);          // kaller metoden over
    } // Programkode 1.3.11 h)




    // ------------------------------
    // Søkmetoder
    // ------------------------------
    public static int lineærsøk(int[] a, int fra, int til, int verdi) {
        fratilKontroll(a.length, fra, til);
        if (fra == til || verdi > a[til - 1]) return -(til + 1);  // verdi er større enn den største
        int i = 0;
        for (; a[i] < verdi; i++);  // siste verdi er vaktpost
        return verdi == a[i] ? i : -(i + 1);
    }

    public static int lineærsøk(int[] a, int verdi) {
        if (a.length == 0 || verdi > a[a.length - 1]) return -(a.length + 1);  // verdi er større enn den største
        int i = 0;
        for (; a[i] < verdi; i++);
        return verdi == a[i] ? i : -(i + 1);
    }

    public static int usortertsøk(int[] a, int verdi) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == verdi) return i;
        }
        return -1;
    }

    // ------------------------------
    // Kontrollmetoder
    // ------------------------------
    public static void fratilKontroll(int tablengde, int fra, int til) {
        if (fra < 0) throw new ArrayIndexOutOfBoundsException("fra(" + fra + ") er negativ!");
        if (til > tablengde) throw new ArrayIndexOutOfBoundsException("til(" + til + ") > tablengde(" + tablengde + ")");
        if (fra > til) throw new IllegalArgumentException("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    // ------------------------------
    // Byttemetoder
    // ------------------------------
    public static void bytt(char[] a, int i, int j) {
        char temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    // Lik Nicolai sin
    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // ------------------------------
    // RandPerm Metoder
    // ------------------------------
    public static int[] randPerm(int n) {
        Random r = new Random();
        int[] a = new int[n];
        Arrays.setAll(a, i -> i + 1);
        for (int k = n - 1; k > 0; k--) {
            int i = r.nextInt(k + 1);
            bytt(a, k, i);
        }
        return a;
    }
    // Nicolai sin metode:
    public static int[] randPerm2(int n){
        Random r = new Random();
        int[] tabell = new int[n];
        for (int i = 0 ; i < n ; i++){
            tabell[i] = i + 1;  // verdiene blir satt til 1, 2, 3, 4, ..., n
        }
        for (int k = n-1 ; k > 0 ; --k){
            int i = r.nextInt(k + 1);
            bytt(tabell, k, i);
        }
        return tabell;
    }

    public static void randPerm(int[] a) {
        Random r = new Random();
        for (int k = a.length - 1; k > 0; k--) {
            int i = r.nextInt(k + 1);
            bytt(a, k, i);
        }
    }

    // ------------------------------
    // Maks og Min Metoder
    // ------------------------------
    public static int maks(int[] a) {
        return maks(a, 0, a.length);
    }

    public static int maks(int[] a, int fra, int til) {
        if (fra < 0 || til > a.length || fra >= til) throw new IllegalArgumentException("Illegalt intervall!");
        int m = fra;
        int maksverdi = a[fra];
        for (int i = fra + 1; i < til; i++) {
            if (a[i] > maksverdi) {
                m = i;
                maksverdi = a[m];
            }
        }
        return m;
    }

    public static int min(int[] a) {
        return min(a, 0, a.length);
    }

    public static int min(int[] a, int fra, int til) {
        if (fra < 0 || til > a.length || fra >= til) throw new IllegalArgumentException("Illegalt intervall!");
        int m = fra;
        int minverdi = a[fra];
        for (int i = fra + 1; i < til; i++) {
            if (a[i] < minverdi) {
                m = i;
                minverdi = a[m];
            }
        }
        return m;
    }

    // ------------------------------
    // Nest Maks/Min Metoder
    // ------------------------------
    public static int[] nestMaks(int[] a) {
        int n = a.length;
        if (n < 2) throw new NoSuchElementException("a.length(" + n + ") < 2!");

        int m = 0, nm = 1;
        if (a[1] > a[0]) { m = 1; nm = 0; }

        int maksverdi = a[m], nestmaksverdi = a[nm];

        for (int i = 2; i < n; i++) {
            if (a[i] > nestmaksverdi) {
                if (a[i] > maksverdi) {
                    nm = m;
                    nestmaksverdi = maksverdi;
                    m = i;
                    maksverdi = a[m];
                } else {
                    nm = i;
                    nestmaksverdi = a[nm];
                }
            }
        }
        return new int[] {m, nm};
    }

    public static int[] nestMinst(int[] a) {
        int n = a.length;
        if (n < 2) throw new NoSuchElementException("Tabellen må ha minst to elementer, men har bare " + n);

        int m = 0, nm = 1;
        if (a[1] < a[0]) { m = 1; nm = 0; }

        int minverdi = a[m], nestminverdi = a[nm];

        for (int i = 2; i < n; i++) {
            if (a[i] < nestminverdi) {
                if (a[i] < minverdi) {
                    nm = m;
                    nestminverdi = minverdi;
                    m = i;
                    minverdi = a[m];
                } else {
                    nm = i;
                    nestminverdi = a[nm];
                }
            }
        }
        return new int[] {m, nm};
    }

    // ------------------------------
    // Inversjon og Sortering
    // ------------------------------
    public static int inversjoner(int[] a) {
        int antall = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) antall++;
            }
        }
        return antall;
    }

    public static boolean erSortert(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) return false;
        }
        return true;
    }

    public static int boble(int[] a) {
        int antall = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                bytt(a, i - 1, i);
                antall++;
            }
        }
        return antall;
    }

    public static void boblesortering(int[] a) {
        for (int n = a.length; n > 1; n--) {
            for (int i = 1; i < n; i++) {
                if (a[i - 1] > a[i]) bytt(a, i - 1, i);
            }
        }
    }
    public static void innsettingssortering(int[] a)
    {
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            int verdi = a[i], j = i - 1;      // verdi er et tabellelemnet, j er en indeks
            for (; j >= 0 && verdi < a[j]; j--) a[j+1] = a[j];  // sammenligner og flytter
            a[j + 1] = verdi;                 // j + 1 er rett sortert plass
        }
    }

}
