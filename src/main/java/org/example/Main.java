package org.example;

import javax.swing.*;

public class Main {
    public static JFrame window;
    public static void main(String[] args) {
        System.out.println("Hello world!");
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tile Editor");
        //window.setIconImage();
        window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setUpPanel();
        gamePanel.startGameThread();
    }
}