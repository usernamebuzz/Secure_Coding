protected void doPost(HttpServletRequest request, HttpServletResponse response) {
 
  // Validate input (omitted)
 
  String username = request.getParameter("username");
  char[] password = request.getParameter("password").toCharArray();
  boolean rememberMe = Boolean.valueOf(request.getParameter("rememberme"));
   
  LoginService loginService = new LoginServiceImpl();
         
  if (rememberMe) {
    if (request.getCookies()[0] != null && 
	    request.getCookies()[0].getValue() != null) {
			
      String[] value = request.getCookies()[0].getValue().split(";");
       
      if (!loginService.isUserValid(value[0], value[1].toCharArray())) {
        // Set error and return
      } else {
        // Forward to welcome page
      }
    } else {
        boolean validated = loginService.isUserValid(username, password);
       
        if (validated) {
          Cookie loginCookie = new Cookie("rememberme", username
                             + ";" + new String(password));
          response.addCookie(loginCookie);
          // ... Forward to welcome page
        } else {
          // Set error and return
        }
     }
   } else {
     // No remember-me functionality selected
     // Proceed with regular authentication;
     // if it fails set error and return
   }
     
  Arrays.fill(password, ' ');
}