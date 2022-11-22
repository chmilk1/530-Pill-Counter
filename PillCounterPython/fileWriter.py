from datetime import date
from datetime import datetime

output_file = 'output.log'

# saves the current pill counts to a file, along with the date and time
def save_to_file(lights1, lights2, lights3, lights4):
    file = open(output_file, "a")
    out = str(datetime.now().strftime("%H:%M:%S")) + ' ' + str(date.today()) + ' || ' + str(lights1) + ' ' + str(lights2) + ' ' + str(lights3) + ' ' + str(lights4) + '\n'

    file.write(str(out))
    file.close()

# appends a star message to the output file
def announce_start():
    file = open(output_file, "a")
    out = 'Program Started ' + str(datetime.now().strftime("%H:%M:%S")) + ' ' + str(date.today()) + '\n'

    file.write(str(out))
    file.close()

# appends a stop message to the output file
def announce_stop():
    file = open(output_file, "a")
    out = 'Program Stopped ' + str(datetime.now().strftime("%H:%M:%S")) + ' ' + str(date.today()) + '\n'

    file.write(str(out))
    file.close()
