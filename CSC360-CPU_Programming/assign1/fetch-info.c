/* getstats.c 
 *
 * CSC 360, Spring 2024
 *
 * - If run without an argument, prints information about 
 *   the computer to stdout.
 *
 * - If run with a process number created by the current user, 
 *   prints information about that process to stdout.
 *
 * Please change the following before submission:
 *
 * Author: Mahdi Haji Hosseini
 * Login:  mahdihajihosseini@uvic.ca 
 */


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/sysinfo.h>

/*
 * Note: You are permitted, and even encouraged, to add other
 * support functions in order to reduce duplication of code, or
 * to increase the clarity of your solution, or both.
 */

void print_process_info(char * process_num) {

    
    char buffer[1024];
    char path[1024];
    unsigned long voluntary_ctxt_switches = 0;                 //assigning the needed arguments
    unsigned long nonvoluntary_ctxt_switches = 0;
    FILE *fp;
    

    
    sprintf(buffer, "/proc/%s/status", process_num);
    
    fp = fopen(buffer, "r");
    
    if (!fp) {
        
        printf("Process number %s not found.\n", process_num);       //deal with the wrong argument, that is process number
        
        return;
        
    }

    printf("Processor number: %s\n", process_num);
    
    char line[1024];                                               
    
    while (fgets(line, sizeof(line), fp) != NULL) {                //getting file name using the fgets method of reading code
        
        if (strncmp(line, "Name:", 5) == 0) {
            
            printf("%s", line);

            
        
    }

    fclose(fp);

    char filename[5000];
        
    strcpy(filename, line + 6);
        
    printf("Filename (if any): %s", filename);                  //parsing the file name for the given output

    
    char line2[1024];

    sprintf(path, "/proc/%s/status", process_num);
    fp = fopen(path, "r");

    
    while (fgets(line2, sizeof(line2), fp) != NULL) {            //getting number of threads
        
        if (strncmp(line2, "Threads:", 8) == 0) {
            
            printf("%s", line2);

            break;
        
    }
        
    }
        
    fclose(fp);

    char line3[1024];
    fp = fopen(path, "r");

    while (fgets(line3, sizeof(line3), fp) != NULL) {
        
        if (strncmp(line3, "voluntary_ctxt_switches", 23) == 0) {
            
            sscanf(line3, "voluntary_ctxt_switches: %lu", &voluntary_ctxt_switches);
            
        } else if (strncmp(line3, "nonvoluntary_ctxt_switches", 26) == 0) {                    //getting the number of each switch
            
            sscanf(line3, "nonvoluntary_ctxt_switches: %lu", &nonvoluntary_ctxt_switches);
            
        }
    }

    unsigned long total_ctxt_switches = voluntary_ctxt_switches + nonvoluntary_ctxt_switches;     //adding all the switches together to the total
        
    printf("Total context switches: %lu\n", total_ctxt_switches);

    
} 

}



void print_full_info() {

    
    FILE *fp = fopen("/proc/cpuinfo", "r"); //opening file section with pointer
    char buffer[1024];
    while (fgets(buffer, sizeof(buffer), fp)) {
        
        if (strncmp(buffer, "model name", 10) == 0) {    //for model name 
            
            printf("%s", buffer);

            break;
        }
        
        
        
    }
    
    fclose(fp);

    fp = fopen("/proc/cpuinfo", "r");
    char buffer2[1024];
    while (fgets(buffer2, sizeof(buffer2), fp)) {
        
        if (strncmp(buffer2, "cpu cores", 9) == 0) {   //for cpu cores
            
            printf("%s", buffer2);

            break;
        }
        
        
        
    }
    
    fclose(fp);
    

    
    fp = fopen("/proc/version", "r");
    
    if (fgets(buffer, sizeof(buffer), fp)) {     //for version
        
        printf("Linux version %s", buffer);
        
    }
    
    fclose(fp);

    fp = fopen("/proc/meminfo", "r");
    char buffer3[1024];
    while (fgets(buffer3, sizeof(buffer3), fp)) {
        
        if (strncmp(buffer3, "MemTotal", 8) == 0) {     //for memory total
            
            printf("%s", buffer3);

            break;
        }
        
        
    }
    
    fclose(fp);

    fp = fopen("/proc/uptime", "r");
    
    double uptime;

    fscanf(fp, "%lf", &uptime);           //for uptime we get the second and tranform it to readable time 
                                          //with days, hours, min and seconds
    int days = uptime / (24 * 3600);
    uptime = uptime - (days * 24 * 3600);
    int hours = uptime / 3600;
    uptime = uptime - (hours * 3600);
    int minutes = uptime / 60;
    int seconds = (int)uptime % 60;
    
    printf("Uptime: %d days, %d hours, %d minutes, %d seconds\n", days, hours, minutes, seconds);

    fclose(fp);
    
    
}






int main(int argc, char ** argv) {  
    
    if (argc == 1) {
        print_full_info();
        
    } else {
        print_process_info(argv[1]);
        
    }
    
}
