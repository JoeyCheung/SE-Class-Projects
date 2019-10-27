# Test Plan

**Author**: \<Team 5>

## 1 Testing Strategy

### 1.1 Overall strategy

We will manually test all of our methods.

### 1.2 Test Selection

Black box will be used for front-end GUI, the user using an app
white box will be for back-end and database functionality, the developers

### 1.3 Adequacy Criterion

A manual test will be implemented for every method

### 1.4 Bug Tracking

Through manual testing if we encounter bugs we will communicate them to the teams via whatsapp

### 1.5 Technology

Manual test cases, only use of an android phone. No technology like junit.

## 2 Test Cases


|Purpose | Steps | Expect | Results | Pass/Fail |
|-------|--------|--------|---------|--------|
|Scrollable  |1.Open app to homepage with all grocery lists 2. View List(s) 3. Access/View all items on your list|You can scroll down to see all the items| Able to scroll | Pass
|AddItemToDB |1.Open app to homepage with all grocery lists 2.View List(s) 3. View Items on the list 4. Search the database for an item you want to add 5. If item not listed, create a new item by giving its name and type| Allows user to add items to the database| Able to add items to database | Pass
|AddItemToList |1.Open app to homepage with all grocery lists 2. View list(s) 3. Access the list 4. Search the database for an item you'd like to add 5. Add Item to list|User is able to add item to the list and see it displayed on their list| Able to add item to list. Visible on the specified list. | Pass
|RemoveItemFromList |1.Open app to homepage with all grocery lists 2. View list(s) 3. Select an item to remove from the list|User was able to remove item from the grocery list| Able to remove items from grocer list. | Pass
|CreateList |1.Open app to homepage with all grocery lists 2. View List(s) 3. Create a new grocery list by entering a name, regardless of whether there exists a list or not|User should be able to create a new grocery list| Newly created lists appear. | Pass
|RemoveList|1.Open app to homepage with all grocery lists 2. View List(s) 3. Select a list the user wants to delete|User should be able to remove a grocery list| Removed grocery lists disappear. | Pass
|RenameList |1.Open app to homepage with all grocery lists 2. View List(s) 3. Hold down on the list you want to rename|User should be able to rename a grocery list| Renaming page opens and saves new name correctly. | Pass
|SearchForItem |1.Open app to homepage with all grocery lists 2. View List(s) 3. When adding an item the user should be able to type in the name of the item they are searching for and the app should respond in real time by showing results that are similar to what they are typing|User should be able to see the item they are searching for and those items with similar names| Able to see filtered choices while typing. Able to see and choose matching item. However, when you backspace it does not refilter results. | Partial Pass
