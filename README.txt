Title of Program:

Appointment Scheduling System


Purpose of Program:

To fulfill requirements for a global consulting organization
that conducts business in multiple languages. 

The organization possesses main offices in the United States,
Canada, and in the United Kingdom, and utilizes a database.
This application helps in accessing that database in an easy-
to-use graphical user interface, allowing for updating
and adding both customers and associated appointments.


Author of Program:

Gabrial Hollifield


Author Contact Information:

Email: gabholli@wgu.edu


Application Version:

1.0


Date:

05/11/2022


IDE Version Number:

IntelliJ Community 2021.1.3


Full JDK Version:

Java JDK 17.0.1


JavaFX Version:

JavaFX-SDK-17.0.1


Directions For Running The Program:

- Launch Application

- On Login Screen, type appropriate credentials to login

- Click Sign In button to sign into application, or click Cancel to exit

- Upon signing in, the main screen appears with two tables. The top table
  is for customer records. The bottom table is for customer appointments

- Click on the Add, Update, or Delete button under the customer records table
  to add, update, or delete a customer, respectively

  - If the Add button is clicked, the add customer form will appear. Fill in
    the appropriate information, then either click Add to add the record to the
    records table, or click Cancel to go back to the main screen without
    adding.
  
  - If the Update button is clicked, the update customer form will appear. Change
    the fields as desired, then either click Update to update the selected record
    in the record table in the main screen, or click Cancel to return to the 
    main screen without updating

  - If the Delete button is clicked, a prompt will appear to verify the user
    desires to delete the customer record. When deleting a customer from the 
    records table, all associated appointments in the appointments table(linked
    by Customer ID) will become deleted also

- Click on the Add, Update, or Delete button under the appointments table to 
  add, update, or delete a customer appointment, respectively
  
  - If the Add button is clicked, the add appointment form will appear. Fill in
    the appropriate information, then either click Add to add the appointment to the
    appointment table, or click Cancel to go back to the main screen without
    adding
  
  - If the Update button is clicked, the update appointment form will appear. Change
    the fields as desired, then either click Update to update the selected appointment
    in the appointments table in the main screen, or click Cancel to return to the 
    main screen without updating

  - If the Delete button is clicked, a prompt will appear to verify the user
    desires to delete the customer appointment

- The appointments table has additional functionality: the ability to sort the
  table by month, week, or all using the corresponding radio buttons

  - Click the Month radio button to view appointments scheduled from the current
    time one month forward

  - Click the Week radio button to view appointments scheduled from the current
    time one week forward

  - Click the All radio button to view all appointments scheduled

- To access specialized reports, click the Reports button below the appointments
  table

  - Upon clicking the Reports button, the reports screen will appear, showing
    three different reports
  
     - The first report allows the user to filter appointments by the associated
       contact. Select the desired contact from the drop down menu and the the
       table will automatically sort
    
     - The second report allows the user to see how many appointments are available,
       filtered by month and type. To use this report, select the desired month, then
       the types found within that month, and the number of appointments found will
       show

     - (This report is the report of my choice for rubric requirement A3F)
       The third report allows the user to see how many unique customers are in the
       scheduling system(sorted by customer name). Simply click on the Show Number
       Of Unique Customers button to generate the report results


MYSQL Connector Driver Version Number:

mysql-connector-java-8.0.25
