import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetText {
    private HashSet<String> words;

    GetText(StringBuilder s) {
        Pattern pattern = Pattern.compile(
                "[А-Яа-яё]+&?",   // &? - обработка тегов вроде &nbsp, неизвестно работает ли
                Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(s);
        HashSet<String> words = new HashSet<>();
        while (matcher.find()) {
            words.add(s.substring(matcher.start(), matcher.end()));
        }
        this.words = words;
    }

    private HashSet<String> urlClusters = new HashSet<>();

    public HashSet<String> getUrlClusters() {
        this.doClusters();
        return urlClusters;
    }

    private void doClusters() {
        int counter;
        HashSet<String> urlClusters = new HashSet<>();
        for (Iterator <String> i = words.iterator(); i.hasNext(); ) {
            String clusterElement = "";
            counter = 0;
            while(counter*4<1000 & i.hasNext()) {  // максимум 1000 символов в URL
                String s = i.next();
                clusterElement = clusterElement + s + "+";
                counter += s.length();
            }
            urlClusters.add(clusterElement+" ");

        }
        this.urlClusters = urlClusters;
    }

}
