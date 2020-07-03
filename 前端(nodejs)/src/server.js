var express = require("express");
var app = express();
var exStatic=require("express-static");
app.use(exStatic("src/static"));
app.listen(3000);