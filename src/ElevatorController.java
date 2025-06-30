import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private final List<Elevator> elevators;
    private static ElevatorController INSTANCE;

    public ElevatorController() {
        this.elevators = new ArrayList<>();
    }

    public static ElevatorController getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ElevatorController();
        }
        return INSTANCE;
    }

    public Elevator addElevator(int capacity){
        Elevator elevator = new Elevator(capacity);
        elevators.add(elevator);
        System.out.println("New Elevator Added with");
        return elevator;
    }

    public void handleRequest(Request request){
        Elevator elevator = getNearestElevator(request);
        if(elevator == null){
            System.out.println("No elevators available");
            return;
        }
        elevator.addRequest(request);
    }

    public Elevator getNearestElevator(Request request){
        Elevator nearestElevator = null;
        int minDistance = Integer.MAX_VALUE;
        for(Elevator elevator : elevators){
            int distance = Math.abs(elevator.getCurrentFloor() - request.getSourceFloor());
            if(elevator.getCurrentCapacity() < elevator.getCapacity() && distance < minDistance){
                nearestElevator = elevator;
                minDistance = distance;

            }
        }
        return nearestElevator;
    }
}
