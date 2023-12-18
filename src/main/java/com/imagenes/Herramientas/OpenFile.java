package com.imagenes.herramientas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpenFile {
    public static Image abrirImagen (){
    
          try {
            FileNameExtensionFilter filtro =
                    new FileNameExtensionFilter("Imagenes","jpg","jpeg","png","bmp");
            JFileChooser selector = new JFileChooser();
            File Folder = new File("src/TestImages");
            selector.setCurrentDirectory(Folder);
            selector.addChoosableFileFilter(filtro);
            selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int res = selector.showOpenDialog(null);
            if (res == 1 ){
                return null;
            }
            File archivo = selector.getSelectedFile();
            BufferedImage bi = ImageIO.read(archivo);
            return ImageSettings.toImage(bi);
        } catch (IOException ex) {
            Logger.getLogger(OpenFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
    
    public static Image abrirImagenAleatoria(String folderPath) {
        try {
            File folder = new File(folderPath);
            File[] files = folder.listFiles();

            if (files == null || files.length == 0) {
                System.err.println("No hay imágenes en la carpeta especificada.");
                return null;
            }

            // Selecciona un archivo aleatorio
            Random random = new Random();
            File randomFile = files[random.nextInt(files.length)];

            BufferedImage bi = ImageIO.read(randomFile);
            return ImageSettings.toImage(bi);
        } catch (IOException ex) {
            Logger.getLogger(OpenFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
