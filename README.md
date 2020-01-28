# UNF-Data-Structures-Java
### These are projects created for the Data Structures course at UNF. This course uses Java

## Project 1 - Array Searches and Sorts
     - This project takes a file named Countries.csv and reads the contents into an array of
          Country objects. It then prompts a console UI asking the user to either Print a Countries
          report, sort by Name using Bubble Sort, sort by Happiness using Selection Sort, sort by
          GDP per capita using Insertion Sort, find and print a given Country, or quit out of the program.
       
     - I recieved a 91% with the issues being:
           1. The directions required Insertion sort to sort by GDP per capita and I mistakenly
                sorted by GDP
           2. The program crashes upon character inputs during the UI process
           3. I didn't provide comments throughout the program
    
## Project 2 - Stacks and Priority Queues
    - This project follows up on Project 1. It will take a file named Countries2.csv and read
      the contents into 5 priority queue's, each of which are arrays of Country objects, based
      on the countries GDP per capita (poor, fair, good, very good, and excellent). After
      reading the contents it will load each priority queue, starting from the lowest class, into
      a stack and then print the stack to show that the priority queues worked properly by removing
      the prioritized elements.
      
    - I recieved a 87% with the issues being:
          1. Due to a possible compilation error, the grader stated that the program crashed while reading
             scientific notation. Even though this worked on my end, it was due to the use of
             Long.parseLong(string) instead of Double.valueOf(string).longValue() which using the latter
             fixes this issue while using the former is volitile accross different platforms.
          2. My printStack() method in the Stack Class misorders the first and last pairs of countries in 
             the Excellent and Fair queues after they're removed from the queue and inserted into the Stack.
          3. In some cases my remove() method in the Priority Class fails to remove the highest priority
             country from the priority queue and is most likely linked to issue #2.
    
## Project 3 - Linked Lists
    - This project follows Project 1 and 2. It will take a file named Countries3.csv and read the
      contents into a Stack which is implemented as a Singly Linked List and print said Stack. Then
      will rotate between various functions to manipulate this data and load to and remove from a
      Queue which is implemented as a Double Linked List.
      
    - I recieved a 95% with the issues being:
          1. The only issue with this program was the Country Mauritania was missing from the final
             Stack print and should be the first item printed.
             
## Project 4 - Binary Search Trees
    - This project again follows on the previous and will take a file named Countries4.csv and read
      the contents into a BST by only storing their name and GDP per capita while having the ability
      to manipulate the BST in various ways. While trying to tackle this project, I got the idea to
      use recursion with the printTopTen() and printBottomTen() methods. Even though during testing
      I thought it worked, some flaws led to issues labeled below. The print methods were supposed
      to print the top and bottom ten countries by GDP per capita while the entire BST was to be
      sorted by country name.
      
    - I recieved an 81% with the issues being:
           1. The countries Australia, Ireland, Singaport, and Somalia are not correctly deleted from the
              tree and this causes the countries to no longer be correctly ordered by country name.
           2. Due to a possible flaw in the comparisons for my recursive methods, the printTopTen()
              and printBottomTen() methods did not return the correct countries. This was overlooked
              because of the vast amount of countries in the .csv file and probably missing certain countries
              while comparing my output with the file.
              
## Project 5 - Hash Tables
     - This project again follows on the previous ones and will take a file named Countries5.csv and
       and read the contents into a Hash Table, storing only their name and GDP per capita. The Hash Table
       itself is a Double Linked List using seperate chaining as the collision management system. It
       does this by having a Node array named first which is the first link in each index's linked list,
       and a Node array named last which is the last link in each index's linked list. It has various
       supporting functions. This is the project that I was at a level that I came to fully understand
       Data Structures as a whole.
       
     - I recieved a 100% on this project with no issues.
    
