import java.net.*;
import java.io.*;
import java.util.Scanner;

public class HtmlRead {
    public static void main(String[] args) {
        HtmlRead app= new HtmlRead();

    }
    public HtmlRead(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your desired link: ");
        String userInput = scanner.nextLine();//Capture the input directly into a String variable
        scanner.close();// Close the scanner to free up system resources

        try{
            System.out.println();
            URL fred = new URL(userInput); // Uses the user inputted URL
            URLConnection urlc = fred.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream()));
            String line;
            while ( (line = reader.readLine()) != null ) {
                int start = 0; //The start starts at 0
                System.out.println(line);
                while ((start = line.indexOf("https://", start)) !=-1){ //finds all "https" in the string
                    String shortline = line.substring(start); //shortened line starts at https
                    int endDoubleQuote = shortline.indexOf("\""); //finds the next double quote in the line
                    int endSingleQuote = shortline.indexOf("'"); //finds the next single quote in the line
                    int end;
                    if (endDoubleQuote == -1){
                        end = endSingleQuote; //if there is no double quote use single quote as the end
                    }
                    else if (endSingleQuote == -1){
                        end = endDoubleQuote; //if there is no single quote use double quote as the end
                    }
                    else {
                        end = Math.min(endDoubleQuote, endSingleQuote); //if both single and double quotes are present, use the one that occurs first after the start of the quote
                    }
                    if (end != 0){
                        String singleURL = shortline.substring(0,end); //shortens shortline to just the URL
                        System.out.println(singleURL); //prints out only the URL, so long as end is not 0
                    }
                    start ++; //adds +one to the start
                }
            }
            reader.close();
        } catch(Exception ex){
            System.out.println(ex);
        }
    }
}