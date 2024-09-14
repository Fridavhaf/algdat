import java.util.Arrays;

// Definerer et interface "Test" med én metode, "test", som tar inn et tegn og returnerer en boolsk verdi.
// Dette lar oss lage forskjellige tester (f.eks. sjekke om et tegn er en vokal, stor bokstav, etc.).
interface Test {
    boolean test(char c); // Dette betyr at vi kan implementere "test"-metoden på forskjellige måter i ulike klasser.
}

// Klasse som implementerer Test for å sjekke om et tegn er en vokal.
class ErVokal implements Test {
    public boolean test(char c) {
        // Array med både store og små norske vokaler.
        char[] vokaler = {'A', 'E', 'I', 'O', 'U', 'Y', 'Æ', 'Ø', 'Å', 'a', 'e', 'i', 'o', 'u', 'y', 'æ', 'ø', 'å'};

        // Går gjennom hver vokal i arrayet og sammenligner med tegnet "c".
        // Hvis c er en vokal, returnerer vi true.
        for (int i = 0; i < vokaler.length; i++) {
            if (c == vokaler[i]) return true; // Hvis vi finner vokalen, returneres true.
        }
        return false; // Hvis tegnet ikke er en vokal, returnerer vi false.
    }
}

// Klasse som implementerer Test for å sjekke om et tegn er en stor bokstav.
class ErStorBokstav implements Test {
    public boolean test(char c) {
        // Sjekker om ASCII-verdien til tegnet er mellom 65 og 90 (A-Z) eller
        // om det er en av de norske spesialtegnene (Å, Æ, Ø).
        return ((65 <= (int) c && (int) c <= 90) || (int) c == 197 || (int) c == 198 || (int) c == 216);
    }
}

// Klasse som implementerer Test for å sjekke om tegnet er 'a' eller 'A'.
class ErA implements Test {
    public boolean test(char c) {
        // Sjekker om tegnet er 'a' eller 'A' (store eller små bokstaver).
        return (c == 'a') || (c == 'A');
    }
}

// Hovedklassen der partisjoneringslogikken ligger.
public class Partisjonering_09_09 {
    public static void main(String[] args) {// Array med store og små bokstaver
        char[] bokstaver = {'A', 'c', 'D', 'e', 'H', 'i', 'k', 'O', 'i', 'r', 'u', 'z'};
        // Sorterer bokstavene ved å flytte store bokstaver først.
        int skille = partisjoner(bokstaver, new ErStorBokstav());
        System.out.println(Arrays.toString(bokstaver));  // Skriver ut arrayet etter partisjonering.
        System.out.println("Skillepunkt for store bokstaver: " + skille);  // Skriver ut indeksen der partisjoneringen deler arrayet.

        char[] sorterAerFørst = {'a', 'A', 'e', 'k', 'a', 'V', 'A', 'j'};
        // Bruk av lambda-funksjon for å unngå eksplisitt klasseimplementering av Test interfacet
        partisjoner(sorterAerFørst, c -> (c == 'a' || c == 'A')); // returnerer true hvis "c" er 'a' eller 'A'.
        System.out.println(Arrays.toString(sorterAerFørst));  // Skriver ut resultatet etter partisjonering.

        // Partisjonerer bokstav-arrayet etter vokaler ved bruk av en metode-referanse "erVokal".
        partisjoner(bokstaver, Partisjonering_09_09::erVokal);
        System.out.println(Arrays.toString(bokstaver));  // Skriver ut arrayet etter partisjonering av vokaler.

        // Bruker ErVokal-klassen for å partisjonere bokstav-arrayet basert på vokaler.
        partisjoner(bokstaver, new ErVokal());
        System.out.println(Arrays.toString(bokstaver));  // Skriver ut arrayet etter partisjonering.

    }

    // Partisjoneringsmetode som sorterer et tegnarray basert på en Test-implementasjon.
    // Test-implementasjonen kan være "ErVokal", "ErStorBokstav", eller en lambda-funksjon som sjekker for eksempel 'a'.
    public static int partisjoner(char[] tabell, Test t) {
        int v = 0; // Startindeks (venstre ende).
        int h = tabell.length - 1; // Sluttindeks (høyre ende).

        // Så lenge venstre indeks ikke har passert høyre indeks:
        while (true) {
            // Flytt venstre indeks til høyre så lenge test-metoden returnerer true.
            // Dette betyr at vi flytter forbi elementer som oppfyller test-kriteriet.
            while (v <= h && t.test(tabell[v])) v++;

            // Flytt høyre indeks til venstre så lenge test-metoden returnerer false.
            // Dette betyr at vi ser etter elementer som ikke oppfyller test-kriteriet.
            while (v <= h && !t.test(tabell[h])) h--;

            // Hvis venstre indeks har passert høyre, bryt ut av løkken.
            if (v > h) break;

            // Hvis vi har funnet elementer som ikke oppfyller kriteriet til venstre og elementer som oppfyller kriteriet til høyre,
            // bytter vi dem plass.
            bytt(tabell, v, h);
        }
        // Returnerer indeksen der partisjoneringen skjer (venstre indeks).
        return v;
    }

    // Metode for å bytte to elementer i et tegnarray.
    public static void bytt(char[] tabell, int i, int j) {
        char tmp = tabell[i];
        tabell[i] = tabell[j];
        tabell[j] = tmp;
    }

    // En metode som sjekker om et tegn er en vokal (store eller små).
    public static boolean erVokal(char c) {
        // Array med alle norske vokaler, både store og små.
        char[] vokaler = {'A', 'E', 'I', 'O', 'U', 'Y', 'Æ', 'Ø', 'Å', 'a', 'e', 'i', 'o', 'u', 'y', 'æ', 'ø', 'å'};

        // Går gjennom arrayet og sjekker om tegnet er en av vokalene.
        for (int i = 0; i < vokaler.length; i++) {
            if (c == vokaler[i]) return true;
        }
        return false; // Hvis ikke tegnet er en vokal, returneres false.
    }

    // En metode som sjekker om et tegn er en stor bokstav (A-Z, Å, Æ, Ø).
    public static boolean erStor(char c) {
        // Bruker ASCII-koder for å sjekke om tegnet er en stor bokstav eller en av de norske spesialtegnene.
        return ((65 <= (int) c && (int) c <= 90) || (int) c == 197 || (int) c == 198 || (int) c == 216);
    }
}
