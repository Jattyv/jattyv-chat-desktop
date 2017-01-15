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
package de.jattyv.desktop.network.client;

import de.jattyv.desktop.network.server.Server;
import de.jattyv.jcapi.client.network.JClient;
import de.jattyv.jcapi.data.jfc.data.Settings;
import de.jattyv.jcapi.data.jobject.Container;
import de.jattyv.jsapi.JattyvServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Client extends JClient {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    protected Reload reload;

    public Client(String host, int port) {
        super(host, port);
    }

    public Client(Settings settings) {
        super(settings);
        if (settings.isAutoStartServer()) {
            Server server = new Server(settings.getPort());
            JattyvServer jServer = new JattyvServer(server);
            jServer.start();
        }
    }

    @Override
    public void start(Container c) {
        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            write(c);
            reload = new Reload(this);
            reload.start();
        } catch (IOException ie) {
            System.out.println(ie);
        }

    }

    @Override
    public void write(Container c) {
        try {
            out.writeUTF(gson.toJson(c));
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void reload() {
        try {
            String input = in.readUTF();
            Container c = gson.fromJson(input, Container.class);
            handler.handle(c);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() {
        try {
            socket.close();
            reload.stop();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
