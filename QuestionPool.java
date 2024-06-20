import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class QuestionPool 
{
    private ArrayList<Question> questionsLevel1;
    private ArrayList<Question> questionsLevel2;
    private ArrayList<Question> questionsLevel3;
    private ArrayList<Question> questionsLevel4;
    private ArrayList<Question> questionsLevel5;

    public QuestionPool() 
    {
        this.questionsLevel1 = new ArrayList<>();
        this.questionsLevel2 = new ArrayList<>();
        this.questionsLevel3 = new ArrayList<>();
        this.questionsLevel4 = new ArrayList<>();
        this.questionsLevel5 = new ArrayList<>();
        this.loadQuestionsFromXml();
    }

    private void loadQuestionsFromXml() 
    {
        try 
        {
            InputStream is = getClass().getResourceAsStream("/questions.xml");
            if (is == null) 
            {
                System.out.println("questions.xml file not found");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(is);

            // Normalize the XML Structure
            doc.getDocumentElement().normalize();

            // Parse questions for each level
            parseQuestions(doc.getElementsByTagName("questionsLevel1"), questionsLevel1);
            parseQuestions(doc.getElementsByTagName("questionsLevel2"), questionsLevel2);
            parseQuestions(doc.getElementsByTagName("questionsLevel3"), questionsLevel3);
            parseQuestions(doc.getElementsByTagName("questionsLevel4"), questionsLevel4);
            parseQuestions(doc.getElementsByTagName("questionsLevel5"), questionsLevel5);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    private void parseQuestions(NodeList nodeList, ArrayList<Question> questions) 
    {
        for (int i = 0; i < nodeList.getLength(); i++) 
        {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) 
            {
                Element element = (Element) node;
                NodeList questionList = element.getElementsByTagName("question");

                for (int j = 0; j < questionList.getLength(); j++) 
                {
                    Node questionNode = questionList.item(j);
                    if (questionNode.getNodeType() == Node.ELEMENT_NODE) 
                    {
                        Element questionElement = (Element) questionNode;

                        String questionText = questionElement.getElementsByTagName("text").item(0).getTextContent();

                        NodeList optionsNodeList = questionElement.getElementsByTagName("option");
                        String[] options = new String[optionsNodeList.getLength()];
                        for (int k = 0; k < optionsNodeList.getLength(); k++) 
                        {
                            options[k] = optionsNodeList.item(k).getTextContent();
                        }

                        int correctOption = Integer.parseInt(questionElement.getElementsByTagName("correctOption").item(0).getTextContent());

                        questions.add(new Question(questionText, options, correctOption));
                    }
                }
            }
        }
    }

    public Question getQuestion() 
    {
        return questionsLevel5.get(9);
    }
}