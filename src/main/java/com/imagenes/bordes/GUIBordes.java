package com.imagenes.bordes;


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

import com.imagenes.convolucion.Convolucion;
import com.imagenes.herramientas.Basicas;
import com.imagenes.herramientas.ImageSettings;
import com.imagenes.herramientas.OpenFile;

public class GUIBordes extends JPanel {
    private static Image Original;
    private static Image RandImage;
    private static Image Grises;
    private  Convolucion convolucionador;
    private static JPanel panelOriginalImage; 
    private static JPanel panelGrisesImage; 
    private static JPanel panelBordesImage; 
    
    public GUIBordes() {
        setName("Bordes");
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
            Grises = escalaGrises(Original);
            detectarBordes(Grises);
        }
        });

        testImage.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            RandImage = OpenFile.abrirImagenAleatoria("src/TestImages");
            if (RandImage != null) {
                Original = null;
                createImages(RandImage);
                Grises = escalaGrises(RandImage);
                detectarBordes(Grises);
            }
        }
        });
        
        JPanel panelImages = new JPanel();
        panelOriginalImage = new JPanel();
        panelGrisesImage = new JPanel();
        panelImages.add(panelOriginalImage);
        panelImages.add(panelGrisesImage);
        
        panelBordesImage = new JPanel();

        // Añadir componentes
        add(panelImageButtons);
        add(panelImages);
        add(panelBordesImage);
        setVisible(true);
    }


    protected static void createImages(Image Original) {
        Image OriginalEscalada = ImageSettings.toBufferedImage(Original).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
        JLabel JLabelOriginal = new JLabel(new ImageIcon(OriginalEscalada));
        panelOriginalImage.removeAll();
        panelOriginalImage.add(BorderLayout.CENTER, JLabelOriginal);
    }
    
    private Image escalaGrises(Image Original) {
        Image OriginalEscalada = ImageSettings.toBufferedImage(Original).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
        JLabel JLabelGrises = new JLabel(new ImageIcon(Basicas.escalaDeGrises(OriginalEscalada)));
        panelGrisesImage.removeAll();
        panelGrisesImage.add(BorderLayout.CENTER, JLabelGrises); 
        return Basicas.escalaDeGrises(OriginalEscalada);
    }

    private void detectarBordes(Image Grises) {
        convolucionador = new Convolucion(ImageSettings.toBufferedImage(Grises));
        Image BordesEscalada = ImageSettings.toBufferedImage(convolucionador.convolucionar(new int[]{0,1,0,1,-4,1,0,1,0}, 1)).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
        JLabel JLabelGrises = new JLabel(new ImageIcon(BordesEscalada));
        panelBordesImage.removeAll();
        panelBordesImage.add(BorderLayout.CENTER, JLabelGrises);
    }
}