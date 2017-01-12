/*
 * Copyright (C) 2017 Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
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
package de.jattyv.client.desktop.data;

import de.jattyv.jcapi.data.jfc.JattyvFileController;
import de.jattyv.jcapi.data.jfc.JattyvFileReader;
import de.jattyv.jcapi.data.jfc.data.Settings;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class ConfigReader implements JattyvFileReader {

    @Override
    public Settings read(String path) {
        try {
            InputStream in = new FileInputStream(path);
            return JattyvFileController.readSettings(in);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void write(String dataname, String content) {

    }

}
