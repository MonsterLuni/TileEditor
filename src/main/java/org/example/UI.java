package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    TileManager tManager;
    public BufferedImage[] clicked = new BufferedImage[2500];
    public BufferedImage selectedTile = null;
    public BufferedImage defaultTile;
    public UI(GamePanel gp, TileManager tManager){
        this.gp = gp;
        this.tManager = tManager;
        setTiles();
        defaultTile = tManager.tiles[0];
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setColor(Color.WHITE);
        drawTitle();
        drawTileSelectionField();
        drawTileField();
        drawEndButton();
        drawEntireField();
        drawFieldOfViewInEntireField();
    }
    public void setTiles(){
        tManager.loadTiles();
    }
    public void drawTitle(){
        makeTextCenterX("Tile Editor",0,100,g2,new Font("arial",Font.ITALIC,24),Color.white);
    }
    public int startY = 200;
    public int startSelectionY = 850;
    public int startSelectionX = 200;
    public int startX = 200;
    public final int step = 40;
    int differenceX = 0;
    int differenceY = 0;
    public boolean saved = false;
    public int rowYLength = 50;
    public void drawEndButton(){

        if(saved){
            g2.drawString("Saved!",1010,190);
            g2.setColor(Color.green);
            g2.fillRect(1000,200,step*2,step);
        }
        else {
            g2.drawString("Save",1010,190);
            g2.setColor(Color.red);
            g2.fillRect(1000,200,step*2,step);
        }
    }

    public void drawTileSelectionField(){
        int y = startSelectionY;
        int x = startSelectionX;
        for(int i = 0; i < 5; i++){
            for(int l = 0; l < 15; l++){
                if (tManager.tiles[l + (i * rowYLength)] != null){
                    g2.setStroke(new BasicStroke(3));
                    if(tManager.tiles[l + (i * rowYLength)] == defaultTile){
                        g2.setColor(new Color(255, 0, 0));
                        g2.fillRect(x,y,step,step);
                    }
                    else if(tManager.tiles[l + (i * rowYLength)] == selectedTile){
                        g2.setColor(new Color(0, 255, 222));
                        g2.drawRect(x,y,step,step);
                    }
                    g2.setStroke(new BasicStroke());
                    g2.drawImage(tManager.tiles[l + (i * rowYLength)],x,y,step,step,null);
                }
                g2.setColor(Color.white);
                g2.drawRect(x,y,step,step);
                x += step;
            }
            y += step;
            x = startSelectionX;
        }
    }
    public void moveCanvas(String axis, int amount) {
        System.out.println(axis + " " + amount);
        if(amount > 0){
            if(Objects.equals(axis, "X")){
                differenceX++;
                if(differenceX > 35){
                    differenceX = 35;
                }
            }
            else {
                differenceY++;
                if(differenceY > 35){
                    differenceY = 35;
                }
            }
        }
        else {
            if(Objects.equals(axis, "X")){
                differenceX--;
                if(differenceX < 0){
                    differenceX = 0;
                }
            }
            else {
                differenceY--;
                if(differenceY < 0){
                    differenceY = 0;
                }
            }
        }
    }
    int startEntireFieldX = 1100;
    public void drawEntireField(){
        g2.setStroke(new BasicStroke(0.5f));
        int y = startY;
        int x = startEntireFieldX;
        int step = 15;
        g2.drawRect(startEntireFieldX,startY,step*50,step*50);
        for(int i = 0; i < 50; i++){
            for(int l = 0; l < 50; l++){
                if(clicked[l + (i * rowYLength)] != null){
                    g2.drawImage(clicked[l + (i * rowYLength)],x,y,step,step,null);
                }
                g2.setColor(Color.white);
                x += step;
            }
            y += step;
            x = startEntireFieldX;
        }
        g2.setStroke(new BasicStroke());
    }
    public void drawFieldOfViewInEntireField(){
        int step = 15;
        g2.setColor(Color.blue);
        g2.drawRect(startEntireFieldX + (step*differenceX),startY + (step*differenceY),15*step,15*step);
    }
    public void drawTileField(){
        int y = startY;
        int x = startX;
        for(int i = differenceY; i < 15 + differenceY; i++){
            for(int l = differenceX; l < 15 + differenceX; l++){
                if(clicked[l + (i * rowYLength)] != null){
                    g2.drawImage(clicked[l + (i * rowYLength)],x,y,step,step,null);
                }
                g2.setColor(Color.white);
                g2.drawRect(x,y,step,step);
                x += step;
            }
            y += step;
            x = startX;
        }
    }
    public void makeTextCenterX(String message, int x, int y,Graphics2D g2, Font font, Color color){
        this.g2 = g2;
        g2.setFont(font);
        g2.setColor(color);
        int textLength = (int)g2.getFontMetrics().getStringBounds(message, g2).getWidth();
        g2.drawString(message,(gp.screenWidth/2 - textLength/2) + x,y);
    }
}
