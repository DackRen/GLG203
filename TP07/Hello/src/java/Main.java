import org.dom4j.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        try {
            // Construit un document xml
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("hello");
            root.addElement("petstore").addAttribute("date", new Date().toString()).addText("Hello Petstore!");
//          Element root1 = document.getRootElement();
//			root1.addAttribute("Status","ok");
            
//          Element root = document.addElement("CreditCard"); 
//
//          root.addElement("CardNumber").addText("1 1");
//          root.addElement("CardType").addText("visa"); 
//          String _creditCardExpiryDate = "10/19";
//			root.addElement("ExpiryDate").addAttribute("Month", _creditCardExpiryDate.substring(0, 2)).addAttribute("Year", _creditCardExpiryDate.substring(3)); 
            
            // Affiche le document xml
            System.out.println(document.asXML());

            // R�cup�re les donn�es avec xPath
            Node node = document.selectSingleNode("/hello/petstore");
//            Node node = document.selectSingleNode("/CreditCard/ExpiryDate");
            System.out.println(node.getText());
//            System.out.println(node.valueOf("@Month")+"  "+node.valueOf("@Year"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

