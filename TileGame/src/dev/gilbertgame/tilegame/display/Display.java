package dev.gilbertgame.tilegame.display;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Display {

    private JFrame frame;//add canvas to this window
    private Canvas canvas;//draw elements on here

    private String title;
    private int width, height;

    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }

    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//to terminate the process on close
        frame.setResizable(false);//we dont want to be able to change the shape of the window
        frame.setLocationRelativeTo(null);//to appear in the center
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);// a bug needed for earlier versions of java-- can be removed

        frame.add(canvas);
        frame.pack();

        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);
    }

    public Canvas getCanvas(){
        return canvas;
    }
    public JFrame getFrame(){
        return frame;
    }
}
