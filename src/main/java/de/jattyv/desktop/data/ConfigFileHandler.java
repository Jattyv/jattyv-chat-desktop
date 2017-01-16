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
package de.jattyv.desktop.data;

import de.jattyv.jcapi.data.jfc.JattyvFileController;
import de.jattyv.jcapi.data.jfc.JattyvFileHandler;
import de.jattyv.jcapi.data.jfc.data.Settings;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class ConfigFileHandler implements JattyvFileHandler {

    @Override
    public Settings readSettings(String path) {
        try {
            File config = new File(path);
            InputStream in = new FileInputStream(path);

            if (config.exists()) {
                return JattyvFileController.readSettings(in, this);
            } else {
                config.createNewFile();
                return JattyvFileController.readSettings(in, this);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void write(String dataname, String content) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(dataname);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(ConfigFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(ConfigFileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String readFile(String path) {
        FileReader in = null;
        File config = new File(path);
        if (config.exists()) {
        try {

            in = new FileReader(path);
            BufferedReader br = new BufferedReader(in);
            String line = "";
            String content = "";
            while ((line = br.readLine()) != null) {
                content += line;
            }
            in.close();
            return content;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ConfigFileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        } else {
            try {
                config.createNewFile();
                return "";
            } catch (IOException ex) {
                Logger.getLogger(ConfigFileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;
    }

}
