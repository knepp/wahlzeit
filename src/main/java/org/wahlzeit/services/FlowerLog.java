package org.wahlzeit.services;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FlowerLog {
    public static final String filename = "LogFile_FlowerLog.txt";
    private static boolean logFileEmpty = false;
    public static void logError(String error) {
        if (!logFileEmpty) {
            try {
                boolean result = Files.deleteIfExists(Paths.get(filename));
                logFileEmpty = true;
            } catch (IOException exception) {
                logError("Old Logfile could not be deleted");
            }
        }
        try
        {
            FileWriter fw = new FileWriter(filename,true);
            fw.write(error + "\n");
            fw.close();
        }catch (IOException e) {
            System.out.println("No error could be logged.");    //no error logging possible, also not for this error
        }
    }
}
