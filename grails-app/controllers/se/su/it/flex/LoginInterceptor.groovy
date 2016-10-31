package se.su.it.flex


class LoginInterceptor {
  int order = 30
  
  LoginInterceptor() {
    matchAll().excludes(controller: 'public')
  }
  
  boolean before() { 
    true 
  }

  boolean after() {
    true
  }

  void afterView() {
        // no-op
  }
}
