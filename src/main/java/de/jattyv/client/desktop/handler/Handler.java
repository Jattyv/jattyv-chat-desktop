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
package de.jattyv.client.desktop.handler;

import de.jattyv.client.desktop.gui.Window;
import de.jattyv.client.desktop.network.Client;
import de.jattyv.jcapi.data.Container;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Handler {

    Client cl;
    Window window;

    public Handler() {

    }

    public void handle(Container c) {

    }

    public void handle(String input) {

    }

    public void start(Container c) {
        cl.start(c);
    }

    public void setClient(Client cl) {
        this.cl = cl;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

}
