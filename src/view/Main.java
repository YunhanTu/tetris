package view;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * The Main class for the tetris.
 * @author Yunhan Tu
 * @version 2017/11/23
 */
public final class Main {
    

    /**
     * This is the default constructor.
     */
    private Main() {
        throw new IllegalStateException();

    }

    /**
     * this sets the them for my GUI.
     */
    private static void setLookAndFeel() {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.put("swing.boldMetal", Boolean.FALSE);
        } catch (final ClassNotFoundException 
                        | InstantiationException 
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is the drive method that will call the GUI to start.
     * 
     * @param theArgs the input arguments.
     */
    public static void main(final String[] theArgs) {
        setLookAndFeel();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                new TetrisGUI().start();
            }
           
        });
    
    }

}
