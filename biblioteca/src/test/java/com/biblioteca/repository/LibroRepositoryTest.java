package com.biblioteca.repository;

import com.biblioteca.model.Libro;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link LibroRepository}.
 * Tests the core functionality of the LibroRepository class, including finding all books,
 * adding a new book, finding a book by title, and finding a book by ISBN.
 * Assumes the database is pre-populated with the provided data.
 */
class LibroRepositoryTest {

    private LibroRepository lr = new LibroRepository();


    /**
     * Tests the {@link LibroRepository#addNewLibro(Libro)} method.
     * Verifies that a new book can be added and retrieved correctly.
     *
     * @throws SQLException if a database error occurs
     */


    @Test
    void addNewLibro() throws SQLException {
        Libro libro = new Libro("Nuovo Libro", "Autore Test", 2023, "9876543210987", 10);
        lr.addNewLibro(libro);
        Libro found = lr.findById("9876543210987");
        assertEquals("Nuovo Libro", found.getTitolo(), "Book title should match");
        assertEquals("Autore Test", found.getAutore(), "Book author should match");
        assertEquals(2023, found.getAnnoPubblicazione(), "Book year should match");
        assertEquals("9876543210987", found.getISBN(), "Book ISBN should match");
        assertEquals(10, found.getNumeroCopie(), "Book copies should match");

        lr.deleteLibro("9876543210987"); // Delete "Nuovo Libro" (no loans)



    }



    /**
     * Tests the {@link LibroRepository#deleteLibro(String)} method.
     * Verifies that a book can be deleted by its ISBN and is no longer retrievable,
     * and handles cases where deletion fails due to foreign key constraints.
     *
     * @throws SQLException if a database error occurs
     */
    @Test
    void deleteLibro() throws SQLException {
        // Add a book to delete (no loans)
        Libro libro = new Libro("Nuovo Libro", "Autore Test", 2023, "9876543210987", 10);
        lr.addNewLibro(libro);
        lr.deleteLibro("9876543210987"); // Delete "Nuovo Libro" (no loans)
        Libro notFound = lr.findById("9876543210987");
        assertNull(notFound, "Deleted book should not be found");
        List<Libro> libri = lr.findAllLibri();
        assertEquals(6, libri.size(), "List should contain five books after deletion");

        // Test deleting a book with active loans (should throw SQLException)
        assertThrows(SQLException.class, () -> lr.deleteLibro("9788845240000"), // "Il nome della rosa" has loans
                "Deleting a book with active loans should throw SQLException");
    }
    /**
     * Tests the {@link LibroRepository#findByTitle(String)} method.
     * Verifies that a book can be found by its title and null is returned for non-existent titles.
     *
     * @throws SQLException if a database error occurs
     */
    @Test
    void findByTitle() throws SQLException {
        List <Libro> libri = lr.findByTitle("Fratellino");
        assertNotNull (libri, "Book should be found");
        Libro libro = libri.getFirst() ;
        assertEquals("Fratellino", libro.getTitolo(), "Book title should match");
        assertEquals("Ibrahima Balde e Amets", libro.getAutore(), "Book author should match");
        assertEquals(2025, libro.getAnnoPubblicazione(), "Book year should match");
        assertEquals("9788807895678", libro.getISBN(), "Book ISBN should match");
        assertEquals(24, libro.getNumeroCopie(), "Book copies should match");

        List<Libro> notFound = lr.findByTitle("Non Esiste");
//        assertNull(notFound, "Non-existent title should return null");
        assertTrue(notFound.isEmpty());
    }

    /**
     * Tests the {@link LibroRepository#findById(String)} method.
     * Verifies that a book can be found by its ISBN and null is returned for non-existent ISBNs.
     *
     * @throws SQLException if a database error occurs
     */
    @Test
    void findById() throws SQLException {
        Libro libro = lr.findById("9788845240000");
        assertEquals("Il nome della rosa", libro.getTitolo(), "Book title should match");
        assertEquals("Umberto Eco", libro.getAutore(), "Book author should match");
        assertEquals(1980, libro.getAnnoPubblicazione(), "Book year should match");
        assertEquals("9788845240000", libro.getISBN(), "Book ISBN should match");
        assertEquals(7, libro.getNumeroCopie(), "Book copies should match");

        Libro notFound = lr.findById("9999999999999");
        assertNull(notFound, "Non-existent ISBN should return null");
    }

    /**
     * Tests the {@link LibroRepository#findAllLibri()} method.
     * Verifies that all books are retrieved correctly from the pre-populated database.
     *
     * @throws SQLException if a database error occurs
     */
    @Test
    void findAllLibri() throws SQLException {
        List<Libro> libri = lr.findAllLibri();
        assertEquals(6, libri.size(), "List should contain five books");
        //scriverlo con ciclo for

        boolean foundRosa = false;
        boolean foundFratellino = false;
        for(Libro libro : libri){
           if("Il nome della rosa".equals(libro.getTitolo()) &&
                   "Umberto Eco".equals(libro.getAutore()) &&
                   1980 == libro.getAnnoPubblicazione() &&
                   "9788845240000".equals(libro.getISBN()) &&
                   7 == libro.getNumeroCopie()){
               foundRosa = true;
           }
           if("Fratellino".equals(libro.getTitolo()) &&
                   "Ibrahima Balde e Amets".equals(libro.getAutore()) &&
                   2025 == libro.getAnnoPubblicazione() &&
                   "9788807895678".equals(libro.getISBN()) &&
                   24 == libro.getNumeroCopie()){
               foundFratellino = true;
           }
        }

        assertTrue(foundRosa, "Il nome della rosa should be in the list");
        assertTrue(foundFratellino, "Fratellino should be in the list");
    }

}

