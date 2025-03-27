#
# "makefile" for the virtual-memory simulation.
#

CC=gcc
CFLAGS=-c -Wall -g

all: virtmem

virtmem.o: virtmem.c
	$(CC) $(CFLAGS) virtmem.c

virtmem: virtmem.o
	$(CC) virtmem.o -o virtmem

clean:
	rm -rf *.o virtmem
