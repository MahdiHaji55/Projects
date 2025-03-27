#!/usr/bin/bash

# usage: either `./full-compare.sh` (which uses the default values of
# FRAMESIZE and NUMFRAMES shown below), or `./full-compare.sh
# <framesize> <numframes>`
#

set -x

BIN=./virtmem
FRAMESIZE="${1:-10}"
REPLACE="fifo"
NUMFRAMES="${2:-12}"
TRACEFILE="traces/matrixmult-out.txt"

for REPLACE in "fifo" "clock" "lru"
do
   ./virtmem --framesize=$FRAMESIZE --numframes=$NUMFRAMES \
         --replace=$REPLACE --file=$TRACEFILE
done
