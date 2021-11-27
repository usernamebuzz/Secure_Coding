@RequestMapping("/getnotifications.htm")
public ModelAndView getNotifications(
  HttpServletRequest request, HttpServletResponse response) {
  ModelAndView mv = new ModelAndView();
  try {
    UserInfo userDetails = getUserInfo();
    List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
    List<Notification> notificationList = 
        NotificationService.getNotificationsForUserId(userDetails.getPersonId());
            
    for (Notification notification: notificationList) {
      Map<String,Object> map = new HashMap<String, Object>();
      map.put("id", notification.getId());
      map.put("message", notification.getMessage());
      list.add(map);
    }
             
     mv.addObject("Notifications", list);
  }
    catch(Throwable t) {
    // Log to file and handle
  }
  
  return mv;
}