# Hello Jev 👋

**Jev from plain Java 25. No framework, no dependencies, one HTTP call.**

A simple starting point for calling [Jev by TypeSafe](https://docs.typesafe.ai/introduction) from Java. Send a support message, ask three questions, and print the results. All the code is in [HelloJev.java](HelloJev.java).

## Run it

1. Install **JDK 25 or newer**. Check with `java --version`.
2. Get an API key from the [TypeSafe console](https://console.typesafe.ai).
3. Download this repo, open a terminal in its folder, and run:

**macOS / Linux**

```sh
TYPESAFE_API_KEY=your-key java HelloJev.java
```

**Windows PowerShell**

```powershell
$env:TYPESAFE_API_KEY = "your-key"
java HelloJev.java
```

Replace `your-key` with your API key. Java compiles and runs the file for you. No build tool or preview flags needed.

You'll see the HTTP status followed by the JSON response. `HTTP 200` means the request succeeded; otherwise, check the response for the error details.

## What it does

The example evaluates a customer message about a failing Stripe integration. It asks three questions in the same request:

| Question | Type | Result |
| --- | --- | --- |
| Is it urgent? | `noul` | A probability between 0 and 1 |
| Which team should handle it? | `choice` | Billing, integrations, or shipping |
| How severe is it? | `score` | A score across three levels, from cosmetic to blocking |

Answers appear under `answers.is_urgent`, `answers.department`, and `answers.severity`. Scores can fall between levels; this example uses levels 0, 1, and 2. See TypeSafe's [question types](https://docs.typesafe.ai/primitives) for the response fields.

## Make it yours

Open `HelloJev.java` and edit the JSON text block:

- Change `state` to the text you want to evaluate.
- Change `instructions` to ask your own questions.
- Change `criteria` to define your categories or scoring levels.

Run the same command again. Keep your API key in the environment, out of the source file.

Learn more in the [TypeSafe quick start](https://docs.typesafe.ai/introduction/quickstart) and [API reference](https://docs.typesafe.ai/api).

An unofficial community example. [MIT licensed](LICENSE).
