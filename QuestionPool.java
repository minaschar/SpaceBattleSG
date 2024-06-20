import java.util.ArrayList;

public class QuestionPool  
{
    private ArrayList<Question> questionsLevel1;
    private ArrayList<Question> questionsLevel2;
    private ArrayList<Question> questionsLevel3;
    private ArrayList<Question> questionsLevel4;
    private ArrayList<Question> questionsLevel5;
    
    public QuestionPool()
    {
        this.questionsLevel1 = new ArrayList<Question>();
        this.questionsLevel2 = new ArrayList<Question>();
        this.questionsLevel3 = new ArrayList<Question>();
        this.questionsLevel4 = new ArrayList<Question>();
        this.questionsLevel5 = new ArrayList<Question>();
        this.addQuestions();
    }
    
    private void addQuestions()
    {
        // Questions were added for level1
        String [] optionsForQuestion1 = {"a) Earth", "b) Mars", "c) Jupiter", "d) Saturn"};
        Question question1 = new Question("What is the largest planet in our solar system?", optionsForQuestion1, 2);
        this.questionsLevel1.add(question1);
    }
    
    public Question getQuestion()
    {
        return questionsLevel1.get(0);
    }
}
