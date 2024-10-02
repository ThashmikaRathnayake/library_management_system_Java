import java.sql.*;
import java.util.Scanner;

public class InsertNewBookMember {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "Bangtan#13@";

        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            while (true) {
                System.out.print("Register Section\n");
                System.out.print("1. Add a new book\n");
                System.out.print("2. Add a new member\n");
                System.out.print("(Select only the number) \n");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                String query;
                PreparedStatement preparedStatement;

                switch (choice) {
                    case 1:
                        System.out.print("Enter the book details\n");
                        System.out.print("Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Publisher: ");
                        String publisher = scanner.nextLine();
                        System.out.print("Published Year: ");
                        int publishedYear = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        query = "INSERT INTO books (title, author, publisher, year_published) VALUES (?, ?, ?, ?)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, title);
                        preparedStatement.setString(2, author);
                        preparedStatement.setString(3, publisher);
                        preparedStatement.setInt(4, publishedYear);

                        preparedStatement.executeUpdate();

                        System.out.println("A new book added to the library successfully");
                        break;

                    case 2:
                        System.out.print("Enter the member details\n");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Phone: ");
                        String phone = scanner.nextLine();

                        query = "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, email);
                        preparedStatement.setString(3, phone);

                        preparedStatement.executeUpdate();

                        System.out.println("A new member added successfully");
                        break;

                    default:
                        System.out.println("Please enter the correct number");
                        continue;
                }

                System.out.println("\nDo you want to add a new book or a member? Yes or No ");
                String response = scanner.nextLine();
                if (!response.equalsIgnoreCase("yes")) {
                    break;
                }
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
