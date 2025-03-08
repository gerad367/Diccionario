package clases;

public class Main {
    public static void main(String args[]) {
        Diccionario<Integer, String> dict = new Diccionario<>();
        for (int i = 0; i < 20; i++) {
            dict.set(i, "Adios");
            System.out.println(dict.getCapacity());
        }
    }
}
