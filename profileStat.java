package profile;

public class profileStat {
	public profileStat(String _name)
	{
		callCount = 0;
		averageCallTime = 0;
		maxCallTime = 0;
		name = _name;
	}
	
	
	String name;
	long callCount;
	double averageCallTime;
	double maxCallTime;
}
