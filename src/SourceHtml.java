import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;



public class SourceHtml {


    private StringBuilder sourceHtml ;

    public StringBuilder getSourceHtml() {
        return sourceHtml;
    }

    SourceHtml(String s) {
        StringBuilder sourceHtml = new StringBuilder();
        try {
            URL url = new URL(s);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                StringBuilder ln = new StringBuilder(line);
                sourceHtml.append(ln+"\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sourceHtml = sourceHtml;
    }
}
