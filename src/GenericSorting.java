import java.util.Arrays;

public class GenericSorting {


    public static class Person implements Comparable<Person>{
        String first_name;
        String last_name;

        public Person(String first_name, String last_name){
            this.first_name = first_name;
            this.last_name = last_name;
        }

        public int compareTo(Person other){
            int last_compare = this.last_name.compareTo(other.last_name);
            if(last_compare == 0){
                return this.first_name.compareTo(other.first_name);
            }
            else{
                return last_compare;
            }

        }
        public String toString(){
            return first_name + " " + last_name;
        }


    }
    public static void main(String[] args) {
        Person[] d = {new Person("Petter", "Pettersen"), new Person("Kari", "Pettersen"), new Person("Nils", "Abrahamsen"),
                new Person("Tor", "Toresen")};
        int person_max_index = maks(d, 0, d.length);
        System.out.println("Siste person, leksiografisk, er " + d[person_max_index]);

        System.out.println("Sorterer personer");
        System.out.println("Før: " + Arrays.toString(d));
        sort(d);
        System.out.println("Etter: " + Arrays.toString(d));
    }
    static <T extends Comparable<? super T>> void sort (T[] values){
        // Går gjennom alle elementene i arrayet unntatt det siste, siden det siste vil være sortert etter de andre er på plass.
        for (int i = 0; i<values.length-1; ++i){
            //Finn største element i intervallet [i, values.length]. Betyr at vi må sette begin og end på maks-metoden
            int max_index = maks(values, i, values.length);

            //Bytt største element til posisjon i
            T temp = values[i];
            values[i] = values[max_index];
            values[max_index] = temp;

            /*
            Eksempel på de tre linjekodene ovenfor:
            Se for deg at du har dette arrayet: [5, 1, 9, 3]
            Hvis vi er i første iterasjon av løkken (i = 0), vil vi finne at det største elementet er 9, som ligger på indeks 2.

                Første trinn: temp = values[0] lagrer 5 i temp.
                Andre trinn: values[0] = values[2] setter 9 på plass 0, så arrayet blir [9, 1, 9, 3].
                Tredje trinn: values[2] = temp setter 5 på plass 2, så arrayet blir [9, 1, 5, 3].

             */
        }
    }

    static <T extends Comparable<? super T>> int maks(T[] values, int begin, int end){
        T current_max = values[begin];
        int current_index = begin;

        for (int i = begin+1; i<end; i++){
            //if(values[i] > current_max){ Kan ikke bruke basale sammenlkninger som < og > lenger.
            // Bruker compareTo fra Comparable Interfacet.
            if (values[i].compareTo(current_max) > 0){ //Hvis values[i] er større, returnerer den 1
                current_max = values[i];
                current_index = i;
            }
        }
        return current_index;
    }

}
