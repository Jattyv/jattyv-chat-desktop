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
package de.jattyv.client.desktop.network.client;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Reload extends Thread {

    private Client cl;
    private boolean connected;

    public Reload(Client cl) {
        this.cl = cl;
        connected = true;
    }

    @Override
    public void run() {
        while (connected) {
            try {
                Thread.sleep(1000);
                cl.reload();
            } catch (InterruptedException ex) {
                Logger.getLogger(Reload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
