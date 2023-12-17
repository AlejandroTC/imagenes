package com.imagenes.ruido;

import javax.swing.*;

public class GUIRuido extends JPanel {
    public GUIRuido() {
        setName("Ruido");
        setSize(600, 500);
        initComponents();
    }
 
    private void initComponents() {

        JTabbedPane principal = new JTabbedPane();
        principal.addTab("Aditivo", null, new GUIAditivo(), "Panel de Ruido Aditivo");
        principal.addTab("Sustractivo", null, new GUISustractivo(), "Panel de Ruido Sustractivo");
        principal.addTab("Mezclado", null, new GUIMezclado(), "Panel de Ruido Mezclado");
        
        add(principal);
        setVisible(true);

    }

}
