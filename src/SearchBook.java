import java.sql.*;
import java.util.Scanner;
public class SearchBook {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "Bangtan#13@";

        Scanner scanner = new Scanner(System.in);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            while (true) {
                System.out.print("BOOK FINDER\n");
                System.out.print("Search by \n" );
                System.out.print("1.Title \n" );
                System.out.print("2.Author \n" );
                System.out.print("3.Published Year \n" );
                System.out.print("(Select only the number) \n" );
                int choice = scanner.nextInt();
                scanner.nextLine();

                String query;
                String title, author, publisher;
                int publishedYear;
                int i = 1;
                PreparedStatement preparedStatement;
                ResultSet result;

                switch(choice) {
                    case 1:
                        System.out.print("Enter the Book Title: ");
                        title = scanner.nextLine();
                        query = "select * from books where title = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, title);

                        result = preparedStatement.executeQuery();

                        if (!result.isBeforeFirst()) {
                            System.out.println("No books available!");
                        } else {
                            System.out.println("\nBook Details");

                            while (result.next()) {
                                title = result.getString("title");
                                author = result.getString("author");
                                publisher = result.getString("publisher");
                                publishedYear = result.getInt("year_published");
                                System.out.println("\n"+i);
                                i++;
                                System.out.println("Title: " + title);
                                System.out.println("Author: " + author);
                                System.out.println("Publisher: " + publisher);
                                System.out.println("Published Year: " + publishedYear);
                            }
                        }

                        break;

                    case 2:
                        System.out.print("Enter the Author: ");
                        author = scanner.nextLine();
                        query = "select * from books where author = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, author);

                        result = preparedStatement.executeQuery();

                        if (!result.isBeforeFirst()) {
                            System.out.println("No books available!");
                        } else {
                            System.out.println("\nBook Details");

                            while (result.next()) {
                                title = result.getString("title");
                                author = result.getString("author");
                                publisher = result.getString("publisher");
                                publishedYear = result.getInt("year_published");
                                System.out.println("\n"+i);
                                i++;
                                System.out.println("Title: " + title);
                                System.out.println("Author: " + author);
                                System.out.println("Publisher: " + publisher);
                                System.out.println("Published Year: " + publishedYear);
                            }
                        }

                        break;

                    case 3:
                        System.out.print("Enter the Book published year: ");
                        publishedYear = scanner.nextInt();
                        scanner.nextLine();
                        query = "select * from books where year_published = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, publishedYear);

                        result = preparedStatement.executeQuery();

                        if (!result.isBeforeFirst()) {
                            System.out.println("No books available!");
                        } else {
                            System.out.println("\nBook Details");

                            while (result.next()) {
                                title = result.getString("title");
                                author = result.getString("author");
                                publisher = result.getString("publisher");
                                publishedYear = result.getInt("year_published");
                                System.out.println("\n"+i);
                                i++;
                                System.out.println("Title: " + title);
                                System.out.println("Author: " + author);
                                System.out.println("Publisher: " + publisher);
                                System.out.println("Published Year: " + publishedYear);
                            }
                        }
                        break;

                    default:
                        System.out.print("Please enter the correct number");
                        continue;
                }
                System.out.println("\nDo you want to search for another book? Yes or No");
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
