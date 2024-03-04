package com.orderservice.ctrl;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTDecoder {
    
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) {
        String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        
        String[] parts = jwtToken.split("\\.");
        
        if (parts.length == 3) {
            String header = decodeBase64(parts[0]);
            String payload = decodeBase64(parts[1]);
            String signature = parts[2];
            Header headerObj= decodePayload( header,Header.class);
            Payload payloadObj=decodePayload( payload,Payload.class   );
            System.out.println(headerObj+"\n"+payloadObj);
            System.out.println("Signature: " + signature);
        } else {
            System.out.println("Invalid JWT token format.");
        }
        
        
        
    }
    
    private static String decodeBase64(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }
   

    public static <T> T decodePayload(String payloadJson, Class<T> valueType) {
         
        try {
            return objectMapper.readValue(payloadJson, valueType);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to decode payload JSON.", e);
        }
    }
    static class Header {
        private String alg;
        private String typ;

        // Getters and setters
        public String getAlg() {
            return alg;
        }

        public void setAlg(String alg) {
            this.alg = alg;
        }

        public String getTyp() {
            return typ;
        }

        public void setTyp(String typ) {
            this.typ = typ;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "alg='" + alg + '\'' +
                    ", typ='" + typ + '\'' +
                    '}';
        }
    }
    static class Payload {
        private String sub;
        private String name;
        private long iat;

        // Getters and setters
        public String getSub() {
            return sub;
        }

        public void setSub(String sub) {
            this.sub = sub;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getIat() {
            return iat;
        }

        public void setIat(long iat) {
            this.iat = iat;
        }

        @Override
        public String toString() {
            return "Payload{" +
                    "sub='" + sub + '\'' +
                    ", name='" + name + '\'' +
                    ", iat=" + iat +
                    '}';
        }
    }
}
