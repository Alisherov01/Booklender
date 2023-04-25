package com.example.Library.mapper;

import com.example.Library.dto.BorrowingDto;
import com.example.Library.entity.Borrowing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowingMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    BorrowingDto map(Borrowing borrowing);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "bookId", target = "book.id")
    Borrowing map(BorrowingDto dto);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "bookId", target = "book.id")
    List<BorrowingDto> map(List<Borrowing> entities);

}
