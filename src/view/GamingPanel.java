/*
 * TCSS 305 - Tetris
 * Part-A
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.JPanel;
import model.Block;
import model.Board;

/**
 * This class is use to show the Tetris game.
 * 
 * @author Yunhan Tu
 * @version 2017/12/8
 *
 */
public class GamingPanel extends JPanel implements Observer, PropertyChangeListener {
    /** 
     * the Serial Version UID.  
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * number 2.
     */
    private static final int TWO = 2;

    /** 
     * the block size.
     */
    private static final int SIZE = 20;
    
    /**  
     * blocks. 
     */
    private final List<Block[]> myBlockList = new ArrayList<Block[]>();

    /** 
     * The board. 
     */
    private Board myBoard;


    /**
     * constructor for GamePanel.
     */
    public GamingPanel() {
        super();
        myBoard = new Board();
        setPreferredSize(new Dimension(myBoard.getWidth() * SIZE + SIZE, 
                                       myBoard.getHeight() * SIZE + SIZE * TWO));
        setBackground(Color.WHITE);
       
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2D = (Graphics2D) theGraphics;

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        final double width = myBoard.getWidth() * SIZE;
        final double height = myBoard.getHeight() * SIZE;
        final Rectangle2D gameBorder = new Rectangle2D.Double(0.0, 0.0, width, height);
        g2D.draw(gameBorder); 
        
        for (int boardHeight = 0; boardHeight < myBlockList.size(); boardHeight++) { //columns
            
            final Block[] temp1 = myBlockList.get(boardHeight);
            final int howRounded = 5;

            for (int i = 0; i < temp1.length; i++) {
                //--------------------------------------------
                if (temp1[i] != null) {
                    final Rectangle2D rTemp =
                                    new Rectangle2D.Double(SIZE * i,
                                                           (SIZE - boardHeight) * SIZE - SIZE,
                                                           SIZE, SIZE);
                    g2D.setPaint(changeColor()); 
                    g2D.fill(rTemp);
                    g2D.setPaint(Color.BLACK); 
                    g2D.draw(rTemp);
                    g2D.drawRoundRect(SIZE * i, (SIZE - boardHeight) * SIZE - SIZE, SIZE, SIZE,
                                      howRounded, howRounded);
                    
                }
            }
        }      
    } 

    /**
     * This grabs a enum value and spits out its color.
     * @return color, depends on what enum is in the parameter.
     */
    public Color changeColor() {
        
        final Random rand = new Random();
        final int r = rand.nextInt(255);
        final int g = rand.nextInt(255);
        final int b = rand.nextInt(255);
       
        return new Color(r, g, b);
    }
    
    @Override
    public void update(final Observable theObservable, final Object theObject) {
        myBoard = (Board) theObservable;
        if (theObject instanceof List) {
            myBlockList.clear();
            myBlockList.addAll((List<Block[]>) theObject);
        }          
    } 

    
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {

    }
    
    
}