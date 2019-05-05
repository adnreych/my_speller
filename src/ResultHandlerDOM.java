import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ResultHandlerDOM {
	HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();

	public HashMap<String, ArrayList<String>> getResult() {
		return result;
	}

	public void resultHandlerDOM(StringBuilder xmlSpeller)
			throws ParserConfigurationException, SAXException, IOException {
		HashMap<String, ArrayList<String>> result = new HashMap<>();
		ByteArrayInputStream input = new ByteArrayInputStream(xmlSpeller.toString().getBytes("UTF-8"));
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(input);
		NodeList errors = document.getDocumentElement().getElementsByTagName("error");

		for (int i = 0; i < errors.getLength(); i++) {
			NodeList currErr = errors.item(i).getChildNodes();
			ArrayList<String> version = new ArrayList<>();
			for (int j = currErr.getLength() - 1; j > 0; j--) {
				version.add(currErr.item(j).getTextContent());
			}
			result.put(currErr.item(0).getTextContent(), version);
		}
		this.result = result;
	}

	public HashMap<String, ArrayList<String>> handResult(String s) {
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		SourceHtml checkedUrl = new SourceHtml(s.toString()); // получаем исходный код проверяемого сайта
		GetText getText = new GetText(checkedUrl.getSourceHtml()); // отбираем все слова с русскими символами
		HashSet<String> cl = getText.getUrlClusters(); // разбиваем на блоки, чтобы не было слишком длинного URL
		ResultHandlerDOM rhd = new ResultHandlerDOM();
		for (String cluster : cl) {
			SourceHtml sourceHtml = new SourceHtml(
					"https://speller.yandex.net/services/spellservice/checkText?text=" + cluster);
			try {
				rhd.resultHandlerDOM(sourceHtml.getSourceHtml());
				result.putAll(rhd.getResult());
			} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}
		}
		// result.removeIf(hm -> hm.isEmpty()==true); // удаляем нулевые элементы из
		// result
		return result;
	}
}
