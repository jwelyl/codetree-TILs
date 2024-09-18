import java.util.Scanner;

public class Main {
    private static final StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        recursion(n);

        System.out.print(sb);
    }   //  main-end

    private static void recursion(int nth) {
        if(nth == 1) {
            sb.append("*\n*\n");
            return;
        }

        for(int i = 0; i < nth; i++)
            sb.append("* ");
        sb.append("\n");
        recursion(nth - 1);
        for(int i = 0; i < nth; i++)
            sb.append("* ");
        sb.append("\n");
    }
}   //  Main-class-end