package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class TileManager {
    JFileChooser chooser;
    public BufferedImage[] tiles = new BufferedImage[2500];
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
    public void CreateFile(){
            try {
                File myObj = new File("filename.txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
    public void WriteToFile() {
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            String map = "";
            int percentage = 0;

            for(int i = 0; i < 50; i++){
                for(int l = 0; l < 50; l++){
                    if(gp.ui.clicked[l + (i * gp.ui.rowYLength)] != null){
                        int j = 0;
                        for (BufferedImage tile:
                            tiles) {
                            if(tile == gp.ui.clicked[l + (i * gp.ui.rowYLength)]){
                                map = map + j + " ";
                            }
                            j++;
                        }
                    }
                    else {
                        map = map + 0;
                        map = map + " ";
                    }

                }
                percentage += 2;
                System.out.println("loading " + percentage + "%");
                map = map + "\n";
            }

            myWriter.write(map);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
