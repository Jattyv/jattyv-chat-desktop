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

import com.google.gson.Gson;
import de.jattyv.jcapi.client.handler.Handler;
import de.jattyv.jcapi.client.network.JClient;
import de.jattyv.jcapi.data.Container;
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
public class Client implements JClient {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    private int port;
    private String host;
    private Gson gson;
    private Handler handler;
    private Reload reload;

    public Client(String host, int port) {
        this.port = port;
        this.host = host;
        gson = new Gson();
    }

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

    public void write(Container c) {
        try {
            out.writeUTF(gson.toJson(c));
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reload() {
        try {
            String input = in.readUTF();
            Container c = gson.fromJson(input, Container.class);
            handler.handle(c);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
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
