
// Klasse som representerer en Person og implementerer Comparable for å kunne sammenligne personer
class Person implements Comparable<Person> {
    private String name;
    private int age;

    // Konstruktør
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter for alder
    public int getAge() {
        return age;
    }

    // Getter for navn
    public String getName() {
        return name;
    }

    // Sammenligner alder for å implementere Comparable
    public int compareTo(Person p) {
        return this.age - p.getAge();
    }
}

// Generisk klasse som kan holde et element av en hvilken som helst type T
class Boks<T> {
    private T element;

    // Legger inn et element av type T
    public void leggInn(T element) {
        this.element = element;
    }

    // Henter ut elementet av type T
    public T hentUt() {
        return element;
    }
}

// Spesialisert klasse for å lagre og hente en String
class StringBoks {
    private String element;

    // Setter inn en streng
    public void leggInn(String s) {
        element = s;
    }

    // Henter ut lagret streng
    public String hentUt() {
        return element;
    }
}

// Klasse som bruker Object for å lagre hvilken som helst type objekt (men krever typecasting)
class ObjBoks {
    private Object element;

    // Setter inn et objekt av type Object
    public void leggInn(Object o) {
        element = o;
    }

    // Henter ut objektet (må typecastes)
    public Object hentUt() {
        return element;
    }
}

// Klasse som inneholder en generisk metode for å finne det største elementet i et array
class Maks {
    // Generisk metode for å finne indeksen til det største elementet i et array
    public static <T extends Comparable<? super T>> int maks(T[] tabell) {
        int størstPlassering = 0;
        T størstVerdi = tabell[0];

        // Går gjennom arrayet for å finne det største elementet
        for (int i = 1; i < tabell.length; i++) {
            if (størstVerdi.compareTo(tabell[i]) < 0) {  // Sammenligner størstVerdi med nåværende element
                størstVerdi = tabell[i];  // Oppdaterer størstVerdi
                størstPlassering = i;     // Oppdaterer plasseringen
            }
        }
        return størstPlassering;
    }
}

public class Dato_09_13 {

    // Main-metoden som kjører programmets logikk
    public static void main(String[] args) {
        maksTest();  // Tester maks-metoden med Integer og Person
        bokser();
    }

    // Testmetode for å finne største element i tabeller
    public static void maksTest() {
        // Tester maks-funksjonen med et array av Integer
        Integer[] tabell = {1, 3, 7, 5, 2, 0};
        int maks = Maks.maks(tabell);  // Finner indeksen til største verdi
        System.out.println("Største verdi i Integer-tabellen er: " + tabell[maks]);

        // Tester maks-funksjonen med et array av Person-objekter
        Person[] personTabell = new Person[2];
        personTabell[0] = new Person("Nikolai", 35);
        personTabell[1] = new Person("Peder", 31);

        maks = Maks.maks(personTabell);  // Finner personen med høyest alder
        System.out.println("Eldste person er: " + personTabell[maks].getName());
    }

    // Testmetode for bruk av bokser
    public static void bokser() {
        // Bruker StringBoks til å lagre og hente ut en streng
        StringBoks sb = new StringBoks();
        sb.leggInn("Hei, hvordan går det?");
        System.out.println(sb.hentUt());

        // Bruker ObjBoks til å lagre og hente et Person-objekt (krever typecasting)
        Person n = new Person("Nikolai", 35);
        ObjBoks ob = new ObjBoks();
        ob.leggInn(n);
        gratulerer((Person) ob.hentUt());

        // Bruker en generisk Boks til å lagre og hente en Person uten typecasting
        Boks<Person> pb = new Boks<>();
        pb.leggInn(n);
        gratulerer(pb.hentUt());

        // Bruker en generisk Boks til å lagre og hente en String
        Boks<String> bedresb = new Boks<>();
        bedresb.leggInn("Kult, det går fint med String.");
        System.out.println(bedresb.hentUt());

        // Bruker en generisk Boks til å lagre og hente en Integer (autoboxing skjer automatisk)
        Boks<Integer> intBoks = new Boks<>();
        intBoks.leggInn(7);
        int j = intBoks.hentUt();  // Integer blir automatisk omgjort til int
        System.out.println("Verdien fra Integer-boksen er: " + j);
    }

    // Metode som gratulerer en Person med alderen
    public static void gratulerer(Person p) {
        System.out.println("Gratulerer med alderen din, " + p.getName() + ". Du er " + p.getAge() + " år!");
    }
}


