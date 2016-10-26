/******************************************************************
 * @authors FOUIA SOFTWARE SOLUTIONS - Arkin Yilmaz
 * @date    08.04.2016
 * @        HOPE for Android / Organization -> Version 1.0
 ******************************************************************/

public class Organization
{
   // Properties
   public String   name;
   public String   link;
   public String   contact;
   public String[] categories;
   public String[] tags;
   
   // Constructor
   public Organization( String name, String link, String contact, int category, String[] tags)
   {
      this.name = name;
      this.link = link;
      this.contact = contact;
      this.tags = tags;
      
      // Call buildCategoryList() to match given integers with Categories.CONSTANTS variables
      this.categories = buildCategoryList( category);
   }
   
   // Methods
   public String getName()
   {
      return name;
   }
   
   public String getContact()
   {
      return contact;
   }
   
   public String getLink()
   {
      return link;
   }
   
   public String[] getCategories()
   {
      return categories;
   }
   
   // This can be expanded upan the addition of more categories
   public String[] buildCategoryList( int categoryInteger)
   {
      String categoryString = Integer.toString( categoryInteger);
      int len  = categoryString.length();
      
      String[] tempCategoryList = new String[ len];
      
      for (int i = 0; i < len; i++)
      {
         int n = Character.getNumericValue( categoryString.charAt( i));
         
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
      return "Name: "          + name + 
             "\nLink: "        + link + 
             "\nContact: "     + contact + 
             "\nCategories: "  + tCate + 
             "\nTags: "        + tTags;
   }
}