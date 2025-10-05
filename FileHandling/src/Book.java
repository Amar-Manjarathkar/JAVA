import java.io.*;

public class Book {
    public Book(File file) {
        // Constructor logic would go here if needed.
    }

    public static void main(String[] args) {
        // The Book instance 'b' is not used, so it can be removed.
        // Book b = new Book(new File("D:\\Code\\JAVA\\FileHandling\\src\\Books.txt"));

        String filePath = "D:\\Code\\JAVA\\FileHandling\\src\\Books.txt";

        // Use a "try-with-resources" block to ensure the file reader and buffer
        // are automatically closed, even if an error occurs.
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String currentLine;

            // Loop as long as readLine() returns a non-null string.
            // This is the standard way to read a file line by line.
            while ((currentLine = br.readLine()) != null) {
                // Print the actual line content, not the BufferedReader object.
                System.out.println(currentLine);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found at the specified path: " + filePath);
        } catch (IOException e) {
            System.err.println("Error: An error occurred while reading the file.");
        }
        String data = "This is new data";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("Books.txt"))){
            bw.write(data);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}