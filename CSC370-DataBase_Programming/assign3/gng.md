# Green-not-Greed Database Relations

## 1. Volunteer Table

- **Volunteer**(`VolunteerID`, `Name`, `Email`, `Phone`, `Tier`)
  
  - `VolunteerID`: Primary Key

## 2. Employee Table

- **Employee**(`EmployeeID`, `Name`, `Position`, `Salary`, `Email`, `Phone`)
  
  - `EmployeeID`: Primary Key

## 3. Donor Table

- **Donor**(`DonorID`, `Name`, `Email`, `Phone`, `Donation Amount`, `Donation Date`)
  
  - `DonorID`: Primary Key

## 4. Campaign Table

- **Campaign**(`CampaignID`, `Title`, `StartDate`, `EndDate`, `Location`, `Budget`, `Description`)
  
  - `CampaignID`: Primary Key

## 5. Event Table

- **Event**(`EventID`, `CampaignID`, `Date`, `Location`, `Description`, `Cost`)
  
  - `EventID`: Primary Key
  - `CampaignID`: Foreign Key referencing `Campaign(CampaignID)`

## 6. Expense Table

- **Expense**(`ExpenseID`, `CampaignID`, `Amount`, `Date`, `Description`)
  
  - `ExpenseID`: Primary Key
  - `CampaignID`: Foreign Key referencing `Campaign(CampaignID)`

## 7. Contribution Table

- **Contribution**(`ContributionID`, `DonorID`, `CampaignID`, `Amount`, `Date`)
  
  - `ContributionID`: Primary Key
  - `DonorID`: Foreign Key referencing `Donor(DonorID)`
  - `CampaignID`: Foreign Key referencing `Campaign(CampaignID)`


### Explanations of Choices:

- **Primary Keys**: Each table has a primary key to uniquely identify its records. These are critical for ensuring that each record can be uniquely identified and referenced.
- **Foreign Keys**: Foreign keys are used to establish relationships between tables, referencing the primary key of another table.
