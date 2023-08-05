package com.engineer.library;

import com.engineer.library.controller.BookController;
import com.engineer.library.model.Book;
import com.engineer.library.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(BookController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class BookControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    //int id, String name, String author, String imageS3Id, boolean isVisible
    Book RECORD_1 = new Book(1, "Cracking the Coding Interview", "Gayle Laakmann McDowell", null, true);
    Book RECORD_2 = new Book(2, "Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", null, true);
    Book RECORD_3 = new Book(3, "Designing Data-Intensive Applications", "Martin Kleppmann", null, true);
    Book RECORD_4 = new Book(4, "The Pragmatic Programmer", "Andrew Hunt, David Thomas", null, true);
    Book RECORD_5 = new Book(5, "Head First Design Patterns", "Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra", null, true);
    Book RECORD_6 = new Book(6, "Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", null, true);
    Book RECORD_7 = new Book(7, "Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein", null, true);
    Book RECORD_8 = new Book(8, "Refactoring: Improving the Design of Existing Code", "Martin Fowler", null, true);
    Book RECORD_9 = new Book(9, "Clean Architecture: A Craftsmans Guide to Software Structure and Design", "Robert C. Martin", null, true);
    Book RECORD_10 = new Book(10, "Domain-Driven Design: Tackling Complexity in the Heart of Software", "Eric Evans", null, true);


    @Test
    public void getAllRecords_success() throws Exception{
        List<Book> records = new ArrayList<>(Arrays.asList(
                RECORD_1, RECORD_2, RECORD_3,
                RECORD_4, RECORD_5, RECORD_6,
                RECORD_7, RECORD_8, RECORD_9,
                RECORD_10));
        Mockito.when(bookRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/test/all")
                        .header("Api-Version", "v1.0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Matchers.is("Designing Data-Intensive Applications")));
    }
}
