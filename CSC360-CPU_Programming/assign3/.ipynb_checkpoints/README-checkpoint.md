# Assignment 3

## UVic CSC360 Spring 2024

**Due Monday March 25, at 11:55 pm** via `push` to your `gitlab.csc`
repository.


## Programming Platform
For this assignment your code must work in the Jupyterlab environment
provisioned for you at `https://jhub-cosi.uvic.ca/`.  You may already
have access to your own Unix system (e.g., Ubuntu, Debian, Cygwin on
Windows 11, macOS with MacPorts, etc.) yet we recommend you work as
much as possible with your CSC360 JupyterLab environment. Bugs in
systems programming tend to be platform-specific and something that
works perfectly at home may end up crashing on a different
computer-language library configuration. (We cannot give marks for
submissions of which it is said "It worked on Visual Studio!")

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

Write a C program (`feedbackq.c`) implementing a simulation of
round-robin CPU scheduling that also makes use of a multi-level
feedback queue and an anti-starvation task boosting mechanism.

---

Unlike the previous assignments involving a significant amount of
systems programming, in this one your work is to write a C
implementation of a round-robin CPU-scheduler simulator which also
uses the organization of multi-level feedback queue. There is no
threading or synchronization required to complete this assignment.

In effect you will be implementing a tick-by-tick simulation of this
single-core CPU scheduler for a set of CPU-bound and IO-bound tasks.
We will use abstract ticks for time rather than milliseconds or
microseconds.

A starter file is provided to you as `assign3/feedbackq.c` which already
contains significant functionality intended to reduce your work. The
file has, amongst other things:

* code needed to open a test-case file;

* a function named `read_instruction()` which uses the open file to
read out the three integers corresponding to the current line in the
file;

- `printf()` statements with the correct formatting needed for simulator
output, although your code must ensure the correct values are
ultimately provided to arguments to these printf statements;

- code to detect when all test-case lines have been read from the file
into the program, and that the simulator loop should terminate.


 
### Overview of the input into, and output from, the simulator
`assign3/feedbackq.c`

Your implementation of `assign3/feedbackq.c` will accept a single argument
consisting of a text file with lines, the lines together containing
information about tasks for a particular simulation case. For example,
to run the first test case the following would be entered at the
command line:

`./feedbackq cases/test1.txt`

Therefore an input file is a representation of a simulation case,
where each line represents one of three possible facts. For example,
the following line contained within such a file:

```
13,3,0
```

indicates that at tick 13, task 3 has been created (i.e. the 0 means creation). A line in the file such as:

```
14,3,6
```

indicates that at tick 14, task 3 initiates an action that will require 6 ticks of CPU time. There may be many such CPU-tick lines for a task contained in the simulation case.

Lastly, there will appear a line in the file such as:

```
21,3,-1
```

here indicating that at tick 21, task 3 will terminate once its
remaining burst’s CPU ticks are scheduled. (If no such CPU ticks are
remaining, then the task would be said to terminate immediately.)

As mentioned earlier in this document, your simulator is to provide a
tick-by-tick simulation of a CPU scheduler. Although we have still to
describe the nature of this scheduler, you may want to look at the
last two pages of this document where you will find the contents of
`assign3/cases/text1.txt` followed by the expected simulator output for this
specific simulation case. You will see in this output (i.e. very last
page of this document) that:

* The tick at which a task enters the system is shown (i.e., each
simulated tick appears on its own line).

* The currently-running task at the given tick is displayed on a line,
with some statistics about that task at the start of the tick. The
meaning of these statistics will be explained later in this
document.

* If there is no task to run at a tick, then the simulator outputs
IDLE for that tick. (A note on what IDLE means is given later in
this assignment description.)

* When a boost occurs, a message indicating the tick number and boost
message is displayed.


### Nature of the scheduling queue used by `feedbackq.c` ###

You are to implement a multi-level feedback queue, or MLFQ. An MLFQ is
designed to ensure the tasks requiring quick responses (i.e. which are
often characterized as having short CPU bursts) are scheduled before
tasks which are more compute bound (i.e. which are characterized as
having long – or at least longer – CPU bursts).

Your simulation will be of an MLFQ with three different queues: one
with a quantum of 2, one with a quantum of 4, and one with a quantum
of 8.

* When there is a CPU scheduling event, the queue corresponding to q=2
is examined. If it is not empty, then the task at the front is
selected to run and given a quantum of 2. Otherwise the queue
corresponding to q=4 is examined, and if it is not empty, then the
task at the front of this queue selected to run and given a quantum of
4. Otherwise the queue corresponding to q=8 is examined, with the task
at the front of this queue selected to run and given a quantum of 8.

* If a task selected from either the q=2 or q=4 queues finishes its
current burst within quantum provided to it, then it is placed at
the back of the queue from which it was taken.

* However if a task selected from either the q=2 or q=4 queues has a
burst that exceeds its quantum, then it is interrupted (as would be
the case for any round-robin algorithm), and placed into the next
queue with a larger quantum. That is, a task taken from q=2 would be
placed at the end of the q=4 queue; a task taken from q=4 would be
placed at the end of the q=8 queue.

* Without some sort of mechanism for giving tasks a chance to leave
the q=8 queue, a task might starve - for example, a task on the q=8
queue might never get a chance to resume if it is pre-empted by a
series of new tasks on a queue with a higher priority.  To prevent
this, the CPU must boost tasks from the q=8 queue up to the q=2 queue
periodically, to give them a chance to compete for CPU time with other
tasks.


In `assign3/feedbackq.c` the three queues are global variables named
queue_1, queue_2, and queue_3 (i.e. q=2, q=4, q=8).

**You are given a queue implementation in the files `assign3/queue.c` and
`assign3/queue.h`. Please read this code carefully. However, you are not to
modify this code unless given express written permission by the
instructor.**


### What is all this about CPU-bound and IO-bound tasks?

Given this assignment works with a simulation of tasks rather than a
real OS workload, we must also simulate the notion of what is means
for a task to be CPU-bound or IO-bound.

More precisely, it is perfectly possible that a task in our simulator
might not, at some tick, be enqueued within MFLQ as it does not
currently have any CPU-tick requirement. This would be precisely the
same behavior as if that task were now blocked on some IO (i.e.,
IO-bound).

Given this is a possibility, we cannot depend completely on the three
global queues to store all information on tasks within our simulation.
Therefore a global `task_table` in `assign3/feedbackq.c` is provided to you.
When you create a task, an instance of `Task_t` for that new task must
be added to this table. And when the simulator detects that there CPU
time is required for the task (i.e., from the input file), then the
`Task_t` for the task must properly enqueued into the MLFQ. If it should
happen the task was scheduled and as a result has completed all of its
required CPU ticks, then that task will not return to the MLFQ but it
will still exist in the global task_table.


### Task metrics ###

Consider a line taken from the last page of this document (i.e. sample
output from a simulation based on `assign3/cases/test1.txt`):

```
[00015] id=0003 req=6 used=2 queue=1
```

At tick 15, the simulator has scheduled task 3 which currently has a
most-recent CPU-tick requirement of 6 ticks, although 2 ticks of that
have been scheduled during some previous ticks. Also the task was
retrieved from queue 1 (i.e., the queue for which quantum q=2).

The very next line, however, shows an important change:

```
[00016] id=0003 req=6 used=3 queue=2
```

Although the amount of CPU ticks used (i.e. scheduled) has gone up,
the queue from which the task was scheduled has changed. That is,
after completing tick 15, task 3 had used up the whole CPU quantum
given to it, and as it still had more time left in its burst, and
according to the rules of the MLFQ, it had been enqueued after tick 15
to the next queue down (i.e. the queue for which quantum q=4).

And note that the `Task_t` structure given to you in `assign3/queue.h` has
two fields, `burst_time` and `remaining_burst_time` which your
implementation must correctly update and from which can be computed
the proper values for `req` and used. 

Let us consider one more line from the sample output given at the end
of this assignment description:

```
[00021] id=0003 EXIT wt=1 tat=7`
```

This indicates that at tick 21, task 3 exited the system having spent
one tick waiting in an MLFQ queue, and where the task had a
turn-around time of 7 ticks. There are three things to observe here:

* Technically speaking, the turnaround time for a process in an OS is
the sum of the ticks for which the process was scheduled the CPU
plus the ticks for which it was waiting on an MLFQ queue. Note that
turn-around time does not include any simulated IO time.

* In order to keep track of the total number of CPU ticks granted to a
task, you must correctly increment the field named
`total_execution_time` for the task in its instance of `Task_t` (i.e.
definition found in `assign3/queue.h`) when the task is scheduled.

* In order to keep track of the total number of ticks for which a task
is in the MLFQ, on each tick your implementation must correctly
increment the field named `total_wait_time` for all relevant tasks in
the task table. A relevant task is one for which the
`remaining_burst_time` is non-zero –- i.e. by definition such a task
will be in one of the MLFQ queues. You must write code within
`update_task_metrics` in order update relevant tasks on a tick; note
that you need not traverse all of the tasks in all the queues for this
action, i.e. all tasks are already available for examination via the
global `task_table` array in `assign3/feedbackq.c`.


### Some visualization of expected schedules ###

Given the complexity of this assignment, you may desire the assistance
of some other way of visualizing the schedules expected from the
various simulations in the text files (i.e. within the `assign3/cases/`
subdirectory.) 

To that end you will find one PDF per test file with a visualization
of the schedule. Note that this visualization does not include all of
the information that would appear within the simulator’s output.
However, the diagrams in the PDFs can be another resource you can use
to "wrap your mind" around the problem as stated for you in this
assignment.


### A word about `IDLE`

It may be the case that at some tick, there are either no tasks in the
task table, or the only tasks that are not yet terminated have
`remaining_burst_time` equal to zero. By definition this would mean
there are no tasks in the MLFQ, and therefore no tasks that can be
dispatched to the CPU. For that tick, therefore, the CPU is said to be
`IDLE`.


### Boosting

The program must prevent tasks from being starved for CPU time.
Starvation could occur if a task is relegated to the bottom queue
(q=8) and then a large number of new, short tasks are added.  These
new tasks would occupy the upper queues and run one after another,
with the CPU never getting a chance to resume the task on the bottom
queue.

To prevent this, you must implement a boost mechanic.  The `boost()`
function will be called just before the scheduler, and will be
responsible for moving all the tasks from the bottom queue (q=8) to
the top0 queue, and then for moving all the tasks from the middle
queue (q=4) to the top queue.  If there is a current task, it should
continue to be the current task, but its remaining time allocated
should be reduced to 2 if it is currently greater than 2.  Boosting,
like adding or exiting tasks, takes no CPU time.  This boost function
should run periodically -- if `BOOST_INTERVAL` is set to 25, a boost
should occur on tick 25, tick 50, and so on.

### ... And so now you must complete the code!

To help guide your development of a solution to this assignment, there
are many comments provided within `assign3/feedbackq.c` that not only state
some helpful simplifying assumptions, but also indicate places in
which to add functionality via TO DO comments. However:

* You may not add any additional source code files without the express
written permission of the course instructor.

* You must not modify the code in `assign3/queue.c` or `assign3/queue.h` –- and if
you think a modification is absolutely necessary, you must pose a
question on RocketChat stating the problem as you see it and the
reason why you think the code must be changed. A member of the
teaching team will provide an answer.

---

## Submitting your work

You must push changes to your files back to the `assign3` directory of your
repository by the due date.  Only the file `feedbackq.c` will be
marked; any other files in this directory will be disregarded (but
please recall the comment above regarding refactoring and the
requirement of receiving written permission for this).  Your work MUST
be on `gitlab.csc` to be marked –- commit and push your code often, and
double-check that your submission is successful by checking
`https://gitlab.csc.uvic.ca/`.

Any code submitted which has been taken from the web or from textbooks
must be properly cited –- where used –- in a code comment.

---

## Evaluation

**Note: Up to five students may each be asked to demonstrate their work to
the teaching team before their final assignment evaluation is provided
to them.**

- "A" grade: An exceptional submission demonstrating creativity and
initiative. The `feedbackq` program runs without any problems, and is
organized and commented in the manner of someone who intends to write
software professionally.

- "B" grade: A submission completing the requirements of the
assignment. The `feedbackq` program runs without any problems.

- "C" grade: A submission completing most of the requirements of the
assignment. The `feedbackq` program runs with some problems.

- "D" grade: A serious attempt at completing requirements for the
assignment. The `feedbackq` program runs with serious problems.

- "F" grade: Either no submission is given, or the submission
represents very little work.


**Software-similarity tools with be used this semester to detect
plagiarism and inappropriately-shared code.**  As noted above in this
document, if you use code fragments that were originally found on the
web, you must properly cite that usage with a comment that contains
the URL.

---

&copy; 2024 Mike Zastre


**Content of cases/test1.txt**

```
1,1,0
2,1,17
7,2,0
8,2,1
13,3,0
14,3,6
18,2,1
21,3,-1
24,2,1
25,2,-1
28,1,-1
```

**Output from a completed simulator based on cases/test1.txt**

```
[00001] id=0001 NEW
[00001] IDLE
[00002] id=0001 req=17 used=1 queue=1
[00003] id=0001 req=17 used=2 queue=1
[00004] id=0001 req=17 used=3 queue=2
[00005] id=0001 req=17 used=4 queue=2
[00006] id=0001 req=17 used=5 queue=2
[00007] id=0002 NEW
[00007] id=0001 req=17 used=6 queue=2
[00008] id=0002 req=1 used=1 queue=1
[00009] id=0001 req=17 used=7 queue=3
[00010] id=0001 req=17 used=8 queue=3
[00011] id=0001 req=17 used=9 queue=3
[00012] id=0001 req=17 used=10 queue=3
[00013] id=0003 NEW
[00013] id=0001 req=17 used=11 queue=3
[00014] id=0003 req=6 used=1 queue=1
[00015] id=0003 req=6 used=2 queue=1
[00016] id=0003 req=6 used=3 queue=2
[00017] id=0003 req=6 used=4 queue=2
[00018] id=0002 req=1 used=1 queue=1
[00019] id=0003 req=6 used=5 queue=2
[00020] id=0003 req=6 used=6 queue=2
[00021] id=0003 EXIT wt=1 tat=7
[00021] id=0001 req=17 used=12 queue=3
[00022] id=0001 req=17 used=13 queue=3
[00023] id=0001 req=17 used=14 queue=3
[00024] id=0002 req=1 used=1 queue=1
[00025] id=0002 EXIT wt=0 tat=3
[00025] BOOST
[00025] id=0001 req=17 used=15 queue=1
[00026] id=0001 req=17 used=16 queue=1
[00027] id=0001 req=17 used=17 queue=2
[00028] id=0001 EXIT wt=9 tat=26
[00028] IDLE

```


