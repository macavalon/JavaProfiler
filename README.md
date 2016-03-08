JavaProfiler
=============

A very simple java profiling class

Example use
============================
```
import profile.profiler;

profiler Profiler = new profiler();

functionToProfile()
{
    //at start of function
    
    profilepoint _profilepoint = new profilepoint(Profiler,"processMessage");
    
    
    // at end of function
    
    _profilepoint.measure();
}

//to report profile stats at any time

String stats = Profiler.reportStats();
```
e.g. if print stats
```
+                                     Function Name      Call Count -   Average Time -       Max Time
+                                 functionToProfile              99 -       0.029901 -    0.226909796
```

