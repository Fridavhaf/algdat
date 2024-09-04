public class Binærsøketre {

    public static class Node {
        Node parent; // Referanse til nodens forelder
        Node left_child; // Referanse til nodens venstre barn
        Node right_child; // Referanse til nodens høyre barn
        char value; // Verdien noden holder, av typen char

        //konstruktør som oppretter en ny node
        public Node (Node parent, char value){
            this.parent = parent;
            this.value = value;
            this.left_child = null; //noden har ikke barn enda
            this.right_child = null;
        }

        // Metode for å legge til et venstrebarn til noden
        void addLeftChild(char value){
            this.left_child = new Node(this,value);
        } //den nye noden settes som venstrebarn av den nåværende noden

        // Metode for å legge til et høyrebarn til noden
        void addRightChild(char value){
            this.right_child = new Node(this,value);
        }

        /* Metode for å skrive ut nodens verdi og verdiene til dens barn.
        Her brukes preorder traversal, en metode for å traversere (gå gjennom) et binært tre.

        * */
        void print(){
            System.out.println(this.value);
            if(this.left_child != null){
                this.left_child.print();
            }
            if(this.right_child != null){
                this.right_child.print();
            }
        }

        // Metode for å hente besteforelderen til noden
        Node grandParent(){
            if (this.parent != null && this.parent.parent != null) {
                return this.parent.parent; // Returnerer nodens besteforelder (forelderen til forelderen)
            }
            return null;
        }

    }

    public static void main(String[] args){
        char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

        // Oppretter roten til treet med den første verdien i arrayet
        Node root = new Node(null, a[0]);
        root.addLeftChild(a[1]);
        root.addRightChild(a[2]);

        root.left_child.addLeftChild(a[3]);
        root.left_child.addRightChild(a[4]);

        root.right_child.addLeftChild(a[5]);
        root.right_child.addRightChild(a[6]);

        root.left_child.left_child.addLeftChild(a[7]);
        root.left_child.left_child.addRightChild(a[8]);

        System.out.println("Printing our Binary Search Tree!");
        root.print();

        //Finn besteforelderen til Node I
        Node iNode = root.left_child.left_child.right_child; // Dette er noden med verdi 'D'
        Node grandParent = iNode.grandParent(); // Kaller grandParent-metoden

        // Sjekk om grandParent eksisterer og skriv ut verdien
        if (grandParent != null) {
            System.out.println("Besteforelderen til 'I' er: " + grandParent.value);
            System.out.println("Forelderen til til 'I' er: " + iNode.parent.value);
        } else {
            System.out.println("'D' har ingen besteforelder.");
        }


    }
}
