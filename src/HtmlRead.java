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
                if (line.contains("https")){
                    int start = line.indexOf("https");
                    String shortline = line.substring(start);
                    int end = shortline.indexOf("\"");
                    System.out.println(line);
                    String shortestline = shortline.substring(0, end);
                    System.out.println(shortestline);

                }
            }
            reader.close();
        } catch(Exception ex){
            System.out.println(ex);
        }
    }
}