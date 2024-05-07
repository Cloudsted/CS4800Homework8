import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private StateOfVendingMachine idleState;
    private StateOfVendingMachine waitingForMoneyState;
    private StateOfVendingMachine dispensingState;
    private StateOfVendingMachine currentState;

    private String selectedSnack;
    private int totalMoneyInserted;
    private Map<String, Snack> snacks = new HashMap<>();
    private SnackDispenseHandler snackHandlerChain;

    public VendingMachine() {
        idleState = new IdleState(this);
        waitingForMoneyState = new WaitingForMoneyState(this);
        dispensingState = new DispensingState(this);

        currentState = idleState;

        // Initialize snacks
        Snack coke = new Snack("Coke", 2, 5);
        Snack pepsi = new Snack("Pepsi", 2, 5);
        Snack cheetos = new Snack("Cheetos", 1, 3);
        Snack doritos = new Snack("Doritos", 1, 3);
        Snack kitkat = new Snack("KitKat", 3, 4);
        Snack snickers = new Snack("Snickers", 2, 2);

        snacks.put(coke.getName(), coke);
        snacks.put(pepsi.getName(), pepsi);
        snacks.put(cheetos.getName(), cheetos);
        snacks.put(doritos.getName(), doritos);
        snacks.put(kitkat.getName(), kitkat);
        snacks.put(snickers.getName(), snickers);

        // Initialize chain of responsibility
        SnackDispenseHandler cokeHandler = new CokeHandler(coke);
        SnackDispenseHandler pepsiHandler = new PepsiHandler(pepsi);
        SnackDispenseHandler cheetosHandler = new CheetosHandler(cheetos);
        SnackDispenseHandler doritosHandler = new DoritosHandler(doritos);
        SnackDispenseHandler kitkatHandler = new KitKatHandler(kitkat);
        SnackDispenseHandler snickersHandler = new SnickersHandler(snickers);

        cokeHandler.setNextHandler(pepsiHandler);
        pepsiHandler.setNextHandler(cheetosHandler);
        cheetosHandler.setNextHandler(doritosHandler);
        doritosHandler.setNextHandler(kitkatHandler);
        kitkatHandler.setNextHandler(snickersHandler);

        snackHandlerChain = cokeHandler;
    }

    public void selectSnack(String snackName) {
        currentState.selectSnack(snackName);
    }

    public void insertMoney(int amount) {
        currentState.insertMoney(amount);
    }

    public void dispenseSnack() {
        currentState.dispenseSnack();
    }

    public void setState(StateOfVendingMachine state) {
        currentState = state;
    }

    public StateOfVendingMachine getIdleState() {
        return idleState;
    }

    public StateOfVendingMachine getWaitingForMoneyState() {
        return waitingForMoneyState;
    }

    public StateOfVendingMachine getDispensingState() {
        return dispensingState;
    }

    public String getSelectedSnack() {
        return selectedSnack;
    }

    public void setSelectedSnack(String selectedSnack) {
        this.selectedSnack = selectedSnack;
    }

    public int getTotalMoneyInserted() {
        return totalMoneyInserted;
    }

    public void addMoney(int amount) {
        totalMoneyInserted += amount;
    }

    public void setTotalMoneyInserted(int totalMoneyInserted) {
        this.totalMoneyInserted = totalMoneyInserted;
    }

    public Snack getSnackByName(String snackName) {
        return snacks.get(snackName);
    }

    public void requestSnack(String snackName) {
        snackHandlerChain.handle(snackName, this);
    }
}