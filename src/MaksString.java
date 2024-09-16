public class MaksString {
    public static void main(String[] args) {
        String[] strenger = {"hallo", "hei", "alle"};
        int største = maks(strenger);
        System.out.println(største);
    }

    public static int maks (String[] a){
        int m = 0; //Antar at største har indeks 0
        String maksverdi = a[0];
        for (int i = 1; i < a.length; i++){
            if (a[i].compareTo(maksverdi) > 0){
                maksverdi = a[i];
                m = i;
            }
        }
        return m;
    }
}
