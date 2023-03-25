package com.example.Library.service;

import com.example.Library.entity.Book;
import com.example.Library.entity.Library;
import com.example.Library.entity.Reader;
import com.example.Library.enums.Status;
import com.example.Library.repository.LibraryRepository;
import com.example.Library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LibraryService {
    ReaderService readerService;
    BookService bookService;
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
        Book book = bookService.getById(bookId);
        book.setStatus(Status.NOT_AVAILABLE);
        Reader reader = readerService.getById(readerId);
        book.setReader(reader);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        reader.setBooks(bookList);
        bookService.update(book);
        readerService.update(reader);
        return "Читатель " + reader.getUserName() + " взял книгу "+book.getNameOfBook()+".";
    }
    public String returnOfTheBook (Long readerId , Long bookId) {
        Book book = bookService.getById(bookId);
        book.setStatus(Status.AVAILABLE);
        Reader reader = readerService.getById(readerId);
        for (int i = 0; i < reader.getBooks().size(); i++) {
          if (bookId == reader.getBooks().get(i).getId()){
              reader.getBooks().get(i).setStatus(Status.AVAILABLE);

          }
          readerService.update(reader);
        }
        bookService.update(book);

        return "Читатель " + reader.getUserName() + " вернул книгу "+ book.getNameOfBook();
    }
}
