<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>
      Jags Rolls - Admin Logon
    </title>
    <link rel="stylesheet" type="text/css" href="../style.css" />
  </head>
  <body>
    <div id="main-title-area" class="main-section">
      <img src="../images/jagsrolls.png" />
    </div>
    <div id="input-section" class="main-section">
    <s:form action="AdminLogon" namespace="/logon">
    	<s:password name="password" label="Password" />
    	<s:submit align="center" />
    </s:form>
    <s:actionerror />
	</div>    
  </body>
</html>