import java.awt.*;
import java.util.Random;

public class MondrianArt {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int X = 0;
    private static final int Y = 0;

    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
        drawSquare(X, Y, WIDTH, HEIGHT, panel);
    }

    private static void drawSquare(int x, int y, int width, int height, DrawingPanel panel) {
        Graphics draw = panel.getGraphics();

        // create something to determine if you split the area vertical, horizontal or both
        // add if statements to the bottom 3 else ifs, top 3 do not get touched
        
        int splitChanceWidth = splitChance(width);
        int splitChanceHeight = splitChance(height);

        if (width > WIDTH / 2 && height > HEIGHT / 2) {
            int split_width = randomSplit(width);
            int split_height = randomSplit(height);

            // top left
            drawSquare(x, y, split_width, split_height, panel);

            // top right
            drawSquare(x + split_width, y, width - split_width, split_height, panel);

            // bottom left
            drawSquare(x, y + split_height, split_width, height - split_height, panel);

            // bottom right
            drawSquare(x + split_width, y + split_height, width - split_width, height - split_height, panel);

        }

        else if (width > WIDTH / 2) {
            int split_width = randomSplit(width);
            
            // left
            drawSquare(x, y, split_width, height, panel);

            // right
            drawSquare(x + split_width, y, width - split_width, height, panel);
            
        }

        else if (height > HEIGHT / 2) {
            int split_height = randomSplit(height);
            
            // top
            drawSquare(x, y, width, split_height, panel);

            // bottom
            drawSquare(x, y + split_height, width, height - split_height, panel);
            
        }

        else if (width > 100 && height > 100 && splitChanceWidth < width && splitChanceHeight < height) {
            int split_width = randomSplit(width);
            int split_height = randomSplit(height);
            
            // top left
            drawSquare(x, y, split_width, split_height, panel);

            // top right
            drawSquare(x + split_width, y, width - split_width, split_height, panel);

            // bottom left
            drawSquare(x, y + split_height, split_width, height - split_height, panel);

            // bottom right
            drawSquare(x + split_width, y + split_height, width - split_width, height - split_height, panel);
             
        }

        else if (width > 100 && splitChanceWidth < width) {
            int split_width = randomSplit(width);
            if (splitChance(width) < width) {
            // left
            drawSquare(x, y, split_width, height, panel);
            // right
            drawSquare(x + split_width, y, width - split_width, height, panel);
            } 
        }

        else if (height > 100 && splitChanceHeight < height) {
            int split_height = randomSplit(height);
            if (splitChance(width) < width) {
            // top
            drawSquare(x, y, width, split_height, panel);

            // bottom
            drawSquare(x, y + split_height, width, height - split_height, panel);
            } 
        }

        else {
            Color color = colorChance();
            if (color != null) {
                draw.setColor(color);
                draw.fillRect(x, y, width, height);
                draw.setColor(Color.BLACK);
                draw.drawRect(x, y, width, height);
            } else {
                draw.drawRect(x, y, width, height);
            }
        }
    }

    // returns a number that is between 100 and the cord * 1.5
    // to determine if the program should split or not
    private static int splitChance(int cord) {
        // Ensure the cord is at least 100
        cord = Math.max(100, cord);
        // Generate a random number between 100 and cord * 1.5
        Random rand = new Random();
        int randomNumber = rand.nextInt((int) (cord * 1.5) - 100 + 1);
        return randomNumber;
    }


    // returns number that is 33% - to 67% of the given number
    private static int randomSplit(int cord) {
        int lowerBound = cord / 3;
        int upperBound = cord * 2 / 3;
        Random rand = new Random();

        return rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    // 3/6 chance it will return a color
    private static Color colorChance() {
        Random rand = new Random();
        int random = rand.nextInt(6);
        if (random == 5) {
            return Color.RED;
        } else if (random == 2) {
            return Color.BLUE;
        } else if (random == 3) {
            return Color.YELLOW;
        } else {
            return null;
        }
    }
}
