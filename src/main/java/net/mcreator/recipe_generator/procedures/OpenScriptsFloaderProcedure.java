package net.mcreator.recipe_generator.procedures;

import java.io.IOException;

public class OpenScriptsFloaderProcedure {
    public static void execute() {
        String userHome = System.getProperty("user.home");
        String scriptsDir = userHome + "\\AppData\\Roaming\\.minecraft\\scripts";

        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                new ProcessBuilder("powershell", "-Command", "Start-Process explorer.exe -ArgumentList '" + scriptsDir + "'").start();
            } else if (os.contains("mac")) {
                new ProcessBuilder("open", scriptsDir).start();
            } else if (os.contains("nix") || os.contains("nux")) {
                new ProcessBuilder("xdg-open", scriptsDir).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
