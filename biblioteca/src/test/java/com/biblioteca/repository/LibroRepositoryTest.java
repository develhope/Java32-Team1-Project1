package com.biblioteca.repository;

import com.biblioteca.model.Libro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibroRepositoryTest {
private LibroRepository lr= new LibroRepository();
    @Test
    void   findById() {
        {
            Libro libro = lr.findById("9788845240000");
            // Asserzioni per verificare che i valori siano corretti
            assertEquals("Il nome della rosa", libro.getTitolo());
            assertEquals("Umberto Eco", libro.getAutore());
            assertEquals(1980, libro.getAnnoPubblicazione());
            assertEquals("9788845240000", libro.getISBN());
        }

    }
//   @Test
//    void  cercaTitolo(){
//        Libro libro= lr.cercaTitolo("Fratellino");
//
//   }
}
