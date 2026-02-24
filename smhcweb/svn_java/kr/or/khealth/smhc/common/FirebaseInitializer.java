package kr.or.khealth.smhc.common;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class FirebaseInitializer {

    public static void initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("/WAS/jeus/attchfile/serviceKey/smhcapp-firebase-adminsdk.json");
        	//FileInputStream serviceAccount = new FileInputStream("D:\\workspace\\smhcweb\\google-services.json");
        	
        	GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount)
        			.createScoped(Arrays.asList("https://www.googleapis.com/auth/firebase.messaging"));
        	
            FirebaseOptions options = FirebaseOptions.builder()
            		.setProjectId("smhcapp")
                    .setCredentials(credentials)
                    .build();
            
            if(FirebaseApp.getApps().isEmpty()) {
            	FirebaseApp.initializeApp(options);
            	System.out.println(">>> Firebase Initialized!");
            } else {
            	System.out.println(">>> Firebase already Initialized!");
            }
            
            System.out.println("Firebase Project ID: " + FirebaseApp.getInstance().getOptions().getProjectId());
            
            System.out.println("#####FirebaseInitializer Success!!");
            System.out.println(options);
            System.out.println("###############################");
            
        } catch (IOException e) {
            e.printStackTrace();
            // 로깅 또는 예외 처리
        }
    }
}