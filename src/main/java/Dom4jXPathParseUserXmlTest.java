import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.List;

public class Dom4jXPathParseUserXmlTest {
    public static void main(String[] args) {
        SAXReader saxReader=new SAXReader();
        try {

            Document document= saxReader.read(Dom4jXPathParseUserXmlTest.class.getClassLoader().getResource("users"));
            //拿到第一个用户的密码
            System.out.println("1.------>使用绝对路径方式来查找元素");
            Element element = (Element) document.selectSingleNode("/users/user/password");
            String password = element.getText();
            System.out.println(password);

            System.out.println("2.------>使用相对路径查找元素");
            //element是当前获取的password元素
            Element name = (Element) element.selectSingleNode("../name");
            System.out.println("第一个用户的姓名为"+name.getText());

            System.out.println("3.------>使用全局搜索的方式");
            //获取所有的id元素的文本
            List<Node> idNodeList = document.selectNodes("//id");
            for (Node node : idNodeList) {
                Element idElement=(Element) node;
                System.out.println(idElement.getText());
            }

            System.out.println("4.------>谓语形式");
            //获取id=10002的用户信息
            Element idElement = (Element) document.selectSingleNode("//user[@id='10002']");
            List<Element> elements = idElement.elements();
            for (Element element1 : elements) {
                System.out.println(element1.getName()+"="+element1.getText());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
