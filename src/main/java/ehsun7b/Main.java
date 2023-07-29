package ehsun7b;

public class Main {
    public static void main(String[] args) {
        var a = new CustomerFileGenerator(".");
        new Thread(a).start();

        var i = new InventoryItemGenerator(".");
        new Thread(i).start();

        var o = new OrderGenerator(".");
        new Thread(o).start();
    }
}