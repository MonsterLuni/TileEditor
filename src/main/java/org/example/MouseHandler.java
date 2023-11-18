package org.example;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    GamePanel gp;
    UI ui;
    public MouseHandler(GamePanel gp, UI ui){
        this.gp = gp;
        this.ui = ui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point location = e.getLocationOnScreen();
        if(location.x > ui.startX && location.x < ui.startX + (ui.step * 15) &&
            location.y > ui.startY && location.y < ui.startY + (ui.step * 15)){
            // clicked inside of Field
            getField(location);
        }
    }
    public void getField(Point location){
        int fieldX = (int) Math.floor((double)(location.x - ui.startX) / ui.step);
        int fieldY = (int) Math.floor((double)(location.y - ui.startY) / ui.step);
        ui.selected[fieldX + (fieldY * 15)] = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(MouseEvent.MOUSE_DRAGGED == 506) {
            //System.out.println("Dragged");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
