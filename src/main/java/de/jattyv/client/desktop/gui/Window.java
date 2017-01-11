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
package de.jattyv.client.desktop.gui;

import de.jattyv.client.desktop.gui.cards.ChatCard;
import de.jattyv.client.desktop.gui.cards.LoginCard;
import de.jattyv.client.desktop.gui.cards.MenuCard;
import de.jattyv.client.desktop.gui.cards.RegistrationCard;
import de.jattyv.client.desktop.network.server.Server;
import de.jattyv.jcapi.client.gui.JGui;
import de.jattyv.jcapi.client.handler.Handler;
import de.jattyv.jsapi.JattyvServer;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Window implements JGui {

    private JFrame frame;

    private CardLayout clayout;
    private JPanel card;
    private MenuCard mcard;
    private LoginCard lcard;
    private RegistrationCard rcard;
    private ChatCard ccard;

    public final String MENUC = "menu";
    public final String LOGINC = "login";
    public final String REGISTRATIONC = "registration";
    public final String CHATC = "chat";

    private Handler handler;

    public Window() {
        mcard = new MenuCard(this);
        lcard = new LoginCard(this);
        rcard = new RegistrationCard(this);
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    public void addMessage(String message) {
        ccard.addMessage(message);
    }

    public void startServer() {
        Server server = new Server(36987);
        JattyvServer jServer = new JattyvServer(server);
        jServer.start();
    }

}
