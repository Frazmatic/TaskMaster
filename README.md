# Task Master

An app for tracking tasks and their completion.

## 5 - 9 October 2099
## Cognito 
- LogIn Button:
  - Changes to LogOut function if already logged in
  - Recycler (tasks) hidden if not logged in
  - goes to log in page if logged out
- LogIn Activity:
  - Email & Password elements
  - gets authentication from AWS Cognito then return to main page
- SignUp Activity:
  - Linked from Log In page
  - email & password
  - Links to Confirmation Activity
- Confirmation Activity:
  - Takes confirmation code then returns to main
  - refreshes if not valid
  - TO DO: resend confirmation request from AWS

![Hope Page - Not Logged In](./screenshots/homePageNotLoggedIn.jpg)
![Log In](./screenshots/loginPage.jpg)
![Home Page - Logged In](./screenshots/loggedIn.jpg)
![Sign Up](./screenshots/signup.jpg)
![Confirmation Page](./screenshots/confirmationPage.jpg)
![Confirmation Failure](./screenshots/confirmationFailure.jpg)


## 4 October 2022
## Teams
- Added Team entity with relationship to Task (Team has many tasks)
- Configured Add Task to load Teams from DynamoDB
- Added Spinner to Choose Team
- Team now saved to Task with Add task
- Adjusted Settings Page to choose Team
- Home page only shows user team's tasks
  - Shows all tasks if no team selected

![Homepage](./screenshots/homepageV4a.jpg)
![Homepage 2](./screenshots/homepageV4b.jpg)
![Add Task](./screenshots/addtaskV2.jpg)
![Settinggs](./screenshots/settings.jpg)

## 1 October 2022
## DynamoDB & GraphQL
- Removed Rooms local DB classes and references
- Created Task & TaskState models in schema.graphql
- Updated Classes to work with Amplify generated models:
  - AddTask Activity
  - Main Activity
  - RecyclerView Adapter (TaskAdapter)
- Updated Classes to Load to/from Amplify DynamoDB:
  - AddTask
  - MainActivity
- Figured out how to get corrected String format for date-time object returned from DB via Amplify

![AWS loaded screenshot](./screenshots/TasksLoadedFromAWS.jpg)
![Add Task Uploaded to DynamoDB Screenshot](./screenshots/DynamoDb.jpg)

## 29 September 2022
### AWS
- Set up AWS account w/ admin user
- Configured & initialized Amplify in root directory
- Setup initial backend API with sample data schema

![AWS GraphQL Initial Setup](/screenshots/GraphQLApi.jpg)

## 26 September 2022
### Espresso Testing
- Recorded Tests for updating username with Settings Activity
  - Asserts that name is saved and show up upon returning to Main Activity
- Recorded Tests for Add Task Activity
  - Asserts that tasks added counter is updated and visible
  - Asserts that tasks with their name, description, and state are visible on Main Page in RecyclerView
- Recorded Tests for Task Details
  - Asserts that tasks in RecyclerView can be clicked/tapped and loads TaskDetails Activity
  - Asserts that name, description, and state of the clicked/tapped task are visible in TaskDetails
- Did not change UIs today, screenshots remain identical.

## 22 September 2022
### Database Persistence
- Added Room DB dependencies
- Updated Task class to Entity
- Moved TaskState enum to subclass of Task
  - implemented string conversion methods for Enum
- Built Data Access Object (DAO) class for Task
- Built Database (Room) class with Task entity & TaskDAO
- Added Date created field to Task
- Added Date created display to Adapter & Layout for RecyclerView
- Add DB to MainActivity and Load it to the RecyclerView
  - Method to setup database called in onCreate
  - Methods to reset list used in RecyclerView called in onResume
- DB setup for AddTask Activity
  - Tasks Added Count added; 0 on create; increments with adds
    - Updates tasks added count display on add 
  - Setup method to add tasks to DB
  - Added Toast for successful submission
  - Ensured empty title task can't be added
- Updated TaskAdapter and TaskDetails to carry over title, description, date, and state.
- Added Espresso Tests (with Recorder)

![Home Page Version3](./screenshots/homepagev3.jpg)
![Add Task Screenshot](./screenshots/addtask.jpg)
![Task Details Version 3](./screenshots/task-detailsV3.jpg)

## 21 September 2022
### RecyclerView
- Created Task Class
- Placed RecyclerView Component in Main Activity
- Created methods in Main Activity Java to set RecyclerView layout & adapter, and load with sample Task data
- Created fragment class and layout for displaying tasks in RecyclerView
- Created Adapter & ViewHolder class to setup items for display in recycler
- Stretch: fill in details on TaskDetails Activity from recycler object call

![Home Page Version 2](./screenshots/homepageV2.jpg)
![Task Details Version 2](./screenshots/task-detailsV2.jpg)

---

## 20 September 2022
- Created Settings Page
  - TextView with hint for entering username.
  - Save button.
  - Toast popup.
  - Saved to DefaultSharedPreferences under userName Key.
  - Updated parent in Manifest for upward navigation
  - User Name ExitText filled in with username if already exists
- Create Task Details Page
  - Created Title TextView
    - Title Fills in from Main page's Intent (the task title)
  - Created Description Text View
  - Updated parent in Manifest for upward navigation
- Adjusted Home/Main Page
  - Add Three Buttons With Task Titles
    - Wrote method to open Task Detail's Page with relevant Title in Intent
  - Username loaded and displayed from DefaultSharedPreferences, on create & resume
  - Added Button to load Settings page

![Task Details Screenshot](./screenshots/task-details.jpg)

---

## 19 September 2022
- Added Activities for Home, Add Task, and All Tasks
- Added text views to all Activities
- Added image views to Home & All to display title and fill in for future task list display.
- Added buttons to home that navigate to Add & All Activities.
- Added upward navigation to All & Add Activities
- Added submit Add button to AddTask Activity that causes a text element to display "Submitted!"
- All text & button fields obtained from strings.hml in res/values

![Home Page Screenshot](./screenshots/homepage.jpg)

---
