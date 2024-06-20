import greenfoot.*;

public class QuestionPopUp extends Actor 
{
    public static final float FONT_SIZE = 36.0f;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    
    private boolean correctAnswer;
    private World world;
    
    public QuestionPopUp(Question question, World world) 
    {
        this.world = world;
        this.correctAnswer = showQuestionPopup(question.getQuestion(), question.getOptions(), question.getCorrectOption());
    }
    
    public boolean showQuestionPopup(String question, String[] options, int correctOption) 
    {
        String answer = Greenfoot.ask(question + "\n" + String.join("\n", options) + "\nEnter your answer (a, b, c, d):");
        int answerIndex = answer.charAt(0) - 'a';
        
        if (answerIndex == correctOption)
        {
            Greenfoot.playSound("correct.mp3");
            return true;
        } else 
        {
            Greenfoot.playSound("wrong.mp3");
            String feedback = options[correctOption];
            PlayerFeedbackPopup popup = new PlayerFeedbackPopup(feedback);
            this.world.addObject(popup, this.world.getWidth() / 2, this.world.getHeight() / 2);
            Greenfoot.delay(70); 
            this.world.removeObject(popup);
            
            return false;
        }
    }
    
    public boolean isCorrectAnswer() 
    {
        return correctAnswer;
    }
}