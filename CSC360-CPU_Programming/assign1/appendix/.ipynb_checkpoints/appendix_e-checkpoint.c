/*
 * Appendix E program.
 * CSC 360, Spring 2024
 *
 * This shows to use the time functions within a C program.
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <unistd.h>


int main(int argc, char *argv[]) {
    struct timeval before, after;

    gettimeofday(&before, NULL);
    printf("Before going to sleep...\n");
    sleep(2);
    printf("After waking up.\n");
    gettimeofday(&after, NULL);

    printf("The process slept for %lu microseconds\n",
        (after.tv_sec - before.tv_sec) * 1000000 +
            after.tv_usec - before.tv_usec);


    printf("\n");


    long sum = 0;
    long i = 0, j = 0;

    gettimeofday(&before, NULL);
    printf("Before starting the loop...\n");
    for (j = 0; j < 500; j++) {
        sum = 0;
        for (i = 0; i < 1234567; i++) {
            sum += i;
        }
    }
    printf("After finishing loop.\n");
    gettimeofday(&after, NULL);

    printf("The process spent %lu microseconds in nested loop\n",
        (after.tv_sec - before.tv_sec) * 1000000 +
            after.tv_usec - before.tv_usec);
}
