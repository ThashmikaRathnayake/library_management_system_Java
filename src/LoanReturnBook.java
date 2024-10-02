import java.sql.*;
import java.util.Scanner;
public class LoanReturnBook {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "Bangtan#13@";

        Scanner scanner = new Scanner(System.in);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            while (true) {
                System.out.print("Books Borrowing Section\n");
                System.out.print("1.Borrow \n" );
                System.out.print("2.Return \n" );
                System.out.print("(Select only the number) \n" );
                int choice = scanner.nextInt();
                scanner.nextLine();

                String query;

                PreparedStatement preparedStatement;


                switch(choice) {
                    case 1:
                        System.out.print("Enter the book loan details\n");
                        System.out.print("Book ID: ");
                        int bookId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Member ID: ");
                        int memberId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Loan Date (yyyy-mm-dd) : ");
                        String loanDateI = scanner.nextLine();
                        System.out.print("Due Date (yyyy-mm-dd) : ");
                        String dueDateI = scanner.nextLine();

                        //Converting to proper date format
                        Date loanDate = Date.valueOf(loanDateI);
                        Date dueDate = Date.valueOf(dueDateI);

                        query = "insert into loans (book_id, member_id, loan_date, return_date) values (?, ?, ?, ?)";

                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, bookId);
                        preparedStatement.setInt(2, memberId);
                        preparedStatement.setDate(3, loanDate);
                        preparedStatement.setDate(4, dueDate);

                        preparedStatement.executeUpdate();

                        System.out.println("Borrowed a book successfully");

                        break;

                    case 2:
                        System.out.print("Enter the book return details\n");
                        System.out.print("Loan ID: ");
                        int loan_Id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Return Date (yyyy-mm-dd) : ");
                        String returnDateI = scanner.nextLine();

                        Date returnDate = Date.valueOf(returnDateI);

                        query = "update loans set return_date = ?, status = 'returned' where loan_id = ?";

                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setDate(1, returnDate);
                        preparedStatement.setInt(2, loan_Id);

                        preparedStatement.executeUpdate();

                        System.out.println("Book is returned successfully");

                        break;

                    default:
                        System.out.print("Please enter the correct number");
                        continue;
                }
                System.out.println("\nDo you want to loan or return another book? Yes or No");
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
