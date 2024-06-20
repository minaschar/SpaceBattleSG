import greenfoot.*;

public class QuestionPopUp extends Actor 
{
    public static final float FONT_SIZE = 36.0f;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    
    private boolean correctAnswer;
    
    public QuestionPopUp(Question question) 
    {
        makeImage("Question", question.getQuestion(), question.getOptions());
        this.correctAnswer = showQuestionPopup(question.getQuestion(), question.getOptions(), question.getCorrectOption());
    }
    
    private void makeImage(String title, String questionText, String[] options) 
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);
    
        image.setColor(new Color(255, 255, 255, 128));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(5, 5, WIDTH - 10, HEIGHT - 10);
    
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(title, 60, 50);
        image.drawString(questionText, 60, 100);
    
        for (int i = 0; i < options.length; i++) 
        {
            image.drawString(options[i], 60, 150 + i * 40);
        }
    
        setImage(image);
    }
    
    public boolean showQuestionPopup(String question, String[] options, int correctOption) 
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);
    
        image.setColor(new Color(255, 255, 255, 128));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(5, 5, WIDTH - 10, HEIGHT - 10);
    
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(question, 60, 100);
    
        for (int i = 0; i < options.length; i++) 
        {
            image.drawString(options[i], 60, 150 + i * 40);
        }
    
        setImage(image);
    
        int answer = Greenfoot.ask(question + "\n" + String.join("\n", options)).charAt(0) - 'a';
        return answer == correctOption;
    }
    
    public boolean isCorrectAnswer() 
    {
        return correctAnswer;
    }
}