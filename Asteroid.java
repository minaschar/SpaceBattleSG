import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A rock in space
 * 
 * @author Poul Henriksen
 */
public class Asteroid extends SmoothMover
{
    /** Size of this asteroid */
    private int size;

    /** When the stability reaches 0 the asteroid will explode */
    private int stability;

    public Asteroid()
    {
        this(50);
    }
    
    public Asteroid(int size)
    {
        super(new Vector(Greenfoot.getRandomNumber(360), 2));
        setSize(size);
    }
    
    public Asteroid(int size, Vector speed)
    {
        super(speed);
        setSize(size);
    }
    
    public void act()
    {         
        move();
    }
    
    /**
     * Hit this asteroid dealing the given amount of damage.
     */
    public void hit(int damage, Rocket shooter) 
    {
        stability = stability - damage;
        if(stability <= 0) 
        {
            World worldObj = this.getWorld();
            breakUp();
            QuestionPool questionPoolObj = QuestionPool.getInstance();
            Question questionObj = questionPoolObj.getQuestion();
            QuestionPopUp popUp = new QuestionPopUp(questionObj);
            worldObj.addObject(popUp, worldObj.getWidth() / 2, worldObj.getHeight() / 2);
            
            if (popUp.isCorrectAnswer()) 
            {
                shooter.increaseLifeByOne();
            }
            
            worldObj.removeObject(popUp);
        }
    }
    
    /**
     * Break up this asteroid. If we are still big enough, this will create two
     * smaller asteroids. If we are small already, just disappear.
     */
    private void breakUp() 
    {
        Greenfoot.playSound("rock-break.mp3");
        
        if(size <= 16) 
        {
            getWorld().removeObject(this);
        } else 
        {
            int r = getMovement().getDirection() + Greenfoot.getRandomNumber(45);
            double l = getMovement().getLength();
            Vector speed1 = new Vector(r + 60, l * 1.2);
            Vector speed2 = new Vector(r - 60, l * 1.2);        
            Asteroid a1 = new Asteroid(size/2, speed1);
            Asteroid a2 = new Asteroid(size/2, speed2);
            getWorld().addObject(a1, getX(), getY());
            getWorld().addObject(a2, getX(), getY());        
            a1.move();
            a2.move();
        
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Set the size of this asteroid. Note that stability is directly
     * related to size. Smaller asteroids are less stable.
     */
    public void setSize(int size) 
    {
        stability = size;
        this.size = size;
        GreenfootImage image = getImage();
        image.scale(size, size);
    }
    
    /**
     * Return the current stability of this asteroid. (If it goes down to 
     * zero, it breaks up.)
     */
    public int getStability() 
    {
        return stability;
    }
}