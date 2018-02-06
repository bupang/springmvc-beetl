<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${msg}视图解析器</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://v3.bootcss.com/dist/css/bootstrap.css" rel="stylesheet">
    <link href="http://v3.bootcss.com/assets/css/docs.min.css" rel="stylesheet">
    <link rel="shortcut icon" type="image/x-icon" href="/favicon.ico">
</head>
<body>
<div class="container bs-docs-container">
    <div class="row">
        <div class="col-md-9" role="main">
            <div class="bs-docs-section">
                <h1 id="overview" class="page-header">hello,${msg}!</h1>
                <p class="lead">这是${msg}视图解析器，在spring-mvc.xml中配置了两个viewResolver，分别是：beetlViewResolver、JSPViewResolver,解析视图的时候，会根据order参数顺序解析视图。可以看出，在一般的简单用法上，两者的语法是类似的。</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
