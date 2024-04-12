//CSCI 145
//Lab 5: Mondrian Art

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CustomMondrianArt {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int X = 0;
    private static final int Y = 0;
    private static final Random rand = new Random();

    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
        drawSquare(X, Y, WIDTH, HEIGHT, panel);
    }
    
        /*Creating Parameters for drawSquare takes in
        x which at 0 is the  coordinate of the top left corner of the square being drawn
        y which at 0 is also th top left corner being drawn
        width the width of the current square
        height which the height of the current square
        panel is calling to the Graphics  panel set up in main
        */


    private static void drawSquare(int x, int y, int width, int height, DrawingPanel panel) {
        //Creating new draw call variabe with Graphics 
        Graphics draw = panel.getGraphics();
        // create something to determine if you split the area vertical, horizontal or both
        // add if statements to the bottom 3 else ifs, top 3 do not get touched
        
        if (width > 100 && height > 100) {
            //Creating a 33%-66% percent number of the current width or height
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
            //end of drawing first 4 corners to be split

        }else if (width > WIDTH / 2) {

            int split_width = randomSplit(width);
            
            // left
            drawSquare(x, y, split_width, height, panel);

            // right
            drawSquare(x + split_width, y, width - split_width, height, panel);
            
        } else if (height > HEIGHT / 2) {
            int split_height = randomSplit(height);
            
            // top
            drawSquare(x, y, width, split_height, panel);

            // bottom
            drawSquare(x, y + split_height, width, height - split_height, panel);
            //if it is more than 50 pixels it creates these rectangle from this position to the other postion 

        }else if (width > 50 && height > 50 && splitChance(height) < height && splitChance(width) < width) {
            //pre- Decaring new Variables to be called RandomOffest, new rectangle which is rect0 and rect1 
            //new int of fromX,fromY,toX,toY, which helps the system remmeber the space inbetween squares
            int randomOffset;
            Rectangle rect0, rect1;
            int fromX, fromY, toX, toY;
            fillInRect(x, y, width, height, draw);
            fillInRect(x, y, width, height, draw);

            //checks if witfh is less than height than adds unique split to go for rectangle calling the random offset which is a 33-66 percent version of the height 
            if (width < height) {
                randomOffset = randomSplit(height);
                rect0 = new Rectangle(x, y, width, randomOffset);
                rect1 = new Rectangle(x, y + randomOffset, width, height - randomOffset);
                fromX = x;
                fromY = y + randomOffset;
                toX = fromX + width;
                toY = fromY;
                
                }
             
        } else if (width > 100 && splitChance(width) < width) {
            
            int split_width = randomSplit(width);
            
            if (splitChance(width) < width) {
            // left
            drawSquare(x, y, split_width, height, panel);
            // right
            drawSquare(x + split_width, y, width - split_width, height, panel);
            } 
        } else if (height > 100 && splitChance(height) < height) {
            int split_height = randomSplit(height);
            if (splitChance(width) < width) {
            // top
            drawSquare(x, y, width, split_height, panel);

            // bottom
            drawSquare(x, y + split_height, width, height - split_height, panel);
            } 
        }
        
    }

    // returns a number that is between 100 and the cord * 1.5
    // to determine if the program should split or not
    private static int splitChance(int cord) {
        // Ensure the cord is at least 100
        cord = Math.max(100, cord);
        // Generate a random number between 100 and cord * 1.5
        int randomNumber = rand.nextInt((int) (cord * 1.5) - 100 + 1);
        return randomNumber;
    }


    //Pre: number being inputed should be put in the randomSplit(x or y or whatver
    //post: returns Int that is 33% - to 67% of the given number 
    private static int randomSplit(int cord) {
        int lowerBound = cord / 3;
        int upperBound = cord * 2 / 3;

        return rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    //Pre:fill in rect takes in 5 parameter of intx, int y, int width, int height, and draw from the Graphics engine
    //it creates a random gap width using ThreadLocalRandom calling current 
    //creatinga  random rgb value by taking a random percent of red grn and blue 
    private static void fillInRect(int x, int y, int width, int height, Graphics draw) {
        int gap = ThreadLocalRandom.current().nextInt(1,20); // Changed the gap width
        float red = rand.nextFloat();
        float grn = rand.nextFloat();
        float blu = rand.nextFloat();
        Color randomColor = new Color(red, grn, blu);
        draw.setColor(randomColor);
        draw.fillRect(x + gap, y + gap, width - 2*gap, height - 2*gap);
      }
    
    }

