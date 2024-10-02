import java.sql.*;
import java.util.Scanner;
public class InsertNewBook {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "Bangtan#13@";
        String query = "insert into books (title, author, publisher, year_published) values (?, ?, ?, ?)";

        Scanner scanner = new Scanner(System.in);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            System.out.print("Enter the book details\n");
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Author: ");
            String author = scanner.nextLine();
            System.out.print("Publisher: ");
            String publisher = scanner.nextLine();
            System.out.print("Published Year: ");
            int yearPublished = scanner.nextInt();


            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, publisher);
            preparedStatement.setInt(4, yearPublished);

            preparedStatement.executeUpdate();

            System.out.println("A new book added to the library successfully");

            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
