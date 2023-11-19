package org.example;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseMotionListener, MouseListener {
    GamePanel gp;
    UI ui;
    public MouseHandler(GamePanel gp, UI ui){
        this.gp = gp;
        this.ui = ui;
    }
    public void getSelectionField(Point location){
        int fieldX = (int) Math.floor((double)(location.x - ui.startSelectionX) / ui.step);
        int fieldY = (int) Math.floor((double)(location.y - ui.startSelectionY) / ui.step);
        ui.selectedTile = gp.tManager.tiles[fieldX + (fieldY * 15)];
    }
    public void getField(Point location){
        int fieldX = (int) Math.floor((double)(location.x - ui.startX) / ui.step) + ui.differenceX;
        int fieldY = (int) Math.floor((double)(location.y - ui.startY) / ui.step) + ui.differenceY;
        if(ui.selectedTile != null){
            ui.clicked[fieldX + (fieldY * ui.rowYLength)] = ui.selectedTile;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
            Point location = e.getLocationOnScreen();
            if(location.x > ui.startX && location.x < ui.startX + (ui.step * 15) &&
                    location.y > ui.startY && location.y < ui.startY + (ui.step * 15)){
                // clicked inside of Field
                getField(location);
            }
            else if(location.x > ui.startSelectionX && location.x < ui.startSelectionX + (ui.step * 15) &&
                    location.y > ui.startSelectionY && location.y < ui.startSelectionY + (ui.step * 5)){
                getSelectionField(location);
            }
            else if(location.x > 1000 && location.x < 1000 + (ui.step) &&
                    location.y > 200 && location.y < 200 + (ui.step)){
                gp.tManager.CreateFile();
                gp.tManager.WriteToFile();
            }
            else if(location.x > ui.startEntireFieldX && location.x < ui.startEntireFieldX + (15 * 50) &&
                    location.y > ui.startY && location.y < ui.startY + (15 * 50)){
                getFieldEntireField(location);
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

    @Override
    public void mouseDragged(MouseEvent e) {
        Point location = e.getLocationOnScreen();
        if(location.x > ui.startX && location.x < ui.startX + (ui.step * 15) &&
                location.y > ui.startY && location.y < ui.startY + (ui.step * 15)){
            // clicked inside of Field
            getField(location);
        }
        else if(location.x > ui.startEntireFieldX && location.x < ui.startEntireFieldX + (15 * 50) &&
                location.y > ui.startY && location.y < ui.startY + (15 * 50)){
            getFieldEntireField(location);
        }
    }
    public void getFieldEntireField(Point location){
        int fieldX = (int) Math.floor((double)(location.x - ui.startEntireFieldX) / 15);
        int fieldY = (int) Math.floor((double)(location.y - ui.startY) / 15);

        ui.differenceX = fieldX - 7;
        ui.differenceY = fieldY - 7;
        if(ui.differenceX < 0){
            ui.differenceX = 0;
        }
        if(ui.differenceY < 0){
            ui.differenceY = 0;
        }
        if(ui.differenceX > 35){
            ui.differenceX = 35;
        }
        if(ui.differenceY > 35){
            ui.differenceY = 35;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
