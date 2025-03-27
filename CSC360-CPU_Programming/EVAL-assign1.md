## CSC 360: Spring 2024

### Assignment #1 evaluation

* Student name: Hosseini Haji
* Student netlink: mahdihajihosseini
* Student number:  V00968642

---

### Part 1: `fetch-info.c`

* Submitted and compiles without errors or warnings: yes
* `./fetch-info` (without arguments): good
* `./fetch-info <pnum>` (process number of `conda`): good
* `./fetch-info 9999` (expect error message for invalid number): good


**Regarding code structure of solution to part 1:**

You've structured the program around a nice functional decomposition
. Such code is definitely easier to
maintain and extend. Good work.


---

### Part 2: `pipe4.c`

* Submitted and compiles without errors or warnings: yes
* Single command w/o arguments: good
* Single command w/ arguments: good
* Two commands: good
* Three commands: good
* Four commands: good

Here is the four-command pipe as it would be expressed in `bash` (but
of course broken up line-by-line for use with `pipe4`):
```
    /usr/bin/ls -1 /usr/lib | /usr/bin/sort  \
         | /usr/bin/tail -2 | /usr/bin/wc
```

**Regarding code structure of solution to part 2:**

You've pursued the somewhat challenging approach of a loop to connect
up each process to a pipe -- well done. 


---

### Other evaluator comments

---

### Assignment grade: A+


