import java.net.*;
import java.io.*;
import java.util.Scanner;

public class HtmlRead {
    public static void main(String[] args) {
        HtmlRead app= new HtmlRead();

    }
    public HtmlRead(){
        Scanner scanner = new Scanner(System.in);

        //scanner.close();// Close the scanner to free up system resources

        try{
            boolean rerun = true;
            while (rerun = true) {//the first time rerun is true, then it is only true if the user enters 'r' at the end
            System.out.println();
            System.out.print("Enter your desired link: "); // lets user chose link
            String chosenURL = scanner.nextLine();//Capture the input directly into a String variable
            System.out.print("Enter the filter for the links here (what you want each link printed to start with): "); //let user choose the string that filters the links
                String chosenFilter = scanner.nextLine();
            URL fred = new URL(chosenURL); // Uses the user inputted URL
            URLConnection urlc = fred.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream()));
            String line;


                while ((line = reader.readLine()) != null) {
                    int start = 0; //The start starts at 0
                    //System.out.println(line);
                    while ((start = line.indexOf(chosenFilter, start)) != -1) { //finds all URLs starting with the filter the user chose in the string
                        String shortline = line.substring(start); //shortened line starts at https
                        int endDoubleQuote = shortline.indexOf("\""); //finds the next double quote in the line
                        int endSingleQuote = shortline.indexOf("'"); //finds the next single quote in the line
                        int end;
                        if (endDoubleQuote == -1) {
                            end = endSingleQuote; //if there is no double quote use single quote as the end
                        } else if (endSingleQuote == -1) {
                            end = endDoubleQuote; //if there is no single quote use double quote as the end
                        } else {
                            end = Math.min(endDoubleQuote, endSingleQuote); //if both single and double quotes are present, use the one that occurs first after the start of the quote
                        }
                        if (end != 0) {
                            String singleURL = shortline.substring(0, end); //shortens shortline to just the URL
                            System.out.println(singleURL); //prints out only the URL, so long as end is not 0
                        }
                        start++; //adds +one to the start
                    }
                }
                rerun = false; //does not rerun the program until the user chooses to
                System.out.println("Enter 'r' to rerun the program");
                String restart = scanner.nextLine();
                if (restart.equals("r")){
                    System.out.println("restarting...");
                    rerun = true;
                } //if the user enters 'r' the process restarts
                reader.close();
            }


        } catch(Exception ex){
            System.out.println(ex);
        }
    }
}