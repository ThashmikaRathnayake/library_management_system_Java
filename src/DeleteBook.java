import java.sql.*;
import java.util.Scanner;
public class DeleteBook {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "Bangtan#13@";
        String query = "delete from books where book_id = ?";

        Scanner scanner = new Scanner(System.in);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            while(true) {
                System.out.print("Enter the Book ID: ");
                int bookId = scanner.nextInt();
                scanner.nextLine();

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, bookId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Book deleted from the library successfully");
                } else {
                    System.out.println("Book doesn't exists. Please check ");
                }

                System.out.println("\nDo you want to delete another book? Yes or No");
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
