public class Main {
    public static void main(String[] args) {
        int c = UIable.askNum("prompt");
        System.out.println(c);
        System.out.println(UIable.askString("prompt"));
        System.out.println(UIable.askOption("a","b","c"));
    }
}
