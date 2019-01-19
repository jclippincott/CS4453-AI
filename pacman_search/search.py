# search.py
# ---------
# Licensing Information:  You are free to use or extend these projects for 
# educational purposes provided that (1) you do not distribute or publish 
# solutions, (2) you retain this notice, and (3) you provide clear 
# attribution to UC Berkeley, including a link to 
# http://inst.eecs.berkeley.edu/~cs188/pacman/pacman.html
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero 
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and 
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


"""
In search.py, you will implement generic search algorithms which are called by
Pacman agents (in searchAgents.py).
"""

from util import *

class SearchProblem:
    """
    This class outlines the structure of a search problem, but doesn't implement
    any of the methods (in object-oriented terminology: an abstract class).

    You do not need to change anything in this class, ever.
    """

    def getStartState(self):
        """
        Returns the start state for the search problem.
        """
        util.raiseNotDefined()

    def isGoalState(self, state):
        """
          state: Search state

        Returns True if and only if the state is a valid goal state.
        """
        util.raiseNotDefined()

    def getSuccessors(self, state):
        """
          state: Search state

        For a given state, this should return a list of triples, (successor,
        action, stepCost), where 'successor' is a successor to the current
        state, 'action' is the action required to get there, and 'stepCost' is
        the incremental cost of expanding to that successor.
        """
        util.raiseNotDefined()

    def getCostOfActions(self, actions):
        """
         actions: A list of actions to take

        This method returns the total cost of a particular sequence of actions.
        The sequence must be composed of legal moves.
        """
        util.raiseNotDefined()


def tinyMazeSearch(problem):
    """
    Returns a sequence of moves that solves tinyMaze.  For any other maze, the
    sequence of moves will be incorrect, so only use this for tinyMaze.
    """
    from game import Directions
    s = Directions.SOUTH
    w = Directions.WEST
    return  [s, s, w, s, w, w, s, w]

def depthFirstSearch(problem):
    """
    Search the deepest nodes in the search tree first.

    Your search algorithm needs to return a list of actions that reaches the
    goal. Make sure to implement a graph search algorithm.

    To get started, you might want to try some of these simple commands to
    understand the search problem that is being passed in:

    print "Start:", problem.getStartState()
    print "Is the start a goal?", problem.isGoalState(problem.getStartState())
    print "Start's successors:", problem.getSuccessors(problem.getStartState())
    """

    ##Store the starting state for future reference, and instantiate all variables
    start = problem.getStartState()
    DFS = Stack()               
    visited = set()
    parents = {}
    finalMoves = []

    ##Push the first node and start a loop that runs until the solution is found or the stack is empty
    DFS.push(start)   

    while not(DFS.isEmpty()):             
        current = DFS.pop()

        ##If we've found the goal state, find and return the moves required to get us to that state
        if problem.isGoalState(current[0]):
            while(current != start):
                finalMoves = [current[1]]+finalMoves
                current = parents[current]
            return finalMoves
        
        else:
            ##If not goal state, check if it hasn't been visited (or is the start)
            if ((current[0] not in visited) or (current == start)):
                #Add the current node to visited and generate the successors
                if(current == start):
                    visited.add(current)
                    childoptions = problem.getSuccessors(current)
                else:
                    visited.add(current[0])   
                    childoptions = problem.getSuccessors(current[0])
                ##Set the parent node of each child to current, then push any non-visited children to the stack
                for i in range(len(childoptions)):
                    parents[childoptions[i]] = current
                    if childoptions[i][0] not in visited:
                        DFS.push(childoptions[i])     

    return finalMoves

def breadthFirstSearch(problem):
    """Search the shallowest nodes in the search tree first."""

    ##Store the starting state for future reference, and instantiate all variables
    BFS = Queue()                  
    visited = set()
    start = problem.getStartState()
    parents = {}
    finalMoves = []

    ##Push the first node and start a loop that runs until the solution is found or the queue is empty
    BFS.push(start)   

    while not(BFS.isEmpty()):             
        current = BFS.pop()

        ##If we've found the goal state, find and return the moves required to get us to that state
        if problem.isGoalState(current[0]):
            while(current != start):
                finalMoves = [current[1]]+finalMoves
                current = parents[current]
            return finalMoves

        else:
            ##If not goal state, check if it hasn't been visited (or is the start)
            if ((current[0] not in visited) or (current == start)):
                #Add the current node to visited and generate the successors
                if(current == start):
                    visited.add(current)
                    childoptions = problem.getSuccessors(current)
                else:
                    visited.add(current[0])   
                    childoptions = problem.getSuccessors(current[0])
                    
                ##Set the parent node of each child to current, then push any non-visited children to the stack 
                for i in range(len(childoptions)):
                    parents[childoptions[i]] = current
                    if childoptions[i][0] not in visited:
                        BFS.push(childoptions[i])       

    return finalMoves


def uniformCostSearch(problem):
    """Search the node of least total cost first."""

    ##Store the starting state for future reference, and instantiate all variables
    UCS = PriorityQueue()                 
    visited = set()
    start = problem.getStartState()
    parents = {}
    finalMoves = []

    UCS.push(start,1)
    ##Push the first node and start a loop that runs until the solution is found or the queue is empty

    while not(UCS.isEmpty()):             
        current = UCS.pop()

        ##If we've found the goal state, find and return the moves required to get us to that state
        if problem.isGoalState(current[0]):
            while(current != (start)):
                finalMoves = [current[1]]+finalMoves
                current = parents[current]
            return finalMoves
        
        else:
            ##If not goal state, check if it hasn't been visited (or is the start)
            if ((current[0] not in visited) or (current == start)):
                #Add the current node to visited and generate the successors
                if(current == start):
                    visited.add(current)
                    childoptions = problem.getSuccessors(current)
                else:
                    visited.add(current[0])   
                    childoptions = problem.getSuccessors(current[0])
 
                for i in range(len(childoptions)):
                    ##Set the parent node of each child to current 
                    parents[childoptions[i]] = current
                    ##If we haven't already visited the child
                    if childoptions[i][0] not in visited:
                        ##Calculate the cost to reach the child and push it onto the stack with priority=cost
                        curr = childoptions[i]
                        moves = []
                        cost = 0
                        while(curr != (start)):
                            moves = [curr[1]] + moves
                            curr = parents[curr]
                        cost = problem.getCostOfActions(moves)
                        
                        UCS.push(childoptions[i],cost)
    return finalMoves



def nullHeuristic(state, problem=None):
    """
    A heuristic function estimates the cost from the current state to the nearest
    goal in the provided SearchProblem.  This heuristic is trivial.
    """
    return 0

def aStarSearch(problem, heuristic=nullHeuristic):
    """Search the node that has the lowest combined cost and heuristic first."""

    ##Store the starting state for future reference, and instantiate all variables
    Astar = PriorityQueue()                  
    visited = set()
    start = problem.getStartState()
    parents = {}
    finalMoves = []

    ##Push the first node and start a loop that runs until the solution is found or the queue is empty
    Astar.push(start,1)

    while not(Astar.isEmpty()):             
        current = Astar.pop()

        ##If we've found the goal state, find and return the moves required to get us to that state
        if problem.isGoalState(current[0]):
            while(current != (start)):
                finalMoves = [current[1]]+finalMoves
                current = parents[current]
            return finalMoves
        else:
            ##If not goal state, check if it hasn't been visited (or is the start)
            if ((current[0] not in visited) or (current == start)):
                #Add the current node to visited and generate the successors
                if(current == start):
                    visited.add(current)
                    childoptions = problem.getSuccessors(current)
                else:
                    visited.add(current[0])   
                    childoptions = problem.getSuccessors(current[0])
                    
                for i in range(len(childoptions)):
                    ##Set the parent node of each child to current 
                    parents[childoptions[i]] = current
                    ##If we haven't already visited the child
                    if childoptions[i][0] not in visited:
                        ##Calculate the cost to reach the child and push it onto the stack with priority=(cost+heuristic)
                        curr = childoptions[i]
                        moves = []
                        cost = heuristic(curr[0],problem)
                        while(curr != (start)):
                            moves = [curr[1]] + moves
                            curr = parents[curr]
                        cost += problem.getCostOfActions(moves)
                        
                        Astar.push(childoptions[i],cost)
    return finalMoves


# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch
