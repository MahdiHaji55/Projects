#!/usr/bin/bash

set -x

BIN=./virtmem
FRAMESIZE=9
REPLACE="lru"
TRACEFILE="traces/hello-out.txt"

for NUMFRAMES in 40 20
do
   ./virtmem --framesize=$FRAMESIZE --numframes=$NUMFRAMES \
         --replace=$REPLACE --file=$TRACEFILE
done
