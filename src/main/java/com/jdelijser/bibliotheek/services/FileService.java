package com.jdelijser.bibliotheek.services;

import com.jdelijser.bibliotheek.controller.SceneController;
import com.jdelijser.bibliotheek.model.Book;

import java.io.*;
import java.util.ArrayList;

public class FileService {
    public static void writeToFile(String fileName, Object object) {
        try (FileOutputStream fos = new FileOutputStream("books/files/" + FileService.formatTitle(fileName) + ".dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(object);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getFileName(Book book) {
        return FileService.formatTitle(book.title + " " + book.author);
    }

    public static void deleteActiveBook() throws IOException {
        Book activeBook = ActiveBook.getInstance().getBook();

        if (activeBook != null) {
            File myObj = new File("books/files/" + FileService.formatTitle(FileService.getFileName(activeBook)) + ".dat");

            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
                SceneController.toMain();
            } else {
                System.out.println("Failed to delete the file.");
            }
        }
    }

    public static ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();
        File folder = new File("books/files");
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                Book book = getBook(file.getPath());
                books.add(book);
            }
        }

        return books;
    }

    public static Book getBook(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            objectIn.close();

            return (Book) obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String formatTitle(String fileName) {
        return fileName.toLowerCase().replaceAll(" ", "-");
    }
}
