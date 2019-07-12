# Plan Generator

## How to Run
1 - Open project in your IDE<br/>
2 - Open terminal on project root path, `mvn clean package`<br/>
3 - All test cases should be passed<br/>
4 - Build should be successful<br/>
5 - Setup SpringBoot Server and set path of main file<br/>
6 - Run the server<br/>

## Request Curl
`
curl -X POST \
  http://localhost:8080/v1/api/plans \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 9f215bfa-d218-bb11-ca0a-7db603b3e1ec' \
  -d '{
	"duration":24,
	"loanAmount": "5000",
	"nominalRate": "5.0",
	"startDate": "2020-01-01T00:00:01Z"
}' 
`

## TODO (Missed)

1 - Swagger Integration<br/>
2 - Class Diagram<br/>
3 - Integration Tests<br/>
4 - Profiling<br/>
5 - Code optimization<br/>
