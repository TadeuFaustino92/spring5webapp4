package guru.springframework.spring5webapp.model.controllers;

import guru.springframework.spring5webapp.model.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")                       // chamada http de /books
    public String getBooks(Model model) {           // model do MVC

        model.addAttribute("books", bookRepository.findAll());      // adiciona um atributo ao módulo chamado "books", que será uma lista
        return "books";                                                // de livros do nosso repositório
    }                                                                  // retorna uma string books e associa-se com a thymeleaf view
}
