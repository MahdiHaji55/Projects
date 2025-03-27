/** @file listy.h
 *  @brief Function prototypes for the linked list.
 */
#ifndef _LINKEDLIST_H_
#define _LINKEDLIST_H_

#define MAX_WORD_LEN 200

/**
 * @brief An struct that represents a node in the linked list.
 */
typedef struct node_t{
	
    char* subject;
	int statistic;
	struct node_t* next;
	
}node_t;

/**
 * Function protypes associated with a linked list.
 */
node_t *new_node(char *,int);
node_t *add_front(node_t *, node_t *);
node_t *add_end(node_t *, node_t *);
node_t *add_inorder(node_t *, node_t *);
node_t *peek_front(node_t *);
node_t *remove_front(node_t *);
node_t *find_in_list(node_t* , char* );
node_t *create_sorted_list(node_t*);

void apply(node_t *, void (*fn)(node_t *, void *), void *arg);

#endif
