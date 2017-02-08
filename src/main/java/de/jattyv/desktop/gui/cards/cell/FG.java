/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jattyv.desktop.gui.cards.cell;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class FG {
    
    private final String title;
    
    private final int type;
    
    private final String id;
    
    public static final int FG_TYPE_FRIEND = 0;
    
    public static final int FG_TYPE_GROUP = 1;
    
    public FG(String title, int type, String id){
        this.title = title;
        this.type = type;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

    public String getId() {
        return id;
    }
    
    
    
    
    
}
