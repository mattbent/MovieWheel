import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class MovieWheel{
  public static void main(String[] args) {
    String movieList[] = new String[20];
    File file;
    boolean usingFile = false;
    System.out.println("Please Choose your input method");
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Press F to import movies from file OR press I for direct input");
    String answer = keyboard.nextLine();
    keyboard.close();
    int count = 0;
    if (answer == "F") {

      usingFile = true;

      System.out.println("Enter movie text filename(max 20 lines): ");

      String filename = keyboard.nextLine();
      file = new File(filename);
      Scanner fileScanner = new Scanner(file);
      while(fileScanner.hasNextLine()){
        movieList[count] = fileScanner.nextLine();
        count++;
      }
      fileScanner.close();
    }
    else {
      System.out.println("Enter movies and hit enter between each title, Type"
      +" 'END' and hit enter to finish\nMax 20 lines\n");
      Scanner movieInput = new Scanner(System.in);
      String movieTitle = movieInput.nextLine();
      while(movieTitle != "END"){
        movieList[count] = movieTitle;
        count++;
        movieTitle = movieInput.nextLine();
      }
      movieInput.close();

    }

    String movies[] = new String[count];
    for (int i = 0;i <= count; i++) {
      movies[count] = movieList[count];
    }

    Random rand = new Random();

    int n = rand.nextInt(count);

    System.out.println("Your movie is: " + movies[n]);

    if (usingFile) {
      System.out.println("Would you like to remove this movie from the movie file?(Y for Yes, N for No)");
      Scanner ans = new Scanner(System.in);
      String remove = ans.nextLine();
      if (remove == "Y") {
        removeLine(movies[n], file);
        System.out.println("Movie Removed");
      }

    }

  }

  public static void removeLine(String toRemove, File file){
    File tempFile = new File("myTempFile.txt");

    BufferedReader reader = new BufferedReader(new FileReader(file));
    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

    String currentLine;

    while((currentLine = reader.readLine()) != null) {
    // trim newline when comparing with lineToRemove
      String trimmedLine = currentLine.trim();
      if(trimmedLine.equals(toRemove)) continue;
      writer.write(currentLine + System.getProperty("line.separator"));
    }
    writer.close();
    reader.close();
    boolean successful = tempFile.renameTo(file);
  }
}
