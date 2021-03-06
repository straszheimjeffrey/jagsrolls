<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>
      Jags Rolls
    </title>
    <link rel="stylesheet" type="text/css" href="style.css" />
    <script src="jquery-1.2.6.pack.js"></script>
    <script src="typewatch1.1.1-DEBUG.js"></script>
    <script src="jags-rolls.js"></script>
  </head>
  <body>
    <div id="main-title-area" class="main-section">
      <img src="images/jagsrollssmall.png" />
    </div>
    <div id="rolls-section" class="main-section">
      <table>

        <tbody>
          <tr>          
            <td class="roll-table">
              <h2>
                Rolls
              </h2>
              <div id="rolls-left"></div>
            </td>
            <td class="roll-table">
              <h2>
                Initiative
              </h2>
              <div id="rolls-right"></div>
            </td>
          </tr>
        </tbody>
      </table>
      <div id="sizer" class="sizer-out-small"></div>

    </div>
    <div id="character-section" class="main-section">
      <ul></ul>
      <p>
        <s:if test="#session.isGm">
        	<a id="gm-roll" href="#">
        		GM Roll
        	</a>
	        <a id="new-round" href="#">
    	      New Round
        	</a>
        </s:if>
        <a id="add-character" href="#">
          Add Character
        </a>
        <a id="all-init" href="#">
          Add All Init
        </a>
      </p>
    </div>
    <div id="impact-damage-section" class="main-section damage-section">
      <h2>
        Impact Damage
      </h2>
      <div id="impact-damage-table" class="damage-table"></div>

      <p>
        <a id="add-impact-damage" href="#">
          Add
        </a>
        <a id="remove-impact-damage" href="#">
          Remove
        </a>
      </p>
    </div>
    <div id="penetration-damage-section" class="main-section damage-section">
      <h2>
        Penetration Damage
      </h2>

      <div id="penetration-damage-table" class="damage-table"></div>
      <p>
        <a id="add-penetration-damage" href="#">
          Add
        </a>
        <a id="remove-penetration-damage" href="#">
          Remove
        </a>
      </p>
    </div>
    
    <div id="opposed-roll-section" class="main-section">
    	<h2>
    		Opposed Rolls
    	</h2>
    	
    	<form>
    	<table>
    	<tr>
    		<th>Rolling</th>
    		<th>Resisting</th>
    		<th>Needed Roll</th>
    	</tr>
    	<tr>
    		<td><input type="text" id="left-score" size="2"></td>
    		<td><input type="text" id="right-score" size="2"></td>
    		<td><span id="result">&nbsp;</span></td>
    	</tr>
    	</table> 
    	</form>
    </div>
    
    <div id="wound-effects" class="main-section">
    	<h2>
    		Wound Effects
    	</h2>
    	
    	<table>
    		<tr>
    			<th>Wound</th>
    			<th>No Effect</th>
    			<th>Stunned</th>
    			<th>Dazed</th>
    			<th>Unconscious</th>
    			<th>Internal Damage</th>
    			<th>Dying</th>
    			<th>Dead</th>
    		</tr>
    		<tr>
    			<th>Minor</th>
    			<td>+3 or more</td>
    			<td>+2 to +0</td>
    			<td>-1 to -3</td>
    			<td>-4 to -5</td>
    			<td>-6 to -10</td>
    			<td>nil</td>
    			<td>nil</td>
    		</tr>
    		<tr>
    			<th>Major</th>
    			<td>+8 or more</td>
    			<td>+7 to +5</td>
    			<td>+4 to +3</td>
    			<td>+2 to -1</td>
    			<td>-2 to -3</td>
    			<td>-4 to -5</td>
    			<td>-6 or worse</td>
    		</tr>
    		<tr>
    			<th>Critial</th>
    			<td>+10 or more</td>
    			<td>+9 to +7</td>
    			<td>+6 to +5</td>
    			<td>+4 to 0</td>
    			<td>-1 to -3</td>
    			<td>-4 to -5</td>
    			<td>-6 or worse</td>
    		</tr>
    	</table>
    </div>
    
    <p class="signature">By Jeffrey Straszheim</p>
    
    <script>
    <!--
    var url = {};
    url.add_roll = '<s:url action="AddRoll" namespace="/game" />';
    url.add_init_roll = '<s:url action="AddInitRoll" namespace="/game" />';
    url.delete_init_roll = '<s:url action="DeleteInitRoll" namespace="/game" />';
    url.add_all_init = '<s:url action="AddAllInit" namespace="/game" />';
    url.add_rea_cost = '<s:url action="AddReaCost" namespace="/game" />';
    url.undo_rea_cost = '<s:url action="UndoReaCost" namespace="/game" />';
    url.clear_round = '<s:url action="ClearRound" namespace="/game" />';
    url.add_condition = '<s:url action="AddCondition" namespace="/game" />';
    url.remove_condition = '<s:url action="RemoveCondition" namespace="/game" />';
    url.get_results = '<s:url action="GetResults" namespace="/game" />';
    var isGm = <s:property value="#session.isGm" />;
    -->
    </script>
    
  </body>
</html>