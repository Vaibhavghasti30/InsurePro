package com.insurepro;

import java.io.*;
import java.util.*;


public class IcecreamParlor {

	public static void main(String[] args)
	{	
      Scanner sc = new Scanner(System.in);
      System.out.println("--Wel-Come to Icecream Parlor--");
      System.out.println("***************");
      System.out.println("To know total sales of the store press 1");
      System.out.println("To know total monthwise sales of the store press 2");
      System.out.println("To know popular item sales monthwise of the store press 3");
      System.out.println("To know items generating revenue in each month of the store press 4");
      System.out.println("To know popular item's min,max & average number of order each month");
      int i = sc.nextInt();
      if(i==1)
      { 
    	  int totalSales = 0;
    	  File file = new File("C:\\Users\\Admin\\Downloads\\sales-data.txt");
    	  try {
			BufferedReader br = new BufferedReader(new FileReader(file));
		}
    	  catch (FileNotFoundException e1)
    	  {
			e1.printStackTrace();
		  }     
    		       try {
    		            Scanner sc1 = new Scanner(file);
    		            while (sc1.hasNextLine()) 
    		            {
    		                String line = sc1.nextLine();
    		                //spliting texts into the fileds 
    		                String[] fields = line.split(","); 
    		                int sales = Integer.parseInt(fields[1]);
    		                
    		                //logic for counting total price 
    		                totalSales +=  sales;
    		            }
    		            sc1.close();
    		        } 
    		        catch (FileNotFoundException e)
    		        {
    		            System.out.println("File not found: " + file);
    		        } 
    		        catch (NumberFormatException e) {
    		            System.out.println("Invalid sales data in file: " + file);
    		        }
    		       //printing totalsales
    		        System.out.println("Total sales: " + totalSales);  
    	
      }
      else if(i==2)
      {
              try {
                  // Reading sales data from file
                  BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Admin\\Downloads\\sales-data.txt"));
                  String line;
                  
                  //storing salesdata into arraylist
                  List<String[]> salesData = new ArrayList<>();
                  while ((line = br.readLine()) != null)
                  {
                      String[] row = line.split(",");
                      salesData.add(row);
                  }
                  br.close();

                  //monthwise sales totals here string reprsents month and double represents sales value
                  Map<String, Double> monthSales = new HashMap<>();
                  for (String[] row : salesData)
                  {
                      String date = row[1];
                      double amount = Double.parseDouble(row[1]);
                      //spliting dates
                      String month = date.substring(0, 7);
                      if (monthSales.containsKey(month))
                      {
                          monthSales.put(month, monthSales.get(month) + amount);
                      } 
                      else 
                      {
                          monthSales.put(month, amount);
                      }
                  }

                  // Print month-wise sales totals
                  System.out.println("Month-wise Sales Totals:");
                  for (Map.Entry<String, Double> entry : monthSales.entrySet())
                  {
                      String month = entry.getKey();
                      double salesTotal = entry.getValue();
                      System.out.println(month + ": " + salesTotal);
                  }
              } catch (IOException e)
              {
                  System.err.println("Error reading file: " + e.getMessage());
              }
          }
      
      else if(i==3)
      {
    	    	  // Read the data file
    	          String fileName = "C:\\Users\\Admin\\Downloads\\sales-data.txt";
    	          //storing month and quantity in string and integer
    	          Map<String, Integer> monthlyQuantity = new HashMap<String, Integer>();
    	          //storing month and items to get popular item
    	          Map<String, String> monthlyItem = new HashMap<String, String>();

    	          try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

    	              String line;
    	              while ((line = br.readLine()) != null)
    	              {

    	                  // Split the line into columns
    	                  String[] columns = line.split(",");

    	                  // Extract the month and quantity sold
    	                  String date = columns[0];
    	                  int quantity = Integer.parseInt(columns[2]);
    	                  String itemName = columns[1];

    	                  // Update the monthly quantity and most popular item
    	                  String month = date.substring(0, 7);
    	                  if (!monthlyQuantity.containsKey(month) || quantity > monthlyQuantity.get(month)) {
    	                      monthlyQuantity.put(month, quantity);
    	                      monthlyItem.put(month, itemName);
    	                  }
    	              }

    	          }
    	          catch (IOException e)
    	          {
    	              e.printStackTrace();
    	          }

    	          // Print the most popular item for each month
    	          for (String month : monthlyQuantity.keySet()) 
    	          {
    	              System.out.println("Most popular item in " + month + ": " + monthlyItem.get(month));
    	          }
    	      }
    
      else if(i==4)
      {  
             // Open the file for reading			 
					 Scanner sc2 = null;
					try {
						sc2 = new Scanner(new File("C:\\Users\\Admin\\Downloads\\sales-data.txt"));
					}
					catch (FileNotFoundException e) 
					{
						e.printStackTrace();
					}
				
    	          // storing the sales data
    	          HashMap<String, HashMap<String, Integer>> salesData = new HashMap<>();

    	          // Loop through the lines of the file
    	          while (sc2.hasNextLine())
    	          {
    	              // Parse the line into its components
    	              String[] components = sc2.nextLine().split(",");
    	              String month = components[0];
    	              String item = components[1];
    	              int quantity = Integer.parseInt(components[2]);

    	              // Adding the sales data 
    	              if (!salesData.containsKey(month))
    	              {
    	                  salesData.put(month, new HashMap<String, Integer>());
    	              }
    	              HashMap<String, Integer> monthData = salesData.get(month);
    	              if (!monthData.containsKey(item))
    	              {
    	                  monthData.put(item, 0);
    	              }
    	              monthData.put(item, monthData.get(item) + quantity);
    	          }
    	          // Close the file
    	          sc2.close();
    	          
    	          // Find the most popular item and the min, max, and average number of orders for each month
    	          for (String month : salesData.keySet())
    	          {
    	              HashMap<String, Integer> monthData = salesData.get(month);

    	              // Find the most popular item
    	              int maxQuantity = 0;
    	              String mostPopularItem = "";
    	              for (String item : monthData.keySet())
    	              {
    	                  int quantity = monthData.get(item);
    	                  if (quantity > maxQuantity) 
    	                  {
    	                      maxQuantity = quantity;
    	                      mostPopularItem = item;
    	                  }
    	              }
    	              System.out.println("Most popular item in " + month + ": " + mostPopularItem);

    	              // Find the min, max, and average number of orders
    	              Collection<Integer> quantities = monthData.values();
    	              int minQuantity = Collections.min(quantities);
    	              int maxQuantity2 = Collections.max(quantities);
    	              double averageQuantity = 0;
    	              for (int quantity : quantities)
    	              {
    	                  averageQuantity += quantity;
    	              }
    	              averageQuantity /= quantities.size();
    	              System.out.println("Min orders in " + month + ": " + minQuantity);
    	              System.out.println("Max orders in " + month + ": " + maxQuantity2);
    	              System.out.println("Average orders in " + month + ": " + averageQuantity);
    	          }
    	      }
      else
      {
    	  System.out.println("Enter only 1 to 4 numbers");
      }
	}
}
  
