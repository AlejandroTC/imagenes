package com.imagenes.morfologicas;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.imagenes.convolucion.ConvolucionFive;
import com.imagenes.herramientas.Basicas;
import com.imagenes.herramientas.ImageSettings;
import com.imagenes.herramientas.OpenFile;
public class GUIMorfologia extends JPanel{
    private static Image Original;
    private static Image RandImage;
    private static JPanel panelOriginalImage; 

    public GUIMorfologia() {
        setName("Morfologia");
        setSize(900, 900);
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        initComponents();
    }

    private void initComponents() {
        // Panel de Botones - Imágenes
        JPanel panelImageButtons = new JPanel();
        JButton selectImage = new JButton("Seleccionar Imagen");
        panelImageButtons.add(selectImage);
        JButton testImage = new JButton("Usar imagen de prueba");
        panelImageButtons.add(testImage);

        // Funcionamiento Panel de Botones - Imágenes
        selectImage.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Original = OpenFile.abrirImagen();
            RandImage = null;
            createImages(Original);
        }
        });

        testImage.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            RandImage = OpenFile.abrirImagenAleatoria("src/TestImages");
            if (RandImage != null) {
                Original = null;
                createImages(RandImage);
            }
        }
        });

        // Botones de control
        JPanel controlButtons = new JPanel();
        JButton aButton = new JButton("A");
        JButton bButton = new JButton("B");
        JButton cButton = new JButton("C");
        controlButtons.add(aButton);
        controlButtons.add(bButton);
        controlButtons.add(cButton);

        panelOriginalImage = new JPanel();
        add(panelImageButtons);
        add(panelOriginalImage);
        add(controlButtons);
        setVisible(true);
    }

    protected static void createImages(Image Original) {
        Image OriginalEscalada = ImageSettings.toBufferedImage(Original).getScaledInstance(700, 700, BufferedImage.TYPE_INT_RGB);
        JLabel JLabelOriginal = new JLabel(new ImageIcon(OriginalEscalada));
        panelOriginalImage.removeAll();
        panelOriginalImage.add(BorderLayout.CENTER, JLabelOriginal);
    }

}
