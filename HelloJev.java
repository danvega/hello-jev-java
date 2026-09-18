// Java 25+. No framework, no dependencies, one HTTP call.
// Run: TYPESAFE_API_KEY=your-key java HelloJev.java

import module java.net.http;

void main() throws Exception {
    var apiKey = System.getenv("TYPESAFE_API_KEY");
    if (apiKey == null || apiKey.isBlank()) {
        System.err.println("Set TYPESAFE_API_KEY to your TypeSafe API key before running.");
        System.exit(1);
    }

    var body = """
            {
              "state": "Hi, I've been trying to connect my Stripe account for 3 days and it keeps failing. I'm losing sales. Please help ASAP.",
              "model": "jev-latest",
              "questions": {
                "is_urgent": {
                  "type": "noul",
                  "instructions": "Does this message express urgency?"
                },
                "department": {
                  "type": "choice",
                  "instructions": "Which team should handle this?",
                  "criteria": {
                    "billing": "Charges, invoices, payment problems",
                    "integrations": "Connecting third-party services such as Stripe",
                    "shipping": "Delivery status, delays, lost packages"
                  }
                },
                "severity": {
                  "type": "score",
                  "instructions": "How severe is the reported issue?",
                  "criteria": [
                    "Cosmetic; no impact to functionality",
                    "Broken or degraded feature, but a workaround exists",
                    "Blocking issue; no workaround exists"
                  ]
                }
              }
            }
            """;

    var request = HttpRequest.newBuilder(URI.create("https://api.typesafe.ai/v1/systemone"))
            .header("Authorization", "Bearer " + apiKey)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();

    try (var client = HttpClient.newHttpClient()) {
        long start = System.nanoTime();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        IO.println("HTTP " + response.statusCode());
        IO.println(response.body());
        IO.println("Took " + Duration.ofNanos(System.nanoTime() - start).toMillis() + " ms");
    }
}
