import java.net.*;
import java.io.*;

public class HtmlRead {
    public static void main(String[] args) {
        HtmlRead app= new HtmlRead();
    }
    public HtmlRead(){

        try{
            System.out.println();
            URL fred = new URL("https://www.roblox.com/");
            URLConnection urlc = fred.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream()));
            String line;
            while ( (line = reader.readLine()) != null ) {
                int start = 0;
                while ((start = line.indexOf("https", start)) !=-1){
                    String shortline = line.substring(start);
                    int endDoubleQuote = shortline.indexOf("\"");
                    int endSingleQuote = shortline.indexOf("'");
                    int end;
                    if (endDoubleQuote == -1){
                        end = endSingleQuote;
                    }
                    else if (endSingleQuote == -1){
                        end = endDoubleQuote;
                    }
                    else {
                        end = Math.min(endDoubleQuote, endSingleQuote);
                    }
                    if (end != -1){
                        String singleURL = line.substring(end);
                        System.out.println(singleURL);
                    }
                    start ++;
                }
            }
            reader.close();
        } catch(Exception ex){
            System.out.println(ex);
        }
    }
}