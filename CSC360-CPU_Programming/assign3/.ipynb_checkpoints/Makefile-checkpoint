#
# "Makefile" for the Producer Consumer Task.
#

CC=gcc -Wall -ggdb3
FLAGS=-g -D_REENTRANT
LIBS=-pthread -lrt

all: feedbackq
.PHONY: all clean

feedbackq: feedbackq.o queue.o
	$(CC) $(LIBS) $(FLAGS) -o feedbackq feedbackq.o queue.o

feedbackq.o: feedbackq.c queue.h
	$(CC) $(LIBS) $(FLAGS) -c feedbackq.c

queue.o: queue.c queue.h
	$(CC) $(FLAGS) -c queue.c

clean:
	rm -rf *.o feedbackq
