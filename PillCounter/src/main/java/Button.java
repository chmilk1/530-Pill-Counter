import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;
import com.pi4j.util.Console;

public class Button {

    private final String name;
    private final int buttonId, id1, id2, id3;
    private final int HOLD_THRESHOLD = 6;
    private int state = 0, holdLength = 0;
    private boolean isRunning = false, didPress = false;

    private DigitalOutput led1, led2, led3;
    private final Console console;
    private final Context pi4j;

    private void turnOff(DigitalOutput led) {
        if (led.equals(DigitalState.HIGH)) {
            console.println("Turn off: " + led);
            led.low();
        }
    }

    private void turnOn(DigitalOutput led) {
        if (led.equals(DigitalState.LOW)) {
            console.println("Turn on: " + led);
            led.high();
        }
    }

    public void run() {
        if (isRunning)
            return;

        isRunning = true;

        var ledConfig1 = DigitalOutput.newConfigBuilder(pi4j)
                .id(name + "led1")
                .name(name + " LED 1")
                .address(id1)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .provider("raspberrypi-digital-output");
        led1 = pi4j.create(ledConfig1);

        var ledConfig2 = DigitalOutput.newConfigBuilder(pi4j)
                .id(name + "led2")
                .name(name + " LED 2")
                .address(id2)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .provider("raspberrypi-digital-output");
        led2 = pi4j.create(ledConfig2);

        var ledConfig3 = DigitalOutput.newConfigBuilder(pi4j)
                .id(name + "led3")
                .name(name + " LED 3")
                .address(id3)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .provider("raspberrypi-digital-output");
        led3 = pi4j.create(ledConfig3);

        var buttonConfig = DigitalInput.newConfigBuilder(pi4j)
                .id(name + "button")
                .name(name)
                .address(buttonId)
                .pull(PullResistance.PULL_DOWN)
                .debounce(3000L)
                .provider("raspberrypi-digital-input");
        var button = pi4j.create(buttonConfig);
        button.addListener(e -> {
            if (e.state() == DigitalState.HIGH) {
                if (!didPress) {
                    state++;
                    didPress = true;
                    holdLength = 0;
                    console.println(name + " pressed, moving to state: " + state);
                }
            }
        });
        button.addListener(e -> {
            if (e.state() == DigitalState.LOW) {
                if (didPress) {
                    didPress = false;
                    console.println(name + " released");
                }
            }
        });

        turnOn(led1);
        turnOn(led2);
        turnOn(led3);
    }

    public void checkState() {

        // Increment the holdLength if the button is held
        if (didPress) {
            holdLength++;
        }

        // Check if we have been holding longer than the threshold
        if (holdLength >= HOLD_THRESHOLD) {
            // Reset the hold variables and set state to 0
            holdLength = 0;
            didPress = false;
            state = 0;
            console.println(name + " reset by holding");
        }

        // Turn on/off leds based on state
        switch (state) {
            case 3:
                turnOn(led3);
            case 2:
                turnOn(led2);
            case 1:
                turnOn(led1);
                break;
            case 0:
                turnOff(led1);
                turnOff(led2);
                turnOff(led3);
                break;
            default:
                state = 3;
        }
    }

    public String getName() {
        return name;
    }

    public Button(String name, int buttonId, int id1, int id2, int id3, Console console, Context pi4j) {
        this.name = name;
        this.buttonId = buttonId;
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
        this.console = console;
        this.pi4j = pi4j;
    }
}
