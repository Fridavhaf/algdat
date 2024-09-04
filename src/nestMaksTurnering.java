public class nestMaksTurnering {

    public static void main(String[] args) {
        int[] a = {3, 5, 8, 7, 10, 4};
        int[] verdier = nestMaks(a);
        System.out.println("Indeksen til den største verdien er " + verdier[0]);
        System.out.println("Indeksen til den nest største verdien er " + verdier[1]);
    }

    public static int[] nestMaks(int[] a) {
        int n = a.length;  // Lengden på tabellen a

        if (n < 2) {
            throw new IllegalArgumentException("a.length(" + n + ") < 2!");
        }

        int[] b = new int[2 * n];  // Turneringstreet som bruker 2 * n plasser
        int[] indeks = new int[2 * n];  // Array for å holde styr på indeksene

        // Kopierer verdiene fra a til den nederste delen av turneringstreet
        for (int i = 0; i < n; i++) {
            b[n + i] = a[i];  // Verdier fra a kopieres til b fra posisjon n
            indeks[n + i] = i;  // Lagrer den opprinnelige indeksen
        }

        // Bygger turneringstreet
        for (int k = 2 * n - 2; k > 1; k -= 2) {
            if (b[k] > b[k + 1]) {
                b[k / 2] = b[k];
                indeks[k / 2] = indeks[k];  // Oppdaterer indeksen til vinneren
            } else {
                b[k / 2] = b[k + 1];
                indeks[k / 2] = indeks[k + 1];  // Oppdaterer indeksen til vinneren
            }
        }

        int maksindeks = indeks[1];  // Indeksen til den største verdien
        int nestmaksverdi = Integer.MIN_VALUE;
        int nestmaksindeks = -1;

        // Finner den nest største verdien ved å sammenligne med taperne på veien
        int k = 1; // Start på rotknuten
        while (k < n) {
            int venstreBarn = 2 * k;
            int høyreBarn = 2 * k + 1;

            if (b[venstreBarn] == b[k]) {
                // Maksverdien er i venstre barn, så vi sammenligner høyre barn med nest maks
                if (b[høyreBarn] > nestmaksverdi) {
                    nestmaksverdi = b[høyreBarn];
                    nestmaksindeks = indeks[høyreBarn];
                }
                k = venstreBarn; // Gå nedover i venstre subtre
            } else {
                // Maksverdien er i høyre barn, så vi sammenligner venstre barn med nest maks
                if (b[venstreBarn] > nestmaksverdi) {
                    nestmaksverdi = b[venstreBarn];
                    nestmaksindeks = indeks[venstreBarn];
                }
                k = høyreBarn; // Gå nedover i høyre subtre
            }
        }

        return new int[] {maksindeks, nestmaksindeks};  // Returnerer indeksene
    }
}
