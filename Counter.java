import greenfoot.*;  

public class Counter extends Actor 
{
    private int value = 0;
    private String text;

    public Counter(String prefix) 
    {
        text = prefix;
        updateImage();
    }
    
    private void updateImage() 
    {
        GreenfootImage image = new GreenfootImage(text + value, 20, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(image);
    }

    public void setValue(int newValue) 
    {
        value = newValue;
        updateImage();
    }

    public int getValue() 
    {
        return value;
    }
}