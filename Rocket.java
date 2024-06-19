import greenfoot.*;  

public class Rocket extends SmoothMover
{
    private static final int GUN_RELOAD_TIME = 5;     

    private String position; // "left" or "right"
    private int fixedX;
    private Counter lifeCounter;
    private int reloadDelayCount;              
    private int lifes; 
    
    private String keyMoveUp;
    private String keyMoveDown;
    private String keyFire;
    
    private GreenfootImage rocket;
    private GreenfootImage rocketWithThrust;
    private GreenfootImage labeledImage; 
    
    public Rocket(String position, int fixedX, Counter lifeCounter, String rocketFileName, String rocketWithThrustFileName, 
                  String keyMoveUp, String keyMoveDown, String keyFire)
    {
        reloadDelayCount = 5;
        lifes = 3; // Set the initial number of lives
        
        this.position = position;
        this.fixedX = fixedX;
        this.lifeCounter = lifeCounter;
        this.lifeCounter.setValue(lifes); 
        this.keyMoveUp = keyMoveUp;
        this.keyMoveDown = keyMoveDown;
        this.keyFire = keyFire;
        
        rocket = new GreenfootImage(rocketFileName);
        rocket.scale(90, 50);
        rocketWithThrust = new GreenfootImage(rocketWithThrustFileName);
        rocketWithThrust.scale(90, 50);
        
        setImage(rocket);
    }

    public void act()
    {
        checkKeys();
        checkCollision();
        reloadDelayCount++;
    }
    
    private void checkKeys() 
    {
        if (getX() != fixedX) 
        {
            setLocation(fixedX, getY()); 
        }
        
        int moveDistance = 5; // Distance to move on each key press
        int upperBound = 0 + moveDistance; // Set the upper boundary limit
        int lowerBound = getWorld().getHeight() - moveDistance; // Set the lower boundary limit
        
        if (Greenfoot.isKeyDown(keyMoveUp) && getY() > upperBound) 
        { 
            setLocation(fixedX, getY() - moveDistance);
            setImage(rocketWithThrust);
        }
            
        if (Greenfoot.isKeyDown(keyMoveDown) && getY() < lowerBound) 
        { 
            setLocation(fixedX, getY() + moveDistance);
            setImage(rocketWithThrust);
        }
            
        if (Greenfoot.isKeyDown(keyFire)) 
        {
            fire();
        }
            
        if (!Greenfoot.isKeyDown(keyMoveUp) && !Greenfoot.isKeyDown(keyMoveDown)) 
        {
            setImage(rocket);
        }
    }
    
    private void checkCollision() 
    {
        Actor a = getOneIntersectingObject(Asteroid.class);
        
        if (a != null) 
        {
            lifeCounter.setValue(0);  // Update counter display
            SpaceBattle space = (SpaceBattle) getWorld();
            space.addObject(new Explosion(), getX(), getY());
            space.removeObject(this);
            space.gameOver(position);
        }
    }
    
    public void hit() 
    {
        lifes--;
        lifeCounter.setValue(lifes);  // Update counter display
        
        if (lifes <= 0) 
        {
            SpaceBattle space = (SpaceBattle) getWorld();
            space.addObject(new Explosion(), getX(), getY());
            space.removeObject(this);
            space.gameOver(position);
        }
    }
    
    private void fire() 
    {
        if (reloadDelayCount >= GUN_RELOAD_TIME) 
        {
            int bulletDirection = 0; // Default direction for left-facing rocket
            
            if (RocketPositionEnum.RIGHT.getPosition().equals(position)) 
            {
                bulletDirection = 180; // Adjust for right-facing rocket
            }
            
            Bullet bullet = new Bullet(getMovement().copy(), bulletDirection, this);
            int fixedPosition = RocketPositionEnum.RIGHT.getPosition().equals(position) ? -40 : 40;
            getWorld().addObject(bullet, getX() + fixedPosition, getY());
            bullet.move();
            reloadDelayCount = 0;
        }
    }
    
    public void increaseLifeByOne()
    {
        lifes++;
        lifeCounter.setValue(lifes);  // Update counter display
    }
    
    public int getLifes()
    {
        return lifes;
    }
}