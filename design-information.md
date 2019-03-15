1. A grocery list consists of items the users want to buy at a grocery store. The application                
must allow users to add items to a list, delete items from a list, and change the quantity                 
of items in the list (e.g., change from one to two pounds of apples). 

I included a class List which will hold a list of reminders. There is a method to add a reminder. I also created a class for the reminder objects which will be able to be deleted or edited.

2. The application must contain a database (DB) of items​  and corresponding item types​ . 

I didn't include the database portion since it wasn't necessary for the diagram.

3. Users must be able to add items to a list by picking them from a hierarchical list, where                 
the first level is the item type (e.g., cereal), and the second level is the name of the                 
actual item (e.g., shredded wheat). After adding an item, users must be able to specify a               
quantity for that item. 

I included classes for reminder type and specific reminders.

4. Users must also be able to specify an item by typing its name. In this case, the                
application must look in its DB for items with similar names and ask the users, for each                
of them, whether that is the item they intended to add. If a match cannot be found, the                 
application must ask the user to select a type for the item and then save the new item,                 
together with its type, in its DB. 

I included a method for a user to be able to specify a reminder.

5. Lists must be saved automatically and immediately after they are modified. 

Not necessary to include in diagram.

6. Users must be able to check off items in a list (without deleting them). 

Each reminder has a method to check it off.

7. Users must also be able to clear all the check-off marks in a list at once. 

Each list has a method to check all.

8. Check-off marks for a list are persistent and must also be saved immediately. 

Not necessary for the diagram.

9. The application must present the items in a list grouped by type, so as to allow users to                 
shop for a specific type of products at once (i.e., without having to go back and forth                
between aisles). 

Not necessary for the diagram, this is specific to the UI.

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly             
farmer’s market list”). Therefore, the application must provide the users with the ability to             
create, (re)name, select, and delete lists. 

A user can invoke the createList method any number of times.

11. The User Interface (UI) must be intuitive and responsive.
