import java.util.Arrays;

public class VisitorPatternTest {

    public static class Person implements Comparable<Person>{
        String first_name;
        String last_name;

        public Person(String first_name, String last_name){
            this.first_name = first_name;
            this.last_name = last_name;
        }


        @Override
        public String toString(){
            return first_name + " " + last_name;
        }
    }

    @FunctionalInterface
    public interface Komparator<T>{
        int compare(T a, T b);
    }
    public static class AscendingPersonComparator implements Komparator<Person> {
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

    public static void main(String[] args) {
        System.out.println("Hello world");
        Person[] persons = {new Person("Petter", "Pettersen"),
                new Person("Kari", "Pettersen"),
                new Person("Nils", "Abrahamsen"),
                new Person("Tor", "Toresen")};

        // 0 Lag comparator-objektet
        //vi kan nå kjøre comp.compare(a, b)
        AscendingPersonComparator comp = new AscendingPersonComparator();

        // 1 Lage sorteringsfunksjon som tar inn både person-arrayet og en sammenlikningsfunksjon
        sort(persons, comparator)

        System.out.println(Arrays.toString(persons));
    }

}
