package com.panjohnny.why;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KeyboardStuff implements KeyListener {

    CockRidingCrow main;
    public KeyboardStuff(CockRidingCrow main) {
        this.main = main;
    }
    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int velocity = 100;
        if(e.getKeyCode() == KeyEvent.VK_W) {
            main.y-=velocity;
        } else if(e.getKeyCode() == KeyEvent.VK_S) {
            main.y+=velocity;
        }
        if(e.getKeyCode() == KeyEvent.VK_A) {
            main.x-=velocity;
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            main.x+=velocity;
        }
        if(e.isAltDown() || e.getKeyCode() == KeyEvent.VK_WINDOWS) // I have better idea
        {
            try {
                new CockRidingCrow();
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        // secret way to close it
        if(e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_B)
            System.exit(0);

        main.haha++;
        main.party();
        // epilepsy warning!
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
