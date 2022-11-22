import RPi.GPIO as GPIO

import fileWriter
from Button import Button
import time

fileWriter.announce_start()

GPIO.setmode(GPIO.BOARD)

blueButton = Button("Blue", 13, 7, 10, 8)
greenButton = Button("Green", 19, 15, 18, 16)
yellowButton = Button("Yellow", 36, 21, 24, 23)
redButton = Button("Red", 31, 40, 29, 26)

blueButton.setup()
greenButton.setup()
yellowButton.setup()
redButton.setup()

while (True):
    blueButton.check_state()
    greenButton.check_state()
    yellowButton.check_state()
    redButton.check_state()
    time.sleep(.125)
