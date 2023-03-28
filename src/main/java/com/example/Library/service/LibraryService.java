package com.example.Library.service;

/*import com.example.Library.entity.Book;
import com.example.Library.entity.Library;
import com.example.Library.entity.Reader;
import com.example.Library.enums.Status;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.LibraryRepository;
import com.example.Library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;*/

/*@Service
@AllArgsConstructor
public class LibraryService {
    BookRepository bookRepository;
    ReaderRepository readerRepository;
    LibraryRepository libraryRepository;
    public Library findById (Long id) {
        Optional<Library> library = libraryRepository.findById(id);
        Library library1 = new Library();
        library1.setId(library.get().getId());
        library1.setBooks(library.get().getBooks());
        library1.setName(library.get().getName());
        return library1;

    }

    public String issueOfTheBook (Long readerId,Long bookId) {
      Book book = bookRepository.findById(bookId).orElseThrow(() ->
              new IllegalArgumentException("Invalid book ID"));
      Reader reader = readerRepository.findById(readerId).orElseThrow(()->
              new IllegalArgumentException("Invalid reader ID"));
        if (book.getReader() != null) {
            throw new IllegalArgumentException("Book is already taken");
        }
        book.setReader(reader);
        book.setStatus(Status.NOT_AVAILABLE);
        bookRepository.save(book);
        List<Book> bookList = reader.getBooks();
        bookList.add(book);
        reader.setBooks(bookList);
        readerRepository.save(reader);
        return "Читатель " + reader.getUserName() + " взял книгу "+book.getNameOfBook()+".";
    }
    public String returnOfTheBook (Long readerId , Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalArgumentException("Invalid book ID"));
        Reader reader = readerRepository.findById(readerId).orElseThrow(()->
                new IllegalArgumentException("Invalid reader ID"));
        book.setStatus(Status.AVAILABLE);
        book.setReader(null);
        bookRepository.save(book);
        return "Читатель " + reader.getUserName() + " вернул книгу "+
                reader.getBooks().get(Math.toIntExact(bookId)).getNameOfBook();
    }

}*/
