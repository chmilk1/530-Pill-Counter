import RPi.GPIO as GPIO


def turn_off(led):
    if GPIO.input(led):
        print("Turn off: " + str(led))
        GPIO.output(led, False)


def turn_on(led):
    if not GPIO.input(led):
        print("Turn on: " + str(led))
        GPIO.output(led, True)


class Button:

    def __init__(self, name, button_id, led1, led2, led3):
        self.name = name
        self.buttonId = button_id
        self.led1 = led1
        self.led2 = led2
        self.led3 = led3
        self.isRunning = False
        self.didPress = False
        self.holdLength = 0
        self.state = 0
        self.HOLD_THRESHOLD = 20

    def setup(self):
        if self.isRunning:
            return

        self.isRunning = True

        GPIO.setup(self.led1, GPIO.OUT)
        GPIO.setup(self.led2, GPIO.OUT)
        GPIO.setup(self.led3, GPIO.OUT)

        GPIO.setup(self.buttonId, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)

        turn_off(self.led1)
        turn_off(self.led2)
        turn_off(self.led3)

    def check_state(self):

        if GPIO.input(self.buttonId):
            if not self.didPress:
                self.state += 1
                self.didPress = True
                self.holdLength = 0
                print(self.name + " pressed, moving to state: " + str(self.state))
        elif self.didPress:
            self.didPress = False
            self.holdLength = 0
            print(self.name + " released")

        #  Increment the holdLength if the button is held
        if self.didPress:
            self.holdLength += 1

        #  Check if we have been holding longer than the threshold
        if self.holdLength >= self.HOLD_THRESHOLD:
            #  Reset the hold variables and set state to 0
            self.holdLength = 0
            self.state = 0
            print(self.name + " reset by holding")

        #  Turn on/off leds based on state
        if self.state >= 3:
            turn_on(self.led3)
            self.state = 3

        if self.state >= 2:
            turn_on(self.led2)

        if self.state >= 1:
            turn_on(self.led1)

        if self.state == 0:
            turn_off(self.led1)
            turn_off(self.led2)
            turn_off(self.led3)

    def get_name(self):
        return self.name
