# CLI OAuth2
OAuth2 implementation using [google-api-java-client](https://github.com/google/google-api-java-client).
  
## How to run
* Go to [console.developers.google.com](https://console.developers.google.com/projectselector/apis/dashboard)
* Create or select an existing application
* Go to "APIs & Services" -> "Credentials"
* Click on "Create credentials" choose "OAuth Client ID" and Application type "Other"
* Now you can see the newly created OAuth 2.0 client ID. Click on the "Download JSON" button
* Copy the downloaded file to `src/main/resources/client_secrets.json`
* Run `Oauth2CliApp#main`