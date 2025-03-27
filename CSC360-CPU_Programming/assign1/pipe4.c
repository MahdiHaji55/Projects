/* gopipe.c
 *
 * CSC 360, Spring 2024
 *
 * Execute up to four instructions, piping the output of each into the
 * input of the next.
 *
 * Please change the following before submission:
 *
 * Author: Mahdi Haji Hosseini
 * Login:  mahdihajihosseini@uvic.ca 
 */


/* Note: The following are the *ONLY* header files you are
 * permitted to use for this assignment! */

#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <wait.h>


char command[4][81];            //setting our global variables
int count = 0;

void read_input(){

     char buffer[81];
     ssize_t nbytes;

     for (int i = 0; i < 4; i++){
        
         nbytes = read(STDIN_FILENO, buffer, 81 - 1);    //reading using read funtion
        
         if (nbytes <= 1){

             break;
         }
        
         buffer[nbytes - 1] = '\0';                 //using system call and strcpy to add the command 
         strcpy(command[count++], buffer);
        
        
    }

        return;
    
    


}


void execution(){

    int descriptor[2];                 //setting descriptors for pipe
    int prev_descriptor = 0;

    for (int i = 0; i < count; i++) {
        
        if (i < count - 1) {
            
            if (pipe(descriptor) < 0) {
                
                exit(1);        //exiting if pipe fails
                
            }
            
        }

        pid_t pid = fork();
        
        if (pid == 0) {                 //processing child
            
            if (i > 0) {
                
                dup2(prev_descriptor, STDIN_FILENO);            //setting stdin for previous command
                close(prev_descriptor);
                
            }
            
            if (i < count - 1) {
                
                close(descriptor[0]); 
                dup2(descriptor[1], STDOUT_FILENO); 
                close(descriptor[1]);                        //setting next commands input
                
            }

           
            char *argv[10];                        //arguments holder to prepare to execute
            int argc = 0;
            argv[argc] = strtok(command[i], " ");
            
            while (argv[argc] != NULL) {
                
                argc++;
                argv[argc] = strtok(NULL, " ");
                
            }

            execvp(argv[0], argv);
            exit(1); 
            
        } else if (pid < 0) {
            
            exit(1);
            
        } else {                                 //processing parent
            
            if (i > 0) {
                
                close(prev_descriptor);
                
            }
            
            if (i < count - 1) {
                
                close(descriptor[1]); 
                prev_descriptor = descriptor[0];                 //closing and prepare for next command
                
            }
            
        }
        
    }

    
    while (wait(NULL) > 0);                   //waiting till all child processing is done

    
}


int main() {
    
    
    
    read_input();          //read input from bash
    
    
    
    if (count > 0){

        execution();         // execution of the commands we got
        
    }
    
    return 0;
    
}