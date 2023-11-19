package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TileManager {
    JFileChooser chooser;
    public BufferedImage[] tiles = new BufferedImage[75];
    GamePanel gp;
    public TileManager(GamePanel gp){
        this.gp = gp;
    }
    public void loadTiles(){
        int i = 0;
        chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(true);
        chooser.showOpenDialog(gp);
        for(File file : chooser.getSelectedFiles()){
            try{
                tiles[i] = ImageIO.read(file);
            }catch (IOException e){
                e.printStackTrace();
            }
            i++;
        }
        System.out.println(Arrays.toString(tiles));
    }
}
