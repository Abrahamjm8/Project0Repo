cat planetLogs.log

httpCodes=$(grep Response: planetLogs.log | cut -b 12-14)

latencyTimes=$(grep Response: planetLogs.log | cut -d ' ' -f 6)

httpRequestTotal=0
httpFailures=0

# then loop through the log data and perform any necessary operations to initialize your variables
for code in $httpCodes
do
	((httpRequestTotal++))
	if [ $code -eq 500 ]
	then
		((httpFailures++))
	fi
done

httpSuccess=$(($httpRequestTotal - $httpFailures))
result=$(awk "BEGIN {print $httpSuccess / $httpRequestTotal * 100; exit}")

echo "HTTP success rate: $result%"



totalLatency=0
numberOfHttpRequests=0

for code in $latencyTimes
do 	
	((numberOfHttpRequests++))
	totalLatency=$(awk "BEGIN {print $code + $totalLatency; exit}")
done 

avgLatency=$(awk "BEGIN {print $totalLatency / $numberOfHttpRequests; exit}")

echo "Average Latency: $avgLatency m/s"
