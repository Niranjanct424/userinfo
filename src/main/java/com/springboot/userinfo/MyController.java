package com.springboot.userinfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class MyController {

    private final RestTemplate restTemplate;

    public MyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getData/{accountNumber}/{pageNumber}")
    public ResponseEntity<String> getData(@PathVariable String accountNumber, @PathVariable int pageNumber,
                                          @RequestHeader(value = HttpHeaders.ACCEPT) String acceptHeader) {
        // Sanitize input
        accountNumber = sanitizeInput(accountNumber);

        // Build URI using UriComponentsBuilder
        String apiUrl = UriComponentsBuilder.fromUriString("http://localhost:9191/test/").path(accountNumber)
                .build()
                .toUriString();

        // Make a REST call to the backend API using RestTemplate
        String backendResponse = restTemplate.postForObject(apiUrl, null, String.class);

        // Process the response based on the Accept header
        String responseData = processResponse(acceptHeader, backendResponse);

        // Return the response
        return ResponseEntity.ok(responseData);
    }

    private String sanitizeInput(String input) {
        // Simple HTML encoding logic without external library
        input = input.replace("&", "&amp;")
                     .replace("<", "&lt;")
                     .replace(">", "&gt;")
                     .replace("\"", "&quot;")
                     .replace("'", "&#39;");

        return input;
    }

    private String processResponse(String acceptHeader, String backendResponse) {
        if (acceptHeader.contains(MediaType.APPLICATION_JSON_VALUE)) {
            // Handle JSON response (Mock implementation)
            return "{ \"data\": \"" + backendResponse + "\" }";
        } else if (acceptHeader.contains("text/csv")) {
            // Handle CSV response (Mock implementation)
            return "CSV data: " + backendResponse;
        } else {
            // Handle other formats or return an error for unsupported formats
            return "Unsupported format requested";
        }
    }
    
    @PostMapping("/test/{accountNumber}")
    public String simpleAPI(@PathVariable String accountNumber) {
    	return accountNumber;
    }
    
    @GetMapping("/greet")
    public String simplseAPI() {
    	return "Hi";
    }
    
    @PostMapping("/googleapis")
    public Object simpleAPI() {
    	System.out.println("Hi");
    	Object postForObject = null;
    	RestTemplate restTemplate = new RestTemplate();
    	try {
    		String url = "https://www.googleapis.com/geolocation/v1/geolocate?key=12345";
        	 postForObject = restTemplate.postForObject(url, null, Object.class);
		} catch (Exception e) {
			System.out.println("error : "+e);
		}
		return postForObject;
    }
}
