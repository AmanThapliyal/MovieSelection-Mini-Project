import java.sql.*;
import java.util.Scanner;

public class project {
    private static final String DB_URL = "jdbc:mysql://localhost/movie";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "user1";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            Scanner scanner = new Scanner(System.in);

            // Get user input for selection criteria
            String selection = getUserSelection(scanner);
            if (selection == null) {
                System.out.println("Invalid selection. Exiting.");
                return;
            }

            // Execute query based on user selection
            String query = "";
            if (selection.equals("title")) {
                System.out.print("Enter movie title: ");
                String movieTitle = scanner.nextLine();
                query = "SELECT * FROM Movies WHERE Title = '" + movieTitle + "'";
            } else if (selection.equals("rating")) {
                System.out.print("Enter minimum IMDb rating: ");
                double minRating = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character
                System.out.print("Enter maximum IMDb rating: ");
                double maxRating = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character
                query = "SELECT Title FROM Movies WHERE Rating >= " + minRating + " AND Rating <= " + maxRating;
            } else if (selection.equals("runtime")) {
                System.out.print("Enter minimum runtime (in minutes): ");
                int minRuntime = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                System.out.print("Enter maximum runtime (in minutes): ");
                int maxRuntime = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                query = "SELECT Title FROM Movies WHERE Runtime >= " + minRuntime + " AND Runtime <= " + maxRuntime;
            } else if (selection.equals("year")) {
                System.out.println("Select a year between 2000 and 2018:");
                for (int year = 2000; year <= 2018; year++) {
                    System.out.print(year + " ");
                }
                System.out.println();
                int chosenYear;
                while (true) {
                    System.out.print("Enter the year: ");
                    chosenYear = scanner.nextInt();
                    if (chosenYear >= 2000 && chosenYear <= 2018) {
                        break;
                    }
                    System.out.println("Invalid year. Please enter a year between 2000 and 2018.");
                }
                scanner.nextLine(); // Consume newline character
                query = "SELECT Title FROM Movies WHERE Year = " + chosenYear;
            } else if (selection.equals("combined")) {
                System.out.print("Enter minimum IMDb rating: ");
                double minRating = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character
                System.out.print("Enter maximum IMDb rating: ");
                double maxRating = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character
                System.out.print("Enter minimum runtime (in minutes): ");
                int minRuntime = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                System.out.print("Enter maximum runtime (in minutes): ");
                int maxRuntime = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                System.out.println("Select a year between 2000 and 2018:");
                for (int year = 2000; year <= 2018; year++) {
                    System.out.print(year + " ");
                }
                System.out.println();
                int chosenYear;
                while (true) {
                    System.out.print("Enter the year: ");
                    chosenYear = scanner.nextInt();
                    if (chosenYear >= 2000 && chosenYear <= 2018) {
                        break;
                    }
                    System.out.println("Invalid year. Please enter a year between 2000 and 2018.");
                }
                scanner.nextLine(); // Consume newline character
                query = "SELECT Title FROM Movies WHERE Rating >= " + minRating + " AND Rating <= " + maxRating +
                        " AND Runtime >= " + minRuntime + " AND Runtime <= " + maxRuntime +
                        " AND Year = " + chosenYear;
            }

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Process the result
            if (!rs.isBeforeFirst()) {
                System.out.println("No movies found.");
            } else {
                if (selection.equals("title")) {
                    printMovieInfo(rs);
                } else {
                    while (rs.next()) {
                        System.out.println(rs.getString("Title"));
                    }
                    System.out.print("Enter the name of the movie from the list: ");
                    String selectedMovie = scanner.nextLine();
                    String movieQuery = "SELECT * FROM Movies WHERE Title = '" + selectedMovie + "'";
                    ResultSet movieResult = stmt.executeQuery(movieQuery);
                    printMovieInfo(movieResult);
                }
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String getUserSelection(Scanner scanner) {
        System.out.println("Select an option:");
        System.out.println("1. Search by title");
        System.out.println("2. Search by IMDb rating range");
        System.out.println("3. Search by runtime range");
        System.out.println("4. Search by specific year");
        System.out.println("5. Search by combined IMDb rating, runtime, and year");
        System.out.print("Enter your selection (1/2/3/4/5): ");
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                return "title";
            case "2":
                return "rating";
            case "3":
                return "runtime";
            case "4":
                return "year";
            case "5":
                return "combined";
            default:
                return null;
        }
    }

    private static void printMovieInfo(ResultSet rs) throws SQLException {
        if (rs.next()) {
            System.out.println("Title: " + rs.getString("Title"));
            System.out.println("Year: " + rs.getInt("Year"));
            System.out.println("Summary: " + rs.getString("Summary"));
            System.out.println("IMDB ID: " + rs.getString("IMDB_ID"));
            System.out.println("Runtime: " + rs.getInt("Runtime"));
            System.out.println("YouTube Trailer: " + rs.getString("YouTubeTrailer"));
            System.out.println("Rating: " + rs.getFloat("Rating"));
            System.out.println("Movie Poster: " + rs.getString("MoviePoster"));
            System.out.println("Director: " + rs.getString("Director"));
            System.out.println("Writers: " + rs.getString("Writers"));
            System.out.println("Cast: " + rs.getString("Cast"));
        } else {
            System.out.println("Movie not found in the database.");
        }
    }
}
