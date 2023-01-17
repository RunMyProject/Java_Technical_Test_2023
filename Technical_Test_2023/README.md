#Shell:

-[x] mvn clean dependency:copy-dependencies package

-[x] mvn compile exec:java -Dexec.mainClass="mypack.Main"

#EXERCISE 1
As software developer, you have to implement a method in order to produce the following daily report:
/reports/ipaddr.csv: it must be a text/csv file containing the traffic data per IP Address.

Each rows must have the following fields:

1. IP Address
2. Number of requests
3. Percentage of the total amount of requests
4. Total Bytes sent
5. Percentage of the total amount of bytes

The data set must be sorted by the number of requests (DESC). The source data for your report is stored in the file
/logfiles/requests.log where each row (record) contains the following semicolon-separated values:

1. TIMESTAMP: the moment when the event occurred.
2. BYTES: the number of bytes sent to a client.
3. STATUS: HTTP response status.
4. REMOTE_ADDR: IP address of the client.

Exclude from your report all the lines in the source file where the STATUS is different from “OK” ( RFC 2616).

-[x] Note:
1. Write the requested code in Java;
2. Don’t use any frameworks but only the Java standard edition or at most, use light libraries;
3. It's not required, but allowing to choose between two possible output modes on daily report file (for example: csv, json) will be taken into account;
4. It's not required, but adding unit tests will be taken into account.

#EXERCISE 2
You are requested to implement a method to multiply integer numbers using the addition operator (+) and not the
multiplication operator (x), storing the values in arrays. So, for example, to calculate “15 × 2 ” you will store these values in
two arrays to perform the operation:

1. 54321
	1.	 15
2. 54321
	1.	 2

The result will end in a third array as in :

3. 54321
	1.	 30
	

Considering moreover that the addition operation is only possible between single digits [0,9] and the new multiplication
function must be usable to calculate the factorial value of 100 (100 !, the product of the first 100 integers).

Note:

-[x] Write the requested code in a programming language of your choice

-[x] It's not required, but adding unit tests will be taken into account.
