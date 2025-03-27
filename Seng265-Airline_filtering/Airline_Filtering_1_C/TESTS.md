# Tests for `SENG 265`, Assignment #1

* Test 1
    * Input: `airline-routes-data.csv`
    * Expected output: `test01.txt`
    * Command: `./route_manager --DATA="airline-routes-data.csv" --AIRLINE="SWR" --DEST_COUNTRY="Argentina"`
    * Test: `./tester 1`

* Test 2
    * Input: `airline-routes-data.csv`
    * Expected output: `test02.txt`
    * Command: `./route_manager --DATA="airline-routes-data.csv" --AIRLINE="ETH" --DEST_COUNTRY="Egypt"`
    * Test: `./tester 2`

* Test 3
    * Input: `airline-routes-data.csv`
    * Expected output: `test03.txt`
    * Command: `./route_manager --DATA="airline-routes-data.csv" --AIRLINE="ACA" --DEST_COUNTRY="Bahamas"`
    * Test: `./tester 3`

* Test 4
    * Input: `airline-routes-data.csv`
    * Expected output: `test04.txt`
    * Command: `./route_manager --DATA="airline-routes-data.csv" --SRC_COUNTRY="Australia" --DEST_CITY="Zurich" --DEST_COUNTRY="Switzerland"`
    * Test: `./tester 4`

* Test 5
    * Input: `airline-routes-data.csv`
    * Expected output: `test05.txt`
    * Command: `./route_manager --DATA="airline-routes-data.csv" --SRC_COUNTRY="India" --DEST_CITY="Tokyo" --DEST_COUNTRY="Japan"`
    * Test: `./tester 5`

* Test 6
    * Input: `airline-routes-data.csv`
    * Expected output: `test06.txt`
    * Command: `./route_manager --DATA="airline-routes-data.csv" --SRC_COUNTRY="Panama" --DEST_CITY="New York" --DEST_COUNTRY="United States"`
    * Test: `./tester 6`

* Test 7
    * Input: `airline-routes-data.csv`
    * Expected output: `test07.txt`
    * Command: `./route_manager --DATA="airline-routes-data.csv" --SRC_CITY="Victoria" --SRC_COUNTRY="Canada" --DEST_CITY="Cancun" --DEST_COUNTRY="Mexico"`
    * Test: `./tester 7`

* Test 8
    * Input: `airline-routes-data.csv`
    * Expected output: `test08.txt`
    * Command: `./route_manager --DATA="airline-routes-data.csv" --SRC_CITY="Toronto" --SRC_COUNTRY="Canada" --DEST_CITY="Cancun" --DEST_COUNTRY="Mexico"`
    * Test: `./tester 8`

* Test 9
    * Input: `airline-routes-data.csv`
    * Expected output: `test09.txt`
    * Command: `./route_manager --DATA="airline-routes-data.csv" --SRC_CITY="Paris" --SRC_COUNTRY="France" --DEST_CITY="Dubai" --DEST_COUNTRY="United Arab Emirates"`
    * Test: `./tester 9`
