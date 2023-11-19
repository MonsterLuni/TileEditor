package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    public TileManager tManager = new TileManager(this);
    public UI ui = new UI(this,this.tManager);
    MouseHandler mouseL = new MouseHandler(this, this.ui);
    Thread editThread;
    BufferedImage tempScreen;
    Graphics2D g2;
    // FPS
    int drawCount = 0;
    long timer = 0;
    int FPS = 60;
    int currentFps = FPS;
    public final int screenWidth = 1920;
    public final int screenHeight = 1080;
    public double drawInterval = (double) 1000000000 /FPS;
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseL);
        this.setFocusable(true);
    }
    @Override
    public void run() {
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(editThread != null){
            currentTime = System.nanoTime(); // returns the time from the Java Engine
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if(delta >= 1){
                update();
                drawToTempScreen(g2);
                drawToScreen();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                currentFps = drawCount;
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void setFullScreen(){
        // GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
    public void update(){
    }
    public void setUpPanel(){
        tempScreen = new BufferedImage(screenWidth, screenHeight,BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();
        setFullScreen();
    }
    public void startGameThread(){
        editThread = new Thread(this);
        editThread.start(); // calls run()
    }
    public void drawToTempScreen(Graphics g){
        ui.draw(g2);
    }
    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen,0,0, screenWidth,screenHeight,null);
        g.dispose();
    }
}
