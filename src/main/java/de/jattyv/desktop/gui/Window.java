/*
 * Copyright (C) 2016 Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.jattyv.desktop.gui;

import de.jattyv.desktop.data.FileHandler;
import de.jattyv.desktop.gui.cards.ChatCard;
import de.jattyv.desktop.gui.cards.LoginCard;
import de.jattyv.desktop.gui.cards.MenuCard;
import de.jattyv.jcapi.client.gui.JGui;
import de.jattyv.jcapi.client.gui.cell.FG;
import de.jattyv.jcapi.client.handler.Handler;
import de.jattyv.jcapi.data.jfc.data.Settings;
import de.jattyv.jcapi.server.ChatServer;
import java.awt.CardLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Window implements JGui, WindowListener {

    private JFrame frame;

    private final CardLayout clayout;
    private final JPanel card;
    private final MenuCard mcard;
    private final LoginCard lcard;
    private final LoginCard rcard;
    private final ChatCard ccard;

    public final String MENUC = "menu";
    public final String LOGINC = "login";
    public final String REGISTRATIONC = "registration";
    public final String CHATC = "chat";

    private Handler handler;
    private ChatServer server;

    public Window() {
        mcard = new MenuCard(this);
        lcard = new LoginCard(this, false);
        rcard = new LoginCard(this, true);
        ccard = new ChatCard(this);
        clayout = new CardLayout();
        card = new JPanel();
        card.setLayout(clayout);
        card.add(mcard, MENUC);
        card.add(lcard, LOGINC);
        card.add(rcard, REGISTRATIONC);
        card.add(ccard, CHATC);
    }

    public void init() {
        frame = new JFrame("Jattyv");
        frame.addWindowListener(this);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(card);
        frame.setVisible(true);

        clayout.show(card, "menu");
    }

    public JFrame getFrame() {
        return frame;
    }

    public Handler getHandler() {
        return handler;
    }

    public void changeCard(String card) {
        clayout.show(this.card, card);
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void addMessage(String fName, String message) {
        ccard.addMessage(fName, message);
    }

    public void startServer() {
        server = new ChatServer(new FileHandler());
        server.start();
        JOptionPane.showMessageDialog(null, "Server started", "INFO", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void changeWindow(String window) {
        if (window.equals(JGui.CHAT_WINDOW)) {
            changeCard(CHATC);
            frame.setTitle(frame.getTitle() + " - " + handler.getUser().getName());
        }
    }

    @Override
    public void showError(String errKey) {
        JOptionPane.showMessageDialog(null, errKey, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public boolean alert(String msg, String alertType) {
        int answer = JOptionPane.showConfirmDialog(null, msg, alertType, JOptionPane.INFORMATION_MESSAGE);
        if (answer == JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }

    @Override
    public void addGroup(String gName, String gID) {
        ccard.addGroup(gName, gID);
    }

    @Override
    public void addGroupMessage(String gID, String message) {
        ccard.addGroupMessage(gID, message);
    }

    @Override
    public void addFriend(String fName) {
        ccard.addFriend(fName);
    }

    @Override
    public void updateFGList(List<FG> fgs) {
        ccard.updateFGList(fgs);
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        int confirm = JOptionPane.showOptionDialog(
                null, "Are You Sure to Close Application?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == JOptionPane.YES_OPTION) {
            if(server != null){
                server.shutdown();
            }
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

}
