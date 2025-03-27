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







--writing the questions for the queries:

-- Q1: Total donations per campaign
CREATE VIEW Q1_TotalDonationsPerCampaign AS
SELECT CampaignID, SUM(Amount) AS TotalDonations
FROM Contribution
GROUP BY CampaignID;

-- Q2: Top 3 donors by total donation amount
CREATE VIEW Q2_Top3Donors AS
SELECT Donor.Name, SUM(Contribution.Amount) AS TotalDonation
FROM Donor
JOIN Contribution ON Donor.DonorID = Contribution.DonorID
GROUP BY Donor.Name
ORDER BY TotalDonation DESC
LIMIT 3;

-- Q3: Average event cost per campaign
CREATE VIEW Q3_AverageEventCostPerCampaign AS
SELECT CampaignID, AVG(Cost) AS AverageCost
FROM Event
GROUP BY CampaignID;

-- Q4: Donors above average donation
CREATE VIEW Q4_DonorsAboveAverage AS
SELECT DonorID, Name, DonationAmount
FROM Donor
WHERE DonationAmount > (SELECT AVG(DonationAmount) FROM Donor);

-- Q5: Campaign finances (total expenses and contributions)
CREATE VIEW Q5_CampaignFinances AS
SELECT 
    CampaignID,
    (SELECT SUM(Amount) FROM Expense WHERE Expense.CampaignID = Campaign.CampaignID) AS TotalExpenses,
    (SELECT SUM(Amount) FROM Contribution WHERE Contribution.CampaignID = Campaign.CampaignID) AS TotalContributions
FROM 
    Campaign;

-- Q6: Campaigns over budget
CREATE VIEW Q6_CampaignsOverBudget AS
SELECT 
    CampaignID, 
    Title, 
    Budget, 
    (SELECT SUM(Amount) FROM Expense WHERE Expense.CampaignID = Campaign.CampaignID) AS TotalExpenses
FROM 
    Campaign
WHERE 
    (SELECT SUM(Amount) FROM Expense WHERE Expense.CampaignID = Campaign.CampaignID) > Budget;

-- Q7: Campaign event counts
CREATE VIEW Q7_CampaignEventCounts AS
SELECT CampaignID, COUNT(EventID) AS EventCount
FROM Event
GROUP BY CampaignID;

-- Q8: Campaign with most contributions
CREATE VIEW Q8_CampaignWithMostContributions AS
SELECT CampaignID, COUNT(ContributionID) AS ContributionCount
FROM Contribution
GROUP BY CampaignID
ORDER BY ContributionCount DESC
LIMIT 1;

-- Q9: Campaigns receiving donations from 'Enviro Enthusiast'
CREATE VIEW Q9_CampaignsFromEnviroEnthusiast AS
SELECT Campaign.CampaignID, Campaign.Title
FROM Campaign
JOIN Contribution ON Campaign.CampaignID = Contribution.CampaignID
JOIN Donor ON Contribution.DonorID = Donor.DonorID
WHERE Donor.Name = 'Enviro Enthusiast';

-- Q10: Campaign with the lowest cost event
CREATE VIEW Q10_CampaignWithLowestCostEvent AS
SELECT CampaignID, MIN(Cost) AS LowestEventCost
FROM Event
GROUP BY CampaignID
ORDER BY LowestEventCost ASC
LIMIT 1;



--now contructing to see the answers:

-- To see the total donations per campaign
SELECT * FROM Q1_TotalDonationsPerCampaign;

-- To see the top 3 donors by total donation amount
SELECT * FROM Q2_Top3Donors;

-- To see the average event cost per campaign
SELECT * FROM Q3_AverageEventCostPerCampaign;

-- To see donors who have donated more than the average donation amount
SELECT * FROM Q4_DonorsAboveAverage;

-- To see campaign finances (total expenses and contributions)
SELECT * FROM Q5_CampaignFinances;

-- To see campaigns that have exceeded their budget
SELECT * FROM Q6_CampaignsOverBudget;

-- To see the count of events per campaign
SELECT * FROM Q7_CampaignEventCounts;

-- To find the campaign with the most contributions
SELECT * FROM Q8_CampaignWithMostContributions;

-- To list campaigns that received donations from 'Enviro Enthusiast'
SELECT * FROM Q9_CampaignsFromEnviroEnthusiast;

-- To identify the campaign with the lowest cost event
SELECT * FROM Q10_CampaignWithLowestCostEvent;