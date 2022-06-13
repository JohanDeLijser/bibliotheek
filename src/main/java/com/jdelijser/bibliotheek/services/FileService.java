package com.jdelijser.bibliotheek.services;

import com.jdelijser.bibliotheek.controller.SceneController;
import com.jdelijser.bibliotheek.model.Author;
import com.jdelijser.bibliotheek.model.Book;
import com.jdelijser.bibliotheek.model.Genre;
import com.jdelijser.bibliotheek.model.Publisher;

import java.io.*;
import java.util.ArrayList;

public class FileService {
    public static void writeToFile(String folder, String fileName, Object object) {
        try (FileOutputStream fos = new FileOutputStream(folder + "/files/" + FileService.formatTitle(fileName) + ".dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(object);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getBookFileName(Book book) {
        return FileService.formatTitle(book.title + " " + book.author);
    }

    public static String getGenreFileName(Genre genre) {
        return FileService.formatTitle(genre.name);
    }

    public static String getAuthorFileName(Author author) {
        return FileService.formatTitle(author.name);
    }

    public static String getPublisherFileName(Publisher publisher) {
        return FileService.formatTitle(publisher.name);
    }

    public static void deleteActiveBook() throws IOException {
        Book activeBook = ActiveBook.getInstance().getBook();

        if (activeBook != null) {
            File myObj = new File("books/files/" + FileService.formatTitle(FileService.getBookFileName(activeBook)) + ".dat");

            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
                SceneController.toMain();
            } else {
                System.out.println("Failed to delete the file.");
            }
        }
    }
    
    public static void deleteActiveGenre() throws IOException {
        Genre activeGenre = ActiveGenre.getInstance().getGenre();

        if (activeGenre != null) {
            File myObj = new File("genres/files/" + FileService.formatTitle(FileService.getGenreFileName(activeGenre)) + ".dat");

            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
                SceneController.setScene("genre-view.fxml", "Genres");
            } else {
                System.out.println("Failed to delete the file.");
            }
        }
    }

    public static void deleteActiveAuthor() throws IOException {
        Author activeAuthor = ActiveAuthor.getInstance().getAuthor();

        if (activeAuthor != null) {
            File myObj = new File("authors/files/" + FileService.formatTitle(FileService.getAuthorFileName(activeAuthor)) + ".dat");

            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
                SceneController.setScene("author-view.fxml", "Authors");
            } else {
                System.out.println("Failed to delete the file.");
            }
        }
    }

    public static void deleteActivePublisher() throws IOException {
        Publisher activePublisher = ActivePublisher.getInstance().getPublisher();

        if (activePublisher != null) {
            File myObj = new File("publishers/files/" + FileService.formatTitle(FileService.getPublisherFileName(activePublisher)) + ".dat");

            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
                SceneController.setScene("publisher-view.fxml", "Publishers");
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

    public static ArrayList<Genre> getGenres() {
        ArrayList<Genre> genres = new ArrayList<>();
        File folder = new File("genres/files");
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                Genre genre = getGenre(file.getPath());
                genres.add(genre);
            }
        }

        return genres;
    }

    public static ArrayList<Author> getAuthors() {
        ArrayList<Author> authors = new ArrayList<>();
        File folder = new File("authors/files");
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                Author author = getAuthor(file.getPath());
                authors.add(author);
            }
        }

        return authors;
    }

    public static ArrayList<Publisher> getPublishers() {
        ArrayList<Publisher> publishers = new ArrayList<>();
        File folder = new File("publishers/files");
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                Publisher publisher = getPublisher(file.getPath());
                publishers.add(publisher);
            }
        }

        return publishers;
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

    public static Genre getGenre(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            objectIn.close();

            return (Genre) obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Author getAuthor(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            objectIn.close();

            return (Author) obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Publisher getPublisher(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            objectIn.close();

            return (Publisher) obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String formatTitle(String fileName) {
        return fileName.toLowerCase().replaceAll(" ", "-");
    }
}
