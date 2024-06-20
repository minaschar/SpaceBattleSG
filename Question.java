public class Question  
{
    
    private String question;
    private String[] options;
    private int correctOption;
    
    public Question(String question, String[] options, int correctOption)
    {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion()
    {
        return this.question;
    }
    
    public String[] getOptions()
    {
        return this.options;
    }
    
    public int getCorrectOption()
    {
        return this.correctOption;
    }

}
