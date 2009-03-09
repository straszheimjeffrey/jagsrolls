<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>
      Jags Rolls - Welcome
    </title>
    <link rel="stylesheet" type="text/css" href="style.css" />
  </head>
  <body>
    <div id="title-page-header" class="main-section">
      <img src="images/jagsrolls.png" />
    </div>
    <a href="<s:url action="NewGameInput" namespace="/logon" />" class="big-link">
    	Create a New Game<br>
    	<span class="for_whom">for the GM</span>
    </a>
    <a href="<s:url action="LogonInput" namespace="/logon" />" class="big-link">
    	Logon to an Existing Game<br>
    	<span class="for_whom">for the Players</span>
    </a> 
    
    <p class="signature">By Jeffrey Straszheim</p>
    
  </body>
</html>