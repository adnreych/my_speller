
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Speller {

    static ArrayList<HashMap<String, ArrayList<String>>> spellCheck(StringBuilder s) {
        SourceHtml checkedUrl = new SourceHtml(s.toString()); // получаем исходный код проверяемого сайта
        GetText getText = new GetText(checkedUrl.getSourceHtml()); // отбираем все слова с русскими символами
        HashSet<String> cl = getText.getUrlClusters(); // разбиваем на блоки, чтобы не было слишком длинного URL
        ArrayList<HashMap<String, ArrayList<String>>> result = new ArrayList<>();
        ResultHandlerDOM rhd = new ResultHandlerDOM();
        for(String cluster : cl) {
            SourceHtml sourceHtml = new SourceHtml("https://speller.yandex.net/services/spellservice/checkText?text="+cluster);
            try {
                    result.add(rhd.resultHandlerDOM(sourceHtml.getSourceHtml()));
            } catch (ParserConfigurationException|SAXException | IOException e) {
                e.printStackTrace();
            }
        }
        result.removeIf(hm -> hm.isEmpty()==true); // удаляем нулевые элементы из result
        return result;
    }

    public static void main(String[] args) {
        long l = System.nanoTime();
        System.out.println(Speller.spellCheck(new StringBuilder("https://glloss.ru/")));
        long l2 = System.nanoTime()-l;
        System.out.println(l2);

    }
}
//НЕ УВИДЕЛ СЛОВО "ДЕШЕВ". ПРОВЕРИТЬ ЕГО. СПЕЛЛЕР ДАННОЕ СЛОВО ЗА ОШИБКУ ПРИНИМАЕТ ТОЛЬКО В КОНТЕКСТЕ
// обязательно надо вводить протокол (но это можно обработать). Программа не обрабатывает редиректы