/*
 * kosmos-sem.c (semaphores)
 *
 * For UVic CSC 360, Spring 2024
 *
 * Here is some code from which to start.
 *
 * PLEASE FOLLOW THE INSTRUCTIONS REGARDING WHERE YOU ARE PERMITTED
 * TO ADD OR CHANGE THIS CODE. Read from line 170 onwards for
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
    
#ifdef VERBOSE
    printf("Maximum # of radicals expected: %d\n", max_radicals);
#endif

    wait_to_terminate(max_radicals);
}
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


int radicals = 0; //intialization of needed variables

sem_t sem_c_count, sem_h_count, sem_o_count, sem_radical;




/*
 * FUNCTIONS YOU MAY/MUST MODIFY.
 */

void kosmos_init(void) {
    
    
    sem_init(&sem_radical, 0, 1);
    sem_init(&sem_c_count, 0, 0); //Initialized sem variables
    sem_init(&sem_o_count, 0, 0);
    sem_init(&sem_h_count, 0, 0);
                                         
    
}
    



void *h_ready( void *arg )
{
    int id = *((int *)arg);
    
    sem_post(&sem_h_count); //check if sem ready
    
    sem_wait(&sem_radical); //form radical
    
    int c_count, h_count, o_count;
    
    sem_getvalue(&sem_h_count, &h_count);
    sem_getvalue(&sem_c_count, &c_count); //checking atom numbers
    sem_getvalue(&sem_o_count, &o_count);
    
    if (h_count >= 3 && o_count >= 1 && c_count >= 1) {
       
        for (int i = 0; i < 3; i++) sem_wait(&sem_h_count);
        
        sem_wait(&sem_c_count);
        sem_wait(&sem_o_count);


        char maker[10]; 
        sprintf(maker, "H%03d", id); //getting maker id
        
        
        if(id % 3 == 0){
            
            make_radical(id / 3, id / 3, id, id - 1, id - 2, maker);

		}
            
		else if(id % 3 == 1){
            
            make_radical((id - 1) / 3, (id - 1) / 3, (id - 1), id, id - 1, maker);
            
		}
            
		else
            
            make_radical((id - 2) / 3, (id - 2) / 3, (id - 2), id - 1, id, maker);
        
    }
    
    sem_post(&sem_radical); 
    
    
    return NULL;
    

}


void *c_ready( void *arg )
{

    int id = *((int *)arg);
    
    sem_post(&sem_c_count); //check if sem ready
    
    sem_wait(&sem_radical); //form radical
    
    int h_count, c_count, o_count;
    
    sem_getvalue(&sem_h_count, &h_count);
    sem_getvalue(&sem_c_count, &c_count); //checking atom numbers
    sem_getvalue(&sem_o_count, &o_count);
    
    if (h_count >= 3 && c_count >= 1 && o_count >= 1) {
        
        for (int i = 0; i < 3; i++) sem_wait(&sem_h_count);
        
        sem_wait(&sem_c_count);
        sem_wait(&sem_o_count);

        char maker[10]; 
        sprintf(maker, "C%03d", id); //getting maker id
           
       
        make_radical(id, id, id * 3, (id * 3) + 1,(id * 3) + 1, maker);
        
    }
    
    sem_post(&sem_radical); 
    
    return NULL;

    
}


void *o_ready( void *arg )
{
    int id = *((int *)arg);
    
    sem_post(&sem_o_count); //check if sem ready
    
    sem_wait(&sem_radical); //form radical
    
    int h_count, c_count, o_count;
    
    sem_getvalue(&sem_h_count, &h_count);
    sem_getvalue(&sem_c_count, &c_count); //checking atom numbers
    sem_getvalue(&sem_o_count, &o_count);
    
    if (h_count >= 3 && o_count >= 1 && c_count >= 1) {
        
        
        for (int i = 0; i < 3; i++) sem_wait(&sem_h_count);
        
        sem_wait(&sem_c_count);
        sem_wait(&sem_o_count);

        char maker[10];
        sprintf(maker, "O%03d", id); //getting maker id
        
        
        make_radical(id, id, id * 3, (id * 3) + 1, (id * 3) + 1, maker);
        
    }
    
    sem_post(&sem_radical); 
    
    
    return NULL;

        
}




/* 
 * Note: The function below need not be used, as the code for making radicals
 * could be located with c_ready, h_ready, or o_ready. However, it is
 * perfectly possible that you have a solution which depends on such a
 * function having a purpose as intended by the function's name.
 */
void make_radical(int c, int o, int h1, int h2, int h3, char *maker) {

    
    h3 = h3 + 1; //setting the format for the following hydrogen
    
    printf("%03d: C%03d O%03d H%03d H%03d H%03d (%s)\n", radicals++, c, o, h1, h2, h3, maker); //formatintg to the desired format needed
    
}


void wait_to_terminate(int expected_num_radicals) {
    /* A rather lazy way of doing it, for now. */
    sleep(MAX_KOSMOS_SECONDS);
    kosmos_log_dump();
    exit(0);
}



