package main;

import model.Powerbank;

public class PrototypeProvider {
    private static PrototypeProvider prototypeProvider;
    private Powerbank powerbank;

    private PrototypeProvider() {}

    public static PrototypeProvider getInstance() {
        if(prototypeProvider == null) {
            prototypeProvider = new PrototypeProvider();
        }
        return prototypeProvider;
    }

    public Powerbank getPowerbank() {
        if(powerbank == null) {
            powerbank = new Powerbank();
        }
        else powerbank = powerbank.clone();

        return powerbank;
    }
}
