# Task Master

An app for tracking tasks and their completion.

## 21 September 2022
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