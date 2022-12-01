package org.wahlzeit.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FlowerLog {
    private static boolean logFileEmpty = false;
    public static void logError(String error) {
        System.out.println("TODO");//TODO
        if (!logFileEmpty) {
            //TODO remove content of file
            logFileEmpty = true;
        }
        try {
            Files.writeString(
                    Path.of(System.getProperty("java.io.tmpdir"), "filename.txt"),
                    error + System.lineSeparator(),
                    CREATE, APPEND
            )
        }catch (IOException e) {
            System.out.println("No error could be logged.");    //no error logging possible, also not for this error
        }
    }
}
