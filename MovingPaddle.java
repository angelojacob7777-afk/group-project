import greenfoot.*;

public class MovingPaddle extends Actor
{
    private int width;
    private int height;
    private int dx;

    public MovingPaddle(int width, int height, boolean movingRight)
    {
        this.width = width;
        this.height = height;
        dx = movingRight ? 1 : -1;
        
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(Color.BLACK);
        image.fill();
        setImage(image);
    }

    public void act()
    {
        setLocation(getX() + dx, getY());

        World world = getWorld();
        
      if (dx > 0 && getX() >= world.getWidth() - width/2)
        {
            respawnOnOtherSide(world, true);
        }
        else if (dx < 0 && getX() + width/2 < 0)
        {
            respawnOnOtherSide(world, false);
        }
    }

    private void respawnOnOtherSide(World world, boolean enterFromLeft)
    {
        int newX = enterFromLeft ? width/2 : world.getWidth() - width/2;
        int newY = Greenfoot.getRandomNumber(world.getHeight() - 150);

        world.removeObject(this);
        world.addObject(new MovingPaddle(width, height, enterFromLeft), newX, newY);
    }
}