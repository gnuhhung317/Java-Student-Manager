public class Test {
    public static void main(String[] args) {
        int[] a = new int[10];
        int[] b = new int[20];
        a = b;
        for (int i = 0; i < 10; i++) {
            a[i] = i;
        }

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
