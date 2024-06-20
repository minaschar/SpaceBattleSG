import greenfoot.*;

public class QuestionPopUp extends Actor 
{
    public static final float FONT_SIZE = 36.0f;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    
    private boolean correctAnswer;
    
    public QuestionPopUp(Question question) 
    {
        this.correctAnswer = showQuestionPopup(question.getQuestion(), question.getOptions(), question.getCorrectOption());
    }
    
    public boolean showQuestionPopup(String question, String[] options, int correctOption) 
    {
        String answer = Greenfoot.ask(question + "\n" + String.join("\n", options) + "\nEnter your answer (a, b, c, d):");
        int answerIndex = answer.charAt(0) - 'a';
        
        return answerIndex == correctOption;
    }
    
    public boolean isCorrectAnswer() 
    {
        return correctAnswer;
    }
}