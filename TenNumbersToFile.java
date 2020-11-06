import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class TenNumbersToFile {

    public static void main(String[] args) throws IOException{
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter ten numbers: ");

        ArrayList<Integer> tenNumbers = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            int n = myObj.nextInt();
            if (n <= 10) tenNumbers.add(n);
        }

        BufferedWriter fileWriterBuffered = new BufferedWriter(new FileWriter(args[0]));
        for (int i = 0; i < tenNumbers.size(); i++) {
            System.out.println(tenNumbers.get(i));
            fileWriterBuffered.write(tenNumbers.get(i).toString() + '\n');
            fileWriterBuffered.flush();
        }
    }
}