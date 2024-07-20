import java.util.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;
import java.io.BufferedWriter;
import java.io.*; 


class Node
    {
        private City nameNode;
        private double cost; 
        private int time;
        
        public Node(City nameNode, double cost, int time)
        {
            this.nameNode = nameNode;
            this.cost = cost;
            this.time = time;
        }
        public Node(Node n)
        {
            nameNode = n.nameNode;
            cost = n.cost;
            time = n.time;
        }
        public void setNameNode(City nameNode)
        {
            this.nameNode = nameNode;
        }
        public void setCost(double cost)
        {
            this.cost = cost;
        }
        public void setTime(int time)
        {
            this.time = time;
        }
        public City getNameNode()
        {
            return nameNode;
        }
        public double getCost()
        {
            return cost; 
        }
        public int getTime()
        {
            return time;
        }
        
        
    }
    
class City
    {
        private String name;
        private boolean visited;
        private LinkedList<Node> neighborList;
        
        public City(String name)
        {
            this.name = name;
            visited = false; 
            neighborList = new LinkedList<>();
        }
        
        public City(String name, LinkedList<Node> neighborList)
        {
            this.name = name;
            this.neighborList = neighborList;
            this.visited = false;
        }
        
        public City(City c)
        {
            name = c.name;
            visited = c.visited;
            neighborList = c.neighborList;
        }
        
        public String getName()
        {
            return name;
        }
        public boolean getVisited()
        {
            return visited;
        }
        public LinkedList<Node> getNeighborList()
        {
            return neighborList;
        }
        public void setString(String name)
        {
            this.name = name;
        }
        public void setVisited(boolean visited)
        {
            this.visited = visited; 
        }
        public void setNeighborlist(LinkedList<Node> neighborList)
        {
            this.neighborList = neighborList;
        }
        public void addNeighbors(City nameNeighbor, double cost, int time)
        {
            Node myNode = new Node(nameNeighbor, cost, time);
            neighborList.add(myNode);
        }

        public boolean canReachCity(City city)
        {
            int size = neighborList.size();
            for(int i = 0; i < size; i++)
            {
                if(neighborList.get(i).getNameNode().getName().equals(city.getName()))
                {
                    return true; 
                }
            }
            return false; 
        }
    }
    class Request
    {
        private String sourceCity;
        private String endCity; 
        private boolean isCost; 
        
        public Request(String sourceCity, String endCity, boolean isCost)
        {
            this.sourceCity = sourceCity;
            this.endCity = endCity;
            this.isCost = isCost;
        }
        
        public String getSourceCity()
        {
            return sourceCity;
        }
        
        public String getEndCity()
        {
            return endCity;
        }
        
        public boolean getIsCost()
        {
            return isCost;
        }
    }
    class FlightPlan
    {
        private LinkedList<City> cities;
        private double totalCost;
        private int totalTime;
        
        public FlightPlan(City startingCity, double cost, int time) 
        {
            this.cities = new LinkedList<>();
            this.cities.add(startingCity);
            this.totalCost = cost;
            this.totalTime = time;
        }
        public FlightPlan(FlightPlan f)
        {
            LinkedList<City> c = new LinkedList<>();
            for(int i = 0; i < f.getCities().size(); i++)
            {
                City temp = f.getCities().get(i);
                c.add(i, temp);
            }
            cities = c;
            totalCost = f.totalCost;
            totalTime = f.totalTime;
        }
        public City getLastCity() 
        {
            return cities.get(cities.size() - 1);
        }
      
        
        public void addCity(City city) 
        {
            cities.add(city);
        }
        
        public void updateCost(double cost) 
        {
            this.totalCost += cost;
        }
        public void updateTime(int time)
        {
            this.totalTime += time;
        }
        public double getTotalCost()
        {
            return totalCost;
        }
        public int getTotalTime()
        {
            return totalTime;
        }
        public LinkedList<City> getCities()
        {
            return cities;
        }
        public String printPlan()
        {
            String toReturn = "";
            int index = cities.size()-1;
            for(int i = 0; i < cities.size()-1; i++)
            {
                toReturn += cities.get(i).getName();
                toReturn += "->";
            }
            toReturn += cities.get(index).getName();
            toReturn += ". ";
            toReturn += "Time: ";
            toReturn += totalTime;
            toReturn += " ";
            toReturn += "Cost: ";
            String costString = String.format("%.2f", totalCost);
            toReturn += costString;
            return toReturn;
        }
        public boolean isEqual(FlightPlan f)
        {
            boolean toReturn;
            if(f.getCities().size() != cities.size())
            {
                return false;
            }
            for(int i = 0; i < cities.size(); i++)
            {
                if(!(f.getCities().get(i).getName().equals(cities.get(i).getName())))
                {
                    return false;
                }
            }
            return true;
        }
        
    }
    
    class FlightPlanUtils
    {
        public boolean isVisited(City city) 
        {
            return city.getVisited();
        }
        
        public void markVisited(City city)
        {
            city.setVisited(true);
        }
        
        public void resetVisited(City city) 
        {
            city.setVisited(false);
        }
        
        public boolean isValidPlan(FlightPlan plan, String destination) 
        {
            return plan.getLastCity().getName().equals(destination);
        }
        
        public double calculateCost(City source, City destination)
        {
            LinkedList<Node> sourceNeighbors = source.getNeighborList();
            for(int i = 0; i < sourceNeighbors.size(); i++)
            {
                if(destination.getName().equals(sourceNeighbors.get(i).getNameNode().getName()))
                {
                    return sourceNeighbors.get(i).getCost();
                }
            }
            return -1;
        }
        public int calculateTime(City source, City destination)
        {
            LinkedList<Node> sourceNeighbors = source.getNeighborList();
            for(int i = 0; i < sourceNeighbors.size(); i++)
            {
                if(destination.getName().equals(sourceNeighbors.get(i).getNameNode().getName()))
                {
                    return sourceNeighbors.get(i).getTime();
                }
            }
            return -1;
        }
        public LinkedList<City> findNeighborsToVisit(LinkedList<City> cities, FlightPlan plan, City endCity)
        {
            LinkedList<City> myNeighborsReturn = new LinkedList<City>();
            int citySize = cities.size();
            City currCity = plan.getLastCity();
            LinkedList<Node> neighbors = currCity.getNeighborList();
            for(int i = 0; i < citySize; i++)
            {
                
                if(currCity.canReachCity(cities.get(i)))
                {
                    if(!(isVisited(cities.get(i))))
                    {
                        if(cities.get(i).getName().equals(endCity.getName()))
                        {
                            myNeighborsReturn.addLast(cities.get(i));
                        }
                        myNeighborsReturn.addFirst(cities.get(i));
                    }
                }
            }
            return myNeighborsReturn;
        }
        
        public City findCityName(LinkedList<City> cities, String toFind)
        {
            for(int i = 0; i < cities.size(); i++)
            {
                City temp = cities.get(i);
                if(temp.getName().equals(toFind))
                {
                    return temp;
                }
            }
            String errorName = "error";
            City notFound = new City(errorName);
            return notFound;
        }
        
    }
    
    class FlightSearch
    {
        public ArrayList<FlightPlan> sortPlansCost(ArrayList<FlightPlan> toSort)
        {
        ArrayList<FlightPlan> toReturn = new ArrayList<>();
        for(int i = 0; i < toSort.size()-1; i++)
        {
            for(int j = 0; j < toSort.size()-i-1; j++)
            {
                if(toSort.get(j).getTotalCost() > toSort.get(j+1).getTotalCost())
                {
                FlightPlan temp = toSort.get(j);
                toSort.set(j, toSort.get(j+1));
                toSort.set(j+1, temp);
                }
            }
            
        }
        toReturn = toSort;
        return toReturn; 
        }
        
        public ArrayList<FlightPlan> sortPlansTime(ArrayList<FlightPlan> toSort)
        {
        ArrayList<FlightPlan> toReturn = new ArrayList<>();
        for(int i = 0; i < toSort.size()-1; i++)
        {
            for(int j = 0; j < toSort.size()-i-1; j++)
            {
                if(toSort.get(j).getTotalTime() > toSort.get(j+1).getTotalTime())
                {
                FlightPlan temp = toSort.get(j);
                toSort.set(j, toSort.get(j+1));
                toSort.set(j+1, temp);
                }
            }
            
        }
        toReturn = toSort;
        return toReturn; 
        }
        
        
        public ArrayList<FlightPlan> findPlans(LinkedList<City> cities, String startCity, String endCity, boolean useCost)
        {
            FlightPlanUtils helper = new FlightPlanUtils();
            City source = helper.findCityName(cities, startCity);
            City dest = helper.findCityName(cities, endCity);
            Stack<FlightPlan> backtracker = new Stack<>();
            ArrayList<FlightPlan> topPlans = new ArrayList<>(3);
            if(source.getName().equals("error") || dest.getName().equals("error"))
            {
                return topPlans; 
            }
            FlightPlan start = new FlightPlan(source, 0, 0);
            backtracker.push(start);
            int numIter = 0; 
            for(int i = 0; i < cities.size(); i++)
            {
                helper.resetVisited(cities.get(i));
            }
            
     
            ArrayList<City> lastCities = new ArrayList<>();
            while(!(backtracker.empty()))
            {
                FlightPlan current = backtracker.pop();
                if(helper.isValidPlan(current, endCity))
                {
                    boolean checkDup = false;
                    helper.resetVisited(current.getLastCity());
                    for(int i = 0; i < topPlans.size(); i++)
                    {
                        if(topPlans.get(i).isEqual(current))
                        {
                            checkDup = true;
                        }
                    }
                    if(!checkDup)
                    {
                        topPlans.add(current);
                    }
                    if(useCost == true)
                    {
                        topPlans = sortPlansCost(topPlans);
                    }
                    else
                    {
                        topPlans = sortPlansTime(topPlans);
                    }
                    
                    if(topPlans.size() > 3)
                    {
                        topPlans.remove(topPlans.size() - 1);
                    }
                }
                else 
                {
                    
                    numIter++;
                    FlightPlan temp = new FlightPlan(current);
                   
                   
                    for(int i = 0; i < lastCities.size(); i++)
                    {
                        
                        if(current.getCities().size() > 1)
                        {
                            if(current.getLastCity().getName().equals(lastCities.get(i).getName()))
                        {
                                    City checkNeighbor = new City(lastCities.get(i));
                                    LinkedList<Node> currNeighbors = checkNeighbor.getNeighborList();
                                    for(int k = 0; k < currNeighbors.size(); k++)
                                    {
                                        City temp3 = new City(currNeighbors.get(k).getNameNode());
                                        boolean checkDup2 = false;
                                        for(int a = 0; a < current.getCities().size(); a++)
                                        {
                                            //current.printPlan();
                                            if(temp3.getName().equals(current.getCities().get(a).getName()))
                                            {
                                                checkDup2 = true;
                                            
                                            }
                                        }
                                        if(!(temp3.getName().equals(startCity)) && !(checkDup2))
                                        {
                                            helper.resetVisited(temp3);
                                            for(int j = 0; j < cities.size(); j++)
                                            {
                                                if(cities.get(j).getName().equals(temp3.getName()))
                                                {
                                                    helper.resetVisited(cities.get(j));
                                                    
                                                }
                                            }
                                        }
                                    }
                        }
                        }
                        
                    }
                    
                        
                    for(City neighbor : helper.findNeighborsToVisit(cities, current, dest))
                    {
                        
                        FlightPlan toAdd = new FlightPlan(current);
                        
                        boolean repeat = false;
                        for(int j = 0; j < toAdd.getCities().size(); j++)
                        {
                            if(toAdd.getCities().get(j).getName().equals(neighbor.getName()))
                            {
                                repeat = true;
                            }
                        }
                        if(!repeat)
                        {
                           toAdd.addCity(neighbor); 
                        }
                        if(toAdd.getLastCity().getName().equals(endCity))
                        {
                            helper.resetVisited(toAdd.getLastCity());
                        }
                        double costToAdd = helper.calculateCost(current.getLastCity(), neighbor);
                        int timeToAdd = helper.calculateTime(current.getLastCity(), neighbor);
                        toAdd.updateCost(costToAdd);
                        toAdd.updateTime(timeToAdd);
                        
                        temp = new FlightPlan(toAdd);
                        boolean checkDupPlan = false; 
                        
                        int backSize = backtracker.size();
                        for(int i = 0; i < backSize; i++)
                        {
                            if(backtracker.get(i).isEqual(toAdd))
                            {
                                checkDupPlan = true;
                            }
                        }
                        for(int i = 0; i < backSize; i++)
                        {
                            FlightPlan dupCheck = new FlightPlan(backtracker.get(i));
                            for(int j = 0; j < dupCheck.getCities().size(); j++)
                            {
                                for(int k = j+1; k < dupCheck.getCities().size(); k++)
                                {
                                    if(dupCheck.getCities().get(j).getName().equals(dupCheck.getCities().get(k).getName()))
                                    {
                                        
                                        checkDupPlan = true;
                                    }
                                }
                            }
                        }
                        if(!checkDupPlan)
                        {
                            backtracker.push(toAdd);
                        }
                        helper.markVisited(neighbor);
                    }
                    int size = current.getLastCity().getNeighborList().size();
                    int stackSize = backtracker.size();
                
    
                        boolean checkCityDup = false;
                        boolean checkCityDup2 = false;
                        if(numIter >= 1)
                        {
                            for(int i = 0; i < stackSize; i++)
                            {
                            int num = backtracker.get(i).getCities().size();
                            lastCities.add(backtracker.get(i).getLastCity());
                            /*
                            for(int k = 0; k < initialPaths.size(); k++)
                            {
                                if(backtracker.get(i).getLastCity().getName().equals(initialPaths.get(k).getName()))
                                {
                                    checkCityDup = true;
                                }
                                
                            }*/
                            
                            
                            /*if(backtracker.get(i).getCities().size() == 2 && !checkCityDup)
                            {
                                initialPaths.add(backtracker.get(i).getLastCity());
                            }*/
                            
                            
                            }
                        }
                        
                    
                    
                    if(backtracker.size() != 0)
                    {
                        FlightPlan temp2 = new FlightPlan(backtracker.peek());
                        helper.resetVisited(temp.getLastCity());
                        helper.resetVisited(temp2.getLastCity());
                    }
                    
                }
            }
            
            return topPlans;
        }
    }
public class Main
{
    public static ArrayList<String> readInputFile()
	{
	    System.out.println("Enter file name: ");
	    Scanner input = new Scanner(System.in);
	    String name = input.nextLine();
	    File fr = new File(name);
	    ArrayList<String> records = new ArrayList<String>();
	    String line;
	    try (Scanner scan = new Scanner(fr))
	    {
	        while(scan.hasNext())
	        {
	            line = scan.nextLine();
	            records.add(line);
	        }
	        return records;
	    }
	    catch (IOException e)
        {
            System.out.println("Error invalid input");
            System.exit(0);
            return records;
        }
	}

	
	public static LinkedList<City> formatInput(ArrayList<String> data)
	{
	    LinkedList<City> myCities = new LinkedList<>();
	    
	    for(int i = 1; i < data.size(); i++)
	    {
	        String b = data.get(i);
	        String[] array = b.split("\\|");
	        String name = array[0];
	        String neighbor = array[1];
	        String costString = array[2];
	        String timeString = array[3];
	        int length = timeString.length() - 1;
    	    char lastChar = timeString.charAt(length);
    	    if(Character.isWhitespace(lastChar))
    	    {
    	        timeString = timeString.substring(0, length);
    	    }
    	    double costNum = Double.parseDouble(costString);
    	    int timeNum = Integer.parseInt(timeString);
    	    City temp = new City(name);
    	    City neighborTemp = new City(neighbor);
    	    temp.addNeighbors(neighborTemp, costNum, timeNum);
    	    neighborTemp.addNeighbors(temp, costNum, timeNum);
    	    boolean avoidDouble = false;
    	    boolean avoidDouble2 = false ;
    	    
    	    for(int j = 0; j < myCities.size(); j++)
    	    {
    	        if(myCities.get(j).getName().equals(temp.getName()))
    	        {
    	            avoidDouble = true;
    	            myCities.get(j).addNeighbors(neighborTemp, costNum, timeNum);
    	        }
    	    }
    	    for(int k = 0; k < myCities.size(); k++)
    	    {
    	        if(myCities.get(k).getName().equals(neighborTemp.getName()))
    	        {
    	            avoidDouble2 = true;
    	            myCities.get(k).addNeighbors(temp, costNum, timeNum);
    	        }
    	    }
    	    if(!avoidDouble)
    	    {
    	        myCities.add(temp);
    	    }
    	    if(!avoidDouble2)
    	    {
    	        myCities.add(neighborTemp);
    	    }
    	    
	    }
	    return myCities;
	}
	
	public static ArrayList<Request> getRequests(ArrayList<String> data)
	{
	    ArrayList<Request> myRequests = new ArrayList<>();
	    for(int i = 1; i < data.size(); i++)
	    {
	        String b = data.get(i);
	        String[] array = b.split("\\|");
	        String theSource = array[0];
	        String theDest = array[1];
	        String timeOrCost = array[2];
	        boolean timeCost;
	        if(timeOrCost.equals("T"))
	        {
	            timeCost = false; 
	        }
	        else if(timeOrCost.equals("C"))
	        {
	            timeCost = true; 
	        }
	        else
	        {
	            timeCost = false;
	            theSource = "error";
	        }
    	    Request temp = new Request(theSource, theDest, timeCost);
    	    
    	    myRequests.add(temp);
	    }
	    return myRequests;
	}
	
	public static ArrayList<FlightPlan> findPlansOutput(Request userReq, LinkedList<City> cityList)
	{
	    String a = userReq.getSourceCity();
	    String b = userReq.getEndCity();
	    boolean calcCost = userReq.getIsCost();
	    FlightSearch test = new FlightSearch();
	    ArrayList<FlightPlan> toReturn = test.findPlans(cityList, a, b, calcCost);
	    return toReturn;
	}
	public static void writeOutput(LinkedList<City> cityList, ArrayList<Request> requestList)
	{
	    FileWriter outputFile;
	    try {
	        outputFile = new FileWriter("OutputFile.txt");
	        BufferedWriter writer = new BufferedWriter(outputFile);
	        int numFlights = requestList.size();
	        for(int i = 0; i < numFlights; i++)
	        {
	            ArrayList<FlightPlan> plans = findPlansOutput(requestList.get(i), cityList);
	            if(plans.size()!=0)
	            {
	                boolean isCost = requestList.get(i).getIsCost();
	                String start = requestList.get(i).getSourceCity();
	                String end = requestList.get(i).getEndCity();
	                String type;
	                int flightNum = i+1;
	                if(isCost)
	                {
	                    type = "Cost";
	                }
	                else{
	                    type = "Time";
	                }
	                if(i > 0)
	                {

	                    writer.write('\n');
	                    writer.write('\n');
	                }
	            
	          
	                writer.write("Flight " + flightNum + ": " + start + ", " + end + "("  + type + ")" + '\n');
	                for(int j = 0; j < plans.size(); j++)
		            {
		                int pathNum = j+1;

		                writer.write("Path " + pathNum + ": ");
		                String planOutput = plans.get(j).printPlan();
		                writer.write(planOutput);
		                writer.write('\n');
		            }
	            }
	            else
	            {
	                writer.write("Error no plan viable");
	            }
	            
		        
	    }
	    writer.close();
	    }
	    catch(IOException e)
	    {
	        System.out.println("Error making file");
	        System.exit(0);
	    }
	    
	}
	public static void main(String[] args) {
		ArrayList<String> inputData = readInputFile();
	    ArrayList<String> requestData = readInputFile();
		LinkedList<City> citiess = formatInput(inputData);
	    ArrayList<Request> requests = getRequests(requestData);
		writeOutput(citiess, requests);

		
		
	}
}
