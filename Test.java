import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Test
{
  public static void main (String[] args) throws Exception
  {  
    // Init. variables & constants...
    String txt = "";
    final String TEXT_DIRECTORY = "C:/Users/UmutBerk/Desktop/charity test/deneme.txt";
    String[] tags;
    ArrayList<Charity> charityCollection = new ArrayList<>();
    
    FileReader file = new FileReader( TEXT_DIRECTORY ); // Where should I read from?
    BufferedReader reader = new BufferedReader(file);   // from which FileReader object should I read from?
    
    String line = reader.readLine(); // Initialize line string to be used in the while loop below
    
    // An alternate approach to this would be:
    // ... BufferedReader reader = new BufferedReader( new FileReader( "MY_FILE_LOCATION"));
    
    // Read .txt file line by line and append to new String
    // ... until an empty line is found.
    while (line != null)
    {
      txt += line;
      line = reader.readLine();
    }
    
    String[] parts = txt.split("%");
    
    for (String s : parts)
    {
      String[] charity = s.split(" ");
      
      // Set the length of the tags array to the 
      // ... amount of tags in the charity array
      // ... which was formed by .split(" ");
      tags = new String[charity.length - 5];
      
      // Set the variables to the corresponding
      // ... array elements.
      String name = charity[0];      
      String link = charity[1];      
      String bank = charity[2];
      String cont = charity[3];
      int    cate = Integer.parseInt(charity[4]);
      
      // Find and add tags to new String[]
      for (int i = 5; i < charity.length; i++)
      {
        tags[i - 5] = charity[i];
      }
      
      // Create new Charity object and add it to the collection
      charityCollection.add( new Charity(name, link, bank, cont, tags, cate) );
    } 
    
    System.out.println(charityCollection); // report charity collection
    
    // Seaarch engine 
    String userIn = "";
    Scanner scan = new Scanner(System.in);
    
    while (!userIn.equals("exit"))
    {
      userIn = scan.nextLine();
      ArrayList<Charity> userCharityList = new ArrayList<>();;
      
      for (Charity c : charityCollection)
      {
        String[] tempTags = c.getTags();
        String[] tempCategories = c.getCategories();
        
        String combinedTags = "";
        String combinedCategories = "";
        
        for (String sT : tempTags)
        {
          combinedTags += sT;
        }
        
        for (String sC : tempCategories)
        {
          combinedCategories += sC;
        }
        
        boolean tagFlag = combinedTags.toLowerCase().contains(userIn.toLowerCase()); 
        boolean categoryFlag = combinedCategories.toLowerCase().contains(userIn.toLowerCase());
        boolean nameFlag = c.getName().toLowerCase().contains(userIn.toLowerCase());
          
        boolean userInputIsValid = !userIn.equals("") || !userIn.equals(" ") || !userIn.equals("\n");
        boolean searchQuerySuccessful = tagFlag || categoryFlag || nameFlag;
        
        if (userInputIsValid && searchQuerySuccessful)
          userCharityList.add( c );
      }
      
      System.out.println( (userCharityList.size() != 0) ? (userCharityList) : "\nNo results found.\n" );
    } 
  }
}

