import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ResultHandlerDOM {
    public HashMap<String, ArrayList<String>> resultHandlerDOM (StringBuilder xmlSpeller)
            throws ParserConfigurationException, SAXException, IOException
                                                                    {
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        ByteArrayInputStream input = new ByteArrayInputStream(
                xmlSpeller.toString().getBytes("UTF-8"));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(input);
        NodeList errors = document.getDocumentElement().getElementsByTagName("error");

        for(int i=0; i<errors.getLength();i++) {
            NodeList currErr = errors.item(i).getChildNodes();
            ArrayList<String> version = new ArrayList<> ();
            for(int j=currErr.getLength()-1; j>0;j--) {
                version.add(currErr.item(j).getTextContent());
            }
                result.put(currErr.item(0).getTextContent(), version);
        }
        return result;
    }
}
