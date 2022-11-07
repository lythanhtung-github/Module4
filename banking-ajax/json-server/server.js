const jsonServer = require('json-server');
const server = jsonServer.create();
// const router = jsonServer.router('db.json');
const router = jsonServer.router('banking_data.json');
const middlewares = jsonServer.defaults();

server.use(middlewares);
server.use(router);
server.listen(1502, () => {
  console.log('JSON Server is running');
});
