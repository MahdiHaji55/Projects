## CSC 360: Spring 2024

### Assignment #2 evaluation

* Student name: Hosseini Haji
* Student netlink: `mahdihajihosseini`
* Student number:  V00968642

---

### Part 1: `kosmos-sem.c`

* Submitted and compiles without errors or warnings: YES

If YES above, **program is executed using seed 123 and 80 atoms.**

* Program runs without crashing: YES

* Program creates at least one valid radical: YES

* Program create several valid radicals: YES

* Program creates all valid radicals: MOSTLY

* Only semaphores used in the solution: YES

* More than one semaphore appears to form the solution: YES

* Code appears to "clean up" atoms not forming a radical: NO

* Code is commented: MOSTLY


---

### Part 2: `kosmos-mcv.c`

* Submitted and compiles without errors or warnings: YES

If YES above, **program is executed using seed 123 and 80 atoms.**

* Program runs without crashing: YES

* Program creates at least one valid radical: NO

* Program create several valid radicals: NO

* Program creates all valid radicals: NO

* Only Pthread condition variables and mutexes used: YES

* More than one CV appears to form the solution: NO

* Code is commented: MOSTLY


---

### Other evaluator comments

To begin, your code is quite legible and makes for an easy read. Your
part A has some small issues with what hydrogens are included in the
radical. Due to your modulo logic, only specific atoms can be used in
each hydrogen location in the radical which is too controlled and
actually limits what atoms are ever used. For example in the case
above, H001 and H002 were never used. Part B never made a radical.

---

### Assignment grade: C
