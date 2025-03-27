import subprocess
import os
from contextlib import closing


def execute_query_via_psql(query, fetch=True, print_directly=False):
   
    os.environ['PGPASSWORD'] = 'jFzVlrV3'
    
    cmd = [
        "psql",
        "--host=studentdb.csc.uvic.ca",
        "--username=c370_s048",
        "--no-password",
        "--command={}".format(query)
    ]

    try:
        result = subprocess.run(cmd, check=True, text=True, capture_output=True)
        print(result.stdout)
        return result.stdout
    except subprocess.CalledProcessError as e:
        # if there are error
        print(f"Error executing query: {e.stderr}")
    finally:
        
        del os.environ['PGPASSWORD']



def load_queries():
    
    queries = []
    try:
        with open('gng-dump.sql', 'r') as file:
            lines = file.readlines()
        
        current_query = {'description': '', 'sql': ''}
        for line in lines:
            if line.startswith('--'):  # This is a description
                if current_query['sql']:  # Save the previous query if there is one
                    queries.append(current_query)
                    current_query = {'description': '', 'sql': ''}
                current_query['description'] = line.strip('--').strip()
            else:
                current_query['sql'] += line
        if current_query['sql']:  
            queries.append(current_query)
    except Exception as e:
        print(f"Failed to load queries: {e}")
    return queries

def predefined_queries_menu():
    
    queries = load_queries()
    
    if len(queries) < 29:
        print("Insufficient queries found.")
        return

    # Display the description for this section
    print(queries[27]['description'])  

    
    for i, query in enumerate(queries[28:], start=29):
        print(f"{i}. {query['description']}")

    choice = input("Select a query to execute (or 'q' to return): ")
    if choice.lower() == 'q':
        return

    try:
        
        choice_index = int(choice) - 29
        if 0 <= choice_index < len(queries) - 28:  # Ensure the choice is within the range after the description
            
            print("Executing:", queries[choice_index + 28]['description'])  # Adjust the index to match the queries list
            execute_query_via_psql(queries[choice_index + 28]['sql'])
        else:
            print("Invalid selection. Please choose a valid query number.")
    except ValueError:
        print("Please enter a valid number.")


def add_campaign():

 # Prompting for campaign details matching the Campaign table schema
    title = input("Enter the campaign title: ")
    description = input("Enter the campaign description: ")
    start_date = input("Enter the start date (YYYY-MM-DD): ")
    end_date = input("Enter the end date (YYYY-MM-DD): ")
    location = input("Enter the campaign location: ")
    budget = input("Enter the campaign budget: ")

    
    sql_command = f"""INSERT INTO Campaign (Title, StartDate, EndDate, Location, Budget, Description) VALUES ('{title.replace("'", "''")}', '{start_date}', '{end_date}', '{location.replace("'", "''")}', {budget}, '{description.replace("'", "''")}');"""

    # Executing the SQL command
    print(f"Adding new campaign: {title}")
    execute_query_via_psql(sql_command)
    info = input("Add volunteer? yes/no: ")

    if info == "yes":
        add_volunteer()
    if info == "no":
        return

    return

def add_volunteer():
    
    # Prompting for volunteer details matching the Volunteer table schema
    name = input("Enter the volunteer's name: ")
    email = input("Enter the volunteer's email: ")
    phone = input("Enter the volunteer's phone number: ")
    tier = input("Enter the volunteer's tier (e.g., 1, 2): ")

    
    sql_command = f"""INSERT INTO Volunteer (Name, Email, Phone, Tier) VALUES ('{name.replace("'", "''")}', '{email.replace("'", "''")}', '{phone.replace("'", "''")}', {tier});"""

    # Executing the SQL command
    print(f"Adding new volunteer: {name}")
    execute_query_via_psql(sql_command)

    info = input("Add Event? yes/no: ")

    if info == "yes":
        add_event()
    if info == "no":
        return

    return
    
def add_event():
    
    # Prompting for event details matching the Event table schema
    campaign_id = input("Enter the campaign ID this event is associated with: ")
    event_date = input("Enter the event date (YYYY-MM-DD): ")
    location = input("Enter the event location: ")
    description = input("Enter the event description: ")
    cost = input("Enter the event cost: ")

   
    sql_command = f"""INSERT INTO Event (CampaignID, Date, Location, Description, Cost) VALUES ({campaign_id}, '{event_date}', '{location.replace("'", "''")}', '{description.replace("'", "''")}', {cost});"""

    # Executing the SQL command
    print("Adding new event...")
    execute_query_via_psql(sql_command)
    return



def print_textual_report():
    financial_data = []
    query = """
    SELECT Donor.Name, SUM(Contribution.Amount) AS TotalDonation
    FROM Donor
    JOIN Contribution ON Donor.DonorID = Contribution.DonorID
    GROUP BY Donor.Name
    ORDER BY TotalDonation DESC;
    """
    command_output = execute_query_via_psql(query)

    lines = command_output.strip().split('\n')[2:-1]
    for line in lines:
        parts = line.split('|')
        if len(parts) == 2:
            name, total_donation = parts
            name = name.strip()
            total_donation = float(total_donation.strip())  # Convert to float for numeric operations
            financial_data.append((name, total_donation))  # Store in the list for later use
            print(f"{name}: ${total_donation}")  # Printing the processed report
    print('\n')
    
def print_graphical_report():
    
    financial_data = []  # To store (name, total_donation) tuples
    query = """
    SELECT Donor.Name, SUM(Contribution.Amount) AS TotalDonation
    FROM Donor
    JOIN Contribution ON Donor.DonorID = Contribution.DonorID
    GROUP BY Donor.Name
    ORDER BY TotalDonation DESC;
    """
    command_output = execute_query_via_psql(query)
    
    
    lines = command_output.strip().split('\n')[2:-1]

    
    for line in lines:
        parts = line.split('|')
        if len(parts) == 2:
            name, total_donation = parts
            name = name.strip()
            total_donation = float(total_donation.strip())  # Convert to float for numeric operations
            financial_data.append((name, total_donation))  # Store in the list for later use
            
    # Now, print the ASCII bar chart
    print_ascii_bar_chart(financial_data)  # Call the ASCII bar chart function with the collected data

def print_ascii_bar_chart(financial_data):
    
    print("\nDonor Financial Report (ASCII Bar Chart):")
    max_donation = max(total for _, total in financial_data)
    scale = max_donation / 50  # Adjust the scale to fit your output width preferences

    for name, donation in financial_data:
        bar_length = int(donation / scale)  # Calculate the length of the bar
        bar = '#' * bar_length  # Generate the bar using '#' characters
        print(f"{name}: {bar} (${donation})")  # Print the bar chart line
        
    print('\n')



def browse_membership_history():
    
    volunteer_id = input("Enter the volunteer's ID to browse their history: ")

    # Ensure the input is an integer
    try:
        volunteer_id = int(volunteer_id)
    except ValueError:
        print("Invalid ID. Please enter a numeric Volunteer ID.")
        return
    
    # SQL query to find campaigns based on contributions linked to the volunteer's ID.
    
    query = f"""
SELECT DISTINCT Campaign.Title, Campaign.StartDate, Campaign.EndDate, Campaign.Description, Campaign.Notes
FROM Campaign
JOIN Contribution ON Campaign.CampaignID = Contribution.CampaignID
JOIN Donor ON Contribution.DonorID = Donor.DonorID
WHERE Donor.DonorID = {volunteer_id};
"""

    # Execute the query and process results
    campaign_results = execute_query_via_psql(query)
    if campaign_results:
        print("\nCampaigns associated with the volunteer:")
        print(campaign_results)
    else:
        print("No history found for the volunteer.")

def add_annotation_to_volunteer():
    
    volunteer_id = input("volunteer id to add note to: ")
    note = input("note to add to volunteer: ")
    
    query = f"""
    UPDATE Volunteer
    SET Notes = COALESCE(Notes, '') || ' ' || '{note}'
    WHERE VolunteerID = {volunteer_id};
    """
    execute_query_via_psql(query)

def add_annotation_to_campaign():

    campaign_id = input("campaign id to add note to: ")
    note = input("note to add to campaign: ")
    
    query = f"""
    UPDATE Campaign
    SET Notes = COALESCE(Notes, '') || ' ' || '{note}'
    WHERE CampaignID = {campaign_id};
    """
    execute_query_via_psql(query)

def submit_campaign_feedback():
    
    """Allows a volunteer to submit feedback for a campaign."""
    
    campaign_id = input("campaign id to add feedback to: ")
    volunteer_id = input("volunteer id associated: ")
    feedback_text = input("The feedback text to add : ")

    query = f"""
    INSERT INTO CampaignFeedback (CampaignID, VolunteerID, Feedback)
    VALUES ({campaign_id}, {volunteer_id}, '{feedback_text}');
    """
    execute_query_via_psql(query)
    print("Feedback submitted successfully.")


def view_campaign_feedback():
    
    """Displays all feedback for a specific campaign."""
    
    campaign_id = input("campaign id to view feedback of: ")
    
    query = f"""
    SELECT Volunteer.Name, CampaignFeedback.Feedback, CampaignFeedback.SubmissionDate
    FROM CampaignFeedback
    JOIN Volunteer ON CampaignFeedback.VolunteerID = Volunteer.VolunteerID
    WHERE CampaignFeedback.CampaignID = {campaign_id}
    ORDER BY CampaignFeedback.SubmissionDate DESC;
    """
    feedback_results = execute_query_via_psql(query)
    if feedback_results:
        print(f"Feedback for Campaign ID {campaign_id}:")
        print(feedback_results)
    else:
        print("No feedback found for this campaign.")


def feedback():

    choice = input("choose options:\n1.Add feedback\n2.View feedback\n")
    
    if choice == '1':
        print('\n')
        submit_campaign_feedback()
    elif choice == '2':
        view_campaign_feedback()
        print('\n')
    
    else:
        print("Invalid selection.")

def history():

    choice = input("choose options:\n1.History\n2.Add annotation to volunteer\n3.Add annotation to campaign\n")
    
    if choice == '1':
        print('\n')
        browse_membership_history()
    elif choice == '2':
        add_annotation_to_volunteer()
        print('\n')
    elif choice == '3':
        add_annotation_to_campaign()
        
    else:
        print("Invalid selection.")
    
def report_accounting_information():
    
    choice = input("Choose report type (1 for Textual, 2 for Graphical): ")
    
    if choice == '1':
        print('\n')
        print_textual_report()
    elif choice == '2':
        print('\n')
        print_graphical_report()
    else:
        print("Invalid selection.")
        

def list_campaigns():
    query = """
    SELECT CampaignID, Title, Description, Budget
    FROM Campaign
    ORDER BY CampaignID;
    """

    # Execute the query and fetch the results
    print("List of Existing Campaigns:")
    execute_query_via_psql(query)
    

    query = """
    SELECT VolunteerID, Name, Email, Phone, Tier
    FROM Volunteer
    ORDER BY VolunteerID;
    """
    
    # Execute the query and fetch the results
    print("List of Volunteers:")
    execute_query_via_psql(query)
    
    query = """
    SELECT EventID, CampaignID, Date, Location, Description, Cost
    FROM Event
    ORDER BY Date;
    """
    
    # Execute the query and fetch the results
    print("Upcoming Events:")
    execute_query_via_psql(query)
   


def campaign_setupper():
    
    choice = input("choose options:\n1. Setup a campaign\n2. view the list of Campaigns, volunteers and events\n")
    
    if choice == '1':
        print('\n')
        add_campaign()
        
    elif choice == '2':
        print('\n')
        list_campaigns()
        
    else:
        print("Invalid selection.")

def main_menu():
    
    print("1. Execute Predefined Queries")
    print("2. Setup Campaign")
    print("3. View Accounting Information")
    print("4. Browse Membership History")
    print("5. Extra Features")
    print("6. Exit")
    choice = input("Enter your choice: ")
    return choice

def main():
    while True:
        choice = main_menu()
        if choice == '1':
            print('\n')
            predefined_queries_menu()
            
        elif choice == '2':
            print('\n')
            campaign_setupper()
            
        elif choice == '3':
            print('\n')
            report_accounting_information()

        elif choice == '4':
            print('\n')
            history()
            
        elif choice == '5':
            print('\n')
            feedback()
            
        elif choice == '6':
            print("Exiting program.")
            break
        else:
            print("This feature is not implemented yet.")

if __name__ == "__main__":
    main()