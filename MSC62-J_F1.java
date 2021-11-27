public final class Password {
  private void setPassword(byte[] pass) throws Exception {
    // Arbitrary encryption scheme
    byte[] encrypted = encrypt(pass);
    clearArray(pass);   
    // Encrypted password to password.bin
    saveBytes(encrypted,"password.bin");
    clearArray(encrypted);
  }
 
  boolean checkPassword(byte[] pass) throws Exception {
    // Load the encrypted password
    byte[] encrypted = loadBytes("password.bin");
    byte[] decrypted = decrypt(encrypted);
    clearArray(encrypted);
    boolean arraysEqual = Arrays.equal(decrypted, pass);
    clearArray(decrypted);
    clearArray(pass);
    return arraysEqual;
  }
 
  private void clearArray(byte[] a) {
    for (int i = 0; i < a.length; i++) {
      a[i] = 0;
    }
  }
 
  private byte[] encrypt(byte[] clearValue) {
    // ... symmetric encryption of clearValue bytes, returning the encrypted value
  }
 
  private byte[] decrypt(byte[] encryptedValue) {
    // ... symmetric decryption of  encryptedValue bytes, returning clear value
  }
 
  private void saveBytes(byte[] bytes, String filename) throws IOException {
    // ... write bytes to the file
  }
   
  private byte[] loadBytes(String filename) throws IOException {
    // ... read bytes to the file
  }
}