# Need to recover from JVM OutOfMemoryException (OME)

The JVM has an option -XX:OnOutOfMemoryError which allows you to run a script when it dies due to an OME.  
This is an example of how this can be used.

The startTest.sh starts a java program which runs the JVM out of memory. When this happens the restart.sh script is executed and restarts the test.   
Yes this will go around and around, hit CTL+C to stop the bus.

## Installation
  
    git clone https://github.com/jonboylailam/outOfMemory.git
    cd outOfMemory
    ./startTest.sh

## Highlights

startTest.sh

    java -Xmx10m -cp classes -XX:OnOutOfMemoryError="./restart.sh" TestOutOfMemory	