/** @file route_manager.c
 *  @brief A small program to analyze airline routes data.
 *  @author Mike Z.
 *  @author Felipe R.
 *  @author Hausi M.
 *  @author Jose O.
 *  @author Saasha J.
 *  @author Victoria L.
 *  @author Mahdi Haji Hosseini
 *
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

// TODO: Make sure to adjust this based on the input files given
#define MAX_LINE_LENGTH 1024

/**
 * @brief Serves as an incremental counter for navigating the list.
 *
 * @param p The pointer of the node to print.
 * @param arg The pointer of the index.
 *
 */
void inccounter(node_t *p, void *arg)
{
    int *ip = (int *)arg;
    (*ip)++;
}

/**
 * @brief Allows to print out the content of a node.
 *
 * @param p The pointer of the node to print.
 * @param arg The format of the string.
 *
 */
void print_node(node_t *p, void *arg)
{
    char *fmt = (char *)arg;
    printf(fmt, p->subject);
}

/**
 * @brief Allows to print each node in the list.
 *
 * @param l The first node in the list
 *
 */
void analysis(node_t *l)
{
    int len = 0;

    apply(l, inccounter, &len);
    printf("Number of words: %d\n", len);

    apply(l, print_node, "%s\n");
}

/**
 * @brief The main function and entry point of the program.
 *
 * @param argc The number of arguments passed to the program.
 * @param argv The list of arguments passed to the program.
 * @return int 0: No errors; 1: Errors produced.
 *
 */
 
/*
typedef struct flight {
    char* airline_name;
    char* airline_icao_unique_code;
    char* airline_country;
    char* from_airport_name;
    char* from_airport_city;
    char* from_airport_country;
    char* from_airport_icao_unique_code;
    float from_airport_altitude;
    char* to_airport_name;
    char* to_airport_city;
    char* to_airport_country;
    char* to_airport_icao_unique_code;
    float to_airport_altitude;
    struct flight* next;
} Flight;
 */
 


 
 
void q1(char* data, int n){
	
	//open file
	FILE* file = fopen(data, "r");      
	
	char* line = NULL;
	size_t len = 0;
    ssize_t read;
	
	char* airline_name;
    char* airline_icao_unique_code;
    char* to_airport_country;
    
	node_t* list = NULL;
	
	//read and save the arguments
	while ((read = getline(&line, &len, file)) != -1) {
		
		if (strstr(line, "airline_name: ")) {
			airline_name = strdup(line + strlen("airline_name: ") + 2); // +1 to skip the ": " substring
			airline_name[strcspn(airline_name, "\r\n")] = 0;
		}
		else if (strstr(line, "airline_icao_unique_code: ")) {
			airline_icao_unique_code = strdup(line + strlen("airline_icao_unique_code: ") + 2); // +1 to skip the ": " substring
			airline_icao_unique_code[strcspn(airline_icao_unique_code, "\r\n")] = 0;
		}
		else if (strstr(line, "to_airport_country: ")) {
			to_airport_country = strdup(line + strlen("to_airport_country: ") + 2); // +1 to skip the ": " substring
			to_airport_country[strcspn(to_airport_country, "\r\n")] = 0;
			
			
			
		}
	
		
		//check if destination is canada
		if (strcmp(to_airport_country, "Canada") == 0){
				
			char stat[MAX_LINE_LENGTH];
			sprintf(stat, "%s (%s)", airline_name, airline_icao_unique_code);
			//printf("%s\n", stat);
			
			//if found in list statistic++, if null then make new node and add 	
			if (find_in_list(list, stat) == NULL){
					
					
				node_t *new_flight = new_node(stat, 1);
				node_t *list = add_front(list, new_flight);
				
				//printf("Subject: %s\n", new_flight->subject);
				//printf("Statistic: %d\n", new_flight->statistic);	
				
				
				
			}
				
				
				
		}
			
    }
		
    analysis(list);
	
	

	//sort the list
	node_t* sorted_list = create_sorted_list(list);
			
	FILE *output_file = fopen("output.csv", "w");
			
	fprintf(output_file, "subject,statistic\n");
			
	node_t *curr = sorted_list;
	
	//put the items in the output file
	for (int i = 0; i < n && curr != NULL; i++, curr = curr->next) {
			
		fprintf(output_file, "%s,%d\n", curr->subject, curr->statistic);
			
	}
	
	//close files
    fclose(output_file);
			
			

	
	fclose(file);
	
	
}

/*
void q2(char data, int n){

}	
 
void q3(char data, int n){
	
	
}
 */
 
int main(int argc, char *argv[]){
	
	char *data = NULL;
	int question = 0;
	int n = 0;
	
	//Parse command line arguments
    for (int i = 1; i < argc; i++) {
        if (strncmp(argv[i], "--DATA=", 7) == 0) {
            data = argv[i] + 7;
        } else if (strncmp(argv[i], "--QUESTION=", 11) == 0) {
            question = atoi(argv[i] + 11);
        } else if (strncmp(argv[i], "--N=", 4) == 0) {
            n = atoi(argv[i] + 4);
        }
    }
	
	//check for question then take to the correct function
    if (question == 1){
		
		q1(data, n);
		
	}
	
	/*
	if (question == 2){
		
		q2(data, n);
		
	}
	
	if (question == 3){
		
		q3(data, n);
		
	}
	
	*/
	
	
	
	
}
