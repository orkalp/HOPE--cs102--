import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

/******************************************************************
 * @authors FOUIA SOFTWARE SOLUTIONS - IREM URAL                  *
 * @date    09 April 2016                                         *
 * @        HOPE for Android / User&UserCollection -> Version 1.0 *
 ******************************************************************/
public class UserCollection implements Iterator
{
  //constants
  private final int NO_OF_PROPERTIES = 5;
  
  //properties
  
  private ArrayList<User> collection = new ArrayList<User>();
  private int currentUser;
  private boolean currentUserIsActive;
  private String userDirectory;
  
  
  
  //constructor
  public UserCollection(String userDirectory)
  {
    this.userDirectory = userDirectory;
  }
  
  //methods
  
  //gets existing users from a text file
  public void build() throws IOException
  { 
    String line;
    int numberOfLines;
    String  name;
    String  password; 
    double  timeSpent;
    double  totalDonations;
    int     totalPoints;
    collection = new ArrayList<>();
    
    FileReader file = new FileReader(userDirectory);
    BufferedReader buffReader = new BufferedReader(file);
    
    numberOfLines = 0;
    name = "";
    password = "";
    timeSpent = -1 ; 
    totalDonations = -1;
    totalPoints = -1;
    
    // reads line by line
    line = buffReader.readLine();
    
    // user has five properties which are added line by line in the txt file 
    //reads line by line sets user properties and adds to the collection
    while(line != null)
    {
      numberOfLines++;
      if( numberOfLines % NO_OF_PROPERTIES == 1)
        name = line;
      else if( numberOfLines % NO_OF_PROPERTIES == 2)
        password = line;
      else if( numberOfLines % NO_OF_PROPERTIES == 3)
        timeSpent = Double.parseDouble(line);
      else if( numberOfLines % NO_OF_PROPERTIES == 4)  
        totalDonations = Double.parseDouble(line);
      else if( numberOfLines % NO_OF_PROPERTIES == 0)
      {
        totalPoints = Integer.parseInt(line);
        if( !!name.equals("") && !password.equals("") && totalDonations != -1 && totalPoints != -1 && timeSpent != -1)
           collection.add( new User(name,password,timeSpent, totalDonations,totalPoints ));
      }
      
      //read next line
      line = buffReader.readLine();
    }
     
    
  }
  
  @Override
  public boolean hasNext()
  {
    if (next() != null)
      return true;
    return false;
  }
  
  @Override
  public User next()
  {
    if(hasNext())
      return next();  
    return null;
  }
  
  //search user by user's name 
  public User searchUser(String target)
  {
    User user;
    while( hasNext())
    {
      user = next();
      if(user != null && user.getName() == target)
        return user;
    }
    return null;
    
  }
  
  //find the user who is login in the application and set currentUser
  public void setCurrentUser(User user)
  {
    int  count;
    User current; 
    count = 0;
    
    while(hasNext())
    {
      current = next();
      count++;
      
      if(current.getName() == user.getName() )
        currentUser = count;
        
    }
    
  }
  
  public User getCurrentUser()
  {
    return collection.get(currentUser);
  }
  
  //The users will be added in the text file or by using SQL
  public void addUser(User user)
  {
    collection.add(user);
  }
  
  
  private class User 
  {
     //properties
     private String name;
     private String password;
     private int totalPoints;
     private double totalDonations;
     private double totalTimeSpent;
  
    //constructor
    public User(String name, String password,double totalTimeSpent,double totalDonations,int totalPoints)
    {
      this.name     = name;
      this.password = password;
      this.totalDonations = totalDonations;
      this.totalTimeSpent = totalTimeSpent;
      this.totalPoints = totalPoints;
    }
    
    
    //methods 
    public void setName(String name)
    {
      this.name = name;
    }
    
    public String getName()
    {
       return name;
    }
    
    public boolean setPassword(String password)
    {
       boolean upper;
       boolean lower;
       boolean number;
       char  current;
       number = false;
       upper  = false;
       lower  = false;
       //password should contain at least seven characters
       if( password.length() < 7)
         return false;
       
       // check whether password contains at least one capital letter, one lower case letter and a number
       for( int i = 0; i < password.length(); i++)
       {
         current = password.charAt(i);
         if( current >= 'A' && current <= 'Z')
           upper = true;
         if( current >= 'a' && current <= 'z')
           lower = true;
         if( current >= '1' && current <= '9')
           number = true;
       }
       // if all conditions are satisfied set password
       if( upper == true && lower == true && number == true)
       {
         this.password = password;
         return true;
       }
       else 
         return false;
       
    }
  
    public double getDonations()
    {
       return totalDonations;
    }  
  
    public int getPoints()
    {
       return totalPoints;
    }
  
    // calculate points earned by donationAmount and increase score
    public void addPoint(double donationAmount)
    {
      
       totalPoints = totalPoints + (int)(donationAmount * 10);
    
    }
    
 
    //the first time when the user employs our application they will register to our appliction
    public boolean register(String name,String password,String passConfirm)
    {
      boolean existName;
      existName = false;
     
       //control whether user with the same name exists in the collection or not
      while(hasNext() == true && existName == false )
      {
        if(next().getName() == name )
          existName = true;
      }
      
      if(existName == false)
      { 
        //check password and passConfrim match
        if ( !password.equals(passConfirm))
          return false;
        if (setPassword(password) == false )
          return false;
        setName(name);
        totalDonations = 0;
        totalTimeSpent = 0;
        totalPoints    = 0;
        
        return true;
      }
      return false;
    } 
  
    //increment score according to donation amount and make a donation to charity
    public void donateTo(Charity charity,double amount)
    {
    
       charity.donate(amount);
    
       //increment points & total donation 
       addPoint(amount);
       totalDonations = totalDonations + amount;
    }  
    
    //according to the time spent by the user in our application,increment users score
     public void addTime(double time)
     {
       totalTimeSpent = totalTimeSpent + time;
    
       //increment points 
        addPoint(time);
     }
  }
}