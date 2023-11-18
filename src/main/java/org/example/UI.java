package org.example;

import javax.xml.stream.Location;
import java.awt.*;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    public Boolean[] selected = new Boolean[225];
    public UI(GamePanel gp){
        this.gp = gp;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        drawTitle();
        drawTileField();
    }
    public void drawTitle(){
        makeTextCenterX("Tile Editor",0,100,g2,new Font("arial",Font.ITALIC,24),Color.white);
    }
    public int startY = 200;
    public int startX = 200;
    public int step = 40;
    public void drawTileField(){
        int y = startY;
        int x = startX;
        for(int i = 0; i < 15; i++){
            for(int l = 0; l < 15; l++){
                if(selected[l + (i * 15)] != null){
                    if(selected[l + (i * 15)]){
                        g2.fillRect(x,y,step,step);
                    }
                }
                g2.drawRect(x,y,step,step);
                x += step;
            }
            y += step;
            x = startX;
        }
    }
    public void drawSelectedTileField(){}
    public void makeTextCenterX(String message, int x, int y,Graphics2D g2, Font font, Color color){
        this.g2 = g2;
        g2.setFont(font);
        g2.setColor(color);
        int textLength = (int)g2.getFontMetrics().getStringBounds(message, g2).getWidth();
        g2.drawString(message,(gp.screenWidth/2 - textLength/2) + x,y);
    }
}
