/*
 * kosmos-mcv.c (mutexes & condition variables)
 *
 * For UVic CSC 360, Spring 2024
 *
 * Here is some code from which to start.
 *
 * PLEASE FOLLOW THE INSTRUCTIONS REGARDING WHERE YOU ARE PERMITTED
 * TO ADD OR CHANGE THIS CODE. Read from line 133 onwards for
 * this information.
 */

#include <assert.h>
#include <pthread.h>
#include <semaphore.h>
#include <sched.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "logging.h"


/* Random # below threshold that particular atom creation. 
 * This code is a bit fragile as it depends upon knowledge
 * of the ordering of the labels.  For now, the labels 
 * are in alphabetical order, which also matches the values
 * of the thresholds.
 */

#define C_THRESHOLD 0.2
#define H_THRESHOLD 0.8
#define O_THRESHOLD 1.0
#define DEFAULT_NUM_ATOMS 40

#define MAX_ATOM_NAME_LEN 10
#define MAX_KOSMOS_SECONDS 5

/* Global / shared variables */
int  cNum = 0, hNum = 0, oNum = 0;
long numAtoms;


/* Function prototypes */
void kosmos_init(void);
void *c_ready(void *);
void *h_ready(void *);
void *o_ready(void *);
void make_radical(int, int, int, int, int, char *);
void wait_to_terminate(int);


/* Needed to pass legit copy of an integer argument to a pthread */
int *dupInt( int i )
{
	int *pi = (int *)malloc(sizeof(int));
	assert( pi != NULL);
	*pi = i;
	return pi;
}




int main(int argc, char *argv[])
{
	long seed;
	numAtoms = DEFAULT_NUM_ATOMS;
	pthread_t **atom;
	int i;
	int status;
    double random_value;

	if ( argc < 2 ) {
		fprintf(stderr, "usage: %s <seed> [<num atoms>]\n", argv[0]);
		exit(1);
	}

	if ( argc >= 2) {
		seed = atoi(argv[1]);
	}

	if (argc == 3) {
		numAtoms = atoi(argv[2]);
		if (numAtoms < 0) {
			fprintf(stderr, "%ld is not a valid number of atoms\n",
				numAtoms);
			exit(1);
		}
	}

    kosmos_log_init();
	kosmos_init();

	srand(seed);
	atom = (pthread_t **)malloc(numAtoms * sizeof(pthread_t *));
	assert (atom != NULL);
	for (i = 0; i < numAtoms; i++) {
		atom[i] = (pthread_t *)malloc(sizeof(pthread_t));
        random_value = (double)rand() / (double)RAND_MAX;

		if ( random_value <= C_THRESHOLD ) {
			cNum++;
			status = pthread_create (
					atom[i], NULL, c_ready,
					(void *)dupInt(cNum)
				);
		} else if (random_value <= H_THRESHOLD ) {
			hNum++;
			status = pthread_create (
					atom[i], NULL, h_ready,
					(void *)dupInt(hNum)
				);
		} else if (random_value <= O_THRESHOLD) {
			oNum++;
			status = pthread_create (
					atom[i], NULL, o_ready,
					(void *)dupInt(oNum)
				);
        } else {
            fprintf(stderr, "SOMETHING HORRIBLY WRONG WITH ATOM GENERATION\n");
            exit(1);
        } 

		if (status != 0) {
			fprintf(stderr, "Error creating atom thread\n");
			exit(1);
		}
	}

    /* Determining the maximum number of ethynyl radicals is fairly
     * straightforward -- it will be the minimum of the number of
     * cNum, oNum, and hNum / 3.
     */
    int max_radicals = 0;

    if (cNum < oNum && cNum < hNum / 3) {
        max_radicals = cNum;
    } else if (oNum < cNum && oNum < hNum / 3) {
        max_radicals = oNum;
    } else {
        max_radicals = (int)(hNum / 3);
    }
#ifdef VERBOSE
    printf("Maximum # of radicals expected: %d\n", max_radicals);
#endif

    wait_to_terminate(max_radicals);
}

/*
* Now the tricky bit begins....  All the atoms are allowed
* to go their own way, but how does the Kosmos ethynyl-radical
* problem terminate? There is a non-zero probability that
* some atoms will not become part of a radical; that is,
* many atoms may be blocked on some semaphore of our own
* devising. How do we ensure the program ends when
* (a) all possible radicals have been created and (b) all
* remaining atoms are blocked (i.e., not on the ready queue)?
*/




/*
 * ^^^^^^^
 * DO NOT MODIFY CODE ABOVE THIS POINT.
 *
 *************************************
 *************************************
 *
 * ALL STUDENT WORK MUST APPEAR BELOW.
 * vvvvvvvv
 */



/* 
 * DECLARE / DEFINE NEEDED VARIABLES IMMEDIATELY BELOW.
 */

int radicals = 0;
pthread_mutex_t mutex; //intialization of needed variables


/*
 * FUNCTIONS YOU MAY/MUST MODIFY.
 */

void kosmos_init() {
    
	pthread_mutex_init(&mutex, NULL); //Initialized mutex variable
    
}


void *h_ready( void *arg )
{
    
  	int id = *((int *)arg);
    pthread_mutex_lock(&mutex); //lock to access global counters
    
    while (hNum < 3 || cNum < 1 || oNum < 1) {
        
        pthread_mutex_unlock(&mutex);
        pthread_mutex_lock(&mutex);
        
    }
        
    hNum = hNum - 1;
        
    pthread_mutex_unlock(&mutex);
    
    pthread_mutex_lock(&mutex);
        
    if (hNum >= 3 && cNum >= 1 && oNum >= 1) { //setting and checking the condition
        
		
		if (id % 3 == 0){
        
			make_radical(radicals++, id / 3, id / 3, id, id, "H"); //sending the argument to compose and print the radicals
        
		}
        
		else if (id % 3 == 1){
        
			make_radical(radicals++, (id - 1) / 3, (id - 1) / 3, (id - 1), id, "H"); //sending the argument to compose and print the radicals
        
		}
        
		else
        
        make_radical(radicals++, (id - 2) / 3, (id-2) / 3, (id - 2), id, "H"); //sending the argument to compose and print the radicals
    
    }

    pthread_mutex_unlock(&mutex); //unlock to let other atoms proceed

    return NULL;

}


void *c_ready( void *arg )
{
    
	int id = *((int *)arg);
    pthread_mutex_lock(&mutex);//lock to access global counters
    
    while (cNum < 1 || oNum < 1 || hNum < 3) { //setting and checking the condition
        
        pthread_mutex_unlock(&mutex);
        pthread_mutex_lock(&mutex);
        
    }
        
    cNum = cNum - 1;  
        
    pthread_mutex_unlock(&mutex);
    
    pthread_mutex_lock(&mutex);
        
    if (hNum >= 3 && oNum >= 1 && cNum >= 1) { 
        
		
        make_radical(radicals++, id, id, id * 3, id, "C"); //sending the argument to compose and print the radicals
        
    }
        
    pthread_mutex_unlock(&mutex);//unlock to let other atoms proceed
    return NULL;
    
}


void *o_ready( void *arg )
{
    
   	int id = *((int *)arg);
    pthread_mutex_lock(&mutex);//lock to access global counters
    
    while (cNum < 1 || oNum < 1 || hNum < 3) { //setting and checking the condition
        
        pthread_mutex_unlock(&mutex);
        pthread_mutex_lock(&mutex);
        
    }
        
    oNum = oNum - 1;
    pthread_mutex_unlock(&mutex);
    pthread_mutex_lock(&mutex);
        
    if (hNum >= 3 && cNum >= 1 && oNum >= 1) {
        
		
        make_radical(radicals++, id, id, id * 3, id, "O"); //sending the argument to compose and print the radicals
        
    }
        
    pthread_mutex_unlock(&mutex); //unlock to let other atoms proceed
    
    return NULL;
    
}


/* 
 * Note: The function below need not be used, as the code for making radicals
 * could be located with c_ready, h_ready, or o_ready. However, it is
 * perfectly possible that you have a solution which depends on such a
 * function having a purpose as intended by the function's name.
 */
void make_radical(int radicalNumber, int c, int o, int h, int id, char *maker) {
    
	char radical[50];

    int h1 = h; //setting the format for the following hydrogen
    int h2 = h + 1;
    int h3 = h + 2;
    
    sprintf(radical, "%03d: C%03d O%03d H%03d H%03d H%03d (%c%03d)\n", radicalNumber, c, o, h1, h2, h3, maker[0], id); //formatintg to the desired format needed
    
    printf("%s",radical);
    
}



void wait_to_terminate(int expected_num_radicals) {
    
    sleep(MAX_KOSMOS_SECONDS);
    kosmos_log_dump();
    exit(0);
    
}

