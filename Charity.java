public class Charity
{
  // PROPERTIES
  private String   name;
  private String   link;
  private String   bank;
  private String   contact;
  private String[] categories;
  private String[] tags;
  
  // CONSTRUCTORS
  public Charity(String name, String link, String bank, String contact, String[] tags, int category)
  {
    this.name     = name;
    this.link     = link;
    this.bank     = bank;
    this.contact  = contact;
    this.tags     = tags;
    
    // Call buildCategoryList() to match given integers with Categories.CONSTANTS variables
    this.categories = buildCategoryList( category ); 
  }
  
  // METHODS
  public String   getName(){return name;}
  public String   getLink(){return link;}
  public String   getBank(){return bank;}
  public String   getCont(){return contact;}  
  public String[] getTags(){return tags;}
  public String[] getCategories(){return categories;}
  
  // This can be expanded upan the addition of more categories
  public String[] buildCategoryList( int categoryInteger )
  {
    String categoryString = Integer.toString( categoryInteger  );
    int len  = categoryString.length();
    
    String[] tempCategoryList = new String[ len ];
    
    for (int i = 0; i < len; i++)
    {
      int n = Character.getNumericValue( categoryString.charAt( i ) );
      
      for (int j = 0; j < Categories.SIZE; j++)
      {
        if (n == Categories.INT_CATEGORY_LIST[j])
          tempCategoryList[i] = Categories.STR_CATEGORY_LIST[j];
      }
    }
    
    return tempCategoryList;
  }
  
  public String toString()
  {
    String tTags = "";
    String tCate = "";
    
    // Create a String form from the String[] tags
    for (String s : tags)
    {
      tTags += s + ", ";
    }
    
    // Create a String form from the String[] categories
    for (String s : categories)
    {
      tCate += s + ", ";
    }
    
    return "Name:       " + name    + " \n " +
           "Link:       " + link    + " \n " +
           "Bank:       " + bank    + " \n " +
           "Contact:    " + contact + " \n " +
           "Tags:       " + tTags   + " \n " +
           "Categories: " + tCate   + " \n\n ";
  }
}