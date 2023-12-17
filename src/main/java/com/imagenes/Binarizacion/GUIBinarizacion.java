package com.imagenes.binarizacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.imagenes.herramientas.Basicas;
import com.imagenes.herramientas.ImageSettings;
import com.imagenes.herramientas.OpenFile;
import com.imagenes.herramientas.Utilities;

public class GUIBinarizacion extends JPanel {
    private JTextField textFieldJ;
    private JTextField textFieldJ2;
    private JTextField textFieldI;
    private static Image Original;
    private static JPanel panelOriginalImage; 
    private static JPanel panelBinImage1; 
    private static JPanel panelBinImage2; 

    public GUIBinarizacion() {
        setName("Binarizacion");
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
            createImages(Original);
        }
        });

        testImage.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Image randomImage = OpenFile.abrirImagenAleatoria("src\\TestImages");
            // Haz algo con la imagen aleatoria, por ejemplo, mostrarla en un JLabel
            if (randomImage != null) {
                createImages(randomImage);
            }
        }
        });

        // Componentes para el primer método de binarización
        JPanel panelBinarizacion1 = new JPanel();
        textFieldJ = new JTextField(5);
        JButton botonBinarizar1 = new JButton("Binarizar");

        // Configurar el ActionListener para el primer método
        botonBinarizar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int j = Integer.parseInt(textFieldJ.getText());
                Image imagenOriginal = Original;
                Image imagenModificada = Binarizacion.binarizarImagen(Basicas.escalaDeGrises(imagenOriginal), j);
                mostrarImagenModificada1(imagenModificada);
            }
        });

        // Agregar componentes al panel
        panelBinarizacion1.add(new JLabel("Valor de J:"));
        panelBinarizacion1.add(textFieldJ);
        panelBinarizacion1.add(botonBinarizar1);

        // Componentes para el segundo método de binarización
        JPanel panelBinarizacion2 = new JPanel();
        textFieldI = new JTextField(5);
        textFieldJ2 = new JTextField(5);
        JButton botonBinarizar2 = new JButton("Binarizar");
        
        // Configurar el ActionListener para el segundo método
        botonBinarizar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = Integer.parseInt(textFieldI.getText());
                int j = Integer.parseInt(textFieldJ2.getText());
                Image imagenOriginal = Original;
                Image imagenModificada = Binarizacion.binarizarImagen(Basicas.escalaDeGrises(imagenOriginal), i, j);
                mostrarImagenModificada2(imagenModificada);
            }
        });

        // Panel para las imágenes
        panelOriginalImage = new JPanel();

        // Panel para las imágenes
        panelBinImage1 = new JPanel();

        // Panel para las imágenes
        panelBinImage2 = new JPanel();

        // Agregar componentes al panel
        panelBinarizacion2.add(new JLabel("Valor de I:"));
        panelBinarizacion2.add(textFieldI);
        panelBinarizacion2.add(new JLabel("Valor de J:"));
        panelBinarizacion2.add(textFieldJ2);
        panelBinarizacion2.add(botonBinarizar2);


        panelOriginalImage.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Establecer un borde
        panelBinImage1.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Establecer un borde
        panelBinImage2.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Establecer un borde
        panelImageButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Establecer un borde
        panelBinarizacion1.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Establecer un borde
        panelBinarizacion2.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Establecer un borde

        // Agregar paneles al GUIBinarizacion
        add(panelImageButtons);
        add(panelOriginalImage);
        add(panelBinarizacion1);
        add(panelBinImage1);
        add(panelBinarizacion2);
        add(panelBinImage2);
    }

    private void mostrarImagenModificada1(Image imagenModificada) {
        Image ModificadaEscalada = ImageSettings.toBufferedImage(imagenModificada).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
        JLabel JLabelMod1 = new JLabel(new ImageIcon(ModificadaEscalada));
        panelBinImage1.removeAll();
        panelBinImage1.add(BorderLayout.CENTER, JLabelMod1);
        Utilities.calcularHistograma(imagenModificada, "Binarización", panelBinImage1); 
    }
    private void mostrarImagenModificada2(Image imagenModificada) {
        Image ModificadaEscalada = ImageSettings.toBufferedImage(imagenModificada).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
        JLabel JLabelMod2 = new JLabel(new ImageIcon(ModificadaEscalada));
        panelBinImage2.removeAll();
        panelBinImage2.add(BorderLayout.CENTER, JLabelMod2);
        Utilities.calcularHistograma(imagenModificada, "Binarización", panelBinImage2); 
    }
    protected static void createImages(Image Original) {
        Image OriginalEscalada = ImageSettings.toBufferedImage(Original).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
        JLabel JLabelOriginal = new JLabel(new ImageIcon(OriginalEscalada));
        panelOriginalImage.removeAll();
        panelOriginalImage.add(BorderLayout.CENTER, JLabelOriginal);
        Utilities.calcularHistograma(Original, "Original", panelOriginalImage); 
    }
}
