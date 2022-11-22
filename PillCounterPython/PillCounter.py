import RPi.GPIO as GPIO
from Button import Button
import time

GPIO.setmode(GPIO.BOARD)

blueButton = Button("BlueButton", 40, 8, 19, 10)
# greenButton = Button("GreenButton", 6, 24, 17, 27)
# yellowButton = Button("YellowButton", 23, 15, 14, 4)
# redButton = Button("RedButton", 16, 8, 25, 11)

blueButton.setup()
# greenButton.run()
# yellowButton.run()
# redButton.run()

while (True):
    blueButton.check_state()
    # greenButton.check_state()
    # yellowButton.check_state()
    # redButton.check_state()
    time.sleep(.125)
