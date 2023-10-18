package UISelenium;

import Infrastructure.BackEnd.Model.BookStore.Books;
import Infrastructure.BackEnd.Model.Information.Book;
import Infrastructure.BackEnd.Service.BackEndInfra;
import Infrastructure.UI.Selenium.Configuration;
import org.testng.annotations.Test;



public class TestItAPI {
    BackEndInfra backEndInfra = new BackEndInfra();
    private static final Configuration configuration = new Configuration();


    public TestItAPI() throws Exception {
    }

    @Test(description = "")
    public void tryItApi() throws Exception {
        String userId = String.valueOf(configuration.getValue("userId"));
        Books books =  backEndInfra.getAllBooks();
        Book book = books.getBooks().get(1);
        backEndInfra.getBook(book.getIsbn());
        backEndInfra.deleteBook(userId, book.getIsbn());
        backEndInfra.addBook(userId, book.getIsbn());
    }
}
