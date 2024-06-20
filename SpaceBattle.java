import greenfoot.*; 
import java.util.*;

public class SpaceBattle extends World
{
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;
    private static final int START_ASTEROIDS_NUMBER = 3;
    private static final int NUMBER_OF_GENERATED_STARS = 600;
    
    private Rocket rocketLeft;
    private Rocket rocketRight;
    
    private Counter lifeCounterRocketLeft;
    private Counter lifeCounterRocketRight;
    
    private int actCount = 0;  
    private boolean gameOver = false;
    
    private int level = 0;
    private int currQuestion = 0;

    public SpaceBattle() 
    {
        super(FRAME_WIDTH, FRAME_HEIGHT, 1);
        
        prepare();
    }
    
    public void prepare() 
    {
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        createStars(NUMBER_OF_GENERATED_STARS);
        
        lifeCounterRocketLeft = new Counter("Lifes left: ");
        addObject(lifeCounterRocketLeft, 50, 20);
        
        lifeCounterRocketRight = new Counter("Lifes left: ");
        addObject(lifeCounterRocketRight, FRAME_WIDTH - 50, 20);
        
        int rocketLeftX = 45; // Fixed X position for left rocket
        int rocketRightX = FRAME_WIDTH - 45; // Fixed X position for right rocket
        
        rocketLeft = new Rocket(RocketPositionEnum.LEFT.getPosition(), rocketLeftX, lifeCounterRocketLeft, "rocket_left.png",
                                "rocket_with_thrust_left.png", "1", "2", "3");
        addObject(rocketLeft, rocketLeftX, FRAME_HEIGHT / 2);
        
        rocketRight = new Rocket(RocketPositionEnum.RIGHT.getPosition(), rocketRightX, lifeCounterRocketRight, "rocket_right.png",
                                "rocket_with_thrust_right.png", "up", "down", "left");
        addObject(rocketRight, rocketRightX, FRAME_HEIGHT / 2);
        
        addAsteroids(START_ASTEROIDS_NUMBER);

        Explosion.initializeImages();
    }
    
    public void act()
    {
        actCount++;  // Increment the act counter every cycle

        if (actCount >= 400 && !gameOver) 
        {
            addAsteroids(1);  // Add an asteroid 
            actCount = 0;     // Reset the act counter
        }
    }
    
    private void addAsteroids(int count)
    {
        int centerX = getWidth() / 2;  // Calculate the center x-coordinate
        int centerY = getHeight() / 2; // Calculate the center y-coordinate
    
        int rangeX = getWidth() / 4;   // Define the range around the center for x
        int rangeY = getHeight() / 4;  // Define the range around the center for y
    
        for (int i = 0; i < count; i++) 
        {
            // Generate a random x and y position within the central box
            int x = centerX - rangeX / 2 + Greenfoot.getRandomNumber(rangeX);
            int y = centerY - rangeY / 2 + Greenfoot.getRandomNumber(rangeY);
    
            addObject(new Asteroid(), x, y);
        }
    }

    private void createStars(int number) 
    {
        GreenfootImage background = getBackground();     
        
        for(int i=0; i < number; i++) 
        {
             int x = Greenfoot.getRandomNumber( getWidth() );
             int y = Greenfoot.getRandomNumber( getHeight() );
             int color = 120 - Greenfoot.getRandomNumber(100);
             background.setColor(new Color(color,color,color));
             int size = Greenfoot.getRandomNumber(3) + 1;
             background.fillOval(x, y, size, size);
        }
    }
    
    public void gameOver(String looserName) 
    {
        
        String winnerName = "Draw";
        String winnerRocketPhotoFileName = "";
        
        gameOver = true;
        
        if (RocketPositionEnum.LEFT.getPosition().equals(looserName))
        {
            winnerName = "R2";
            winnerRocketPhotoFileName = "rocket_right.png";
        } else if (RocketPositionEnum.RIGHT.getPosition().equals(looserName))
        {
            winnerName = "R1";
            winnerRocketPhotoFileName = "rocket_left.png";
        }

        addObject(new GameOverPopUp(winnerName, winnerRocketPhotoFileName), getWidth() / 2, getHeight() / 2);
        removeObjectsAfterTheGameIsOver();
    }
    
    public void removeObjectsAfterTheGameIsOver()
    {
        List<Asteroid> asteroids = getObjects(Asteroid.class);
        List<Bullet> bullets = getObjects(Bullet.class);
    
        List<Actor> allObjects = new ArrayList<Actor>();
        allObjects.addAll(asteroids);
        allObjects.addAll(bullets);
    
        removeObjects(allObjects);
    }
    
    public void increaseLevel()
    {
        this.level++;
    }
    
    public void increaseQuestionCounter()
    {
        this.currQuestion++;
    }
    
    public int getLevel()
    {
        return this.level;
    }
    
    public int getCurrQuestion()
    {
        return this.currQuestion;
    }
}