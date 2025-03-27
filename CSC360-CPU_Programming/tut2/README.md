# CSC360 Tutorial 2

## Getting started

Welcome to the second CSC360 tutorial. As with the first tutorial, the focus here is on helping you get started with an assignment, in this case the ssecond one. Tutorials are also meant to be a place where you are encouraged to make mistakes that lead towards learning and mastery. Tutorials are not graded, so feel free to experiment.

## GitLab

You should have already cloned a copy of your repository as was described at the first tutorial.

Go ahead and log into your account at `jhub-cosi.uvic.ca` and from within a terminal/shell perform a `git pull`. This will bring in the material for both this second tutorial and also for the second assignment. Assuming you have no issues with the `pull`, have a look at the `tut2/` directory. (It is most likely that you are first reading these instructions on the overhead during the tutorial, even though they are really contained within `tut2/` itself!) Ensure that you have opened `README.md`.

**If you are having problems with a `git pull`:** There are at least two reasons why this might have happened.
* *You have work in your local repository that is not yet committed.* Perform `git status` to see what files have been modified (which will show up in red print). Then perform a `git add` and `git commit` for each of those files, and then re-try the `git pull`. In most cases the work from the remote repo will be easily merged into your currently committed work.
* *You have work in your local repository that **has** been committed, but which cannot easily be merged via "fast-forward".* `git` can easily merge work when they involve disjoint files between local and remote repos, but sometimes it requires a specific command from the user to complete more complex merges. If `git` reports that it cannot `fast-forward` merge, then use the command `git merge -m "Some comment"`. This will almost always completely solve the problem. (Sadly there does exist a [darker side to `git`](https://xkcd.com/1597/).)
  

## Challenge 1:

Write a simple program for threads, calling it `hello-thread.c`. You can use the following if you wish:
```
#include <stdio.h> 
#include <stdlib.h> 
#include <unistd.h>  /* Need this for `sleep` */
#include <pthread.h> 

/* Plain-old C function that is executed as a thread 
 * when its name is specified in pthread_create().
 * Notice the function signature.
*/
void *some_thread(void *argp) 
{ 
	sleep(1); 
	printf("Printing some kind of sentence\n"); 
	return NULL; 
} 

int main() 
{ 
	pthread_t thread_id; 

	printf("Music from '2001: A Space Oddysey' ...\n"); 
	pthread_create(&thread_id, NULL, some_thread, NULL); 
	pthread_join(thread_id, NULL); 
	printf("Sad trombone music ...\n"); 

	exit(0); 
}

```

Compile the code using the following command:

```
gcc hello-thread.c -o hello-thread -lpthread`
```

Note that the command just above leaves out the `-Wall -Werror -std=c18` flags, but you can add them in if you wish -- in which the command would be:

```
gcc -Wall -Werror -std=c18 hello-thread.c -o hello-thread -lpthread 
```

What we **did** add was the library flag `-lpthread` which will be necessary when using much more of the POSIX thread library. Be wary here as sometimes the combination of `pthreads` library routines used in a program will still result in successful compilation *without* the use of the `-lpthread` flag, but you might not see the run-time behaviour you expect.

Go ahead and run the compiled program. (You should not need to be told how to do this now, but if you've forgotten, then ask the tutorial leader right away.) Add another function called `some_other_thread` and see if you can copy-and-paste code to get *that* function to run for a new thread.



## Challenge 2:

The syntax for passing a parameter into a thread function is a little tricky at first -- especially since it deals with such types as `void *`. However, consider the following program which will take a command-line argument (if it exists) and cause it to be printed.

```
#include <stdio.h> 
#include <stdlib.h> 
#include <unistd.h>  /* Need this for `sleep` */
#include <pthread.h> 

void *some_thread(void *argp)  
{ 
    sleep(1);
    if (argp != NULL) { 
        printf("This is from the command line: '%s'\n", (char *)argp);
    } else {
        printf("Printing some kind of sentence\n"); 
    }
    return NULL; 
} 

int main(int argc, char *argv[])
{ 
    pthread_t thread_id; 

    printf("Music from '2001: A Space Oddysey' ...\n"); 
    if (argc < 2) {
        pthread_create(&thread_id, NULL, some_thread, NULL);
    } else {
        pthread_create(&thread_id, NULL, some_thread, (void *)argv[1]); 
    }
    pthread_join(thread_id, NULL);  
    printf("Sad trombone music ...\n"); 
    
    exit(0); 
}
```

Notice how `argp` is used in `some_thread ` (i.e. it is cast into a `char *` type), and `argv[1]` from the command-line is cast within `main` into a `void *`.

Your challenge: Write a program that will take three separate arguments from the command line -- each of them mean to be an integer -- and pass them into a modified `some_thread` function such that the three values are multiplied and printed out within the new thread, i.e.:

```
./hello-thread 3 4 10
```
will output:
```
120
```

Hint: For this you will want to declare a new `struct` (call it `struct three_int_t`), and then suitable define a variable of that type, assign values to its fields, pass it into `some_thread`, and use it within that function. For example, the following `struct` could work:
```
struct three_int_t {
    int first, second, third;
};
```
where defining a variable of this `struct` type, and accessing fields of the variable, looks like:
```
struct three_int_t values;
values->first = 10;
values->second = 20;
values->third = -3;
```
And at some point you will need to use these two incantations (but you have to figure out where!):
```
(void *)&values

(struct three_int_t *) argp
```
Another hint: The `atoi()` function will take a string and return its integer value. (Note that the `itoa()` function does not exist -- sorry!)


## Challenge 3

The next challenge takes a different direction, that of synchronizing thread behaviour in time. Create a C program with the following code, compile it, and then run it in `jhub-cosi`.
```
#include <stdio.h> 
#include <stdlib.h> 
#include <unistd.h> 
#include <pthread.h> 
#include <time.h>

void *phase_zero(void *argp)  
{ 
    printf("Phase zero: start\n");
    struct timespec ts = {3, 0}; 
    nanosleep(&ts, NULL);
    printf("Phase zero: end\n");
} 


void *phase_one(void *argp)  
{
    printf("Phase 1: start\n");
    struct timespec ts = {3, 0}; 
    nanosleep(&ts, NULL);
    printf("Phase 1: end\n");
}


void *phase_two(void *argp) {
    printf("Phase 2: start\n");
    struct timespec ts = {3, 0}; 
    nanosleep(&ts, NULL);
    printf("Phase 2: end\n");
}

int main()  
{ 
    int i;  
    pthread_t tid[3]; 

    /* Create the three phases. */
    int r2 = pthread_create(&tid[2], NULL, phase_two, NULL);
    int r0 = pthread_create(&tid[0], NULL, phase_zero, NULL);
    int r1 = pthread_create(&tid[1], NULL, phase_one, NULL);

    if (r0 || r1 || r2) {
        /* If any of the return values from pthread_create are non-zero,
         * then there was an error, and we must terminate.
         */
        fprintf(stderr, "Error when creating threads.\n");
        exit(1);
    }
    pthread_exit(NULL); 
    return 0; 
} 
```
Nothing much exciting happens here, but the names of the functions suggest a behavior we would like to achieve. That is:
* First run phase 1 ...
* ... and after that phase is completed, run phase 2 ...
* ... and after *that* phase is completed, run phase 3.

Furthermore we want accomplish this programmatically  without specifically ordering calls to `pthread_create` in our program text, or writing very carefully crafted calls to `nanosleep` with specific delays, etc.

One technique to accomplish this is through use of a synchronization mechanism that will be introduced in lectures, which is called a **semaphore**. For now, we will use two semaphores -- one to synchronize the `phase_zero` thread with the `phase_one` thread, and the other to synchronize the `phase_one` thread with the `phase_two` thread.

(For now, you are being given some operations on semaphores that we expect to not yet understand. However, within a week you will understand more of what you're being given, and by the end of the term you'll be much more comfortable with them.)

So to proceed: Add the following header-file reference to your current code from up above.
```
#include <semaphore.h>
```
Add these two global-variable declarations.
```
sem_t phase_zero_to_one;
sem_t phase_one_to_two;
```
Before semaphores can be used (or any other `pthread` synchronization mechanism), they must first be initialized. Add the code for this in the `main` just before the calls to `pthread_create`:
```
sem_init(&phase_zero_to_one, 0, 0);
sem_init(&phase_one_to_two, 0, 0);
```
Given how we have initialized the semaphore variables (and that needs to be explained in lectures) we can cause a thread to suspend itself by having it perform a `wait` operation a semaphore. Threads which are suspended in this way are reawakened by another thread performing a `post` on that same semphore. 

So recall from above that we want the following to be true (albeit expressed in a slightly different form to that given higher up in this document):
* `phase_one` must not run (i.e. no calls to `printf`) until `phase_zero` is finished its work, and
* `phase_two` must not run (i.e. no calls to `printf`) until `phase_one` is finished its work.

("Work" here just refers to the trivial actions of calling `printf`, but in a non-trivial problem the work would be much more interesting and significant.)

Add the following line to the end of `phase_zero` (i.e. after its last call to `printf`):
```
sem_post(&phase_zero_to_one);
```

Then add the following line to the start of `phase_one` (i.e. before any calls to `printf`):
```
sem_wait(&phase_zero_to_one);
```
and the following line to the end of `phase one` (i.e. after its last call to `printf`):
```
sem_post(&phase_one_to_two);
```

Finally add the following line to the start of `phase_two` (i.e. before any calls to `printf`):
```
sem_wait(&phase_one_to_two);
```

Then compile and run the result. 

**Can you change the order of the phases just by moving around the calls to `wait` and `post` *and without modifying any other code?*** For example, can you modify the program to enforce the order of (`phase_one`, `phase_zero`, `phase_two`)? The names of the semaphores will be confusing -- and you're welcome to change their names -- but the problem-solving principle still remains, which is to be able to suspend some thread S until another thread T permits S to proceed.
