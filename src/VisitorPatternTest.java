import java.util.Arrays;

public class VisitorPatternTest {

    public static class Person{
        String first_name;
        String last_name;

        public Person(String first_name, String last_name){
            this.first_name = first_name;
            this.last_name = last_name;
        }

        public String toString(){
            return first_name + " " + last_name;
        }
    }

    @FunctionalInterface
    public interface Komparator<T>{
        int compare(T a, T b);
    }
    public static class DescendingPersonComparator implements Komparator<Person> {
        public int compare(Person a, Person b) {
            int last_compare = a.last_name.compareTo(b.last_name);
            if (last_compare == 0){
                return a.first_name.compareTo(b.first_name);
            }
            else{
                return last_compare;
            }
        }
    }

    public static class AscendingPersonComparator implements Komparator<Person> {
        public int compare(Person a, Person b) {
            int last_compare = b.last_name.compareTo(a.last_name);
            if (last_compare == 0){
                return b.first_name.compareTo(a.first_name);
            }
            else{
                return last_compare;
            }
        }
    }

    public static class OddePartallKomparator implements Komparator<Integer> {
        public int compare(Integer a, Integer b) {
            // Først sammenlikner vi "etternavnet" => partall eller oddetall
            // Begge er partall eller begge er et oddetall
            if (a % 2 ==  b % 2){
                return a.compareTo(b);
            }
            // Begge er oddetall
            else if (a % 2 == 1 && b % 2 == 1) {
                return a.compareTo(b);
            }
            // a er oddetall, b er partall
            else if (a % 2 > b % 2) {
                return 1;
            }
            // b er oddetall, a er partall

            else {
                return -1;
            }
        }
    }

    public static <T> int maks(T[] values, int begin, int end, Komparator<T> comp){
        T current_max = values[begin];
        int current_index = begin;

        for (int i = begin+1; i<end; i++) {
            //if(values[i] > current_max){ Kan ikke bruke basale sammenlkninger som < og > lenger.
            // Bruker compareTo fra Comparable Interfacet.
            if (comp.compare(values[i], current_max) > 0) { //Hvis values[i] er større, returnerer den 1
                current_max = values[i];
                current_index = i;
            }
        }
        return current_index;
    }

    public static <T> void sort(T[] values, Komparator<T> comp){
        // Går gjennom alle elementene i arrayet unntatt det siste, siden det siste vil være sortert etter de andre er på plass.
        for (int i = 0; i<values.length-1; ++i) {
            //Finn største element i intervallet [i, values.length]. Betyr at vi må sette begin og end på maks-metoden
            int max_index = maks(values, i, values.length, comp);

            //Bytt største element til posisjon i
            T temp = values[i];
            values[i] = values[max_index];
            values[max_index] = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
        Person[] persons = {new Person("Petter", "Pettersen"),
                new Person("Kari", "Pettersen"),
                new Person("Nils", "Abrahamsen"),
                new Person("Tor", "Toresen")};

        // 0 Lag comparator-objektet
        //vi kan nå kjøre comp.compare(a, b)
        AscendingPersonComparator comp_asc = new AscendingPersonComparator();
        DescendingPersonComparator comp_desc = new DescendingPersonComparator();

        // 1 Lage sorteringsfunksjon som tar inn både person-arrayet og en sammenlikningsfunksjon
        System.out.println("Før sortering: " + Arrays.toString(persons));
        System.out.println("Sorter!");
        sort(persons, comp_asc);
        System.out.println("Etter sortering (Ascending): " + Arrays.toString(persons));
        sort(persons, comp_desc);
        System.out.println("Etter sortering (Descending): " + Arrays.toString(persons));

        Integer[] integers = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        // vil sortere oddetall til venstre, og partall til høyre
        // partall og oddetall skal være inbyrdes sortert
        OddePartallKomparator odd_partall = new OddePartallKomparator();
        System.out.println("Før sortering: " + Arrays.toString(integers));
        sort(integers, odd_partall);
        System.out.println("Etter sortering: " + Arrays.toString(integers));
    }

}
