package com.babelcoding;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {


        public static void writeFile(String filepath, String content) {

        int lastSeparatorIndex = filepath.lastIndexOf(File.separator);
        String folder = filepath.substring(0, lastSeparatorIndex);
        String filename = filepath.substring(lastSeparatorIndex + 1);


        File directory = new File(folder);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created successfully.");
            } else {
                System.out.println("Failed to create the directory.");
                return;
            }
        }

        try {
            FileWriter myWriter = new FileWriter(filepath);
            myWriter.write(content);
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            System.out.println(folder);
            System.out.println(filename);


            e.printStackTrace();
        }


    }

        public static String getFilename(String filepath) {

        File f = new File(filepath);
        String fn = f.getName();
        String [] name = fn.split("\\.");
        return name[0];

    }


    }
