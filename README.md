# InterviewQuestionsPracticeInJava
This repository contains practice solutions to the following common programming questions.

| Question 					   |   Solution types   			  |Running Time  |		Solution class		  |
|:-----------------------------|:---------------------------------|:------------:|:--------------------------:|
| Find any nth fibonacci number |  recursive, dynamic, bottom up   |     O(n)     |     FibonacciNumber.java	  |
| How to find square root of a large number in O(logn) time. | iterative | O(nlogn) | SqrRtUsingBinarySearch.java |
| Find a pair of integers if any in an array, whose sum is a specific number|  binary search, n^2, hashmap, both ends	| O(nlogn) | 	FindSum.java |
| Find the kth minimum number in a given array of distinct integers| using partial and full quicksort | O(nlogn) | FindKthMinInt.java |
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
| Convert infix expression to postfix expression | use stack for operators | O(n) | StackInfixToPostfix.java |
| Evaluate postfix expression | use stack for numbers | O(n) | PostfixEvaluation.java |
| Reverse string | use stack, swap chars | O(n) | ReverseString.java |
| Check for balanced parentheses | use stack | O(n) | CheckParentheses.java |
| Next greater element in array | use stack | O(n^2) | NextGreaterElement.java |
| Reverse a stack | recursion using call stack memory | O(n^2) | ReverseStack.java |
| Sort a stack | recursion using call stack memory | O(n^2) | SortStack.java |
| Stock Span Problem | using iteration and stack | O(n) | StockSpanProblem.java |
| Print nodes at k distance from root | recursive | O(n) | BinaryTreeNodesKDistance.java |
| Print ancestors of a node in binary tree | recursive | O(n) | BinaryTreeAncestors.java |
| Check if a tree is subtree of a binary tree | recursive | O(n) | BinaryTreeSubCheck.java |
| Connect Nodes at same Level | recursive using array | O(n) | BinaryTreeConnectLevel.java |
| Find the predecessor and successor of a given element in a binary search tree | recursive | O(n) | BSTPredecessorSuccessor.java |
| Check if a binary tree is BST or not | recursive w/wo inOrder technique | O(n) | BSTCheck.java |
| Find lowest common ancestor in a binary search tree | iterative, recursive | O(h) | BSTLowestCommonAncestor.java |
| Print the elements of both BSTs in sorted form with limited space | iterative using 2 stacks | T:O(m+n), S:O(h1+h2) | BSTMergePrint2Trees |
| Correct the BST whose Two nodes are swapped | recursive inorder | O(n) | BSTSwap2BadNodes.java |
| Find a pair with given sum in a Balanced BST | inorder and reverse inorder traverse | T:O(n) S:O(logn) | FindSumBalancedBST.java |
| Merge Two Balanced Binary Search Trees | recursive inorder array | O(m+n) | MergeBalancedBSTs.java |
| Convert Binary Tree to Binary Search Tree maintaining its structure | recursive inorder array | O(nlogn) | BinaryTreeToBST.java |
| Sort a nearly sorted (or K sorted) array  | using minheap | O(nlogk)	|	SortAlmostSortedArray.java	|
| print k largest elements in an array | using max heap, min heap | O(n+klogn), O(k+(n-k)logk) | KLargestElements.java 
| Tournament's first and second positions | using max heap | O(n) | TournamentWinnerRunner.java |
| Duplicates within k distance in array | hashset, hashmap | O(n) | DuplicatesKDistance.java |
| Union and Intersection of two Linked Lists | hashset | O(n1+n2) | UnionIntersectionLinkedLists.java |
| Find Itinerary from a given list of tickets | hashmap | O(n) | ItineraryListTickets.java |
| Find number of Employees Under every Employee | hashmap, DP } O(n) | EmployeesUnderEmployee.java |
***