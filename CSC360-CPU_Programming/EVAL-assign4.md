## CSC 360: Spring 2024

### Assignment #4 evaluation

* Student name: Hosseini Haji
* Student netlink: `mahdihajihosseini`
* Student number:  V00968642

---

### Virtual-memory page-replacement simulation

* `virtmem.c` submitted and compiles without errors or warnings: NO

(There is an error reported at lines 171 and 188.)

If YES above, then the following sequence of tests were performed.

(Note: For all of the following, the sole statistic examined was `Page
faults`. It appears there was too much ambiguity with the assignment
specification of the dirty bit and the meaning of swapouts to use
these as a basis for evaluation.)

* `fifo` with `FRAMESIZE` of 10 and `NUMFRAMES` at 70, 30, and 10,
using `hello-out.txt`.  Expected behavior seen? NO

* `lru` with `FRAMESIZE` of 9 and `NUMFRAMES` at 40 and 20, using
`hello-out.txt.  Expected behavior seen? NO

* `clock` with `FRAMESIZE` of 11 and `NUMFRAMES` at 30 and 15, using
`ls-out.txt`.  Expected behavior seen? NO

* `fifo`, `clock`, and `lru` with `FRAMESIZE` of 10 and `NUMFRAMES` at
12, using `matrixmult-out.txt`. Expected behavior seen? NO

* If NO above, is expected behavior largely seen when the values `FRAMESIZE` and
`NUMFRAMES` are varied? NO

* Code is commented in a sensible way: NO

---

### Other evaluator comments

I'm puzzled as I had provided you some extra time to complete this
assignment. (MZ)


---

### Assignment grade: 30
