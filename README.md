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
| Sort n numbers in range from 0 to n^2 � 1 in linear time | modified radix for power 2 or k | O(n) and O(kn) | SortNNumsRangeN2.java | 
| Find the closest pair from two sorted arrays whose sum is closest to x | iterative from start and end | O(n1+n2) | ClosestPairSortedArrays.java |
| Find maximum children to which n candies can be distributed | iterative until no candies | < O(logn) | FairCandyDistribution.java |
| Find common elements in three sorted arrays | iterative all in one | O(n1+n2+n3) | CommonIn3SortedArrays.java |
| Given a sorted array and a number x, find the pair in array whose sum is closest to x | iterative both ends | O(n) | ClosestPairInSortedArray.java |
| Count 1�s in a sorted binary array | binary search | O(logn) | Count1BinarySortedArr.java
| Search in an almost sorted array | modified binary search  | O(logn)  |  SearchAlmostSorted.java |
| Minimum adjacent swaps to move maximum and minimum to corners | single iteration | O(n) |  MinAdjSwapsMaxMin.java |