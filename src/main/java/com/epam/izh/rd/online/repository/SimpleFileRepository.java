package com.epam.izh.rd.online.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        long c = 0;
        File file = new File("src/main/resources/" + path);
        if (file.isDirectory()) {
            for (File newFile : file.listFiles()) {
                c += countFilesInDirectory(path + "/" + newFile.getName());
            }
        } else {
            c++;
        }
        return c;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        long c = 0;
        File file = new File("src/main/resources/" + path);
        if (file.isDirectory()) {
            c++;
            for (File newFile : file.listFiles()) {
                c += countDirsInDirectory(path + "/" + newFile.getName());
            }
        }
        return c;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) throws IOException {
        File root = new File(from);
        File[] list = root.listFiles();
        for (File f : list) {
            if (f.getName().contains(".txt")) {
                Path p = Files.copy(f.toPath(), Paths.get(to + f.getName()));
            }
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {

        //Смог придумать 3 реализации этого метода, через File.createfiles(), BufferedReader и NIO,
        //Все 3 создают файл, но тесты не проходит ни одна
        //Оставил только способ "nio", т.к. закоменченый код запрещен.
        // Буду крайне благодарен если обьясните где я ошибся.

        try {
            Path dir = Files.createDirectories(Paths.get(path));
            OutputStream out = Files.newOutputStream(dir.resolve(name));
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) throws IOException {
        String willReturn = "";
        File f = new File("src/main/resources/" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String newString;
        while ((newString = br.readLine()) != null) {
            willReturn += newString;
        }
        return willReturn;

    }
}
