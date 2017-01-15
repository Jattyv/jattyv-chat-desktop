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
import de.jattyv.jcapi.util.Packer;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class RegistrationCard extends JPanel implements KeyListener {

    JTextField inputRName;
    JPasswordField inputRPassword;

    Window window;

    public RegistrationCard(Window window) {
        super();
        this.window = window;
        GridBagLayout gbl_card3 = new GridBagLayout();
        gbl_card3.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_card3.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_card3.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_card3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        this.setLayout(gbl_card3);

        JLabel lblNewLabel = new JLabel("Username:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 2;
        this.add(lblNewLabel, gbc_lblNewLabel);

        inputRName = new JTextField();
        GridBagConstraints gbc_inputRName = new GridBagConstraints();
        gbc_inputRName.insets = new Insets(0, 0, 5, 0);
        gbc_inputRName.fill = GridBagConstraints.HORIZONTAL;
        gbc_inputRName.gridx = 3;
        gbc_inputRName.gridy = 3;
        this.add(inputRName, gbc_inputRName);
        inputRName.setColumns(8);

        JLabel lblNewLabel_1 = new JLabel("Password:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 6;
        this.add(lblNewLabel_1, gbc_lblNewLabel_1);

        inputRPassword = new JPasswordField();
        inputRPassword.addKeyListener(this);
        GridBagConstraints gbc_inputRPassword = new GridBagConstraints();
        gbc_inputRPassword.insets = new Insets(0, 0, 5, 0);
        gbc_inputRPassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_inputRPassword.gridx = 3;
        gbc_inputRPassword.gridy = 7;
        this.add(inputRPassword, gbc_inputRPassword);
        inputRPassword.setColumns(8);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String userName = inputRName.getText();
            String userPassword = inputRPassword.getText();
            window.getHandler().start(Packer.packRegistration(userName, userPassword));
            window.changeCard(window.CHATC);
        }

    }

}
