<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>
      Jags Rolls - Admin
    </title>
    <link rel="stylesheet" type="text/css" href="../style.css" />
  </head>
  <body>
    <div id="main-title-area" class="main-section">
      <img src="../images/jagsrolls.png" />
    </div>
    <div id="games-list" class="main-section indent">
    	<s:if test="games.isEmpty">
    	<p>No currently available games</p>
    	</s:if>
    	<s:else>
    	<table id="admin-table">
    		<tr><th>Name</th><th>Seconds (Minutes)</th></tr>
    		<s:iterator value="games">
    		<s:set name="name" />
    		<s:set name="age" value="getGameHolder(#name).game.age / 1000" />
    		<tr>
    			<th>
    				<s:url action="Logon" namespace="/logon" id="logon_url"
    											escapeAmp="false" includeParams="get">
    					<s:param name="password" value="'anything'"/>
    					<s:param name="name" value="#name"/>
    				</s:url>
    				<a href="<s:property value="logon_url"/>">
    					<s:property value="#name" />
    				</a>
    			</th>
    			<td>
    				<s:property value="#age" /> (<s:property value="#age / 60" />)</td>
    			</td>
    			<td><a href="<s:url action="DeleteGame"><s:param name="name"><s:property value="#name" /></s:param></s:url>">
    				Delete
    			</a></td>
    		</tr>
    		</s:iterator>
    	</table>
    	</s:else>
	</div>
  </body>
</html>