import java.io.PrintStream;

public class Test {
    public static void main(String[] args) {
        int a = 9;
        methd(a);
        System.out.println(a);
    }

    public static void methd(int a){
        System.setOut(new PrintStream(System.out){
            @Override
            public void println(int x) {
                super.println(x*10);
            }
        });
    }
}
