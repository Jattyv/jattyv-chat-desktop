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

import de.jattyv.desktop.data.ConfigFileHandler;
import de.jattyv.desktop.gui.Window;
import de.jattyv.desktop.gui.cards.cell.FGCellRenderer;
import de.jattyv.jcapi.client.gui.cell.FG;
import de.jattyv.jcapi.data.jfc.JattyvFileController;
import de.jattyv.jcapi.data.jfc.data.Settings;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class ChatCard extends JPanel implements KeyListener, MouseListener, ListSelectionListener, ActionListener {

    private final JTextArea textAreaInput;
    private final JScrollPane scrollPaneFG;
    private final JScrollPane scrollPaneMessages;
    private final JList listFG;
    private final JList<String> listMessages;
    private final DefaultListModel modelFG = new DefaultListModel();
    private final DefaultListModel<String> modelMessages = new DefaultListModel<>();
    private final JMenuBar menuBar;
    private final JMenu mnFriends;
    private final JMenuItem mntmSFR;
    private final JMenu mnGroups;
    private final JMenuItem mntmSGR;
    private final JMenuItem mntmATG;

    Window window;

    Settings settings;

    public ChatCard(Window window, Settings settings) {
        super();
        this.window = window;
        this.setLayout(new BorderLayout(0, 0));

        JSplitPane splitPane_1 = new JSplitPane();
        splitPane_1.setResizeWeight(0.85);
        splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.add(splitPane_1, BorderLayout.CENTER);

        textAreaInput = new JTextArea();
        textAreaInput.addKeyListener(this);
        splitPane_1.setRightComponent(textAreaInput);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.4);
        splitPane_1.setLeftComponent(splitPane);

        scrollPaneFG = new JScrollPane();
        splitPane.setLeftComponent(scrollPaneFG);

        listFG = new JList(modelFG);
        listFG.setCellRenderer(new FGCellRenderer());
        listFG.addListSelectionListener(this);
        listFG.addMouseListener(this);
        scrollPaneFG.setViewportView(listFG);

        scrollPaneMessages = new JScrollPane();
        splitPane.setRightComponent(scrollPaneMessages);

        listMessages = new JList<String>(modelMessages);
        scrollPaneMessages.setViewportView(listMessages);

        menuBar = new JMenuBar();
        this.add(menuBar, BorderLayout.NORTH);

        mnFriends = new JMenu("Friends");
        menuBar.add(mnFriends);

        mnGroups = new JMenu("Groups");
        menuBar.add(mnGroups);

        mntmSFR = new JMenuItem("add Friend");
        mntmSFR.addActionListener(this);
        mnFriends.add(mntmSFR);

        mntmSGR = new JMenuItem("add Group");
        mntmSGR.addActionListener(this);
        mnGroups.add(mntmSGR);

        mntmATG = new JMenuItem("add to Group");
        mntmATG.addActionListener(this);
        mnGroups.add(mntmATG);

        this.settings = settings;

        if (settings.isClientSettingsAvailable()) {
            for (FG fg : settings.getClientSettings().getFriends()) {
                modelFG.addElement(fg);
            }
        }
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
            FG fg = (FG) listFG.getSelectedValue();
            if (fg.getType() == FG.FG_TYPE_FRIEND) {
                String toUser = fg.getTitle();
                String message = textAreaInput.getText();
                textAreaInput.setText("");
                window.getHandler().getOutHandler().sendNewMessage(toUser, message);
            } else if (fg.getType() == FG.FG_TYPE_GROUP) {
                String toGroup = fg.getId();
                String message = textAreaInput.getText();
                textAreaInput.setText("");
                window.getHandler().getOutHandler().sendNewGroupMessage(toGroup, message);
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        modelMessages.clear();
        FG fg = (FG) listFG.getSelectedValue();
        if (fg.getType() == FG.FG_TYPE_FRIEND) {
            LinkedList<String> messages = window.getHandler().getMessages(fg.getTitle());
            for (String message : messages) {
                addMessage(fg.getTitle(), message);
            }
        } else if (fg.getType() == FG.FG_TYPE_GROUP) {
            LinkedList<String> messages = window.getHandler().getGroupMessages(fg.getId());
            for (String message : messages) {
                addMessage(fg.getTitle(), message);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mntmSFR) {
            String fname = JOptionPane.showInputDialog("Enter The Friends Username:", "Friendsname");
            if (!fname.equals("")) {
                window.getHandler().getOutHandler().sendNewFriendRequest(fname);
            }
        }
        if (e.getSource() == mntmSGR) {
            String gname = JOptionPane.showInputDialog("Create a new Group:", "GroupName");
            if (!gname.equals("")) {
                window.getHandler().getOutHandler().createGroup(gname);
            }
        }
        if (e.getSource() == mntmATG) {
            FG fg = (FG) listFG.getSelectedValue();
            if (fg.getType() == FG.FG_TYPE_GROUP) {
                String fname = JOptionPane.showInputDialog("Enter the UserName of you friend you want to add to " + fg.getTitle());
                if (!fname.equals("")) {
                    window.getHandler().getOutHandler().addUserToGroup(fg.getId(), fname);
                }
            }
        }
    }

    public void addMessage(String fName, String message) {
        if (!listFG.isSelectionEmpty()) {
            FG fg = (FG) listFG.getSelectedValue();
            if (fg.getTitle().equals(fName)) {
                modelMessages.addElement(message);
                listMessages.ensureIndexIsVisible(modelMessages.size() - 1);
            }
        }
    }

    public void addGroup(String gname, String gID) {
        int answer = JOptionPane.showConfirmDialog(null, "Do you wanna join the Group " + gname + ".", "GROUPREQUEST", JOptionPane.INFORMATION_MESSAGE);
        if (answer == JOptionPane.YES_OPTION) {
            window.getHandler().getOutHandler().sendOkayToGroupRequest(gname, gID);
        }
    }

    public void addFriend(String fName) {
        int answer = JOptionPane.showConfirmDialog(null, fName + " asks to be your friend.", "FRIENDREQUEST", JOptionPane.INFORMATION_MESSAGE);
        if (answer == JOptionPane.YES_OPTION) {
            window.getHandler().getOutHandler().sendOkayToFriendRequest(fName);
        }
    }

    public void updateFGList(List<FG> fgs) {
        modelFG.clear();
        for (FG fg : fgs) {
            modelFG.addElement(fg);
        }
        if (!settings.isClientSettingsPathAvailable()) {
            new ConfigFileHandler().write(window.getHandler().getUser().getName() + ".json", JattyvFileController.getFGAsJson(fgs));
        } else {
            new ConfigFileHandler().write(settings.getClientSettingsPath(), JattyvFileController.getFGAsJson(fgs));
        }
    }

    public void addGroupMessage(String gID, String message) {
        if (!listFG.isSelectionEmpty()) {
            FG fg = (FG) listFG.getSelectedValue();
            if (fg.getType() == FG.FG_TYPE_GROUP) {
                if (fg.getId().equals(gID)) {
                    modelMessages.addElement(message);
                    listMessages.ensureIndexIsVisible(modelMessages.size() - 1);
                }
            }
        }
    }

}
