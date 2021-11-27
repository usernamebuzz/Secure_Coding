import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public final class Password {
  private SecureRandom random = new SecureRandom();
 
  private void setPassword(String pass) throws Exception {
    byte[] salt = new byte[12];
    random.nextBytes(salt);
    MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
    // Encode the string and salt
    byte[] hashVal = msgDigest.digest((pass+salt).getBytes());
    saveBytes(salt, "salt.bin");
    // Save the hash value to password.bin
    saveBytes(hashVal,"password.bin");
  }
 
  boolean checkPassword(String pass) throws Exception {
    byte[] salt = loadBytes("salt.bin");
    MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
    // Encode the string and salt
    byte[] hashVal1 = msgDigest.digest((pass+salt).getBytes());
    // Load the hash value stored in password.bin
    byte[] hashVal2 = loadBytes("password.bin");
    return Arrays.equals(hashVal1, hashVal2);
  }
 
  private void saveBytes(byte[] bytes, String filename) throws IOException {
    // ... write bytes to the file
  }  
 
  private byte[] loadBytes(String filename) throws IOException {
    // ... read bytes to the file
  }
}