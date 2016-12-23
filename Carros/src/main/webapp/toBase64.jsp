<pre>
<form enctype="multipart/form-data" action="<%=request.getContextPath()%>/rest/carros/toBase64" method="POST">
	<input name="file" type="file"/>
	<br/><br/>
	<input type="submit" value="Enviar arquivo"/>
</form>
</pre>