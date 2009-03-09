<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>
      Jags Rolls - Create New Game
    </title>
    <script src="../jquery-1.2.6.pack.js"></script>
    <script src="input.js"></script>    
    <link rel="stylesheet" type="text/css" href="../style.css" />
  </head>
  <body>
    <div id="main-title-area" class="main-section">
      <img src="../images/jagsrolls.png" />
    </div>
    <div id="input-section" class="main-section">
    	<table><tr>
    	<td class="content">
    	<s:form id="input-form" action="NewGame" namespace="/logon">
    		<s:textfield id="name" name="name" label="Name" />
    		<s:password id="gm-password" name="gmPassword" label="Password (for GM)" />
    		<s:password id="user-password" name="userPassword" label="Password (for players)" />
    		<s:submit align="center" />
    	</s:form>
    	</div>
    	<s:actionerror />
    	</td>
    	<td class="content right">
    	<div class="name help">
    		Please enter a name for your game.  You will provide this to your players.
    	</div>
    	<div class="gm-password help">
    		Please enter a password you can use to logon to the game.
    	</div>
    	<div class="user-password help">
    		Please enter a password for your players to log on to the game.
    	</div>
    	</td>
    	</tr></table>
	</div>
  </body>
</html>