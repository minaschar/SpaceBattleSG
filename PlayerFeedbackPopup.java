import greenfoot.*;  

public class PlayerFeedbackPopup extends Actor 
{
    public static final float FONT_SIZE = 30.0f;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 200;

    public PlayerFeedbackPopup(String correctAnswer) 
    {
        makeImage(correctAnswer); 
    }

    private void makeImage(String correctAnwser) 
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
        image.drawString("Wrong, the correct answer is: ", 60, 100);
        image.drawString(correctAnwser, 60, 150);

        setImage(image);
    }
}