package flex

class UrlMappings {
  static mappings = {
    "/$controller/$action?/$id?(.$format)?"{
      constraints {
        // apply constraints here
      }
    }

    "/robots.txt" (view: "/robots")
    "/sitemap.xml" (view: "/sitemap")
    "/"(view:"/index")
    "500"(view:'/error')
    "404"(view:'/notFound')
  }
}
