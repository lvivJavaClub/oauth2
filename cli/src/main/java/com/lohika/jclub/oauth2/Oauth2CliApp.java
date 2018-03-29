package com.lohika.jclub.oauth2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;

public class Oauth2CliApp {

    private static final List<String> SCOPES = Arrays.asList(
            "https://www.googleapis.com/auth/userinfo.email",
            "https://www.googleapis.com/auth/userinfo.profile");

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        NetHttpTransport netHttpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jacksonFactory,
                new InputStreamReader(Oauth2CliApp.class.getResourceAsStream("/client_secrets.json")));
        DataStoreFactory dataStoreFactory = new MemoryDataStoreFactory();

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(netHttpTransport, jacksonFactory, clientSecrets.getDetails().getClientId(), clientSecrets.getDetails().getClientSecret(), SCOPES)
                .setDataStoreFactory(dataStoreFactory)
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

        Oauth2 oauth2= new Oauth2.Builder(netHttpTransport, jacksonFactory, credential).setApplicationName("").build();
        Tokeninfo execute = oauth2.tokeninfo().execute();
        System.out.println(execute.toPrettyString());
        System.out.println(oauth2.userinfo().get().execute().toPrettyString());
    }
}
