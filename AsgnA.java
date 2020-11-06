import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.io.FileNotFoundException;

public class AsgnA {

    public static void main(String[] args) throws IllegalArgumentException, IOException {

        //do not allow less than 2 arguments. token and filename needed for this program to run
        try {
            if (args.length < 2) throw new IllegalArgumentException("token and filepath required as command line arguments.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            return;
        }

        //read arguments and notify users that operation has started
        String token = args[0];
        String filePath = args[1];
        System.out.println("Operating on [token]: " + token + " at [filepath]: " + filePath);

        //do not allow invalid filepaths (file not found exception)
        File file = null;
        try {
            file = new File(filePath);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            return;
        }

        //create buffer for read
        BufferedReader fileReaderBuffered = new BufferedReader(new FileReader(file));

        //create new file to write to. instead of holding file contents in one string, writing to new file for files
        //with huge sizes can help us avoid overflow
        File fileA = null;
        try {
            fileA = new File("newfile.txt");
            if (fileA.createNewFile()) {
                System.out.println("File created: " + fileA.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        BufferedWriter fileWriterBuffered = new BufferedWriter(new FileWriter(fileA, true));

        //read line by line
        String strCurrentLine;
        while ((strCurrentLine = fileReaderBuffered.readLine()) != null) {
            strCurrentLine = strCurrentLine.replaceAll(token, token.toUpperCase());
            fileWriterBuffered.write(strCurrentLine + '\n');
            fileWriterBuffered.flush();
        }

        fileReaderBuffered.close();
    }
}