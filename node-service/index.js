var app = require('express')();
var bodyParser = require('body-parser');
app.use(bodyParser.json());

app.get('/new-service', function(request, response) {
  response.send({
    message: 'New service is being called.'
  });
});

app.use(function (req, res, next) {
  res.status(200).send({
    message: 'Zuul is pretty cool.'
  })
});

app.listen(8000, function() {
  console.log('Listening on port 8000');
});
