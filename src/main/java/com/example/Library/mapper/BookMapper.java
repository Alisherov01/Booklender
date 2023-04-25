package com.example.Library.mapper;

import com.example.Library.dto.BookDto;
import com.example.Library.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto map(Book entity);
    Book map(BookDto dto);
    List<BookDto> map(List<Book> entities);

}
