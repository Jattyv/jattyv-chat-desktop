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
package de.jattyv.desktop;

import de.jattyv.desktop.data.ConfigReader;
import de.jattyv.desktop.gui.Window;
import de.jattyv.jcapi.client.Chat;
import de.jattyv.jcapi.util.ChatTags;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Jattyv implements ChatTags {

    public static void main(String[] args) {
        Chat c = new Chat(new ConfigReader().read("jattyv.properties"));
        Window window = new Window();
        window.setHandler(c.getHandler());
        c.setGui(window);
        window.init();

    }

}
