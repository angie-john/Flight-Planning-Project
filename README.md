# Flight-Planning-Project
Description: This program will take two input files from the user. From the input data file, the program will create a graph representing the cities with the times and costs it takes to travel between them. From the second input file, the program will process the requests for flight plans between two cities. For each request, the program will perform a modified depth first search, implementing an iterative backtracking algorithm utilizing a stack, on the graph to return the top 3 flight plans, sorted based on user preference of time or cost. The program will write these results to an output file. 

Classes Created:
- Node:
  - used for the neighbor of each City; edges of the graph
  - member variables:
    - double cost: cost of edge
      - int time: time of edge
      - City nameNode: City class instance
  - methods:
    - public Node(City nameNode, double cost, int time):
      - constructor with parameters City nameNode, double cost, and int time
    - public Node(Node n):
      - copy constructor with parameter Node n
    - public void setNameNode(City nameNode):
        - setter for nameNode with parameter City nameNode
    - public void setCost(double cost):
        - setter for cost with parameter double cost
    - public void setTime(int time):
      - setter for time with parameter int time
    - public City getNameNode():
      - getter for nameNode, returns City nameNode
    - public int getCost():
      - getter for cost, returns double cost
    - public int getTime():
      - getter for time, returns int time
- City:
  - Used for the vertices of the graph
  - member variables:
    - String name: Name of City
    - boolean visited: true if the city was visited when performing Depth First Search, false if unvisited 
    - LinkedList<Node> neighborList: linked list used to hold the neighbors/edges of the city
  - methods:
    - public City(String name):
      - constructor with parameter String name
      - sets name, sets visited to false, and declares a new empty neighborList
    - public City(String name, LinkedList<Node> neighborList):
      - constructor with parameters String name, LinkedList<Node> neighborList
      - sets name and neighborList, defaults visited to false
    - public City(City c):
      - copy constructor for City with parameter City c
    - public String getName():
      - getter for name, returns String name
    - public boolean getVisited():
      - getter for visited, returns boolean visited
    - public LinkedList<Node> getNeighborList():
      - getter for neighborList, returns LinkedList<Node> neighborList
    - public void setString(String name):
      - setter for name with parameter String name
    - public void setVisited(boolean visited):
      - setter for visited with parameter boolean visited
    - public void setNeighborlist(LinkedList<Node> neighborList):
      - setter for neighborList with parameter LinkedList<Node> neighborList
    - public void addNeighbors(City nameNeighbor, double cost, int time):
      - method to add a new node to neighborList
      - takes City nameNeighbor, double cost, and int time as parameters
      - creates a new node with parameters and adds it to neighborList
    - public boolean canReachCity(City city)
      - method to determine if a city is a neighbor for this city
      - takes City city as parameter
      - checks if city belongs to the neighborList for this city, returns true if belongs, false if does not

- Request:
  - used to hold the user’s requested flight paths
  - member variables:
    - String sourceCity: start city for the requested flight path
    - String endCity: end city for the requested flight path
    - boolean isCost: true if user requested sorting by cost, false if user requested time
  - methods:
    - public Request(String sourceCity, String endCity, boolean isCost):
      - constructor with parameters String sourceCity, String endCity, and boolean isCost
    - public String getSourceCity():
      - getter for sourceCity, returns String sourceCity
    - public String getEndCity():
      - getter for endCity, returns String endCity
    - public boolean getIsCost():
      - getter for isCost, returns boolean isCost
- FlightPlan:
  - used to hold a single flight plan (e.g. Path 1: Austin->Dallas. Time: 47 Cost: 98)
  - member variables:
    - LinkedList<City> cities: list of cities in plan
    - int totalCost: total cost of flight plan
    - totalTime: total time of flight plan
  - methods:
    - public FlightPlan(City startingCity, double cost, int time): 
      - constructor with parameters City starting city, double cost, and int time
      - adds the starting city to cities and initializes cost and time
    - public FlightPlan(FlightPlan f):
        - copy constructor with parameters FlightPlan f
        - used to make a copy of f
    - public City getLastCity():
      - returns the last city in cities
    - public void addCity(City city): 
      - adds a city to cities, takes City city as a parameter
    - public void updateCost(double cost):
      - adds the parameter double cost to the totalCost
    - public void updateTime(int time):
      - adds the parameter int time to the totalCost
    - public int getTotalCost():
      - getter for totalCost
    - public int getTotalCost():
      - getter for totalTime
    - public LinkedList<City> getCities():
      - getter for cities
    - public String printPlan():
      - method used later when writing out plans to the output file
      - returns a String of the plan, (e.g. Path 1: Austin->Dallas. Time: 47 Cost: 98)
    - public boolean isEqual(FlightPlan f):
      - checks if two plans have the same cities
      - returns true if yes, false if no

- FlightPlanUtils:
  - helper class for searching for flight plans
  - methods:
    - public boolean isVisited(City city): 
      - returns true if parameter city is visited, false if not
    - public void markVisited(City city):
      - marks parameter city as visited
    - public void resetVisited(City city): 
      - marks parameter city as unvisited
    - public boolean isValidPlan(FlightPlan plan, String destination): 
      - checks if parameter FlightPlan plan has the last city of the parameter destination
      - returns true if the FlightPlan ends with the same city as destination, false if otherwise
    - public int calculateCost(City source, City destination):
      - calculates and returns the cost between two cities that are neighbors (parameter source and destination)
      - otherwise returns -1
    - public int calculateTime(City source, City destination):
      - calculates and returns the time between two cities that are neighbors (parameter source and destination)
      - otherwise returns -1
    - public LinkedList<City> findNeighborsToVisit(LinkedList<City> cities, FlightPlan plan, City endCity):
      - method to find the unvisited neighbors of the last city of a given flight plan
      - first creates an empty LinkedList<City> to return
      - gets the size of parameter cities (which is a LinkedList of all the cities in the graph)
      - creates a City currCity to hold the last city in parameter FlightPlan plan
      - creates a LinkedList of nodes to hold the neighbors of the city created
      - goes through all the cities in the graph and checks first if they are a neighbor of currCity, then if they have been visited
      - if the neighbor of currCity is equal to the parameter endCity, it gets added to the end of the list to return 
      - otherwise it gets added to the front and returns the list of unvisited neighbors
    - public City findCityName(LinkedList<City> cities, String toFind):
      - finds a given city name in the list of cities for the graph:
      - goes through all of the cities in the graph and if the name (parameter toFind) is equal to the name of any of the cities in the graph, it returns that City 
      - otherwise returns an error City
- FlightSearch:
  - class that has all of the methods related to searching for a flight plan
  - methods:
    - public ArrayList<FlightPlan> sortPlansCost(ArrayList<FlightPlan> toSort):
      - method to sort the list of FlightPlans by cost
      - takes an ArrayList of FlightPlans as a parameter
      - performs a bubble sort on the list based on the totalCost member variable and returns the new sorted list 
    - public ArrayList<FlightPlan> sortPlansTime(ArrayList<FlightPlan> toSort):
      - method to sort the list of FlightPlans by time
      - takes an ArrayList of FlightPlans as a parameter
      - performs a bubble sort on the list based on the totalTime member variable and returns the new sorted list 
    - public ArrayList<FlightPlan> findPlans(LinkedList<City> cities, String startCity, String endCity, boolean useCost):
      - main method used to search for the top 3 plans
      - iterative depth first search, modified with backtracking to return the top 3 plans 
      - parameters
        - cities: LinkedList of all the cities in the graph 
        - startCity: source city for requested flight path
        - endCity: destination city for requested flight path
      - steps:
        1. create an instance of the helper class to use (helper)
        2. use helper to find startCity and endCity in graph
        3. create a stack (backtracker) to use for backtracking
        4. create an ArrayList of FlightPlans to return
        5. check that start and end cities were found/valid
        6. create a new FlightPlan (start) to start with the source city and starting costs and times of 0
        7. push start onto the stack
        8. reset all cities to visit
        9. create an ArrayList of cities to hold the last cities when backtracking
        10. create an int variable to hold the number of iterations
        11. while the stack is not empty have a FlightPan (current) by popping off the top of the stack
        12. check if current is a valid plan (reaches the intended destination) and add it to the topPlans list. sort and remove from the topPlans list as necessary
        13. if the plan is not valid, first reset the neighbors of the the current plan’s last city (backtracking part) ensuring not to create cycles or infinite loops
        14. then for each neighbor in the list of unvisited neighbors (using helper.findNeighborsToVisit) create a flight plan toAdd based on the current
        15. make sure the neighbor has not been added already and then add that city to toAdd
        16. update the cost and time
        17. go through and make sure the plan has not already been added to the stack
        18. then push toAdd and mark neighbor as visited
        19. then go and get the last cities of all the plans in the stack and add them to the list keeping track of all the last cities
        20. reset the last cities of the current plan (mark them as unvisited)
        21. after emptying the stack, return topPlans

- Other methods used:
  - public static ArrayList<String> readInputFile():
    - opens and reads an input file using the scanner class and returns an ArrayList of type String with each index being a line of the tile
  - public static LinkedList<City> formatInput(ArrayList<String> data):
    - formats the ArrayList from the previous method to be able to create the graph (LinkedList<City>) and return it 
    - first splits each line based on “|” 
    - adds each city plus its neighbors to the graph bidirectionally while checking and avoiding double entries
  - public static ArrayList<Request> getRequests(ArrayList<String> data):
    - formats the ArrayList from method 1 to create an ArrayList of Requests
    - splits each line based on “|”
    - checks that input is valid
  - public static ArrayList<FlightPlan> findPlansOutput(Request userReq, LinkedList<City> cityList):
    - takes a request and the list of cities as a parameter
    - returns an ArrayList of FlightPlans calling the findPlans method (in FlightSearch)
  - public static void writeOutput(LinkedList<City> cityList, ArrayList<Request> requestList):
    - writes to the output file
    - takes the list of cities and list of requests as input 
    - for each request, calls the findPlansOutput method and writes the appropriate output to the file for each request 

