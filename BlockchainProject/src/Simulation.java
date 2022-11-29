public class Simulation {
  public static void main(String[] args) {
    Model model = new Model(20, 10, 10, 5, 1000, 100000, 31, 3);
    View view = new View();
    Controller controller = new Controller(model, view);

    controller.updateView();
  }
}
