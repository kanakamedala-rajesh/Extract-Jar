package com.venkatasudha;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.lang.System.exit;

public class ExtractJar {

      public static void main(String[] args) {
            int count = 1;
            System.out.println("Processing...");
            String jarURL = args[0];
            List<String> classNames = extractJar(jarURL);
            System.out.println("Total Number of Classes: " + classNames.size());
            for (String classFile : classNames) {
                  System.out.println("Class " + count + " : " + classFile);
                  count++;
            }

      }

      public static List<String> extractJar(String path) {
            List<String> classNames = new ArrayList<>();
            ZipInputStream zip = null;
            try {
                  zip = new ZipInputStream(new FileInputStream(path));
                  for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                        if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                              // This ZipEntry represents a class. Now, what class does it represent?
                              String className = entry.getName().replace('/', '.'); // including ".class"
                              classNames.add(className.substring(0, className.length() - ".class".length()));
                        }
                  }
            }
            catch (Exception e) {
                  if (e.getClass().toString().contains("FileNotFoundException")) { System.out.println("File does'nt exists in specified path: " + path); }
                  else { e.printStackTrace(); }
                  exit(0);
            }
            return classNames;
      }
}
