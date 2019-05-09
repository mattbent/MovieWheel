import java.util.Scanner;
import java.io.*;
import java.util.Random;
import java.util.Arrays;

public class MovieWheel{
  public static void main(String[] args) throws FileNotFoundException{
    String movieList[] = new String[20];
    File file = null;
    boolean usingFile = false;
    System.out.println("Please Choose your input method");
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Press F to import movies from file OR press I for direct input");
    String answer = keyboard.nextLine();
    int count = 0;
    if (answer.equals("F")) {

      usingFile = true;

      System.out.println("Enter movie text filename(max 20 lines): ");

      String filename = keyboard.nextLine();
      file = new File(filename);
      Scanner fileScanner;

      try {
        fileScanner = new Scanner(file);
      }
      catch (FileNotFoundException e) {
        System.out.println();
        System.out.println("File not found");
        return;
      }

      while(fileScanner.hasNextLine()){
        movieList[count] = fileScanner.nextLine();
        count++;
      }
      fileScanner.close();
    }
    else{
      System.out.println("Enter movies and hit enter between each title, Type"
      +" 'END' and hit enter to finish\nMax 20 lines\n");

      String movieTitle = keyboard.nextLine();
      while(!movieTitle.equals("END")){
        movieList[count] = movieTitle;
        count++;
        movieTitle = keyboard.nextLine();
      }

    }
    String movies[] = new String[count];
    for (int i = 0;i < count; i++) {
      movies[i] = movieList[i];
    }

    Random rand = new Random();

    int n = rand.nextInt(count);

    System.out.println("Your movie is: " + movies[n]);

    if (usingFile) {
      System.out.println("Would you like to remove this movie from the movie file?(Y for Yes, N for No)");
      String remove = keyboard.nextLine();
      if (remove.equals("Y")) {
        removeLine(movies[n], file);
        System.out.println("Movie Removed");
      }

    }

  }

  public static void removeLine(String toRemove, File file){
    File tempFile = new File("myTempFile.txt");
    BufferedReader reader = null;
    BufferedWriter writer = null;
    try {
      reader = new BufferedReader(new FileReader(file));


    } catch(FileNotFoundException ex) {
      System.out.println("FILE ERROR");
    }

    try {
      writer = new BufferedWriter(new FileWriter(tempFile));
      String currentLine;
      while((currentLine = reader.readLine()) != null) {
      // trim newline when comparing with lineToRemove
        String trimmedLine = currentLine.trim();
        if(trimmedLine.equals(toRemove)) continue;
        writer.write(currentLine + System.getProperty("line.separator"));
      }
      writer.close();
      reader.close();
    } catch(IOException io) {
      System.out.println("IO ERROR");
    }

    boolean successful = tempFile.renameTo(file);
  }
}
