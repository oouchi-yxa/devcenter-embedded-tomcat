<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<body>
<h2>Hello Heroku!</h2>

<a href="/hello">hello サーブレット</a>
<hr />

<a href="/hello2?a=b&a=3&b=あいうえお">パラメタ表示サーブレット</a>
<hr />

<form method="POST" action="/hello3">
<input type="text" name="arg1" value="テストデータ１">
<input type="text" name="arg2" value="テストデータ２">
<input type="text" name="arg3" value="テストデータ３">
<input type="submit">
</form>

<a href="/file/xxx/yyy/zzz/a.gif?a=b&b=c">S3アクセス用のパス指定確認（"/file/xxx/yyy/zzz/a.gif?a=b&b=c"）</a>
<hr />

<a href="/file2/test1/">S3アクセス用のパス指定2</a>
<hr />

<form method="POST" action="/webhook">
<input type="text" name="arg1" value="wテストデータ１">
<input type="text" name="arg2" value="wテストデータ２">
<input type="text" name="arg3" value="wテストデータ３">
<input type="submit">
</form>
<hr />


</body>
</html>
