-- SQL Table Creation for Green-not-Greed Organization

-- Create Volunteer Table
CREATE TABLE Volunteer (
    VolunteerID SERIAL PRIMARY KEY,
    Name VARCHAR(255),
    Email VARCHAR(255) UNIQUE,
    Phone VARCHAR(20),
    Tier INTEGER
);

-- Create Employee Table
CREATE TABLE Employee (
    EmployeeID SERIAL PRIMARY KEY,
    Name VARCHAR(255),
    Position VARCHAR(255),
    Salary DECIMAL,
    Email VARCHAR(255) UNIQUE,
    Phone VARCHAR(20)
);

-- Create Donor Table
CREATE TABLE Donor (
    DonorID SERIAL PRIMARY KEY,
    Name VARCHAR(255),
    Email VARCHAR(255) UNIQUE,
    Phone VARCHAR(20),
    Donationdate DATE,
    DonationAmount DECIMAL
);

-- Create Campaign Table
CREATE TABLE Campaign (
    CampaignID SERIAL PRIMARY KEY,
    Title VARCHAR(255),
    StartDate DATE,
    EndDate DATE,
    Location VARCHAR(255),
    Budget DECIMAL,
    Description TEXT
);

-- Create Event Table
CREATE TABLE Event (
    EventID SERIAL PRIMARY KEY,
    CampaignID INTEGER REFERENCES Campaign(CampaignID),
    Date DATE,
    Location VARCHAR(255),
    Description TEXT,
    Cost DECIMAL
);

-- Create Expense Table
CREATE TABLE Expense (
    ExpenseID SERIAL PRIMARY KEY,
    CampaignID INTEGER REFERENCES Campaign(CampaignID),
    Amount DECIMAL,
    Date DATE,
    Description TEXT
);

-- Create Contribution Table
CREATE TABLE Contribution (
    ContributionID SERIAL PRIMARY KEY,
    DonorID INTEGER REFERENCES Donor(DonorID),
    CampaignID INTEGER REFERENCES Campaign(CampaignID),
    Amount DECIMAL,
    Date DATE
);


-- Dummy Inserting:

-- Inserting dummy data for Volunteer
INSERT INTO Volunteer (Name, Email, Phone, Tier) VALUES
('John Doe', 'johndoe@example.com', '555-1234', 1),
('Jane Smith', 'janesmith@example.com', '555-5678', 2);

-- Inserting dummy data for Employee
INSERT INTO Employee (Name, Position, Salary, Email, Phone) VALUES
('Alice Johnson', 'Coordinator', 30000, 'alicej@example.com', '555-9012'),
('Bob Williams', 'Outreach Manager', 35000, 'bobw@example.com', '555-3456');

-- Inserting dummy data for Campaign
INSERT INTO Campaign (Title, StartDate, EndDate, Location, Budget, Description) VALUES
('Save the Forests', '2023-01-15', '2023-02-15', 'Greenwood Park', 1000, 'Campaign to protect local forests'),
('Clean the River', '2023-03-01', '2023-04-01', 'Riverdale', 1500, 'Initiative to clean the river and surrounding areas');

-- Inserting dummy data for Donor
INSERT INTO Donor (Name, Email, Phone, Donationdate, DonationAmount) VALUES
('Enviro Enthusiast', 'enviro@example.com', '555-6789', '2023-01-20', 500),
('Green Guardian', 'guardian@example.com', '555-9876', '2023-02-05', 750);

-- Inserting dummy data for Event
INSERT INTO Event (CampaignID, Date, Location, Description, Cost) VALUES
(1, '2023-01-16', 'Greenwood Park Center', 'Kickoff meeting for the campaign', 200),
(2, '2023-03-02', 'Riverdale Community Hall', 'River cleanup workshop', 150);

-- Inserting dummy data for Expense
INSERT INTO Expense (CampaignID, Amount, Date, Description) VALUES
(1, 100, '2023-01-17', 'Printed materials for Save the Forests campaign'),
(2, 200, '2023-03-03', 'Cleanup supplies for Clean the River campaign');

-- Inserting dummy data for Contribution
INSERT INTO Contribution (DonorID, CampaignID, Amount, Date) VALUES
(1, 1, 500, '2023-01-20'),
(2, 2, 750, '2023-02-10');






--added queries for assignment 4:

CREATE TABLE CampaignFeedback (
    FeedbackID SERIAL PRIMARY KEY,
    CampaignID INTEGER REFERENCES Campaign(CampaignID),
    VolunteerID INTEGER REFERENCES Volunteer(VolunteerID),
    Feedback TEXT,
    SubmissionDate DATE DEFAULT CURRENT_DATE
);

ALTER TABLE Volunteer ADD COLUMN Notes TEXT;
ALTER TABLE Campaign ADD COLUMN Notes TEXT;
