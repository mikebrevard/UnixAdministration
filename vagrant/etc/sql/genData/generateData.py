
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
TABLESIZEMIN=10
TABLESIZEMAX=20

#randomized sentence min and max for lorem sentences
SENTCOUNTMIN=1
SENTCOUNTMAX=3

#locks
lockDisplay = threading.Lock()

####### SQL Create resources #######
mysqlf = open("create.sql", "w")
schemaList=[]

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
colTypes = { "genName" : "",
          "genAddress" : "",
          "genNumber" : "",
          "genEmail" : "",
          "genTitle" : "",
          "genLorem" : "" }

# generates the string for one row.
def generateRow(colType, num):
    rowStr=str(num)
    for num in range(len(colType)):
        rowStr += ', ' + colOpt[colType[num]]();
    return rowStr

def generateData( fileName, tid ):
    # These bounds are defined above
    numCols = randint(NUMCOLSMIN, NUMCOLSMAX)
    tableSize = randint(TABLESIZEMIN, TABLESIZEMAX)
    tableSize = tableSize * 100
    f = open(fileName, 'w')
    with lockDisplay:
        print ( str(tid) + ': ###############################################\n'+str(tid) + ': File name: ', fileName+'\n'+str(tid) + ': Generating ', numCols, ' rows of data.'+'\n'+str(tid) + ': tableSize  ', tableSize, '.'+'\n'+str(tid) + ': ###############################################')
        print

    # Creating array of enums for columnTypes
    columnTypes=[0]*numCols;
    schemastr = ''
    for entryIndex in range(len(columnTypes)):
        columnTypes[entryIndex]=randint(0,COLOPTMAX)
        #print (str(tid) + ': ' + colOpt[columnTypes[entryIndex]].__name__)
        schemastr = schemastr + colOpt[columnTypes[entryIndex]].__name__ + '\t'
    schemaList[tid] = schemastr

    #generate $tableSize rows of data
    for num in range(0,tableSize):
        if num % (tableSize/10) == 0:
            print (str(tid) + ": " + str((num/(tableSize/10)) * 10) + '%')
        rowData=""
        # generate the row data
        for i in range(0, numCols):
            rowData=generateRow(columnTypes, num)
        rowData = rowData + '\n'
        f.write(rowData)

    print(str(tid) + ': 100% == [DONE ' + str(tid) + ']')
    f.close()

def addSqlCreate( schemaStr ):
    mysqlf.write("test\n");
    #for entry in schemaStr.split("\t"):
    #sqlf.write('CREATE TABLE ' + tableName + '\n(')
    #sqlf.write('\n);');
    #sqlf.close();
    return '';


def main(argv):
    numFiles = input('Enter number of files to generate: ')
    tableName = input('Enter the name of the table: ')
    threads=[]
    print ('Generating ', numFiles, ' files!')
    print ('Hold on to your pants.')

    ####################################################### GENERATE DATA
    numFiles=int(numFiles)
    for num in range(0,numFiles):
        file="data"+str(num)+".csv"
        t=threading.Thread(target=generateData, args=(file, num))
        threads.append(t)
        schemaList.append('')
        t.start()
        #generateData(file)
    else:
        print
        print ('Generated ', numFiles, ' files.')
    for num in range(len(threads)):
        threads[num].join()
        print( '+ ' + str(num) +' is Finished')
    ############################################################ GENERATE

    for num in range(len(schemaList)):
        #print('Schema: ' + schemaList[num])
        #print(convertToSQL(schemaList[num]))
        addSqlCreate(schemaList[num])
    mysqlf.close();

if __name__ == "__main__":
    main(sys.argv[1:])
