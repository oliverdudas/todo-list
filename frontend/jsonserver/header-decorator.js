module.exports = (req, res, next) => {
  res.header('Authorization', 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJjcmVhdGVkIjoxNDk3MTA2MDgxMDI5LCJleHAiOjE0OTc5NzAwODEsInVzZXJuYW1lIjoidXNlciJ9.Lp8saOzC0QIoFcWPBSKVQ0KyJUIL4pwalmaZLJkprIVwB6k9OQyiM9ebpH47GxHn6maanjGXtehMe-Mqr2drtg');
  res.header('Cache-Control', 'no-cache, no-store, maxage=0, must-revalidate');
  res.header('Expires', '0');
  next()
};
