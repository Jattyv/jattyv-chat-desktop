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
import de.jattyv.jcapi.data.jfc.data.Settings;
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
public class LoginCard extends JPanel implements KeyListener {

    private final JTextField inputLName;
    private final JPasswordField inputLPassword;

    private final Window window;

    private boolean registration;

    public LoginCard(Window window, boolean registration, Settings settings) {
        super();
        this.window = window;
        this.registration = registration;
        GridBagLayout gbl_card2 = new GridBagLayout();
        gbl_card2.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_card2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_card2.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_card2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        this.setLayout(gbl_card2);

        JLabel lblNewLabel = new JLabel("Username:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 2;
        this.add(lblNewLabel, gbc_lblNewLabel);

        inputLName = new JTextField();
        if (settings.isuNameAvailable()) {
            inputLName.setText(settings.getuName());
        }
        GridBagConstraints gbc_inputLName = new GridBagConstraints();
        gbc_inputLName.insets = new Insets(0, 0, 5, 0);
        gbc_inputLName.fill = GridBagConstraints.HORIZONTAL;
        gbc_inputLName.gridx = 3;
        gbc_inputLName.gridy = 3;
        this.add(inputLName, gbc_inputLName);
        inputLName.setColumns(8);

        JLabel lblNewLabel_1 = new JLabel("Password:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 6;
        this.add(lblNewLabel_1, gbc_lblNewLabel_1);

        inputLPassword = new JPasswordField();
        inputLPassword.addKeyListener(this);
        GridBagConstraints gbc_inputLPassword = new GridBagConstraints();
        gbc_inputLPassword.insets = new Insets(0, 0, 5, 0);
        gbc_inputLPassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_inputLPassword.gridx = 3;
        gbc_inputLPassword.gridy = 7;
        this.add(inputLPassword, gbc_inputLPassword);
        inputLPassword.setColumns(8);
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
            String userName = inputLName.getText();
            String userPassword = inputLPassword.getText();
            if (registration) {
                window.getHandler().getOutHandler().sendRegist(userName, userPassword);
            } else {
                window.getHandler().getOutHandler().sendLogin(userName, userPassword);
            }
        }
    }

}
