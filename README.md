# Sorting-Algorithms

This repository contains my implementations of insertion sort, selection sort and quick sort. These algortihms' complexities were analyzed both theoriticaly and empricaly, as a project given in my CMPE160 course in Bogazici University. Emprical results will be uploaded in the following updates.


### Analysis of Worst and Best Cases

#### Insertion Sort

The intuition behind insertion sort is to collect elements one by one and do it in a way that this collection always remains sorted. In my implementation of insertion sort, an enhanced for loop with two loops is used. Outer one iterates over every *i*th **element from the first to the last and  the inner one is iterating over every element less than the *i*th element starting from *i*th to the  first. Inside the inner loop, a comparison is made and if it returns true, two elements in the array are swapped.

So what is the complexity? Regardless of the elements in the array, the outer loop will always be iterated for N times, which is the size of the array. What will be different in the worst and best case is how many times the code in the inner loop is executed. 

Realize that when *arr[j] < arr[j-1]* is not true, the inner loop breaks. So in the best case, the array is sorted from the smallest to the greatest and the inner loop iterates for only single time. Then the number of comparisons  in the best case is 

$$\sum^{n-1}_{i=1}{1}=n-1 = O(n)$$

What about the worst case? The maximum number of comparisons is achieved when the inner loop iterates for *j=i, j=i-1, ..., j=1,* which is *i* times. The number of comparisons is

$$
\sum\limits_{i=1}^{n-1}(\sum\limits_{j=1}^{i} 1) = \sum^{n-1}_{i=1}{i} =1+2+...+(n-1)=n(n-1)/2 = O(n^2)
$$


If we are curious about what the worst case scenario is, we can do the following observation: in the worst case when *i=N*, there should be *N-1* comparisons made in the inner loop and this is possible only if this element is smaller than all the other elements. Therefore the smallest element should be in the end of the array. A similar argument is true for *i=N-1, i=N-2...* as well. So the worse case is when elements are sorted in reverse.

#### Selection Sort

What this sorting algorithm does is finding the minimum value after the *(i-1)*th element and assigning to *i*th element. To achieve this, an enhanced for loop is again used. The outer loop iterates for every *i*th element and the inner loop iterates from *i*th to the last element. Inside the inner loop there is a comparison made. 

The important thing to notice here is that these loops never break. No matter how the distribution of the data is outer loop repeats for *N-1* times and inner loop repeats for *N*-*i* times*.* The number of comparison is

$$
\sum\limits_{i=0}^{n-2}(\sum\limits_{j=i}^{n-1} 1) = \sum^{n-2}_{i=0}{n-1-i} =(n-1)+(n-2)+...+1=n(n-1)/2 = O(n^2)
$$

This concludes that for every case, the algorithmic complexity is the same. 

One can easily expect that talking about the best or worse case situations for selection sort is not meaningful. And she would not be absolutely wrong. In the implementation presented here the number of comparisons and swaps are independent of the distribution. When the  minimum element after the *(i-1)*th element is the ith element, algorithm will swap that element with itself. Therefore number of operations is same and there are no best-worse cases. But actually it’s possible to reduce the number of swaps by doing the swaps only if the minimum element after the ith element is different from the ith element. With an implementation like this,  a sorted array would be the best case scenario without any swaps and the worst case would be any with *N-2* swaps, like an array sorted in reverse. Please beware that the algorithmic complexity for every case is still *$O(n^2)$* but for some of them less number of actions are performed which makes them better cases.

#### Quick Sort

The goal of this algorithm is to divide the problem into smaller subproblems, which can be handled with less effort.  A recursive approach is used to achieve this divide-and-conquer strategy.

Firstly a pivot is chosen from the array. It can be the first, last or the middle element, or chosen randomly.Then algorithm compares every element with the pivot and separates them into two groups: elements that are smaller and greater than the pivot. Next, algorithm is again executed for each group until groups have only single element. 

In my implementation, the last element is chosen as the pivot. A for loop iterates from a given start index to a given end index and compares every element with the pivot. If it’s smaller than the pivot, a swap operation is done to accumulate smaller elements in the beginning of the array. When for loop terminates, a final swap is done to put the pivot between the two groups, so that elements on the left side of it will always be smaller than the elements in the right side. Finally, algorithm is again executed for both groups.

Now that we understand the implementation, we can investigate the complexity and the best-worse cases. Until the recursive calls, only linear and constant complexity operations are done since there is a for loop that iterates for *end-start* times. So the question that arises in our mind is how many times are these codes executed? 

Let’s first examine the following scenario, assume that each time a pivot is chosen so well that two subgroups differ in size by at most one. Then in the first step, there are N comparisons done and two groups with N/2 size are created. And in the second step there are N/2 operations done for both groups and they’re divided into four subgroups with N/4 size. If it’s assumed that N is a power of 2 then the total number of comparison is

$$
1.n + 2.(n/2) + 4.(n/4) +...+ n.1 = n.log_{2}(n) = O(n.log(n))
$$

This is the best case scenario, because algorithm could successfully divide the problem into two equal subproblems. And with the same logic in mind, worst case can be easily discovered. If one of the subgroups was an empty array, then it would be the most inefficient separation possible. And if it’s the case, there will be N comparisons in the first step, and N-1  comparisons in the second and it would go like this until there is only one element in the subgroup. Total number comparisons for the worst case is

$$
n + (n-1) + ... + 1 = \frac{n.(n-1)}{2} = O(n^2)
$$

Notice that while doing the analysis, how a pivot is chosen is not mentioned since it doesn’t affect the worst-best case complexities. However, pivot choice matters when we try to find out  how these cases look like. For example, if the last element is chosen as the pivot, just like in our code, worst case scenario will appear in a sorted array. If the median is the pivot, then the worse case is an interesting array with the least or greatest element always in the middle, an example will be shown in the following chapter.

### Example Scenarios of Worst and Best Cases

When analyzing the best-worst possible cases in detail in the previous chapter, we also talked a bit about how would these cases look like. In this chapter some example scenarios as arrays with ten elements will be given.

- **Insertion Sort
Worst Case**
It was shown that an array sorted in reverse would cause the worst case performance. Example:
    
    ```java
     [10,9,8,7,6,5,4,3,2,1]
    ```
    
    **Best Case**
    With the same logic, an example for the best case is 
    
    ```java
    [1,2,3,4,5,6,7,8,9,10]
    ```
    
    **Effect of any Bias**
    If elements with higher values are biased to be in the end, then such an array would look like the best case and will perform better, since a less number of swap operation will be held. Similarly, array with lower elements in the end ought to perform worse.
    
- **Selection Sort**
It was discussed in detail that  all possible cases end up with the same complexity for the selection sort. Therefore no examples will be given. Any bias would not effect the algorithm as well.
- **Quick Sort**
While discovering the behavior of the algorithm, we discovered that worst-best cases depend highly on which element is chosen as the pivot point. 
**Worst Case when the last element is pivot**
If the last element is chosen, then an array sorted in order or in reverse is the worst case.
    
    ```java
    [1,2,3,4,5,6,7,8,9,10]
    ```
    
    **Best Case when the last element is pivot**
    For the algorithm to work with the maximum performance, array should be divided equally in two groups. Then an example would look like
    
    ```java
    [1,2,4,3,6,7,9,10,8,5] //The Best Case array
     
    [1,2,4,3] [6,7,9,10,8] //First Step
    [1,2][4]   [6,7][9,10] //Second Step
    ```
    
    **Effect of any Bias when the last element is pivot**
    If the array is biased to be in order, then quick sort will definitely be less effective. Although it’s not perfectly sorted, if maximum and minimum are biased the in the last slots algorithm will face with problems. Therefore it raises the question of whether choosing the last element as the pivot is a good idea. A better strategy can be randomly select pivots, which will win against any bias. 
    ****
 
