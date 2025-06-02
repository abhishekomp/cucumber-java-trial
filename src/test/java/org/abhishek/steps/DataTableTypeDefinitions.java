package org.abhishek.steps;

import io.cucumber.java.DataTableType;
import org.abhishek.model.Book;

import java.util.Map;

public class DataTableTypeDefinitions {
    @DataTableType
    public Book defineBook(Map<String, String> row) {
        // This method is used to convert a DataTable row into a Book object
        String title = row.get("title");
        String author = row.get("author");
        int year = Integer.parseInt(row.get("year"));
        String genre = row.get("genre");
        // Create and return a new Book object
        return new Book(title, author, year, genre);
    }
}
