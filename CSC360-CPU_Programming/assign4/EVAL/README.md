#### Trying to probe submitted implementations.


I have provided the following scripts:

* `three-fifo.sh`: This is a sanity check run where an
implementation of the `fifo` page-replacement algorithm is used for
three different memory configurations, with the `traces/hello-out.txt`
memory trace.

* `two-lru.sh`: This runs an implementation of the `lru`
page-replacement algorithm for two different memory configurations,
also with the `traces/hello-out.txt` memory trace.

* `one-clock.sh`: This runs an implementaton of the `clock`
page-replacement algorithm for one memory configuration, this time
with the `traces/ls-out.txt` memory trace.

* `full-compare.sh`: This runs all three page-replacement algorithms
with the largest test case (`traces/matrixmult-out.txt`). With this
much larger test case the represents some interesting computation, the
should be a relative ordering of the three algorithsm -- `fifo` having
the most page faults, `lru` having the fewest, and `clock` (being an
approximation of `lru`) somewhere in the middle. If it happens that
`clock` is better than `lru`, it might be a chance effect given the
memory configuration, so this script will also permit you to override
the `FRAMESIZE` (i.e. a power of two) and `NUMFRAMES` (a regular
natural number) given in the script, to try some different
configurations (maybe somewhat smaller or somewhat larger `FRAMESIZE`,
or different `NUMFRAMES`, or some combination). For example,
`./full-compare.sh` runs with `FRAMESIZE` as 10 (i.e. 2^10) and
`NUMFRAMES` as 12. Also as an example, `./full-compare.sh 9 20` runs with
`FRAMESIZE` as 9 (i.e. 2^9) and `NUMFRAMES` as 20. If on balance over
multiple different runs the submission appears to have the `fifo` >
`clock` > `lru` page fault ordering, then we can say that
`full-compare.sh` "passes".

Below are the page fault statistics that are sensible for `three-fifo.sh`,
`two-lru.sh` and `one-clock.sh` (with the understanding that students
cannot be expected were
expected to produce a precise page fault value -- there is some wiggle room
here). Numbers that are to within 10% will be acceptable.


| script | algorithm | trace file | FRAMESIZE | NUMFRAMES |  pagefaults |
| ---    | ----      | ----       | ----      | ----      |  ----       |
| `three-fifo.sh` | `fifo` | `hello-out.txt` | 10 | 70 | ~ 600 |
| `three-fifo.sh` | `fifo` | `hello-out.txt` | 10 | 30 | ~ 1600 |
| `three-fifo.sh` | `fifo` | `hello-out.txt` | 10 | 10 | ~ 5000 |
| `two-lru.sh` | `lru` | `hello-out.txt` | 9 | 40 | ~ 1200 |
| `two-lru.sh` | `lru` | `hello-out.txt` | 9 | 20 | ~ 4000 |
| `one-clock.sh` | `clock` | `ls-out.txt` | 11 | 30 | ~ 5500|
| `one-clock.sh` | `clock` | `ls-out.txt` | 11 | 15 | ~ 11000|
