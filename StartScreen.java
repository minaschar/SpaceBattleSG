import greenfoot.*;

public class StartScreen extends World 
{
    
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;

    public StartScreen() 
    {
        super(FRAME_WIDTH, FRAME_HEIGHT, 1);  
        
        GreenfootImage background = getBackground();
        GreenfootImage logo = new GreenfootImage("space_battle_logo.png");
        logo.scale(200, 200);
        int x = (FRAME_WIDTH - logo.getWidth()) / 2;
        int y = 30; 

        background.drawImage(logo, x, y);
        
        showText("Welcome to Space Battle!!!", 500, 250);  
        showText("It's a 2 players game, 1 vs 1. Each Player has 3 lifes. Be careful of the asteroids!", 500, 300);
        showText("Player on the LEFT (R1): 1 (up) | 2 (down) | 3 (fire)", 500, 350);
        showText("Player on the RIGHT (R2): 'up' arrow (up) | 'down' arrow (down) | 'left' arrow (fire)", 500, 400);
        showText("Press \"SPACE\" to start", 500, 500);   
    }

    public void act() 
    {
        if (Greenfoot.isKeyDown("space")) 
        {
            Greenfoot.setWorld(new SpaceBattle()); // Switch to the game world
        }
    }
}