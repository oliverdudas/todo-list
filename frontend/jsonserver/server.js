const jsonServer = require('json-server')
const server = jsonServer.create()
const router = jsonServer.router('jsonserver/db.json')
const middlewares = jsonServer.defaults()

// Set default middlewares (logger, static, cors and no-cache)
server.use(middlewares)

// Add custom routes before JSON Server router
// server.get('/echo', (req, res) => {
//   res.jsonp(req.query)
// })

// To handle POST, PUT and PATCH you need to use a body-parser
// You can use the one used by JSON Server
server.use(jsonServer.bodyParser)
server.use((req, res, next) => {
  console.log('Request method: ' + req.method)
  if (req.method === 'OPTIONS') {
    res.header('Access-Control-Expose-Headers', 'Authorization');
  }
  // Continue to JSON Server router
  next()
})

// Add this before server.use(router)
server.use(jsonServer.rewriter({
  "/user/": "/"
}))

// Use default router
server.use(router)
server.listen(8080, () => {
  console.log('JSON Server is running')
})
