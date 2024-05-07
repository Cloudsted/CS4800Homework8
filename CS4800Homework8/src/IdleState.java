public class IdleState implements StateOfVendingMachine {
    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("Snack selected: " + snackName);
        vendingMachine.setSelectedSnack(snackName);
        vendingMachine.setState(vendingMachine.getWaitingForMoneyState());
    }

    @Override
    public void insertMoney(int amount) {
        System.out.println("Select a snack first before inserting money.");
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Please select a snack first.");
    }
}
