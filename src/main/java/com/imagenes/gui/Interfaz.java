package com.imagenes.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.imagenes.binarizacion.GUIBinarizacion;
import com.imagenes.bordes.GUIBordes;
import com.imagenes.expansion.GUIExpansion;
import com.imagenes.frecuencia.GUIFrecuencia;
import com.imagenes.repujado.GUIRepujado;
import com.imagenes.ruido.GUIRuido;

public class Interfaz {
    public static void run() {
        JFrame frame = new JFrame("Análisis de Imágenes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);

    //     // Componentes
    //     JPanel generalPanelButtons = new JPanel();
    //     // Panel de Botones Acciones
    //     JPanel panelActionButtons = new JPanel();
    //     JButton Binarizacion = new JButton("Binarización");
    //     JButton Convolucion = new JButton("Convolución");
    //     JButton Expansion = new JButton("Expansión");
    //     JButton Ruido = new JButton("Ruido");
    //     JButton Frecuencias = new JButton("Frecuencias");
    //     panelActionButtons.add(Binarizacion);
    //     panelActionButtons.add(Convolucion);
    //     panelActionButtons.add(Expansion);
    //     panelActionButtons.add(Ruido);
    //     panelActionButtons.add(Frecuencias);
        
    // Agregar GUIExpansion como una pestaña
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Binarizacion", null, new GUIBinarizacion(), "Panel de Binarizacion");
    tabbedPane.addTab("Expansión", null, new GUIExpansion(), "Panel de Expansión");
    tabbedPane.addTab("Ruido", null, new GUIRuido(), "Panel de Ruido");
    tabbedPane.addTab("Bordes", null, new GUIBordes(), "Panel de Bordes");
    tabbedPane.addTab("Efecto Repujado", null, new GUIRepujado(), "Panel de Efecto Repujado");
    tabbedPane.addTab("Frecuencias FTT", null, new GUIFrecuencia(), "Panel de Frecuencias");


    //     // Panel de Botones - Imágenes
    //     JPanel panelImageButtons = new JPanel();
    //     JButton selectImage = new JButton("Seleccionar Imagen");
    //     panelImageButtons.add(selectImage);
    //     JButton testImage = new JButton("Usar imagen de prueba");
    //     panelImageButtons.add(testImage);

    //     // Funcionamiento Panel de Botones - Imágenes
    //     selectImage.addActionListener(new ActionListener() {
    //         public void actionPerformed(ActionEvent e) {
    //             Original = OpenFile.abrirImagen();
    //             createImages(Original);
    //         }
    //     });

    //     testImage.addActionListener(new ActionListener() {
    //         public void actionPerformed(ActionEvent e) {
    //             Image randomImage = OpenFile.abrirImagenAleatoria("/Users/atc/Documentos/Repos/ATC_7CM1/ImageAnalysis/Imágenes de prueba");
    //             // Haz algo con la imagen aleatoria, por ejemplo, mostrarla en un JLabel
    //             if (randomImage != null) {
    //                 createImages(randomImage);
    //             }
    //         }
    //     });

    //     //Ajustar Paneles
    //     frame.setLayout(new BorderLayout());
    //     generalPanelButtons.setLayout(new BorderLayout());
        

    //     // Panel para las imágenes
    //     JPanel panelImages = new JPanel();
    //     panelImages.setLayout(new BorderLayout());
    //     panelOriginalImages = new JPanel();
    //     panelImages.add(BorderLayout.NORTH, panelOriginalImages);
    //     panelOriginalImages.setLayout(new BorderLayout());
 
    //     //Añadir paneles
    //     generalPanelButtons.add(BorderLayout.NORTH, panelActionButtons);
    //     generalPanelButtons.add(BorderLayout.CENTER, panelImageButtons);
    //     frame.add(BorderLayout.NORTH, generalPanelButtons);
    //     frame.add(BorderLayout.CENTER, panelImages); 
        frame.add(tabbedPane);
        frame.setVisible(true);
    // }

    // protected static void createImages(Image Original) {
    //     Image OriginalEscalada = ImageSettings.toBufferedImage(Original).getScaledInstance(300, 300, BufferedImage.TYPE_INT_RGB);
    //     JLabel JLabelOriginal = new JLabel(new ImageIcon(OriginalEscalada));
    //     panelOriginalImages.removeAll();
    //     panelOriginalImages.add(BorderLayout.WEST, JLabelOriginal);
    //     Utilities.calcularHistograma(Original, "Original", panelOriginalImages);
    }
}
