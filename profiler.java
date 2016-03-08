package profile;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.TreeMap;

public class profiler {

	HashMap<String,profileStat> ProfileMap;
	long n;
	public profiler()
	{
		ProfileMap = new HashMap<String,profileStat>();
		long m = 1000;
	    long u = m*1000;
	    n = u*1000;
	}
	
	private Object profilerMutex = new Object();
	
	void addToProfiler(String name, double callTimeNs)
	{
		synchronized(profilerMutex)
        {
			double callTime = callTimeNs/n;
			
			profileStat temp = null;
			
			if(ProfileMap.containsKey(name))
			{
				temp = ProfileMap.get(name);
			}
			else
			{
				//new
				temp = new profileStat(name);
			}
			
			//update
			
			if(callTime > temp.maxCallTime)
			{
				temp.maxCallTime = callTime;
			}
			
			temp.averageCallTime = ((temp.averageCallTime*temp.callCount) + callTime)/(temp.callCount+1);
			
			temp.callCount++;
			
			//store
			ProfileMap.put(name,temp);
        }
	}
	
	
	
	public String reportStats()
	{
		DecimalFormat df = new DecimalFormat("#.######"); 
		//String formatted = df.format(2.456345); 
		//System.out.println(formatted); 

		String output;
		
		output  = String.format("+%50s %15s -%15s -%15s","Function Name", "Call Count", "Average Time", "Max Time");
		
		TreeMap<Double,profileStat> sortedTime = new TreeMap<Double,profileStat>();
		
		for(String name : ProfileMap.keySet())
		{
			profileStat temp = ProfileMap.get(name);
			double deltaTime = 0.000001;
			while(sortedTime.containsKey(temp.averageCallTime))
			{
				temp.averageCallTime += deltaTime;
			}
			sortedTime.put(temp.averageCallTime, temp);
		}
		
		for(Double averageCallTime : sortedTime.descendingKeySet())
		{
			profileStat temp = sortedTime.get(averageCallTime);
			String result = String.format("+%50s %15s -%15s -%15s",temp.name,temp.callCount,df.format(temp.averageCallTime),temp.maxCallTime);
			output += "\n" + result;
		}
		
		return output;
	}
	
}
