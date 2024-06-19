import greenfoot.*;  

public class GameOverPopUp extends Actor 
{
    public static final float FONT_SIZE = 48.0f;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    public GameOverPopUp(String winner, String winnerRocketPhotoFileName) 
    {
        makeImage("Game Over", "Winner: " + winner, winnerRocketPhotoFileName); 
    }

    private void makeImage(String title, String winnerText, String winnerRocketPhotoFileName) 
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);
        GreenfootImage photo = new GreenfootImage(winnerRocketPhotoFileName);

        photo.scale(70, 70);  

        image.setColor(new Color(255, 255, 255, 128));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(5, 5, WIDTH - 10, HEIGHT - 10);

        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(title, 60, 100);
        image.drawString(winnerText, 60, 175);

        image.drawImage(photo, (WIDTH - photo.getWidth()) / 2, 200); 

        setImage(image);
    }
}