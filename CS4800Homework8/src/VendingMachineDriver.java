public class VendingMachineDriver {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.requestSnack("Coke");
        vendingMachine.insertMoney(1);
        vendingMachine.insertMoney(1);
        vendingMachine.insertMoney(1);

        vendingMachine.requestSnack("Doritos");
        vendingMachine.insertMoney(2);

        vendingMachine.requestSnack("KitKat");
        vendingMachine.insertMoney(3);

        vendingMachine.requestSnack("Snickers");
        vendingMachine.insertMoney(2);
        vendingMachine.insertMoney(1);

        vendingMachine.requestSnack("Snickers");
        vendingMachine.insertMoney(2);

        vendingMachine.requestSnack("Pepsi");
        vendingMachine.insertMoney(2);
    }
}