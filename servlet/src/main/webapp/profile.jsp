<html>
<head>
    <title>Lviv Java Club OAuth2</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <img src="${requestScope.avatar}" with="100" height="100">
        <h1>Hello ${requestScope.name}</h1>
        <h2>Your email is ${requestScope.email}</h2>
    </div>
</div>
</body>
<style>
    html, body {
        height: 100%;
        margin: 0;
    }
    .jumbotron {
        display: flex;
        flex-flow: column;
        height: 100%;
    }
    .container {
        text-align: center;
    }
</style>
</html>
