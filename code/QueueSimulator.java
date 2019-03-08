import java.lang.*;

public class QueueSimulator{
  public enum Event { ARRIVAL, DEPARTURE };
  private double currTime;
  private double arrivalRate;
  private double serviceTime;
  private double timeForNextArrival;
  private double timeForNextDeparture;
  private double totalSimTime;
  private LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
  private LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();
  private Event e;
  
  public double getRandTime(double arrivalRate){
    double num, time1, max=1, min=0, randNUM;
    randNUM= Math.random();
    time1= (-1/arrivalRate) * (Math.log(1-randNUM));
    //System.out.println(time1);
    return time1;
  }
  
  public QueueSimulator(double aR, double servT, double simT){
    currTime = 0;
    arrivalRate = aR;
    serviceTime = servT;
    totalSimTime = simT;
    timeForNextArrival = getRandTime(aR);
    timeForNextDeparture = timeForNextArrival+serviceTime;
  }
  
  public double calcAverageWaitingTime(){
    int count=0;
    double sumWaitingTime=0;
    Data packet;
    while(!eventQueue.isEmpty()){
       packet = eventQueue.dequeue();
       count++;
      //System.out.println(count+ " Departure: "+packet.getDepartureTime()+" Arrival: "+ packet.getArrivalTime());
      //System.out.println(count+" Wait time: "+(packet.getDepartureTime()-packet.getArrivalTime()));
      sumWaitingTime+=(packet.getDepartureTime()-packet.getArrivalTime());
    }
    //System.out.println("SumwaitingTime: "+sumWaitingTime+" count: "+count);
    return sumWaitingTime/(double)count;
  }
  
  public double runSimulation(){
    while(currTime<totalSimTime){
      if(!buffer.isEmpty()&&timeForNextDeparture<timeForNextArrival){
        e = Event.DEPARTURE;
        currTime = timeForNextDeparture;
        Data outPacket = buffer.dequeue();
        outPacket.setDepartureTime(currTime);
        eventQueue.enqueue(outPacket);
        timeForNextDeparture = currTime + serviceTime;
      }
      else{
        //System.out.println("Current: "+currTime+" Arrival: "+timeForNextArrival+" Departure: "+timeForNextDeparture);
        e = Event.ARRIVAL;
        currTime = timeForNextArrival;
        Data inPacket = new Data();
        inPacket.setArrivalTime(currTime);
        buffer.enqueue(inPacket);
        if(buffer.size()==1){
          timeForNextDeparture = timeForNextArrival+serviceTime;
        }
        timeForNextArrival = currTime+getRandTime(arrivalRate);
      }
      //System.out.println(currTime);
      if(currTime>=totalSimTime)break;
    }
    return calcAverageWaitingTime();
  }
}






