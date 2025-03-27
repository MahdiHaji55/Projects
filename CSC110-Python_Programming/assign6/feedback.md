
## CSC 110, Spring 2022: Assignment 6
* Submitted by: `Mahdi Haji Hosseini`
* VNum: `V00968642`
* Netlink: `mahdihajihosseini`

---

### Part A and marker comments

* Constructor for `SimpleCipher` is correctly defined.

* Implementations of `encipher` and `deciper` do produce correct result,
   but be careful with using nested loops as you do. You can pre-compute an
   encipher/deciper dictionary in the constructor, eliminating the need
   for (tricky) loop nesting.

---

### Part B and marker comments

* Constructor for `HardwareItem` is suitably defined. With such classes,
   it is normally a good idea to ensure the attributes are private
   (ie. use the `_` or underscore to name attributes, for example,
   `self._description` rather than `self.description`.)

* Implementation of `sold_item` and `restock_item` are clearly written.

* One `getter` was implemented, but assignment did require `getters` 
   for all four attributes.

---

### Part C and marker comments

* `read_food_file` does have problem when the file does not exist
   (ie. it should return an empty list rather than causing a traceback error).

* `dump_data` correctly implemented.

* `compute_only_eaters` correctly implemented.

* `compute_only_eaten` correctly implemented.

* `compute_fussiest_eaters` correctly implemented.

* `compute_most_delicious` correctly implemented.

---

### Mark summary

* Mark for Part A ----> **3.0** / 3.0
* Mark for Part B ----> **2.5** / 3.0
* Mark for Part C ----> **3.5** / 4.0
* **Total Marks** ----> **9.00** / 10.0

