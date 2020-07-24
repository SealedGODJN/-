/*	FibonacciHeap.c -- 斐波那契堆实现文件	*/
#include "FibonacciHeap.h"

/*	局部函数声明	*/

static Node * makeNode (const Item * const pi) ;
static int logBaseOnTwo (const int current) ;
static void link (Node * list, Node * const new_node) ;
static void cut (Node * const gap) ;
static void merge (Node * const root, Node * const child) ;
static void findCascadingCutAndRenew (Node * const pn, Node * const list) ;
static void renewDegree (Node * const parent, const int degree) ;
static void release (Node * const pn) ;

/*	接口函数定义	*/

BOOL Initialize_F (FibonacciHeap * const pfh)
{
	*pfh = (struct fibonacciheap *) malloc (sizeof (struct fibonacciheap)) ;
	if (NULL == *pfh)
		return FALSE ;
	(*pfh) -> min = NULL ;
	(*pfh) -> current = 0 ;

	return TRUE ;
}

BOOL IsEmpty_F (const FibonacciHeap * const pfh)
{
	if (0 == (*pfh) -> current)
		return TRUE ;
	else
		return FALSE ;
}

BOOL Insert_F (const FibonacciHeap * const pfh, const Item * const pi)
{
	Node * new_node ;

	new_node = makeNode (pi) ;
	if (NULL == new_node)
		return FALSE ;
	if (IsEmpty_F (pfh))
		(*pfh) -> min = new_node ;
	else
	{
		link ((*pfh) -> min, new_node) ;
		if (new_node -> item < (*pfh) -> min -> item)
			(*pfh) -> min = new_node ;
	}
	(*pfh) -> current++ ;

	return TRUE ;
}

Item DeleteMin_F (const FibonacciHeap * const pfh)
{
	Node * * assistant, * temp ;
	Node * scan, * record ;
	Item return_value ;
	int size, i ;

	if (IsEmpty_F (pfh))
		return EMPTY ;
	/*	Record the return value.	*/
	return_value = (*pfh) -> min -> item ;
	/*	If there is only one node in the heap.	*/
	if (1 == (*pfh) -> current)
	{
		free ((*pfh) -> min) ;
		(*pfh) -> min = NULL ;
	}
	else
	{
		/*	If it has one child at least.	*/
		if ((*pfh) -> min -> child != NULL)
		{	
			scan = (*pfh) -> min -> child ;
			do
			{
				(*pfh) -> min -> degree -= scan -> degree ;
				temp = scan -> right ;
				scan -> parent = NULL ;
				link ((*pfh) -> min, scan) ;
				scan = temp ;
			}
			while ((*pfh) -> min -> degree > 1)
				;
			/*	Min -> child will be NULL for sure.	*/
			(*pfh) -> min -> child = NULL ;
		}
		/*	If it has no child, only do these.	*/
		temp = (*pfh) -> min ;
		/*	Find a new min, it can be any point.	*/
		(*pfh) -> min = (*pfh) -> min -> right ;
		/*	Delete min.	*/
		cut (temp) ;
		free (temp) ;
		/*	Find the real min.	*/
		/*	It is O (N).	*/
		scan = record = (*pfh) -> min ;
		do
		{
			if (scan -> right -> item < (*pfh) -> min -> item)
				(*pfh) -> min = scan -> right ;
			scan = scan -> right ;
		}
		while (scan != record)
			;
		/*	Allocate memory space for the assistant list.	*/
		size = logBaseOnTwo ((*pfh) -> current - 1) ;
		assistant = (Node * *) malloc (sizeof (Node *) * size) ;
		if (NULL == assistant)
		{
			printf ("Faild in /"if (NULL == assistant)/"") ;
			return EMPTY ;
		}
		for (i = 0; i < size; i++)
			assistant[i] = NULL ;
		/*	Merge.	*/
		scan = record = (*pfh) -> min ;
		for (i = 0; i < size; i++)
		{
			do
			{
				temp = scan -> right ;
				/*	As an example, logBaseOnTwo (5) = logBaseOnTwo (8) = 3, they two will be merged.	*/
				if (i == logBaseOnTwo (scan -> degree))
				{
					if (NULL == assistant[i])
						assistant[i] = scan ;
					else
					{
						temp = scan -> right ;
						if (scan -> item < assistant[i] -> item)
						{
							cut (assistant[i]) ;
							merge (scan, assistant[i]) ;
						}
						else
						{
							cut (scan) ;
							merge (assistant[i], scan) ;
						}
						assistant[i] = NULL ;
					}
				}
				scan = temp ;
			}
			while (scan != record)
				;
		}
		free (assistant) ;
	}

	(*pfh) -> current-- ;
	
	return return_value ;
}

/*	Please make sure that pn is in *pfh before you use this function.	*/
BOOL DecreaseKey_F (const FibonacciHeap * const pfh, Node * const pn, const int delta)
{
	Node * parent ;

	if (IsEmpty_F (pfh))
		return FALSE ;
	assert (pn != NULL) ;
	assert (delta > 0) ;
	pn -> item -= delta ;
	if (pn -> parent != NULL && pn -> item < pn -> parent -> item)
	{
		parent = pn -> parent ;
		renewDegree (parent, pn -> degree) ;
		if (pn -> left == pn)
			parent -> child = NULL ;
		else
		{
			parent -> child = pn -> right ;
			cut (pn) ;
		}
		pn -> parent = NULL ;
		if (TRUE == pn -> index)
			pn -> index = FALSE ;
		link ((*pfh) -> min, pn) ;
		if (TRUE == parent -> index)
			findCascadingCutAndRenew (parent, (*pfh) -> min) ;
		else
			parent -> index = TRUE ;
	}
	if (pn -> item < (*pfh) -> min -> item)
		(*pfh) -> min = pn ;

	return TRUE ;
}

BOOL Delete_F (const FibonacciHeap * const pfh, Node * const pn)
{
	if (IsEmpty_F (pfh))
		return FALSE ;
	/*	For avert overflow, NEGATIVE_INFINTY is not the smallest number in 32 bits.	*/
	DecreaseKey_F (pfh, pn, NEGATIVE_INFINTY) ;
	DeleteMin_F (pfh) ;

	return TRUE ;
}

void Release_F (const FibonacciHeap * const pfh)
{
	if ((*pfh) -> min != NULL)
		release ((*pfh) -> min) ;
	free (*pfh) ;
}

/*	局部函数定义	*/

static Node * makeNode (const Item * const pi)
{
	Node * new_node ;

	new_node = (Node *) malloc (sizeof (Node)) ;
	if (NULL == new_node)
		return NULL ;
	new_node -> item = *pi ;
	new_node -> degree = 1 ;
	new_node -> index = FALSE ;
	new_node -> left = new_node -> right = new_node ;
	new_node -> parent = new_node -> child = NULL ;

	return new_node ;
}

static int logBaseOnTwo (const int current)
{
	int power, value ;

	for (value = 1, power = 0; value < current; power++)
		value <<= 1 ;

	return power ;
}

/*	Can't process if list is empty.	*/
static void link (Node * list, Node * const new_node)
{
	/*	If there is only one node in list.	*/
	if (list == list -> right)
	{
		list -> left = list -> right = new_node ;
		new_node -> left = new_node -> right = list ;
	}
	else
	{
		list -> right -> left = new_node ;
		new_node -> left = list ;
		new_node -> right = list -> right ;
		list -> right = new_node ;
	}
}

static void cut (Node * const gap)
{
	gap -> left -> right = gap -> right ;
	gap -> right -> left = gap -> left ;
}

static void merge (Node * const root, Node * const child)
{
	child -> parent = root ;
	root -> degree += child -> degree ;
	if (NULL == root -> child)
	{
		child -> left = child -> right = child ;
		root -> child = child ;
	}
	else
		link (root -> child, child) ;
}

static void findCascadingCutAndRenew (Node * const pn, Node * const list)
{
	Node * parent = pn -> parent ;

	if (parent != NULL)
	{
		renewDegree (parent, pn -> degree) ;
		if (1 == pn -> degree)
			parent -> child = NULL ;
		else
		{
			parent -> child = pn -> right ;
			cut (pn) ;
		}
		pn -> parent = NULL ;
		link (list, pn) ;
		if (TRUE == parent -> index)
			findCascadingCutAndRenew (parent, list) ;
		else
			parent -> index = TRUE ;		
	}
}

static void renewDegree (Node * const parent, const int degree)
{
	assert (parent != NULL) ;
	parent -> degree -= degree ;
	if (parent -> parent != NULL)
		renewDegree (parent -> parent, degree) ;
}

/*	It is so skilful! I am a talent!	*/
static void release (Node * const pn)
{
	if (pn -> child != NULL)
		release (pn -> child) ;
	if (pn != pn -> right && pn -> right -> item != BE_RELEASED)
	{
		pn -> item = BE_RELEASED ;
		release (pn -> right) ;
	}
	free (pn) ;
}