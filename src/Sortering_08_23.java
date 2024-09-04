import java.util.Arrays;

public class Sortering_08_23 {
    public static void main(String[] args) {
        // Opprett en array med noen testverdier
        int[] tabell = {1, 3, 2, 7, 5, 0, 4, 4, 2, -3};

        System.out.println("Den usorterte tabellen:");
        // Finn indeksen til det største elementet i hele tabellen
        int maks = maksIndeks(tabell);

        System.out.println(Arrays.toString(tabell));
        // Skriv ut indeksen til det største elementet
        System.out.println("Indeksen til det største elementet: " + maks);
        System.out.println("Det største elementet: " + tabell[maks]);

        //Vi kaller sorter metoden og bruker den på tabellen vår
        sorter(tabell);

        System.out.println("\nDen sorterte tabellen ser slik ut: ");
        System.out.println(Arrays.toString(tabell));
        maks = maksIndeks(tabell);
        System.out.println("Det største elementet er fremdeles "+tabell[maks]);
        System.out.println("Men indeksen til det største tallet er nå " + maks);
    }

    //tar kun inn ett parameter
    public static int maksIndeks(int[] tabell){
        // Denne metoden bruker en annen metode, maksIndeksIntervall, til å finne det største elementet i et gitt intervall.
        // Her sendes hele tabellen som søkeområde (fra indeks 0 til tabellens lengde).
        return maksIndeksIntervall(tabell, 0, tabell.length);

    }

    // Metode som finner indeksen til det største elementet i et spesifikt intervall i tabellen
    public static int maksIndeksIntervall(int[] tabell, int fra, int til){
        //Antakelse om at tabellen ikke er tom og at fra er en gyldig indeks
        int storsteIndeks = fra;
        int storste = tabell[fra];

        for (int i = fra+1; i < til; i++){
            if (storste < tabell[i]){
                storsteIndeks = i;
                storste = tabell[i];
            }
        }
        return storsteIndeks;
    }

    //sorteringsmetode som bruker utvalgsortering
    public static void sorter (int[] tabell){

        // Gå gjennom tabellen baklengs, fra slutten til starten
        // Dette for å "plassere" det største gjenværende elementet på riktig plass i hver iterasjon
        for (int i = tabell.length; i > 0; i--){
            // Finn indeksen til det største elementet i det gjenværende usorterte området
            int maks = maksIndeksIntervall(tabell, 0, i);

            // Bytt det største elementet med det siste elementet i det usorterte området
            bytt(tabell, maks, i-1);
        }
    }

    // Metode for å bytte plass på to elementer i tabellen
    public static void bytt(int[] tabell, int i, int j){
        int tmp = tabell[i];
        tabell[i] = tabell[j];
        tabell[j] = tmp;
    }


}


