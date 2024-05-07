public class DispensingState implements StateOfVendingMachine {
    private VendingMachine vendingMachine;

    public DispensingState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("Dispensing snack in progress.");
    }

    @Override
    public void insertMoney(int amount) {
        System.out.println("Snack is being dispensed.");
    }

    @Override
    public void dispenseSnack() {
        String snackName = vendingMachine.getSelectedSnack();
        Snack snack = vendingMachine.getSnackByName(snackName);

        if (snack != null && snack.isAvailable()) {
            if (vendingMachine.getTotalMoneyInserted() >= snack.getPrice()) {
                snack.setQuantity(snack.getQuantity() - 1);
                vendingMachine.setTotalMoneyInserted(vendingMachine.getTotalMoneyInserted() - snack.getPrice());
                System.out.println("Dispensing: " + snackName);
                System.out.println("Remaining Quantity: " + snack.getQuantity());
                System.out.println("Change returned: $" + vendingMachine.getTotalMoneyInserted());
                vendingMachine.setTotalMoneyInserted(0);
                vendingMachine.setSelectedSnack(null);
                vendingMachine.setState(vendingMachine.getIdleState());
            } else {
                System.out.println("Insufficient funds. Unable to dispense snack.");
                vendingMachine.setState(vendingMachine.getIdleState());
            }
        } else {
            System.out.println("Selected snack is out of stock or not available.");
            vendingMachine.setTotalMoneyInserted(0);
            vendingMachine.setState(vendingMachine.getIdleState());
        }
    }
}