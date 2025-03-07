package clases;

public class Main {
    public static void main(String args[]) {
        Diccionario<String, String> dict = new Diccionario<>();
        System.out.println("Funciona!!!");
        for (int i = 0; i < 20; i++) {
            dict.set("Hola", "Adios");
            System.out.println(dict.getCapacity());
        }
    }
}
