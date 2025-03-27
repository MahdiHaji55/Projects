
//Mahdi Haji Hosseini
//V00968642

/** @file route_manager.c
 *  @brief A pipes & filters program that uses conditionals, loops, and string processing tools in C to process airline routes.
 *  @author Felipe R.
 *  @author Hausi M.
 *  @author Jose O.
 *  @author Saasha J.
 *  @author Victoria L.
 *  @author STUDENT_NAME
 *
 */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_CHAR 1024
#define MAX_FLIGHTS 100																		//Our defined Variables
#define MAX_WORDS 1024
#define MAX_CHAR_OUTPUT 10000

/**
 * Function: main
 * --------------
 * @brief The main function and entry point of the program.
 *
 * @param argc The number of arguments passed to the program.
 * @param argv The list of arguments passed to the program.
 * @return int 0: No errors; 1: Errors produced.
 *
 */



struct Line {
	char airline_name[MAX_CHAR];
	char airline_unique_code[MAX_CHAR];														//Our Struct of Parameters in the Line
	char country[MAX_CHAR];
	char from_airport_name[MAX_CHAR];
	char from_airport_city[MAX_CHAR];
	char from_airport_country[MAX_CHAR];
	char from_airport_unique_code[MAX_CHAR];
	double from_airport_altitude;	
	char to_airport_name[MAX_CHAR];
	char to_airport_city[MAX_CHAR];
	char to_airport_country[MAX_CHAR];
	char to_airport_unique_code[MAX_CHAR];
	double to_airport_altitude;
};


//Our UNIX filter Command runner
//Takes in the arguments from the main function,
//and creates then runs the command needed for sorting

void command_runner(char* data, char* airline, char* src_city, char* src_country, char* dest_city, char* dest_country){       
	
	char command[MAX_CHAR] = "cat ";
	
	strcat(command, data);


	if (airline != NULL) {																	//note that this method was used because the assigments description says
		
		strcat(command, " | grep ");															//that we have to use UNIX filters we learned
		strcat(command, "\"");						
		strcat(command, airline);															//also without resorting to dynamic memory allocation
		strcat(command, "\"");
	}

	if (src_city != NULL) {
		
		strcat(command, " | grep ");
		strcat(command, "\"");
		strcat(command, src_city);
		strcat(command, "\"");
	}

	if (src_country != NULL) {
		
		strcat(command, " | grep ");
		strcat(command, "\"");
		strcat(command, src_country);
		strcat(command, "\"");
	}

	if (dest_city != NULL) {
		
		strcat(command, " | grep ");
		strcat(command, "\"");
		strcat(command, dest_city);
		strcat(command, "\"");
	}

	if (dest_country != NULL) {
		
		strcat(command, " | grep ");
		strcat(command, "\"");
		strcat(command, dest_country);
		strcat(command, "\"");
	}

	
	strcat(command, " > output.txt");
	
	
	system(command);

	
	
}
	

//Our read and store function 
//will read the sorted data from the command runner
//then creates the desired output with the 
//arguments given

void read_and_store_and_output(char* airline, char* src_city, char* src_country, char* dest_city, char* dest_country){
	
	

  FILE *ourdata;																				//Creates the pointer to the file

  ourdata = fopen("output.txt", "r");															//Opens the sorted data file in read mode
  char buffer[MAX_CHAR];
  struct Line flights[MAX_FLIGHTS];																//our array of flights
  int resultcount = 0;																			//resultcount keeps track of number of flights
 
  while(fgets(buffer, MAX_CHAR, ourdata) != NULL){												

		char *token;																			//Reads each line and stores it in
	
		token = strtok(buffer, ",");															//a struct
		if (token) strcpy(flights[resultcount].airline_name, token);
		else strcpy(flights[resultcount].airline_name, "empty");

		token = strtok(NULL, ",");
		if (token) strcpy(flights[resultcount].airline_unique_code, token);
		else strcpy(flights[resultcount].airline_unique_code, "empty");

		token = strtok(NULL, ",");
		if (token) strcpy(flights[resultcount].country, token);
		else strcpy(flights[resultcount].country, "empty");

		token = strtok(NULL, ",");
		if (token) strcpy(flights[resultcount].from_airport_name, token);
		else strcpy(flights[resultcount].from_airport_name, "empty");

		token = strtok(NULL, ",");
		if (token) strcpy(flights[resultcount].from_airport_city, token);
		else strcpy(flights[resultcount].from_airport_city, "empty");

		token = strtok(NULL, ",");
		if (token) strcpy(flights[resultcount].from_airport_country, token);
		else strcpy(flights[resultcount].from_airport_country, "empty");

		token = strtok(NULL, ",");
		if (token) strcpy(flights[resultcount].from_airport_unique_code, token);
		else strcpy(flights[resultcount].from_airport_unique_code, "empty");

		token = strtok(NULL, ",");
		if (token) flights[resultcount].from_airport_altitude = atof(token);
		else flights[resultcount].from_airport_altitude = 0;

		token = strtok(NULL, ",");
		if (token) strcpy(flights[resultcount].to_airport_name, token);
		else strcpy(flights[resultcount].to_airport_name, "empty");

		token = strtok(NULL, ",");
		if (token) strcpy(flights[resultcount].to_airport_city, token);
		else strcpy(flights[resultcount].to_airport_city, "empty");

		token = strtok(NULL, ",");
		if (token) strcpy(flights[resultcount].to_airport_country, token);
		else strcpy(flights[resultcount].to_airport_country, "empty");

		token = strtok(NULL, ",");
		if (token) strcpy(flights[resultcount].to_airport_unique_code, token);
		else strcpy(flights[resultcount].to_airport_unique_code, "empty");

		token = strtok(NULL, ",");
		if (token) flights[resultcount].to_airport_altitude = atof(token);
		else flights[resultcount].to_airport_altitude = 0;
		
		resultcount++;

	} 

	long file_size;																				//file_size checks if the file is empty
	
	fseek(ourdata, 0, SEEK_END);											
	file_size = ftell(ourdata);

	fclose(ourdata);




	ourdata = fopen("output.txt", "w");															//open our output file in write mode
	
	


	if (file_size == 0){																		//checks if the file is empty
		
		fputs("NO RESULTS FOUND.\n", ourdata);													//our conditions checker is executed
		
	} else {																					//there are 3 parts for the condition checker
		
		if (dest_city == NULL && src_city == NULL && src_country == NULL){						//to create desired output
		
			char out_to_text[MAX_CHAR_OUTPUT];
			
			snprintf(out_to_text, MAX_CHAR_OUTPUT, "FLIGHTS TO %s BY %s (%s):\n", 								
			dest_country, flights[0].airline_name, airline);
			
			fputs(out_to_text, ourdata);														//first part is for the condtion for the first 3 tests 1-3
			
			for (int i = 0; i < resultcount; i++){

				if (strcmp(flights[i].to_airport_country, dest_country) == 0){
					
					char out_to_text_in_loop[MAX_CHAR_OUTPUT];
					snprintf(out_to_text_in_loop, MAX_CHAR_OUTPUT, "FROM: %s, %s, %s TO: %s (%s), %s\n", 
					flights[i].from_airport_unique_code, flights[i].from_airport_city, 
					flights[i].from_airport_country, flights[i].to_airport_name,
					flights[i].to_airport_unique_code, flights[i].to_airport_city);
					
					fputs(out_to_text_in_loop, ourdata);
					
				}

			}
			
		}


		if (src_city == NULL && airline == NULL){
			
			char out_to_text[MAX_CHAR_OUTPUT];
			
			snprintf(out_to_text, MAX_CHAR_OUTPUT, "FLIGHTS FROM %s TO %s, %s:\n",
			src_country, dest_city, dest_country);
			
			fputs(out_to_text, ourdata);
			
			int found = 0;
			
			for (int i = 0; i < resultcount; i++){												//second part is for the condition for the next 3 tests 4-6
				
				if(strcmp(flights[i].from_airport_country, src_country) == 0 && 
				strcmp(flights[i].to_airport_city, dest_city) == 0 && 
				strcmp(flights[i].to_airport_country, dest_country) == 0){
					
					char out_to_text_in_loop[MAX_CHAR_OUTPUT];
					snprintf(out_to_text_in_loop, MAX_CHAR_OUTPUT, "AIRLINE: %s (%s) ORIGIN: %s (%s), %s\n", 
					flights[i].airline_name, flights[i].airline_unique_code, 
					flights[i].from_airport_name, flights[i].from_airport_unique_code, 
					flights[i].from_airport_city);
					
					fputs(out_to_text_in_loop, ourdata);
					
					found++;
					
				}
				
			}
			
			if (found == 0){
				
				fclose(ourdata);
				
				ourdata = fopen("output.txt", "w");												//checks if none of the criteria match
				
				fputs("NO RESULTS FOUND.\n", ourdata);
				
			}

		}
		
		
		if (airline == NULL && src_city != NULL){
			
			char out_to_text[MAX_CHAR_OUTPUT];
			
			snprintf(out_to_text, MAX_CHAR_OUTPUT, "FLIGHTS FROM %s, %s TO %s, %s:\n",
			src_city, src_country, dest_city, dest_country);
			
			fputs(out_to_text, ourdata);
			
			int found = 0;
			
			for (int i = 0; i < resultcount; i++){												//third part is for the condition for the last 3 tests 7-9
				
				if(strcmp(flights[i].from_airport_city, src_city) == 0 && 
				strcmp(flights[i].from_airport_country, src_country) == 0 && 
				strcmp(flights[i].to_airport_city, dest_city) == 0 && 
				strcmp(flights[i].to_airport_country, dest_country) == 0){
					
					char out_to_text_in_loop[MAX_CHAR_OUTPUT];
					snprintf(out_to_text_in_loop, MAX_CHAR_OUTPUT, "AIRLINE: %s (%s) ROUTE: %s-%s\n", 
					flights[i].airline_name, flights[i].airline_unique_code, 
					flights[i].from_airport_unique_code, flights[i].to_airport_unique_code);
					
					fputs(out_to_text_in_loop, ourdata);
					
					found ++;
					
					
				}
				
				
			}
			
			if (found == 0){
				
				fclose(ourdata);
				
				ourdata = fopen("output.txt", "w");													//checks if none of the criteria match
				
				fputs("NO RESULTS FOUND.\n", ourdata);
				
				
			}
			
			
			
		}
		
		
	}

	fclose(ourdata);																				//after creating desired output we close file




}



int main(int argc, char *argv[])
{
    // TODO: your code.
	
	

	char *data = NULL;
	char *airline = NULL;
	char *src_city = NULL;																			//our arguments variables store for later use
	char *src_country = NULL;
	char *dest_city = NULL;
    char *dest_country = NULL;
	

    
    for (int i = 1; i < argc; i++) {													
		
        char *token = strtok(argv[i], "=");
		
        if (strcmp(token, "--DATA") == 0) {															//our loop goes through the array of arguments 
            data = strtok(NULL, "=");
			
        } else if (strcmp(token, "--SRC_COUNTRY") == 0) {											//and seperates them to the desired variable
            src_country = strtok(NULL, "=");
			
        } else if (strcmp(token, "--DEST_CITY") == 0) {
            dest_city = strtok(NULL, "=");
			
        } else if (strcmp(token, "--DEST_COUNTRY") == 0) {
            dest_country = strtok(NULL, "=");
			
        } else if (strcmp(token, "--AIRLINE") == 0) {
			airline = strtok(NULL, "=");
			
		} else if (strcmp(token, "--SRC_CITY") == 0){
			src_city = strtok(NULL, "=");
		}
		
    }



	command_runner(data, airline, src_city, src_country, dest_city, dest_country);					//our call for command runner function to sort


	read_and_store_and_output(airline, src_city, src_country, dest_city, dest_country);				//our call to read and store then output


	










}
