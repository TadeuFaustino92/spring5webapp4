package guru.springframework.spring5webapp.model.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.model.repository.AuthorRepository;
import guru.springframework.spring5webapp.model.repository.BookRepository;
import guru.springframework.spring5webapp.model.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;



@Component                                                                           // adicionando um spring component, salva os dados
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{     // implementa um listener da aplicação passando um generics de evento

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository,                           // constructor para injetar os repositórios
                        BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {    // gerar a partir de override methods, o evento diz que o contexto startou e está pronto
        initData();
    }

    private void initData() {                                                        // cria os objetos eric, rod e os 2 livros associados à eles
        Publisher publisher = new Publisher();
        publisher.setName("foo");

        publisherRepository.save(publisher);

        //Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without J2EE", "5678", publisher);
        rod.getBooks().add(noEJB);


        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }

}

