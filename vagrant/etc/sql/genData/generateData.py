
import sys, getopt, random, threading
from random import randint

import forgery_py

######## CONFIGURATION GLOBAL VARIABLES #########
# total number of columns rand
NUMCOLSMIN=5
NUMCOLSMAX=24

# total number of rows rand ( size of the table )
# Will be multilied by 100 later on
# normally set to 500-5 000
# for debug set to 1 - 10
TABLESIZEMIN=1
TABLESIZEMAX=2

#randomized sentence min and max for lorem sentences
SENTCOUNTMIN=1
SENTCOUNTMAX=3

####### Data Generation methods #######
# this colOpt enum is used to generate the right string based on
# the number provided to it. To call, use colOpt[func#]()
def genName():
    return forgery_py.name.full_name()
def genAddress():
    return forgery_py.address.street_address()
def genNumber():
    return forgery_py.basic.hex_color()
def genEmail():
    return forgery_py.forgery.internet.email_address(user=None)
def genTitle():
    return forgery_py.lorem_ipsum.title()
def genLorem():
    return forgery_py.forgery.lorem_ipsum.sentences(quantity=randint(SENTCOUNTMIN,SENTCOUNTMAX), as_list=False)
COLOPTMAX=10
colOpt = {  0 : genName,
            1 : genAddress,
            2 : genNumber,
            3 : genNumber,
            4 : genEmail,
            5 : genLorem,
            6 : genTitle,
            7 : genLorem,
            8 : genLorem,
            9 : genLorem,
            10: genLorem }


# generates the string for one row.
def generateRow(colType, num):
    rowStr=str(num)
    for num in range(len(colType)):
        rowStr += ', ' + colOpt[colType[num]]();
    return rowStr

def generateData( fileName ):
    # These bounds are defined above
    numCols = randint(NUMCOLSMIN, NUMCOLSMAX)
    tableSize = randint(TABLESIZEMIN, TABLESIZEMAX)
    tableSize = tableSize * 100
    f = open(fileName, 'w')
    print ('###############################################')
    print ('File name: ', fileName)
    print ('Generating ', numCols, ' rows of data.')
    print ('tableSize  ', tableSize, '.')
    print
    # Creating array of enums for columnTypes
    print ('Schema ordering: ')
    columnTypes=[0]*numCols;
    print ('id column')
    for entryIndex in range(len(columnTypes)):
        columnTypes[entryIndex]=randint(0,COLOPTMAX)
        print (colOpt[columnTypes[entryIndex]].__name__)
    print
    #generate $tableSize rows
    for num in range(0,tableSize):
        if num % (tableSize/10) == 0:
            print ((num/(tableSize/10)) * 10, '%')
        rowData=""
        # generate the row data
        for i in range(0, numCols):
            rowData=generateRow(columnTypes, num)
        rowData = rowData + '\n'
        # print ('[', num, ']: ', rowData)
        f.write(rowData)
    print ('100.0%    --   [ DONE ]')
    f.close()

def main(argv):
    numFiles = input('Enter number of files to generate: ')

    print ('Generating ', numFiles, ' files!')
    print ('Hold on to your pants.')

    numFiles=int(numFiles)
    for num in range(0,numFiles):
        file="data"+str(num)+".csv"
        generateData(file)
    else:
        print
        print ('Generated ', numFiles, ' files.')

if __name__ == "__main__":
    main(sys.argv[1:])
