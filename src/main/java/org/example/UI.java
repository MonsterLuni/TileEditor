package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    TileManager tManager;
    public BufferedImage[] clicked = new BufferedImage[225];
    public BufferedImage selectedTile = null;
    public UI(GamePanel gp, TileManager tManager){
        this.gp = gp;
        this.tManager = tManager;
        setTiles();
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setColor(Color.WHITE);
        drawTitle();
        drawTileSelectionField();
        drawTileField();
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
    public int step = 40;
    public void drawTileSelectionField(){
        int y = startSelectionY;
        int x = startSelectionX;
        for(int i = 0; i < 5; i++){
            for(int l = 0; l < 15; l++){
                if (tManager.tiles[l + (i * 15)] != null){
                    g2.drawImage(tManager.tiles[l + (i * 15)],x,y,step,step,null);
                    if(tManager.tiles[l + (i * 15)] == selectedTile){
                        g2.setStroke(new BasicStroke(3));
                        g2.setColor(new Color(0, 255, 222));
                        g2.drawRect(x,y,step,step);
                    }
                    g2.setStroke(new BasicStroke());
                }
                g2.setColor(Color.white);
                g2.drawRect(x,y,step,step);
                x += step;
            }
            y += step;
            x = startSelectionX;
        }
    }
    public void drawTileField(){
        int y = startY;
        int x = startX;
        for(int i = 0; i < 15; i++){
            for(int l = 0; l < 15; l++){
                if(clicked[l + (i * 15)] != null){
                    g2.drawImage(clicked[l + (i * 15)],x,y,step,step,null);
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
