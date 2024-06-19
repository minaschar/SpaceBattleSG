import greenfoot.*;  

public class Bullet extends SmoothMover
{
    private static final int DAMAGE = 16;
    
    private Rocket shooter;
    private int life = 60;
    
    public Bullet()
    {
    }
    
    public Bullet(Vector speed, int rotation, Rocket shooter)
    {
        super(speed);
        this.shooter = shooter;
        setRotation(rotation);
        addForce(new Vector(rotation, 15));
        Greenfoot.playSound("shot.mp3");
    }
    
    public void act() {
        if (life <= 0 || atWorldEdge()) 
        {
            getWorld().removeObject(this);
        } else 
        {
            life--;
            move();
            checkAsteroidHit();
            
            if (getWorld() != null) 
            {  // Check if the bullet is still in the world
                checkRocketHit();
            }
        }
    }
    
    private void checkAsteroidHit()
    {
        Asteroid asteroid = (Asteroid) getOneIntersectingObject(Asteroid.class);
        
        if (asteroid != null)
        {
            getWorld().removeObject(this);
            asteroid.hit(DAMAGE, this.shooter);
        }
    }
    
    private void checkRocketHit() 
    {
        Rocket rocket = (Rocket) getOneIntersectingObject(Rocket.class);
        
        if (rocket != null) 
        {
            getWorld().removeObject(this);
            rocket.hit(); 
        }
    }
    
    private boolean atWorldEdge()
    {
        if(getX() <= 0 || getX() >= getWorld().getWidth() - 40) 
        {
            return true;
        }
        
        return false;
    }
}