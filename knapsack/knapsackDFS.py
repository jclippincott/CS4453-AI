##Jesse Lippincott
##1/25/2018

import util

DFSstack = Stack()                  #Create the stack for node expansion and the set to keep track of visited nodes
visited = set()

parents = {}
finalNode = ((),)

stack.push(problem.getStartState)   #Push the first state onto the stack

while (!DFSstack.isEmpty()):             #While there are still unexpanded nodes in the deque
    current = stack.pop()                #Pop the first item in the deque
    print(current)
    childoptions = problem.getSuccessors()     #Create a new frozenset that has all the possible child nodes in it
    if (current not in visited):             #If we haven't seen the current subset of items yet, keep going
        visited.add(current)                #Mark the subset as visited

    if(current.isGoalState):
        finalNode = current
        break
    else:     
        for i in range(len(childoptions),-1,-1):          
            stack.append(childoptions[i])       #Push each child onto the stack
            parents[childoptions[i]] = current

