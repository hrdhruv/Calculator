import java.math.BigInteger;
import java.util.Scanner;

public class Calculator {
    // Square root
    public double sqrt(double x) {
        if (x < 0) throw new IllegalArgumentException("sqrt: negative input");
        return Math.sqrt(x);
    }
    // Factorial
    public BigInteger factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("factorial: negative input");
        BigInteger res = BigInteger.ONE;
        for (int i = 2; i <= n; i++) res = res.multiply(BigInteger.valueOf(i));
        return res;
    }
    // Natural logarithm 
    public double ln(double x) {
        if (x <= 0) throw new IllegalArgumentException("ln: input must be > 0");
        return Math.log(x);
    }
    // Power x^b
    public double pow(double x, double b) {
        return Math.pow(x, b);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nScientific Calculator - Menu");
            System.out.println("1) Square root");
            System.out.println("2) Factorial");
            System.out.println("3) Natural log(ln x)");
            System.out.println("4) Power(x^b)");
            System.out.println("5) Exit");
            System.out.print("Choose option: ");
            String opt = sc.nextLine().trim();
            try {
                switch (opt) {
                    case "1":
                        System.out.print("Enter x: ");
                        double x = Double.parseDouble(sc.nextLine());
                        System.out.println("√" + x + " = " + calc.sqrt(x));
                        break;
                    case "2":
                        System.out.print("Enter x: ");      // error for negative input
                        int n = Integer.parseInt(sc.nextLine());
                        BigInteger f = calc.factorial(n);
                        System.out.println(n + "! = " + f.toString());
                        break;
                    case "3":
                        System.out.print("Enter x: ");  // error for x<=0
                        double y = Double.parseDouble(sc.nextLine());
                        System.out.println("ln(" + y + ") = " + calc.ln(y));
                        break;
                    case "4":
                        System.out.print("Enter x: ");  
                        double base = Double.parseDouble(sc.nextLine());
                        System.out.print("Enter b: ");
                        double b = Double.parseDouble(sc.nextLine());
                        System.out.println(base + "^" + b + " = " + calc.pow(base, b));
                        break;
                    case "5":
                        System.out.println("Exiting...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}


