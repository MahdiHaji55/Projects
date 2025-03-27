#!/usr/bin/bash

set -x

BIN=./virtmem
FRAMESIZE=10
REPLACE="fifo"
TRACEFILE="traces/hello-out.txt"

for NUMFRAMES in 70 30 10
do
   ./virtmem --framesize=$FRAMESIZE --numframes=$NUMFRAMES \
         --replace=$REPLACE --file=$TRACEFILE
done
