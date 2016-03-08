package profile;

public class profilepoint {
	public profilepoint(profiler _profiler,String _name)
	{
		name = _name;
		profiler = _profiler;
		startTime = System.nanoTime();
	}
	
	public void measure()
	{
		endTime = System.nanoTime();
		
		long timeTaken = endTime - startTime;
		profiler.addToProfiler(name, timeTaken);
	}
	
	long startTime;
	long endTime;
	String name;
	profiler profiler;
}
