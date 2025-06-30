//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ElevatorSystem {
    public static void main(String[] args) {
        ElevatorController controller = ElevatorController.getInstance();
        final int totalElevator = 3;

        for(int i = 0; i < totalElevator; i++){
            Elevator e = controller.addElevator(5);
            new Thread(e).start();
            System.out.println("Elevator with id: " + e.getId() + " is operational");
        }

        controller.handleRequest(new Request(1, 5));
        controller.handleRequest(new Request(3, 7));
        controller.handleRequest(new Request(5, 9));
        controller.handleRequest(new Request(7, 5));
        controller.handleRequest(new Request(10, 1));
        controller.handleRequest(new Request(10, 2));
        controller.handleRequest(new Request(2, 10));
        controller.handleRequest(new Request(3, 5));
    }
}