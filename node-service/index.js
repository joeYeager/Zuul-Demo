var app = require('express')();

app.get('/', function(request, response) {
    response.send({
      message: 'Zuul is pretty cool.'
    });
});

app.get('/new-service', function(request, response) {
  response.send({
    message: 'New service is being called.'
  });
});

app.listen(8000, function() {});
