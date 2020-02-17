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
import javax.swing.JPanel;
import model.Block;
import model.Point;
import model.TetrisPiece;

/**
 * This class is used to show the next piece  and info panel.
 * 
 * @author Yunhan Tu
 * @version 12/8
 *
 */
public class NextPiece extends JPanel implements Observer, PropertyChangeListener {   
    /** 
     * the Serial Version UID. 
     */
    private static final long serialVersionUID = 1L;

    /** 
     * number 10.
     */
    private static final int TEN = 10;

    /** 
     * number 20. 
     */
    private static final int TWENTY = 20;
    /**
     * number 1.
     */
    private static final int ONE = 1;
    /**
     * number 2.
     */
    private static final int TWO = 2;
    /**
     * number 3.
     */
    private static final int THREE = 3;
    /**
     * number 4.
     */
    private static final int FOUR = 4;
    
    /**
     * number 20.
     */
    private static final int SQUARE_SIZE = 20;

    /** 
     *  the number 260. 
     */
    private static final int KEY_LOCATION = 140;
    
    /** 
     *  the number 260. 
     */
    private static final int STATUS_KEY_LOCATION = 400;
    /** 
     * panel width.
     */
    private static final int PANEL_WIDTH = 100;
    
    /** 
     * multiple for clearing 1 line.
     */
    private static final int ONE_ROW = 40;
    
    /**
     *  multiple for clearing 2 lines. 
     */
    private static final int TWO_ROW = 100;
    
    /** 
     *  multiple for clearing 3 lines.
     */
    private static final int THREE_ROW = 300;
    
    /** 
     * multiple for clearing 4 lines. 
     */
    private static final int FOUR_ROW = 1200;
    
    /**
     * the Line to next Level.
     */
    private int myLineToNextLevel;

    /** 
     * the next piece. 
     */
    private TetrisPiece myTetrisPiece;

    
    /** 
     * total lines cleared. */
    private int myTotalLinesCleared;

    /** 
     * the level. 
     */
    private int myLevelNumber;

    /** 
     * the score. 
     */
    private int myScore;

    /**
     * list of infor.
     */
    private final List<String> myInfoList = new ArrayList<String>();
    
//    /**
//     * list of status.
//     */
//    private final List<String> myStatusList = new ArrayList<String>();

    /**
     * the next piece panel.
     */
    private final Rectangle2D myNextPiecePanel = new Rectangle2D.Double(0, 0, 115, 100);

    /**
     * This is the default constructor for NextPiecePanel.
     */
    public NextPiece() {
        super();
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_WIDTH));
        setBackground(Color.GRAY);    
        reset();
        myInfoList.add("Left : A or ¡û");
        myInfoList.add("Right : D or ¡ú");
        myInfoList.add("Rotate : W or ¡ü");
        myInfoList.add("Down : S or ¡ý");
        myInfoList.add("Drop : Space");  
        myInfoList.add("Pause: p");
        myInfoList.add("NewGame: N");
        myInfoList.add("EndGame: E");
        myInfoList.add("Music: M");
        
    }

//    /**
//     * add info in to list.
//     */
//    public void addInfoList() {
//        myInfoList.add("Left : A or ¡û");
//        myInfoList.add("Right : D or ¡ú");
//        myInfoList.add("Rotate : W or ¡ü");
//        myInfoList.add("Down : S or ¡ý");
//        myInfoList.add("Drop : Space");  
//        myInfoList.add("Pause: p");
//        myInfoList.add("NewGame: N");
//        myInfoList.add("EndGame: E");
//        myInfoList.add("Music: M");
//        myStatusList.add("Next level in:" + myLineToNextLevel);   
//        myStatusList.add("Lines Cleared: " + myTotalLinesCleared);
//        myStatusList.add("Your Score: " + highScoreString());
//        myStatusList.add("Level: " + levelString());
   
//    }
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        super.paintComponent(theGraphics);
        final Graphics2D g2D = (Graphics2D) theGraphics;

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setPaint(Color.WHITE);
        g2D.fill(myNextPiecePanel);

        if (null != myTetrisPiece) {
            final Point[] block = myTetrisPiece.getPoints();
            int widthPosition;
            int heightPosition;
            final int howRounded = 5;
            for (final Point p : block) {
                final int threethrty = 330; 
                final int threetwenty = 320;
                final int onefive = 15;
                final int twotwo = 22;
                final int onetwo = 12;
                final int three = 3;
                final int four = 4;               
                if (myTetrisPiece.getWidth() == four) {
                    widthPosition = onefive;
                    heightPosition = threetwenty;
                } else if (myTetrisPiece.getWidth() == three 
                                &&  myTetrisPiece.getBlock() != Block.O) {
                    widthPosition = twotwo;
                    heightPosition = threethrty;
                } else  {
                    widthPosition = onetwo;
                    heightPosition = threethrty;
                }
                final Rectangle2D tetrisBlock =
                                new Rectangle2D.Double(p.x() * SQUARE_SIZE + widthPosition,
                                                       (SQUARE_SIZE - p.y()) * SQUARE_SIZE
                                                                          - heightPosition,
                                                       SQUARE_SIZE, SQUARE_SIZE);
                g2D.setPaint(Color.BLACK);
                g2D.fill(tetrisBlock);
                g2D.setPaint(Color.BLACK); 
                g2D.draw(tetrisBlock);
                g2D.drawRoundRect(p.x() * SQUARE_SIZE + widthPosition,
                                  (SQUARE_SIZE - p.y()) * SQUARE_SIZE - heightPosition,
                                 SQUARE_SIZE, SQUARE_SIZE, howRounded, howRounded);            
            }
        }       

        final Rectangle2D textBox = new Rectangle2D.Double(5, 120, 95, 190);
        g2D.setPaint(Color.WHITE);
        g2D.fill(textBox);
        g2D.setPaint(Color.BLACK);
        g2D.draw(textBox);
        
        for (int i = 0; i < myInfoList.size(); i++) {
            g2D.drawString(myInfoList.get(i), TEN, KEY_LOCATION + TWENTY * i);
//            System.out.println(myInfoList.get(i));
        }
//   
        final Rectangle2D statBox = new Rectangle2D.Double(5, 320, 95, 110);
        g2D.setPaint(Color.WHITE);
        g2D.fill(statBox);
        g2D.setPaint(Color.BLACK);
        g2D.draw(statBox);
//        for (int i = myStatusList.size() - ONE; i >= 0; i--) {
//            g2D.drawString(myStatusList.get(i), TEN, STATUS_KEY_LOCATION - TWENTY * i);
//        }
//
        g2D.drawString(levelString(), TEN,
                       STATUS_KEY_LOCATION - TWENTY - TWENTY - TWENTY);
        g2D.drawString(highScoreString(), TEN,
                       STATUS_KEY_LOCATION - TWENTY - TWENTY);
        g2D.drawString(totalLinethatAreCleared(), TEN,
                       STATUS_KEY_LOCATION - TWENTY);
        g2D.drawString(checkCount(), TEN,       
                       STATUS_KEY_LOCATION);
   
    } 

    /**
     * return the current level.
     * @return the level 
     */
    private String levelString() {
        return "Level " + myLevelNumber;
    }

    /**
     * return the current Score.
     * 
     * @return total score.
     */
    private String highScoreString() {
        return "Score " + myScore;
    }
    
    /**
     * return the total lines currently cleared.
     * @return  the total lines currently cleared.
     */
    private String totalLinethatAreCleared() {
         
        return  "Line Clear " + myTotalLinesCleared;
    }
    
    /** 
     *  return how many lines to next level.
     * @return  how many lines to next level.
     */
    private String checkCount() {
       
        return "Next Level " + myLineToNextLevel;
    }

    @Override
    public void update(final Observable theObservable, final Object theObject) {

        if (theObject instanceof TetrisPiece) {
            myTetrisPiece = (TetrisPiece) theObject;
            myScore += FOUR;
            repaint();
        }
        if (theObject instanceof Integer[]) {
            final Integer[] intArray = (Integer[]) theObject;
            if (intArray.length == ONE) {
                myScore += ONE_ROW * myLevelNumber;
                myScore += FOUR;
            }
            if (intArray.length == TWO) {
                myScore += TWO_ROW * myLevelNumber;
                myScore += FOUR * TWO;
            }
            if (intArray.length == THREE) {
                myScore += THREE_ROW * myLevelNumber;
                myScore += FOUR * THREE;
            }
            if (intArray.length == FOUR) {
                myScore += FOUR_ROW * myLevelNumber;
                myScore += FOUR * FOUR;
            }
            for (int i = 0; i < intArray.length; i++) {
                myTotalLinesCleared++;
                myLineToNextLevel--;
                if (myTotalLinesCleared % FOUR == 0) {
                    myLevelNumber++;
                    myLineToNextLevel = FOUR;
                }
            }

        }
    }
    
    /**
     * Resets score  for a new game.
     */
    private void reset() {
        myTotalLinesCleared = 0;
        myLevelNumber = 1;
        myScore = 0;
        myLineToNextLevel = FOUR;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("NewGame".equals(theEvent.getPropertyName())) {
            reset();
        }
        
    }
}