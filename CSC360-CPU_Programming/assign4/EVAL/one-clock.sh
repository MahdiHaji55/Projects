#!/usr/bin/bash

set -x

BIN=./virtmem
FRAMESIZE=11
REPLACE="clock"
TRACEFILE="traces/ls-out.txt"

for NUMFRAMES in 30 15
do
   ./virtmem --framesize=$FRAMESIZE --numframes=$NUMFRAMES \
         --replace=$REPLACE --file=$TRACEFILE
done
