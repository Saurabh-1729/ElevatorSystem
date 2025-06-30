import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet; // Better to use during read heavy operations
import java.util.concurrent.LinkedBlockingDeque;

public class Elevator implements Runnable{
    private final String id;
    private final int capacity;
    private Direction currentDirection;
    private int currentFloor;
    private Set<Integer> destinationFloors;
    private BlockingDeque<Request>  requests;

    public Elevator(int capacity) {
        this.capacity = capacity;
        this.id = UUID.randomUUID().toString();
        this.destinationFloors = new ConcurrentHashMap<>().newKeySet();
        this.requests = new LinkedBlockingDeque<>();
        this.currentDirection = Direction.IDLE;
    }
    public void addRequest(Request request) {
        this.requests.add(request);
    }

    public int getCurrentCapacity() {
        return requests.size();
    }

    public int getCurrentFloor(){
        return currentFloor;
    }

    public Direction getCurrentDirection(){
        return currentDirection;
    }

    public int getCapacity(){
        return capacity;
    }

    public String getId(){
        return id;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Request request = requests.poll();
                if (request == null) continue;
                int sourceFloor = request.getSourceFloor();
                int destinationFloor = request.getDestinationFloor();
                moveTo(sourceFloor);
                System.out.println("Elevator " + id + " reached floor " + sourceFloor);
                moveTo(destinationFloor);
                System.out.println("Elevator " + id + " dropped at floor " + destinationFloor);
                destinationFloors.remove(destinationFloor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveTo(int floor) throws InterruptedException {
            while(currentFloor != floor) {
                if(currentFloor < floor) {
                    currentDirection = Direction.UP;
                    currentFloor++;
                } else {
                    currentDirection = Direction.DOWN;
                    currentFloor--;
                }
                Thread.sleep(1000); // simulate movement
            }
        currentDirection = Direction.IDLE;
    }
}

