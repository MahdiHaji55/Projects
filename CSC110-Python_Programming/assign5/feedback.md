 ## CSC 110, Spring 2022: Assignment 4,
 ### Submitted by Haji Hosseini, Mahdi
 ### V00968642

----------------------------------------


### Converting files from .ipynb to .py


Conversion successful.

Conversion successful.



### Automated Testing Output:


### PART A

<mark>NOTE: test_data_01 and test_data_02 below have been used to test the submitted code.<mark>

test_data_01 =  ['2021,5,7,26.15,26.44,26.04,26.29,25.18,3603300']

test_data_02 =  ['2021,5,3,25.56,25.72,25.56,25.58,24.5,1434600', '2021,5,4,25.6,25.7,25.36,25.69,24.61,3102000', '2021,5,5,25.75,25.98,25.67,25.85,24.76,2276300', '2021,5,6,25.95,26.15,25.84,26.12,25.02,3224000', '2021,5,7,26.15,26.44,26.04,26.29,25.18,3603300', '2021,5,10,27.33,27.5,27.2,27.43,26.28,2344000', '2021,5,11,27.48,27.6,27.42,27.51,26.35,1531300', '2021,5,12,27.52,27.75,27.5,27.63,26.47,2795700', '2021,5,13,27.72,27.89,27.66,27.88,26.71,1180100', '2021,5,14,27.96,28.11,27.93,28.07,26.89,2597200', '2021,5,17,28.11,28.14,27.85,28,26.82,4005900', '2021,5,18,27.53,27.68,27.36,27.49,26.63,5802000', '2021,5,19,27.46,27.57,27.33,27.42,26.57,3207400', '2021,5,20,27.44,27.59,27.4,27.49,26.63,2310500', '2021,5,21,26.67,26.94,26.64,26.76,25.63,2737600', '2021,5,25,26.78,27.01,26.67,26.95,25.82,2322100', '2021,5,26,27,27.22,26.91,27.2,26.06,2065100', '2021,5,27,27.26,27.32,27.01,27.05,25.91,3248100', '2021,5,28,27.28,27.35,27.15,27.26,26.11,1743600', '2021,5,31,27.35,27.39,27.07,27.1,25.96,1160700']
* TESTING `compute_month_stats` with test_data_01  

TEST PASSED

* TESTING `compute_month_stats` with test_data_02  

TEST PASSED

### PART B

<mark>NOTE<mark>: The testing has been done with 1) invalid_filname.csv (which does not exist) 2) test.csv (which exists)

* TESTING `compute_all_month_stats` for a file that does not exist with invalid_filename.csv ----> <mark>TEST FAILED<mark>

 Exception generated ----
 [Errno 2] No such file or directory: 'price-data/invalid_filename.csv'

* TESTING `compute_all_month_stats` for a file that exists with test.csv ----> <mark>TEST FAILED<mark>

 Exception generated ----
 name 'list_data' is not defined






### MARKER COMMENTS

#### Comments for  assignment5_partA.ipynb

* Write appropriate comments and documentation in your code. Write comments and documentation so that any other person with programming knowledge can review your code and quickly understand what the code is doing. Marks have not been deducted for this. but may be deducted in future assignments.

#### Comments for  assignment5_partB.ipynb


* assignment5_partB.ipynb submission does not run. Error generated when run.
* Write appropriate comments and documentation in your code. Write comments and documentation so that any other person with programming knowledge can review your code and quickly understand what the code is doing. Marks have not been deducted for this. but may be deducted in future assignments.
* Errors in data processing and monthly data is not computed

### FINAL MARKS
* Mark for partA ----> 3.0 / 3
* Mark for partB ----> 3 / 7
* **TOTAL MARKS** ----> 6.0 / 10
