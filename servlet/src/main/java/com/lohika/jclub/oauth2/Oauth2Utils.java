package com.lohika.jclub.oauth2;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;

public class Oauth2Utils {

    private static final List<String> SCOPES = Arrays.asList(
            "https://www.googleapis.com/auth/userinfo.email",
            "https://www.googleapis.com/auth/userinfo.profile");

    private static final File file = new File(System.getProperty("user.home") + "/.store/");

    static AuthorizationCodeFlow initializeFlow() throws IOException {
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
                new InputStreamReader(LoginServlet.class.getResourceAsStream("/client_secrets.json")));

        DataStoreFactory dataStoreFactory = MemoryDataStoreFactory.getDefaultInstance();
        return new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(), jsonFactory, clientSecrets.getDetails().getClientId(), clientSecrets.getDetails().getClientSecret(), SCOPES)
                .setDataStoreFactory(dataStoreFactory)
                .build();
    }
}
