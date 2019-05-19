# InterviewQuestionsPracticeInJava
This repository contains practice solutions to the following common programming questions.

| Question 					   |   Solution types   			  |Running Time  |		Solution class		  |
|:-----------------------------|:---------------------------------|:------------:|:--------------------------:|
|Find any nth fibonacci number |  recursive, dynamic, bottom up   |     O(n)     |     FibonacciNumber.java	  |
|How to find square root of a large number in O(logn) time. | iterative | O(nlogn) | SqrRtUsingBinarySearch.java |
|Find a pair of integers if any in an array, whose sum is a specific number|  binary search, n^2, hashmap, both ends	| O(nlogn) | 	FindSum.java |
|find the kth minimum number in a given array of distinct integers| using partial and full quicksort | O(nlogn) | FindKthMinInt.java |
| Sort a nearly sorted (or K sorted) array  | modified quick sort	| O(nlogk)	|	SortAlmostSortedArray	|
| Find the Minimum length Unsorted Subarray, sorting which makes the complete array sorted | loops | O(n) | MinimumUnsortedSubarray.java |
| Find k closest elements to a given value | distinct and non distinct 	| O(klogn) | FindKClosestElements.java |
| Sort n numbers in range from 0 to n^2 – 1 in linear time | modified radix for power 2 or k | O(n) and O(kn) | SortNNumsRangeN2.java | 
| Find the closest pair from two sorted arrays whose sum is closest to x | iterative from start and end | O(n1+n2) | ClosestPairSortedArrays.java |
| Find maximum children to which n candies can be distributed | iterative until no candies | < O(logn) | FairCandyDistribution.java |
| Find common elements in three sorted arrays | iterative all in one | O(n1+n2+n3) | CommonIn3SortedArrays.java |
| Given a sorted array and a number x, find the pair in array whose sum is closest to x | iterative both ends | O(n) | ClosestPairInSortedArray.java |
| Count 1’s in a sorted binary array | binary search | O(logn) | Count1BinarySortedArr.java
| Search in an almost sorted array | modified binary search  | O(logn)  |  SearchAlmostSorted.java |
| Minimum adjacent swaps to move maximum and minimum to corners | single iteration | O(n) | MinAdjSwapsMaxMin.java|
| Maximum activities possible when given start and finish times | brute force, greedy algorithm | exp, O(n) | ActivitySelectionProblem.java |
| Sum of 2 numbers represented as linked lists | convert to int, using carry | O(n1+n2) | SumLinkedList.java |
| Rotate Linked list on k nodes | iterative | O(n) | RotateLinkedList.java |
| convert infix expression to postfix expression | use stack for operators | O(n) | StackInfixToPostfix.java |
| evaluate postfix expression | use stack for numbers | O(n) | PostfixEvaluation.java |
| reverse string | use stack, swap chars | O(n) | ReverseString.java |
| Check for balanced parentheses | use stack | O(n) | CheckParentheses.java |
| Next greater element in array | use stack | O(n^2) | NextGreaterElement.java |
| Reverse a stack | recursion using call stack memory | O(n^2) | ReverseStack.java |
| sort a stack | recursion using call stack memory | O(n^2) | SortStack.java |
| Stock Span Problem | using iteration and stack | O(n) | StockSpanProblem.java |
| Print nodes at k distance from root | recursive | O(n) | BinaryTreeNodesKDistance.java |
| print ancestors of a node in binary tree | recursive | O(n) | BinaryTreeAncestors.java |
| check if a tree is subtree of a binary tree | recursive | O(n) | BinaryTreeSubCheck.java |
| Connect Nodes at same Level | recursive using array | O(n) | BinaryTreeConnectLevel.java |
| find the predecessor and successor of a given element in a binary search tree | recursive | O(n) | BSTPredecessorSuccessor.java |
***