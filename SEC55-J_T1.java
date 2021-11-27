public class UploadAction extends ActionSupport {
  private File uploadedFile;
  // setter and getter for uploadedFile
   
  public String execute() {
    try {
      // File path and file name are hardcoded for illustration
      File fileToCreate = new File("filepath", "filename");
 
      boolean textPlain = checkMetaData(uploadedFile, "text/plain");
      boolean img = checkMetaData(uploadedFile, "image/JPEG");
      boolean textHtml = checkMetaData(uploadedFile, "text/html");
 
      if (!textPlain && !img && !textHtml) {
        return "ERROR";
      }    
 
      // Copy temporary file content to this file
      FileUtils.copyFile(uploadedFile, fileToCreate);
      return "SUCCESS";
    } catch (Throwable e) {
      addActionError(e.getMessage());
      return "ERROR";
    }
  }
 
  public static boolean checkMetaData(
    File f, String getContentType) {
    try (InputStream is = new FileInputStream(f)) {
      ContentHandler contenthandler = new BodyContentHandler();
      Metadata metadata = new Metadata();
      metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());
      Parser parser = new AutoDetectParser();
      try {
        parser.parse(is, contenthandler, metadata, new ParseContext());
      } catch (SAXException | TikaException e) {
        // Handle error
        return false;
      }
       
      if (metadata.get(Metadata.CONTENT_TYPE).equalsIgnoreCase(getContentType)) {
        return true;
      } else {
        return false;
      }
    } catch (IOException e) {
      // Handle error
      return false;
    }
  }
}