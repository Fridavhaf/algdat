public class Binærsøk {

    public static void main (String[] args){
        int[] a = {3,8,10,12,14,16,21,24,27,30,32,33,34,37,40};
        System.out.println(binærsøk1(a, 32));
        System.out.println(binærsøk2(a, 32));
        System.out.println(binærsøk3(a, 32));

        System.out.println(binærsøk1(a, 31));
        System.out.println(binærsøk2(a, 31));
        System.out.println(binærsøk3(a, 31));
    }

    public static int binærsøk1(int[] a, int verdi)  // søker i hele a
    {
        return binærsøk1(a,0,a.length,verdi);  // bruker metoden over
    }

    public static int binærsøk2(int[] a, int verdi)  // søker i hele a
    {
        return binærsøk2(a,0,a.length,verdi);  // bruker metoden over
    }
    public static int binærsøk3(int[] a, int verdi)  // søker i hele a
    {
        return binærsøk3(a,0,a.length,verdi);  // bruker metoden over
    }

    public static int binærsøk1 (int[] a, int fra, int til, int verdi){
        Tabell.fratilKontroll(a.length,fra,til);
        int v = fra, h = til - 1;  // v og h er intervallets endepunkter

        while (v <= h)    // fortsetter så lenge som a[v:h] ikke er tom
        {
            int m = (v + h)/2;      // heltallsdivisjon - finner midten
            int midtverdi = a[m];   // hjelpevariabel for midtverdien

            if (verdi == midtverdi) return m;          // funnet
            else if (verdi > midtverdi) v = m + 1;     // verdi i a[m+1:h]
            else  h = m - 1;                           // verdi i a[v:m-1]
        }

        return -(v + 1);    // ikke funnet, v er relativt innsettingspunkt
    }

    public static int binærsøk2 (int[] a, int fra, int til, int verdi){
        Tabell.fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)
        int v = fra, h = til - 1;    // v og h er intervallets endepunkter

        while (v <= h)  // fortsetter så lenge som a[v:h] ikke er tom
        {
            int m = (v + h)/2;     // heltallsdivisjon - finner midten
            int midtverdi = a[m];  // hjelpevariabel for  midtverdien

            if (verdi > midtverdi) v = m + 1;        // verdi i a[m+1:h]
            else if (verdi < midtverdi) h = m - 1;   // verdi i a[v:m-1]
            else return m;                           // funnet
        }

        return -(v + 1);   // ikke funnet, v er relativt innsettingspunkt

    }

    public static int binærsøk3(int[] a, int fra, int til, int verdi)
    {
        Tabell.fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)
        int v = fra, h = til - 1;  // v og h er intervallets endepunkter

        while (v < h)  // obs. må ha v < h her og ikke v <= h
        {
            int m = (v + h)/2;  // heltallsdivisjon - finner midten

            if (verdi > a[m]) v = m + 1;   // verdi må ligge i a[m+1:h]
            else  h = m;                   // verdi må ligge i a[v:m]
        }
        if (h < v || verdi < a[v]) return -(v + 1);  // ikke funnet
        else if (verdi == a[v]) return v;            // funnet
        else  return -(v + 2);                       // ikke funnet
    }
}
