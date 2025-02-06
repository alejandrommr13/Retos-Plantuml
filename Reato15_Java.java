class CoffeeMaker {
private boolean off = true;

    public void on() {
off = false;
System.out.println("Coffee Maker is on");
    }

public void off() {
off = true;
System.out.println("Coffee Maker is off");
}

public boolean isOff() {
    return off;
}
}


class AdaptadorCafetera implements Connectable {
    private CoffeeMaker cafetera;
    public AdaptadorCafetera(CoffeeMaker cafetera) {
this.cafetera = cafetera;
}
public void turnOn() {
cafetera.on();
}
public boolean isOff() {
    return cafetera.isOff();
    }
}


public class TurnOnDevices {
    public static void main(String[] args) {
turnOnDevice(new Lamp());
    turnOnDevice(new Computer());
        turnOnDevice(new AdaptadorCafetera(new CoffeeMaker())); 
    }
private static void turnOnDevice(Connectable device) {
        device.turnOn();
        System.out.println(device.isOff());}
}