public class Controller {
  private Model model;
  private View view;

  public Controller(Model model, View view) {
    this.model = model;
    this.view = view;
  }

  public void updateView() {
    model.genActors();
    view.getBalancesAfter(model.users);
    genState();
    view.getBalancesBefore(model.users);
    getBlocks();
  }

  public void genState() {
    int k = 0;
    while (k < model.nrBlocks * model.poolSize) {
      int option = model.random.nextInt(3);
      if (option == 0) {
        model.genStateTransferTx();
      } else if (option == 1) {
        model.genStateLockTx();
      } else {
        model.genStateSwapTx();
      }
      k++;
      model.addAndMineBlock();
    }
  }

  public void getBlocks() {
    model.blockchain.getBlocks();
  }

}
