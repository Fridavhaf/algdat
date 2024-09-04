public class finnMaks {
    public static void main(String[] args){
        int[] a = {100,2,3,4,5,6,7,8,9,20,10};
        String storste = finnStorste(a);
        System.out.println(storste);
    }

    public static String finnStorste (int[] tabell){
        int storste = tabell[0];
        int storsteIndex = 0;
        for (int i = 0; i < tabell.length; i++){
            if (tabell[i] > storste){
                storste = tabell[i];
                storsteIndex = i;
            }
        }
        return "Det største elemenetet i listen er " + storste + " og denne har index " + storsteIndex;
    }
}
