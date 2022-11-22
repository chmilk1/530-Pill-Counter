from datetime import date
from datetime import datetime

output_file = 'output.log'


# saves a pill counters value to file
def save_to_file(light,val):
    file = open(output_file, "a")  # open file in append

    out = str(datetime.now().strftime("%H:%M:%S")) + ' ' + str(date.today()) + ' :: ' + str(light) + ' ' + str(val) + '\n'

    file.write(str(out))  # write out to file
    file.close()  # close file


# appends a star message to the output file
def announce_start():
    file = open(output_file, "a")  # open file in append

    out = 'Program Started ' + str(datetime.now().strftime("%H:%M:%S")) + ' ' + str(date.today()) + '\n'

    file.write(str(out))  # write out to file
    file.close()  # close file

