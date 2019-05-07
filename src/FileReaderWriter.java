
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReaderWriter {
    
    
//the location of the users.dat file, this is utilised below in the buffered file reader
    String file_location = "C:\\Users\\David\\Documents\\NetBeansProjects\\Vending Machine Assignment Final\\src\\Users.dat";

    public FileReaderWriter(String fileLocation) {
        this.file_location = fileLocation;

    }
    // below the data from the file.dat is converted to an arraylist

    public List<String> readDataFromFile() {

        List<String> dataset1 = new ArrayList<>();
        // Reading from the file above
        try (BufferedReader br = new BufferedReader(new FileReader(file_location))) {
            String line;
            //while file is not blank 
            while ((line = br.readLine()) != null) {
                //removes any whitespace in the array
                dataset1.add(line.replaceAll("\\s+", ""));
            }
            // error to catch when wrong file path is used
        } catch (Exception e) {
            System.out.println("An error occurred while reading the data file.");
        }
        return dataset1;
    }
//Uses a for loop to append data to the file
    public void appendDataToFile(String keyword, String newLine) throws IOException {
        //Appends data to the file, 
        List<String> dataset1 = readDataFromFile();
        for (int i = 0; i < dataset1.size(); i++) {
            if (dataset1.get(i).contains(keyword)) {
                dataset1.set(i, newLine);
                break;
            }
        }
        // this is the filewriter which does same
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(this.file_location));
        for (String line1 : dataset1) {
            bw1.write(line1);
            bw1.newLine();
        }
        //close writer
        bw1.close();
    }
//checks to see if the line is in the file, eg here keyword is username 
    public String doesLineExistInFile(String keyword) {
         //reads data from from file (function above)
        List<String> ar = readDataFromFile();
        for (int i = 0; i < ar.size(); i++) {
            // enables the file to be read espite comma delimitation 
            if (ar.get(i).split(",")[0].equals(keyword)) {
                return ar.get(i);
            }
        }
        return null;
    }
//writes data to file 
    public void writeDataToFile(List<String> dataset) throws IOException {
        // Create file if it doesn't exist.
        if (!new File(file_location).exists()) {
             //lets file path = file_location (designated filepath) 
            File f = new File(file_location);
            try {
                f.createNewFile();
            } catch (IOException ex) {
                //getLogger method from java, used to log to file 
                Logger.getLogger(FileReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Initial file creation
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.file_location));
        for (String line : dataset) {
            bw.write(line);
            bw.newLine();
        }//close writer
        bw.close();
    }

}
