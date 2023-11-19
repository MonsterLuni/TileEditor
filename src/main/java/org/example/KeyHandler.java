package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    UI ui;
    public KeyHandler(GamePanel gp, UI ui){
        this.gp = gp;
        this.ui = ui;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W -> ui.moveCanvas("Y",-1);
            case KeyEvent.VK_A -> ui.moveCanvas("X",-1);
            case KeyEvent.VK_S -> ui.moveCanvas("Y",1);
            case KeyEvent.VK_D -> ui.moveCanvas("X",1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
