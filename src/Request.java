public class Request {
    private final int source_floor;
    private final int destination_floor;

    public Request(int source_floor, int destination_floor) {
        this.source_floor = source_floor;
        this.destination_floor = destination_floor;
    }

    public int getSourceFloor() {
        return source_floor;
    }

    public int getDestinationFloor(){
        return destination_floor;
    }
}
