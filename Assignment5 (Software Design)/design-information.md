1. A grocery list consists of items the users want to buy at a grocery store. The application                
must allow users to add items to a list, delete items from a list, and change the quantity                 
of items in the list (e.g., change from one to two pounds of apples). 

I created a class List which will hold a list of items. I also created a class for the item objects which will be able to be deleted or edited. There is a method to add an item.

2. The application must contain a database (DB) of items​  and corresponding item types​ . 

The database portion wasn't included since it wasn't necessary for the diagram

3. Users must be able to add items to a list by picking them from a hierarchical list, where                 
the first level is the item type (e.g., cereal), and the second level is the name of the                 
actual item (e.g., shredded wheat). After adding an item, users must be able to specify a               
quantity for that item. 

Class created for item type and another class after for the specific item

4. Users must also be able to specify an item by typing its name. In this case, the                
application must look in its DB for items with similar names and ask the users, for each                
of them, whether that is the item they intended to add. If a match cannot be found, the                 
application must ask the user to select a type for the item and then save the new item,                 
together with its type, in its DB. 

Method was included to allow the user to pick out a specific item.

5. Lists must be saved automatically and immediately after they are modified. 

This feature is not necessary for this diagram.

6. Users must be able to check off items in a list (without deleting them). 

Every item has a method that checks off 

7. Users must also be able to clear all the check-off marks in a list at once. 

Lists have a method to check off all items at once

8. Check-off marks for a list are persistent and must also be saved immediately. 

Not necessary for the diagram.

9. The application must present the items in a list grouped by type, so as to allow users to                 
shop for a specific type of products at once (i.e., without having to go back and forth                
between aisles). 

Since this is specific to the UI, so this portion will not be necessary for the diagram.

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly             
farmer’s market list”). Therefore, the application must provide the users with the ability to             
create, (re)name, select, and delete lists. 

When creating a user, the user will be able to create as many lists as they want with createList()

11. The User Interface (UI) must be intuitive and responsive.

This portion is not needed for this diagram.
