# Load Test using JMeter on Wordpress

## Prerequisite
- Docker - installed
- Apache Jmeter 5.4.3(added to system path)

## Steps:
1. Use the docker-compose command as given below to spin up a wordpress server locally for load tests

   `docker compose up -d`

2. Use the Jmeter binary to run the performance tests:

    `jmeter -n -t ./wordpress-local-perf-test.jmx -l wp-perf-test-raw.jtl
   `
The load test results will be stored in the .jtl file in raw format

3. Use the jmeter command to convert the .jtl file to readable HTML report
    `jmeter -g ./test-results-raw-wp.jtl -o ./perf-test-report`

4. Open the html file inside the report directory to view the html report for performance test