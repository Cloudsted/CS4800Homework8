abstract class SnackDispenseHandler {
    protected SnackDispenseHandler nextHandler;
    protected Snack snack;

    public SnackDispenseHandler(Snack snack) {
        this.snack = snack;
    }

    public void setNextHandler(SnackDispenseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handle(String snackName, VendingMachine vendingMachine) {
        if (snack.getName().equalsIgnoreCase(snackName)) {
            vendingMachine.setSelectedSnack(snackName);
            vendingMachine.setState(vendingMachine.getWaitingForMoneyState());
        } else if (nextHandler != null) {
            nextHandler.handle(snackName, vendingMachine);
        } else {
            System.out.println("Snack not found.");
        }
    }
}