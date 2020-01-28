# UNF-Data-Structures-Java
### These are projects created for the Data Structures course at UNF. This course uses Java

## Project 1 - Array Searches and Sorts
     This project takes a file named Countries.csv and reads the contents into an array of Country objects. It then prompts a console UI
      asking the user to either Print a Countries report, sort by Name using Bubble Sort, sort by Happiness using Selection Sort, sort by
      GDP per capita using Insertion Sort, find and print a given Country, or quit out of the program.
     I got a 91% with the issues being:
          1. The directions required Insertion sort to sort by GDP per capita and I mistakenly sorted by GDP
          2. The program crashes upon character inputs during the UI process
          3. I didn't provide comments throughout the program
    
## Project 2 - Stacks and Priority Queues
    - This project follows up on Project 1. It will take a file named Countries2.csv and read the contents into 5 priority queue's, each
      of which are arrays of Country objects, based on the countries GDP per capita (poor, fair, good, very good, and excellent). After
      reading the contents it will load each priority queue, starting from the lowest class, into a stack and then print the stack to show
      that the priority queues worked properly by removing the prioritized elements.
    - I got a 87% with the issues being:
          1. Due to a possible compilation error, the grader stated that the program crashed while reading scientific notation. Even
             though this worked on my end, it was due to the use of Long.parseLong(string) instead of Double.valueOf(string).longValue()
             which using the latter fixes this issue while using the former is volitile accross platforms.
          2. My printStack() method in the Stack Class misorders the first and last pairs of countries in the Excellent and Fair queues 
             after their removed from the queue and inserted into the Stack.
          3. In some cases my remove() method in the Priority Class fails to remove the highest priority country from the priority queue
             and is most likely linked to issue #2.
    
## Project 3 - Linked Lists
    - This project follows Project 1 and 2. It will take a file named Countries3.csv and read the contents into a Stack which is
      implemented as a Singly Linked List
    
