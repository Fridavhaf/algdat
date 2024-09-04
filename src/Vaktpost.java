public class Vaktpost{

    public static int maks(int[] a)   // versjon 3 av maks-metoden
    {
        if (a.length == 0) {
            throw new IllegalArgumentException("Arrayet er tomt");
        }
        int sist = a.length - 1; //siste posisjon i tabellen
        int m = 0; //indeks til største verdi
        int maksverdi = a[0]; //største verdi
        int temp = a[sist]; //tar vare på siste verdi
        a[sist] = 0x7fffffff; //legger tallet 2 147 483 647 til sist (største mulige int)

        for (int i = 0; ; i++) /*i starter med 0, Dette er en uendelig løkke,
        men den vil naturlig stoppe når vi når den siste indeksen i arrayet på grunn av vaktverdien.
            */

            if (a[i] >= maksverdi) //denne blir sann til slutt
            {
                if (i == sist) //sjekker om vi er ferdige
                {
                    a[sist] = temp; //legger siste verdi tilbake
                    return temp >= maksverdi ? sist : m; //er siste størst?
                    /*betingelse ? uttrykk1 : uttrykk2;
                    Hvis betingelse er sann, returneres uttrykk1.
                    Hvis betingelse er falsk, returneres uttrykk2.
                    return bryter en løkke selv om den er uendelig
                     */
                }
                else
                {
                    maksverdi = a[i]; //maksverdi oppdateres
                    m = i; //m oppdateres
                }
            }
    }

    public static void main(String[] args){
        int[] tabell = {};
        int maks = maks(tabell);
        System.out.println("Indeksen til det største tallet er "+maks);
    }
}
