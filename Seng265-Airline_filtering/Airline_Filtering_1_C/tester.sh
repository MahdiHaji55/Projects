#!/bin/sh
gcc -std=c99 -Wall -Wextra -Wpedantic -fsanitize=address,undefined -O0 -g route_manager.c -o route_manager

    ./route_manager --DATA="airline-routes-data.csv" --AIRLINE="SWR" --DEST_COUNTRY="Argentina"
    ./tester 1

    ./route_manager --DATA="airline-routes-data.csv" --AIRLINE="ETH" --DEST_COUNTRY="Egypt"
    ./tester 2
    ./route_manager --DATA="airline-routes-data.csv" --AIRLINE="ACA" --DEST_COUNTRY="Bahamas"
    ./tester 3

    ./route_manager --DATA="airline-routes-data.csv" --SRC_COUNTRY="Australia" --DEST_CITY="Zurich" --DEST_COUNTRY="Switzerland"
    ./tester 4

    ./route_manager --DATA="airline-routes-data.csv" --SRC_COUNTRY="India" --DEST_CITY="Tokyo" --DEST_COUNTRY="Japan"
    ./tester 5

    ./route_manager --DATA="airline-routes-data.csv" --SRC_COUNTRY="Panama" --DEST_CITY="New York" --DEST_COUNTRY="United States"
    ./tester 6

    ./route_manager --DATA="airline-routes-data.csv" --SRC_CITY="Victoria" --SRC_COUNTRY="Canada" --DEST_CITY="Cancun" --DEST_COUNTRY="Mexico"
    ./tester 7

    ./route_manager --DATA="airline-routes-data.csv" --SRC_CITY="Toronto" --SRC_COUNTRY="Canada" --DEST_CITY="Cancun" --DEST_COUNTRY="Mexico"
    ./tester 8

    ./route_manager --DATA="airline-routes-data.csv" --SRC_CITY="Paris" --SRC_COUNTRY="France" --DEST_CITY="Dubai" --DEST_COUNTRY="United Arab Emirates"
    ./tester 9
