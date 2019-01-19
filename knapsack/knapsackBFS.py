##Jesse Lippincott
##1/25/2018

from collections import deque

filename = raw_input("Please enter the name of the file to read from: ")        #Open the input file
userIn = open(filename, 'r');

capacity = int(userIn.readline().strip("\n"))       #Reads the knapsack capacity and number of items into variables
numItems = int(userIn.readline().strip("\n"))
items = set(range(numItems))                        #Creates a new set with all of the possible items
weights = [int(n) for n in userIn.readline().strip().split(" ")]      #Puts the weights and values into lists, with the index as the item number
values = [int(n) for n in userIn.readline().strip().split(" ")]

BFSdeque = deque()      #Create the deque for node expansion and the set to keep track of visited nodes
visited = set([])

maxItems = set()        #Empty variables for the maximum found value
maxWeight = 100000000000
maxVal = 0

for i in range(numItems):   #Push the root nodes into the deque
    temp = frozenset([i])
    BFSdeque.append(temp)

while (len(BFSdeque) > 0):      #While there are still unexpanded nodes in the deque
    current = BFSdeque.popleft()    #Pop the first item in the deque
    childoptions = frozenset(items-current)     #Create a new frozenset that has all the possible child nodes in it
    if (current not in visited):                #If we haven't seen the current subset of items yet, keep going
        visited.add(current)                    #Mark the subset as visited
        weight = 0
        val = 0
        for i in current:                       #Check what the weight and value of the set are 
            weight += weights[i]
            val += values[i]
        if(weight <= capacity):                 #If we haven't exceeded our capacity
            if (val >= maxVal):                 #Check if this is the most valuable set
                maxVal = val
                maxWeight = weight
                maxItems.clear()
                maxItems.update(current)        #If so, replace the current set, value, and weight with the current set, value and weight
            for j in childoptions:
                temp = set([])                  
                temp.clear()
                temp.update(current)
                temp.add(j)
                BFSdeque.append(frozenset(temp)) #Push each child node into the deque, in the form of a frozenset

maxItems = sorted(list(maxItems))
print "The maximum set of items is [",
for i in maxItems:
    print i+1,
print "] with weight "+str(maxWeight)+" and value "+str(maxVal) #Print the most valuable set of items
