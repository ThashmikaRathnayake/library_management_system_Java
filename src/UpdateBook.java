import java.sql.*;
import java.util.Scanner;
public class UpdateBook {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "Bangtan#13@";

        Scanner scanner = new Scanner(System.in);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            while (true) {
                System.out.print("Books update section\n");
                System.out.print("Enter the Book ID: ");
                int bookId = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Update book's \n" );
                System.out.print("1.Title \n" );
                System.out.print("2.Author \n" );
                System.out.print("3.Publisher \n" );
                System.out.print("4.Published Year \n" );
                System.out.print("5.Other \n" );
                System.out.print("(Select only the number) \n" );
                int choice = scanner.nextInt();
                scanner.nextLine();

                String query;
                String title, author, publisher;
                int publishedYear;
                PreparedStatement preparedStatement;

                switch(choice) {
                    case 1:
                        System.out.print("New book title: ");
                        title = scanner.nextLine();
                        query = "update books set title = ? where book_id = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, title);
                        preparedStatement.setInt(2, bookId);
                        break;

                    case 2:
                        System.out.print("New Author: ");
                        author = scanner.nextLine();
                        query = "update books set author = ? where book_id = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, author);
                        preparedStatement.setInt(2, bookId);
                        break;

                    case 3:
                        System.out.print("New Publisher: ");
                        publisher = scanner.nextLine();
                        query = "update books set publisher = ? where book_id = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, publisher);
                        preparedStatement.setInt(2, bookId);
                        break;

                    case 4:
                        System.out.print("New Published Year: ");
                        publishedYear = scanner.nextInt();
                        query = "update books set year_published = ? where book_id = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, publishedYear);
                        preparedStatement.setInt(2, bookId);
                        break;

                    case 5:
                        System.out.print("New book title: ");
                        title = scanner.nextLine();
                        System.out.print("New Author: ");
                        author = scanner.nextLine();
                        System.out.print("New Publisher: ");
                        publisher = scanner.nextLine();
                        System.out.print("New Published Year: ");
                        publishedYear = scanner.nextInt();
                        query = "update books set title = ?, author = ?, publisher = ?, year_published = ?  where book_id = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, title);
                        preparedStatement.setString(2, author);
                        preparedStatement.setString(3, publisher);
                        preparedStatement.setInt(4, publishedYear);
                        preparedStatement.setInt(5, bookId);
                        break;

                    default:
                        System.out.print("Please enter the correct number");
                        return;
                }
                preparedStatement.executeUpdate();
                System.out.println("Book details updated successfully");

                System.out.println("\nDo you want to update another book? Yes or No");
                String response = scanner.nextLine();
                if (!response.equalsIgnoreCase("yes")) {
                    break;
                }
            }
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
