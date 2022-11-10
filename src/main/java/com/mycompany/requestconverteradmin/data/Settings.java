/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.requestconverteradmin.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 041AlikinOS
 */
public class Settings {
    
    private String url;
    private String port;
    private String dbName;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        Settings.path = path;
    }
    
    
    
    
    private static String path = "src/main/java/com/mycompany/requestconverteradmin/data/settings.txt";
    private static Map<String, String> settingsMap = new HashMap<>();
    
    
        public static Map<String, String> prepareSettings() throws IOException {
        try {
            Path getPath = Paths.get(path);
            List<String> list = Files.readAllLines(getPath);            
            for(int i = 0; i < list.size(); i++) {
              String[] buf = list.get(i).split(":");
               settingsMap.put(buf[0], buf[1]);
            }
            return settingsMap;
        } catch (IOException e) {
            e.getStackTrace();
            throw new IOException("Отсутствует файл settings.txt");
        }
    }
        
        public Settings getSettings(Map<String, String> input) {
            
            Settings currentSettings = new Settings();
            
            if(!input.isEmpty()) {
                url = input.get("URL");
                port = input.get("PORT");
                dbName = input.get("DB_NAME");
                username = input.get("USER");
                password = input.get("PASSWORD");
            }
            
            
            return currentSettings;
        }
        
        public static void changeSettings(Settings input) {
            
        settingsMap.replace("URL", input.url);
        settingsMap.replace("PORT", input.port);
        settingsMap.replace("DB_NAME", input.dbName);
        settingsMap.replace("USER", input.username);
        settingsMap.replace("PASSWORD", input.password);
        
        }
        
        public static void saveSettings() {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(path));
                for(Map.Entry<String, String> entry : settingsMap.entrySet()) {
               writer.write(entry.getKey() + ":" + entry.getValue());
               writer.newLine();
            }
                writer.flush();
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        
    
    
}
