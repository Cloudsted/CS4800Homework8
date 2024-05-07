public interface StateOfVendingMachine {
    void selectSnack(String snackName);
    void insertMoney(int amount);
    void dispenseSnack();
}