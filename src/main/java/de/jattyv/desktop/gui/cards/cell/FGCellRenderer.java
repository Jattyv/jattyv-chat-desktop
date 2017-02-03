/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.desktop.gui.cards.cell;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class FGCellRenderer extends JLabel implements ListCellRenderer{
    
    public FGCellRenderer(){
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList jlist, Object e, int i, boolean isSelected, boolean cellHasFocus) {
       FG entry = (FG) e;
       setText(entry.getTitle());
       if(entry.getType() == FG.FG_TYPE_FRIEND){
           setBackground(Color.CYAN);
       }else if(entry.getType() == FG.FG_TYPE_GROUP){
           setBackground(Color.RED);
       }
       if(isSelected){
           setBackground(Color.yellow);
       }
       return this;
    }
    
}
