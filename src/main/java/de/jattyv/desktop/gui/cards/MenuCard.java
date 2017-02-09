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
package de.jattyv.desktop.gui.cards;

import de.jattyv.desktop.gui.Window;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class MenuCard extends JPanel implements ActionListener {

    private final JButton btnLogin;
    private final JButton btnRegist;

    private final JButton startServer;

    private final Window window;

    public MenuCard(Window window) {
        super();
        this.window = window;
        this.setLayout(new BorderLayout(0, 0));
        JSplitPane splitBase = new JSplitPane();
        splitBase.setResizeWeight(0.5);
        splitBase.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.add(splitBase, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.5);
        splitBase.setRightComponent(splitPane);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);
        splitPane.setLeftComponent(btnLogin);

        btnRegist = new JButton("Regist");
        btnRegist.addActionListener(this);
        splitPane.setRightComponent(btnRegist);

        JTextArea textAreaInfo = new JTextArea();
        textAreaInfo.setEditable(false);
        textAreaInfo.setText("Jattyv\n"
                + "A JavaBased Chat Application.\n"
                + "Includes Server and Client.\n"
                + "Version 0.1");
        this.add(textAreaInfo, BorderLayout.CENTER);

        startServer = new JButton("Start Server");
        startServer.addActionListener(this);
        splitBase.setLeftComponent(startServer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            window.changeCard(window.LOGINC);
        } else if (e.getSource() == btnRegist) {
            window.changeCard(window.REGISTRATIONC);
        } else if (e.getSource() == startServer) {
            window.startServer();
        }

    }

}
