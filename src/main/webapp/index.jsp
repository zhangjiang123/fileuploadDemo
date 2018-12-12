<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h2>文件上传</h2>

    <form action="user/fileup" method="post" enctype="multipart/form-data" >
        选择文件：<input type="file" name="load" /><br/>
        <input type="submit" value="上传" /><br/>
    </form>

    <form action="user/fileup2" method="post" enctype="multipart/form-data" >
        选择文件：<input type="file" name="load" /><br/>
        <input type="submit" value="上传" /><br/>
    </form>

    <form action="user/fileup3" method="post" enctype="multipart/form-data" >
        选择文件：<input type="file" name="load" /><br/>
        <input type="submit" value="上传" /><br/>
    </form>

</body>
</html>
