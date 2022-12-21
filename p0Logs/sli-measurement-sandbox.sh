
httpCodes=$(grep Response: planetLogs.log | cut -b 12-14)

LatencyTimes=$(grep Response: planetLogs.log | cut -d ' ' -f 6)

# then create any necessary variables to measure your SLI
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

# save the SLI value and return it

httpSuccess=$(($httpRequestTotal - $httpFailures))

result=$(echo "scale=2; $httpSuccess / $httpRequestTotal" | bc) # this option might not be available via gitbash on your local computer

result=$(awk "BEGIN {print $httpSuccess / $httpRequestTotal * 100; exit}") # this is an alternative way to get the same result as above if bc does not work

echo "HTTP success rate: $result%"
