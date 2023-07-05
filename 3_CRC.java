import java.util.*;

class CRC {
    static int[] divide(int old_data[], int divisor[]) {
        int remainder[], i;
        int data[] = new int[old_data.length + divisor.length];
        System.arraycopy(old_data, 0, data, 0, old_data.length);
        remainder = new int[divisor.length];
        System.arraycopy(data, 0, remainder, 0, divisor.length);
        for (i = 0; i < old_data.length; i++) {
            System.out.println((i + 1) + ".) First data bit is : "
                    + remainder[0]);
            System.out.print("Remainder : ");
            if (remainder[0] == 1) {
                for (int j = 1; j < divisor.length; j++) {
                    remainder[j - 1] = exor(remainder[j], divisor[j]);
                    System.out.print(remainder[j - 1]);
                }
            } else {
                for (int j = 1; j < divisor.length; j++) {
                    remainder[j - 1] = exor(remainder[j], 0);
                    System.out.print(remainder[j - 1]);
                }
            }
            remainder[divisor.length - 1] = data[i + divisor.length];
            System.out.println(remainder[divisor.length - 1]);
        }
        return remainder;
    }

    static int exor(int a, int b) {
        // This simple function returns the xor of two bits
        if (a == b) {
            return 0;
        }
        return 1;
    }

    static void receive(int data[], int divisor[]) {

        int remainder[] = divide(data, divisor);

        for (int i = 0; i < remainder.length; i++) {
            if (remainder[i] != 0) {

                System.out.println("There is an error in received data...");
                return;
            }
        }
        System.out.println("Data was received without any error.");
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n;
        System.out.println("Enter the size of the data:");
        n = scan.nextInt();
        int data[] = new int[n];
        System.out.println("Enter the data, bit by bit:");
        for (int i = 0; i < n; i++) {
            System.out.println("Enter bit number " + (n - i) + ":");
            data[i] = scan.nextInt();
        }

        System.out.println("Enter the size of the divisor:");
        n = scan.nextInt();
        int divisor[] = new int[n];
        System.out.println("Enter the divisor, bit by bit:");
        for (int i = 0; i < n; i++) {
            System.out.println("Enter bit number " + (n - i) + ":");
            divisor[i] = scan.nextInt();
        }

        int remainder[] = divide(data, divisor);
        for (int i = 0; i < remainder.length - 1; i++) {
            System.out.print(remainder[i]);
        }
        System.out.println("\nThe CRC code generated is:");

        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
        }
        for (int i = 0; i < remainder.length - 1; i++) {
            System.out.print(remainder[i]);
        }
        System.out.println();

        int sent_data[] = new int[data.length + remainder.length - 1];
        System.out.println("Enter the data to be sent:");
        for (int i = 0; i < sent_data.length; i++) {
            System.out.println("Enter bit number " + (sent_data.length - i)
                    + ":");
            sent_data[i] = scan.nextInt();
        }
        receive(sent_data, divisor);
    }
}
//-------------------------------------------------------------------------------------------------------------------------------------------------

import java.util.*;

public class BitStuffing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the message:-");
        String d1 = sc.nextLine();
        sc.close();
        String remaining = new String();
        String output = new String();
        int counter = 0;
        for (int i = 0; i < d1.length(); i++) {
            if (d1.charAt(i) != '1' && d1.charAt(i) != '0') {
                System.out.println("Enter valid Binary values");
                return;
            }
            if (d1.charAt(i) == '1') {
                counter++;
                remaining = remaining + d1.charAt(i);
            } else {
                remaining = remaining + d1.charAt(i);
                counter = 0;
            }
            if (counter == 5) {
                remaining = remaining + '0';
                counter = 0;
            }
        }
        System.out.println("Flag--> 01111110");
        String new1 = "|01111110 | " + remaining + " | 01111110|";
        System.out.println("Stuffed data at intermediate site is:");
        for (int k = 0; k <= (28 + d1.length()); k++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println(" " + new1);
        for (int k = 0; k <= (28 + d1.length()); k++) {
            System.out.print("-");
        }
        System.out.println();
        counter = 0;
        for (int i = 0; i < remaining.length(); i++) {
            if (remaining.charAt(i) == '1') {
                counter++;
                output = output + remaining.charAt(i);
            } else {
                output = output + remaining.charAt(i);
                counter = 0;
            }
            if (counter == 5) {
                if ((i + 2) != remaining.length()) {
                    output = output + remaining.charAt(i + 2);
                } else {
                    output = output + '1';
                }
                i = i + 2;
                counter = 1;
            }
        }
        System.out.println("Destuffed BIT is: " + output);
    }
}
