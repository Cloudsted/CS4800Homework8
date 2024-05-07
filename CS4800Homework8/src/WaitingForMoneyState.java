public class WaitingForMoneyState implements StateOfVendingMachine {
    private VendingMachine vendingMachine;

    public WaitingForMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("Snack already selected: " + vendingMachine.getSelectedSnack());
    }

    @Override
    public void insertMoney(int amount) {
        vendingMachine.addMoney(amount);
        System.out.println("Inserted: $" + amount);
        Snack selectedSnack = vendingMachine.getSnackByName(vendingMachine.getSelectedSnack());

        if (selectedSnack != null) {
            if (vendingMachine.getTotalMoneyInserted() >= selectedSnack.getPrice()) {
                vendingMachine.setState(vendingMachine.getDispensingState());
                vendingMachine.dispenseSnack();
            } else {
                System.out.println("Insufficient funds. Please insert more money.");
            }
        }
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Please insert enough money to purchase the selected snack.");
    }
}