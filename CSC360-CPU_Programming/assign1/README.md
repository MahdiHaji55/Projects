# Assignment 1

## UVic CSC360 Spring 2024

** Due Tuesday February 6 at 11:55 pm** via `push` to your `gitlab.csc`
 repository.

## Programming Platform

For this assignment your code must work in the Jupyterlab environment
provisioned for you at `https://jhub-cosi.uvic.ca/`.  You may already
have access to your own Unix system (e.g., Ubuntu, Debian, Cygwin on
Windows 10, macOS with MacPorts, etc.) yet we recommend you work as
much as possible with your CSC360 jupyterlab environment. Bugs in
systems programming tend to be platform-specific and something that
works perfectly at home may end up crashing on a different
computer-language library configuration. (We cannot give marks for
submissions of which it is said “It worked in Visual Code on my gaming laptop!”)

## Individual work

This assignment is to be completed by each individual student (i.e.,
no group work).  Naturally you will want to discuss aspects of the
problem with fellow students, and such discussions are encouraged.
However, **sharing of code is strictly forbidden**. If you are still
unsure about what is permitted or have other questions regarding
academic integrity, please direct them as soon as possible to the
instructor. (Code-similarity tools will be run on submitted programs.)
Any fragments of code found on the web and used in your solution must
be properly cited where it is used (i.e., citation in the form of a
comment given source of code).

## Use of `gitlab.csc.uvic.ca`

Each student enrolled in the course has been assigned a Git repository
at `gitlab.csc.uvic.ca`. For example, the student having Netlink ID
`johnwick` would have their CSC 360 repository at this location: 
```
https://gitlab.csc.uvic.ca/courses/2024011/CSC360_COSI/assignments/johnwick/coursework-os
```

Please form the address of your repository appropriately **and if you
have not already done so**  perform a `git clone` in your jupyterlab
environment. You are also able to access this repository by going to
`https://gitlab.csc.uvic.ca` (and use your Netlink username and
password to log in at that page). **If you have already used `clone` to
obtain your repository** the use `git pull` to retrieve files for this
assignment.

---

## Goals of this assignment

1. Write a program (`fetch-info.c`) that prints out interesting statistics
about the environment in which it is run, or statistics about a single
process running in that environment.

2. Write a program (`pipe4.c`) that executes up to four shell
instructions, piping the output of one to the input of the next.

---

## Part 1: `fetch-info.c`

Every Unix filesystem includes a `/proc` directory.  This directory
contains a great deal of information, as text files, that relate to
the properties of the operating system – its memory usage, its kernel
version number, scheduling and filesystem information, and so on.
Details about processes running on the system are also available in
subdirectories of the `/proc` directory.

### Your task

A basic starter file (`fetch-info.c`) has been provided to you in the
`a1` directory of your `coursework-os` repository.  Modify this file so that the
resulting program (`fetch-info`) has the following behaviour:

- If run without arguments, the program should print to the console
  the following information, in order (one item per line):
 
  1. The model name of the CPU

  2. The number of cores

  3. The version of Linux running in the environment

  4. The total memory available to the system, in kilobytes

  5. The 'uptime' (the amount of time the system has been running, expressed in terms of days, hours, minutes and seconds).

- If run with a numerical argument, the program should print to the
  console information about the corresponding process:

  1. The name of the process

  2. The console command that started the process (if any) 

  3. The number of threads running in the process

  4. The total number of context switches that have occurred during the running of the process.
   
If there is no running process that corresponds to the numerical
argument provided, the program should quit with an error message.
Regardless of which 'mode' the program runs in, it should terminate
once it has finished printing the appropriate information.

For example, if I execute the following command on JupyterHub:

`./fetch-info`

my output is:

```
model name      : AMD EPYC 7313P 16-Core Processor
cpu cores       : 16
Linux version 4.18.0-513.9.1.el8_9.x86_64 (mockbuild@x86-vm-09.build.eng.bos.redhat.com) (gcc version 8.5.0 20210514 (Red Hat 8.5.0-20) (GCC)) #1 SMP Thu Nov 16 10:29:04 EST 2023
MemTotal:       65268208 kB
Uptime: 3 days, 11 hours, 2 minutes, 21 seconds
```

Your output may vary from this (for example, uptime will vary from one
second to the next), but the expected output above should serve as a
guide to your work.  The exact formatting of long lines (such as Linux
version above) will also vary depending on console width and font
settings.

If I execute the following command on jupyterhub:

`./fetch-info 1`

my output is:

```
Process number: 1
Name:   tini
Filename (if any): tini
Threads:        1
Total context switches: 314
```

Again, your output may vary widely from this, depending on the actual
contents of /proc/1 at the time you run the program.  Finally, if I
enter:

`./fetch-info -1`

My output is:

```
Process number -1 not found.
```

Note that your solution must work correctly for process numbers other
that `1` and `-1`.


### Hints

- All the information you need is in the `/proc` directory or one of
  its subfolders, are in contained in ordinary text files.  
  Remember to use `fopen()` and `fclose()` properly.

- You may need to read strings from these files and extract
  information from them in order to produce some of the statistics
  you need.  The libraries that have been included into the starter
  `fetch-info.c` file may help you with this.

### Restrictions

  - Your solution must print information that reflects the actual
    contents of `/proc` at the time your program is run – solutions that
    simply hard-code their output may receive a failing grade.

  - You should not need to use dynamic memory (`malloc`, `free`, etc) for
    this part of the assignment.  If you do choose to use dynamic
    memory, you may lose marks if your program leaks memory or performs
    undefined memory operations (double-frees, dereferences of `NULL`
    pointers, etc).

  - Your solution must close any file pointers that result from calls
    to `fopen()`.

  - Your source code file must be compiled with the following command:
    `gcc -Wall -Werror -std=c18 -o fetch-info fetch-info.c`  If it cannot
    be compiled due to syntax errors or warnings, it may receive
    failing grade.

---

## Part 2: `pipe4.c`

Unix commands that are executed in a shell can take advantage of
'piping' – that is, the output of one command can be used as the input
of another.  For example, the command `ls -1` will list the contents
of the current directory, one entry per line.  The command `wc -l`
counts the number of lines in the input, and outputs that number as an
integer.  We can specify that the output of `ls -1` should be used as
the input of `wc -l` by joining the two commands with a pipe:

`ls -1 | wc -l`

The resulting compound command prints the number of entries in the
current directory.

### Your task

Write a program (`pipe4.c`) that accepts up to four individual
commands, one per line (pressing enter or return at the end of each).
The user may indicate that they have no more commands to enter by
pressing return on a line without entering a command first.  If this
happens, or if the user enters four commands, your program must then
execute each of the four instructions, transferring the output of one
to the input of the next.  The output of the final command in the
sequence must be sent to the console.  For example, if the program is
run as follows:

`./pipe4`

and then the following is entered (where the &#9166; symbol indicates
pressing enter):

`/bin/ls -1` &#9166;

`/bin/wc -l` &#9166;

&#9166;

The program should print out the number of directory entries in the
current directory (as an integer) and then quit.

The program should only execute as many instructions as were entered
(a maximum of four) – if the enter key is pressed without first
entering a command, the program should simply quit.  If the user
enters four commands, the program should not prompt for a fifth, but
should immediately begin executing the commands.

Test your program with inputs of various lengths – chains of zero,
one, two, three or four commands.  The console output in your program
should be identical to what you get at the shell prompt if you enter
the same commands separated by pipes.  For example, the shell command:

`gcc --help | grep dump | tr '[:lower:]' '[:upper:]' | sort `

prints all three lines from the gcc help that contain the word 'dump',
converted to uppercase and sorted alphabetically:

```
  -DUMPMACHINE             DISPLAY THE COMPILER'S TARGET PROCESSOR.
  -DUMPSPECS               DISPLAY ALL OF THE BUILT IN SPEC STRINGS.
  -DUMPVERSION             DISPLAY THE VERSION OF THE COMPILER.
```

Running `./pipe4` and entering:

`/usr/bin/gcc --help` &#9166;

`/usr/bin/grep dump` &#9166; 

`/usr/bin/tr '[:lower:]' '[:upper:]'` &#9166; 

`/usr/bin/sort` &#9166;

Should have exactly the same input.

Note that your solution must work for commands other than the examples
shown above.

### Hints

- You may assume that no command will have more than seven (7)
  arguments – each line of input will have a maximum of eight (8) tokens 
  separated by whitespace.

- You may assume that the total length of each line of input will not
  be greater than 80 characters.

- You do not need to account for incorrectly entered commands – we
  will only test your code with correctly entered instructions.

- You will need to use the `fork()` and `pipe()` functions, and
  `strtok()` will be helpful when it comes to parsing input.

- Remember that you'll need to provide the full paths for each of the
  commands – `ls` won't work; you'll need `/bin/ls`.  `which` is a
  good command to use – `which ls` tells you exactly where the `ls`
  utility is in the filesystem.

- You're not allowed to use `stdio.h` function calls in this part of the
  assignment, so to read input from the user, use the `read()` system
  call with file-descriptor zero (0).

### Restrictions

- **YOU MAY NOT CALL ANY STDIO LIBRARY FUNCTIONS IN YOUR SOLUTION TO
  `pipe4.c`!!**  The libraries that have been included in the starter
  file are all the libraries you may use (you may use `stdio.h` functions
  while debugging, **but any calls to those functions must be commented
  out of your final submission**).

- You should not need to use dynamic memory (`malloc`, `free`, etc) for
  this part of the assignment.  If you do choose to use dynamic
  memory, you will lose marks if your program leaks memory or performs
  undefined memory operations (double-frees, dereferences of `NULL`
  pointers, etc).

- Your source code file must be compiled with the following command:
  `gcc -Wall -Werror -std=c18 -o pipe4 pipe4.c`  If it cannot be
  compiled due to syntax errors or warnings, it may receiving a
  failing grade.


## Submitting your work

You must push changes to your files back to the `a1` directory of your
COURSEWORK repository by the due date.  Only the files `fetch-info.c` and
`pipe4.c` will be marked; any other files in this directory will be
disregarded.  Your work MUST be on gitlab.csc to be marked – commit
and push your code often, and double-check that your submission is
successful by checking `https://gitlab.csc.uvic.ca/`.

Any code submitted which has been taken from the web or from textbooks must be properly cited – where used – in a code comment.

---

## Evaluation

**Note: Up to five students may each be asked to demonstrate their work to
the teaching team before their final assignment evaluation is provided
to them.**

* `A` grade: An exceptional submission demonstrating creativity 
  and initiative, with a solution that is clearly structured. Both
  `fetch-info.c` and `pipe4.c` run without any problems.

* `B` grade: A submission completing the requirements of the
  assignment. Both `fetch-info.c` and `pipe4.c` run without any
  problems.

* `C` grade: A submission completing most of the requirements of the
  assignment. Either `fetch-info.c` or `pipe4.c` (or both) run with
  some problems.

* `D` grade: A serious attempt at completing requirements for the
  assignment. The two program run with quite a few problems.

* `F` grade: Either no submission given, or submission represents very
  little work.

**Software-similarity tools with be used this semester to detect
plagiarism and inappropriately-shared code.**  As noted above in this
document, if you use code fragments that were originally found on the
web, you must properly cite that usage with a comment that contains
the URL.

---

&copy; 2024 Mike Zastre
