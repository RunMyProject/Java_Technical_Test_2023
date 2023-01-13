####Shell:
#####mvn clean dependency:copy-dependencies package
#####mvn compile exec:java -Dexec.mainClass="mypack.Main"

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
