/*
RESTFUL Services by Node Js
Author : Arsenius Purbandono
MY SQL SEARCH
*/

var express = require('express');
var mysql = require('mysql');
var bodyParser = require('body-parser');

//Connect to MySQL
var con = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '', // Default of XAMPP
  database: 'edmtsearch',
});

//Create RESTFul

var app = express();
var publicDir = __dirname + '/public/';
app.use(express.static(publicDir));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

//GET ALL PERSON from DATABASE
app.get('/person', (req, res, next) => {
  con.query('SELECT * FROM person', function (error, result, fields) {
    con.on('error', function (err) {
      console.log('[MSQL] ERROR', err);
    });
    if (result && result.length) {
      res.end(JSON.stringify(result));
    } else {
      res.end(JSON.stringify('No person here'));
    }
  });
});

app.post('/search', (req, res, next) => {
  console.log(req.body);
  /*var post_data = req.body; //GET POST BODY
  var name_search = post_data.search;*/

  var query = "SELECT * FROM person WHERE name LIKE '%" + name_search + "%'";

  /*con.query(query, function (error, result, fields) {
    con.on('error', function (err) {
      console.log('[MSQL] ERROR', err);
    });
    if (result && result.length) {
      res.end(JSON.stringify(result));
    } else {
      res.end(JSON.stringify('No person here'));
    }
  });*/
});

//Start Server
app.listen(3000, () => {
  console.log('Search API was running on port 3000');
});
