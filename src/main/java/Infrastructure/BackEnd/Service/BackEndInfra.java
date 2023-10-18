package Infrastructure.BackEnd.Service;

import Infrastructure.BackEnd.Model.BookStore.AddBookRequest;
import Infrastructure.BackEnd.Model.BookStore.Books;
import Infrastructure.BackEnd.Model.BookStore.DeleteBookRequest;
import Infrastructure.BackEnd.Model.BookStore.ReplaceIsbn;
import Infrastructure.BackEnd.Model.GenerateToken.GenerateToken;
import Infrastructure.BackEnd.Model.GenerateToken.GenerateTokenResponse;
import Infrastructure.BackEnd.Model.Information.Book;
import Infrastructure.BackEnd.Model.Information.UserInformationResponse;
import Infrastructure.UI.Selenium.Configuration;
import Infrastructure.UI.Selenium.JSONReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;



public class BackEndInfra{
    private static final String user = "/Account/v1/User";
    private static final String userInfo = "/Account/v1/User/";
    private static final String token = "/Account/v1/GenerateToken";
    private static final String books = "/BookStore/v1/Books";
    private static final String book = "/BookStore/v1/Book";
    private static final String changeBooks = "/BookStore/v1/Books/{ISBN}";
    private static final Configuration configuration = new Configuration();
    private static final Logger log = LoggerFactory.getLogger(BackEndInfra.class);
    JSONReader jsonReader = new JSONReader();
    private RequestSpecification request;

    public BackEndInfra() throws Exception {
        RestAssured.baseURI = String.valueOf(configuration.getBaseApiPath());
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + generateToken().getToken());
    };

    public GenerateTokenResponse generateToken() throws Exception {
        String userName = String.valueOf(configuration.getValue("username"));
        String password = String.valueOf(configuration.getValue("password"));
        GenerateToken generateToken = GenerateToken.defaultData(userName, password);
        return request.body(generateToken).post(token).prettyPeek().as(GenerateTokenResponse.class);
    }

    public Books getAllBooks(){
        return request.get(books).prettyPeek().as(Books.class);
    }

    public Book addBook(String userId, String isbn){
        AddBookRequest addBookRequest = AddBookRequest.defaultValue(userId, isbn);
        return request
                .body(addBookRequest)
                .post(books)
                .prettyPeek().as(Book.class);
    }

    public Integer deleteBook(String userId, String isbn){
        DeleteBookRequest deleteBookRequest = DeleteBookRequest.defaultValue(userId, isbn);
        return request
                .body(deleteBookRequest)
                .delete(book)
                .prettyPeek().statusCode();
    }


    public Book getBook(String isbn){
        return request.queryParam("ISBN", isbn).get(book)
                .prettyPeek().as(Book.class);
    }

}
