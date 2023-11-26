package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    public TileManager tManager;
    UI ui;
    private Thread editThread;
    private BufferedImage tempScreen;
    private Graphics2D g2;
    private long timer = 0;
    public final int screenWidth = 1920;
    public final int screenHeight = 1080;
    private int screenWidth2 = screenWidth;
    private int screenHeight2 = screenHeight;
    public GamePanel(){
        tManager = new TileManager(this);
        ui = new UI(this,this.tManager);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        MouseHandler mouseL = new MouseHandler(this, this.ui);
        this.addMouseMotionListener(mouseL);
        this.addMouseListener(mouseL);
        KeyHandler keyL = new KeyHandler(this, this.ui);
        this.addKeyListener(keyL);
        this.setFocusable(true);
    }
    @Override
    public void run() {
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(editThread != null){
            currentTime = System.nanoTime(); // returns the time from the Java Engine
            int FPS = 60;
            double drawInterval = (double) 1000000000 / FPS;
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if(delta >= 1){
                drawToTempScreen();
                drawToScreen();
                delta--;
            }
            if(timer >= 1000000000){
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
    public void setUpPanel(){
        setFullScreen();
        tempScreen = new BufferedImage(screenWidth2, screenHeight2,BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();
    }
    public void startGameThread(){
        editThread = new Thread(this);
        editThread.start(); // calls run()
    }
    public void drawToTempScreen(){
        ui.draw(g2);
    }
    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen,0,0, screenWidth2,screenHeight2,null);
        g.dispose();
    }
}
